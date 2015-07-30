/******************************************************************************* 
 * Copyright (c) 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author bfitzpat
 ******************************************************************************/
package org.switchyard.tools.ui.editor.diagram.shared;

import java.io.InputStream;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaFactory;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;
import org.switchyard.tools.ui.JavaUtil;
import org.switchyard.tools.ui.PlatformResourceAdapterFactory;
import org.switchyard.tools.ui.common.ContractControl;
import org.switchyard.tools.ui.common.InterfaceControl.InterfaceType;
import org.switchyard.tools.ui.editor.Activator;
import org.switchyard.tools.ui.editor.diagram.component.IComponentWizard;

/**
 * BaseNewServiceFileWizard
 * 
 * <p/>
 * Base wizard for new service implementations. Extenders should add additional
 * pages and implement getInitialContents() appropriately.
 * 
 * @author bfitzpat
 * @author Rob Cernich
 */
public abstract class BaseNewServiceFileWizard extends BasicNewFileResourceWizard implements IComponentWizard {

    private final Set<InterfaceType> _supportedInterfaceTypes;
    private WizardNewFileCreationPage _page;
    private Component _component;
    private ComponentService _service;
    private boolean _openFileAfterCreate = false;
    private String _createdFilePath = null;
    private String _fileExtension;
    private IJavaProject _project;
    private IFile _newFile;

    /**
     * Create a new BaseNewServiceFileWizard.
     * 
     * @param openAfterCreate true if the new file should be opened in an
     *            editor.
     */
    protected BaseNewServiceFileWizard(boolean openAfterCreate, String fileExtension) {
        this(openAfterCreate, fileExtension, EnumSet.of(InterfaceType.Java, InterfaceType.WSDL, InterfaceType.ESB));
    }

    /**
     * Create a new BaseNewServiceFileWizard.
     * 
     * @param openAfterCreate true if the new file should be opened in an
     *            editor.
     * @param fileExtension the file extension
     * @param supportedInterfaceTypes the set of supported interface types
     */
    protected BaseNewServiceFileWizard(boolean openAfterCreate, String fileExtension,
            Set<InterfaceType> supportedInterfaceTypes) {
        super();
        _openFileAfterCreate = openAfterCreate;
        _fileExtension = fileExtension;
        _supportedInterfaceTypes = supportedInterfaceTypes;
    }

    @Override
    public Component getCreatedObject() {
        return _component;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard#performFinish
     * ()
     */
    @Override
    public boolean performFinish() {
        if (_page != null) {
            _newFile = _page.createNewFile();
            if (_newFile == null) {
                return false;
            }

            _createdFilePath = JavaUtil.getJavaPathForResource(_newFile).toString();

            selectAndReveal(_newFile);

            if (_openFileAfterCreate) {
                // Open editor on new file.
                IWorkbenchWindow dw = getWorkbench().getActiveWorkbenchWindow();
                try {
                    if (dw != null) {
                        IWorkbenchPage page = dw.getActivePage();
                        if (page != null) {
                            IDE.openEditor(page, _newFile, true);
                        }
                    }
                } catch (PartInitException e) {
                    e.printStackTrace();
                    Activator.logError(e);
                }
            }

            Implementation implementation = createImplementation();
            List<ComponentReference> references = createReferences();

            _component = ScaFactory.eINSTANCE.createComponent();
            _component.setName(getComponentName(_newFile.getFullPath()));
            _component.getService().add(_service);
            _component.setImplementation(implementation);
            if (references != null) {
                _component.getReference().addAll(references);
            }

            return true;
        }
        return false;
    }

    /**
     * @return the file path to the new implementation.
     */
    public String getCreatedFilePath() {
        return _createdFilePath;
    }

    protected String getFileExtension() {
        return _fileExtension;
    }
    
    /**
     * @param inPath the path to the file being created.
     */
    public void setCreatedFilePath(String inPath) {
        _createdFilePath = inPath;
    }

    /**
     * @return the newly created resource
     */
    public IFile getCreatedFile() {
        return _newFile;
    }

    @Override
    public void init(org.eclipse.soa.sca.sca1_1.model.sca.Composite container) {
        if (container == null || !(getSelection() == null || getSelection().isEmpty())) {
            return;
        }
        IProject project = PlatformResourceAdapterFactory.getContainingProject(container);
        if (project == null) {
            return;
        }
        IResource folder = JavaUtil.getFirstResourceRoot(JavaCore.create(project));
        StructuredSelection selection = new StructuredSelection(folder == null ? project : folder);
        init(getWorkbench() == null ? PlatformUI.getWorkbench() : getWorkbench(), selection);
    }

    /**
     * Must be invoked before the dialog is opened.
     * 
     * @param serviceInterface the interface; may be null, indicating any
     *            interface is OK.
     */
    public void forceServiceInterfaceType(ComponentService serviceInterface) {
        _service = serviceInterface;
    }

    /**
     * @return the service specified by the user.
     */
    public ComponentService getService() {
        return _service;
    }
    
    protected void setService(ComponentService service) {
        this._service = service;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard#addPages()
     */
    @Override
    public void addPages() {
        _page = new ServiceImplementationFileCreationPage("newFilePage1", getSelection()); //$NON-NLS-1$
        if (_createdFilePath != null) {
            _page.setFileName(_createdFilePath);
        }
        if (_fileExtension != null) {
            _page.setFileExtension(_fileExtension);
        }
        addPage(_page);
    }

    /**
     * @return the new file page
     */
    protected WizardNewFileCreationPage getFileCreationPage() {
        return _page;
    }
    
    protected void setFileCreationPage(WizardNewFileCreationPage page) {
        _page = page;
    }

    /**
     * @return the contents for the file being created
     */
    protected abstract InputStream getInitialContents();

    /**
     * @return the newly created implementation
     */
    protected abstract Implementation createImplementation();

    /**
     * @return references used by the new implementation.
     */
    protected abstract List<ComponentReference> createReferences();

    /**
     * Returns a name based on the file's name, sans extension.
     * 
     * @param newFile the newly created file
     * 
     * @return an appropriate name for the component.
     */
    protected String getComponentName(IPath newFile) {
        String fileName = newFile.removeFileExtension().lastSegment().toString();
        if (fileName.length() > 0) {
            return fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
        }
        return null;
    }

    protected IJavaProject getJavaProject() {
        if (_page == null) {
            return null;
        }
        IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(_page.getContainerFullPath());
        if (resource == null || resource.getProject() == null) {
            _project = null;
        } else if (_project == null || !_project.getProject().equals(resource.getProject())) {

            _project = JavaCore.create(resource.getProject());
        }
        return _project;
    }
    
    protected Set<InterfaceType> getSupportedInterfaceTypes() {
        return this._supportedInterfaceTypes;
    }

    /**
     * Made internal page public so we can get to the contract control.
     * @author brianf
     */
    public class ServiceImplementationFileCreationPage extends WizardNewFileCreationPage {

        private ContractControl _contractControl;

        /**
         * Constructor.
         * @param pageName name of the page
         * @param selection incoming selection
         */
        public ServiceImplementationFileCreationPage(String pageName, IStructuredSelection selection) {
            super(pageName, selection);
        }

        @Override
        protected InputStream getInitialContents() {
            return BaseNewServiceFileWizard.this.getInitialContents();
        }
        
        /**
         * Need to get the contract control to fix a BPEL implementation wizard issue.
         * @return Contract control
         */
        public ContractControl getContractControl() {
            return _contractControl;
        }

        @Override
        protected void createAdvancedControls(Composite parent) {
            Composite contents = new Composite(parent, SWT.NONE);
            contents.setLayout(new GridLayout(3, false));
            contents.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

            _contractControl = new ContractControl(ScaPackage.eINSTANCE.getComponentService(), getJavaProject(),
                    _supportedInterfaceTypes);
            _contractControl.createControl(contents, 3);
            _contractControl.addSelectionChangedListener(new ISelectionChangedListener() {
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    setPageComplete(validatePage());
                }
            });

            if (_service != null) {
                _contractControl.init(_service, null);
                _contractControl.setEnabled(false);
            }
            // get the new instance
            _service = (ComponentService) _contractControl.getContract();
        }

        @Override
        protected boolean validatePage() {
            if (super.validatePage()) {
                _contractControl.setProject(getJavaProject());
                IStatus status = _contractControl.getStatus();
                if (status.getSeverity() < Status.ERROR) {
                    if (!status.isOK()) {
                        setMessage(status.getMessage(), status.getSeverity() == IStatus.WARNING ? WARNING : INFORMATION);
                    }
                    return true;
                }
                setErrorMessage(status.getMessage());
                return false;
            }
            return false;
        }

        @Override
        protected IStatus validateLinkedResource() {
            return Status.OK_STATUS;
        }

        @Override
        protected void createLinkTarget() {
        }

    }

}
