/*************************************************************************************
 * Copyright (c) 2011 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.switchyard.tools.ui.editor.components.camel.java;

import java.util.EnumSet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.ui.CodeGeneration;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Contract;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.switchyard.tools.ui.common.ContractControl;
import org.switchyard.tools.ui.common.InterfaceControl.InterfaceType;
import org.switchyard.tools.ui.editor.Messages;

/**
 * NewCamelRouteClassWizardPage
 * 
 * Create a new Camel route definition class.
 * 
 * @author Rob Cernich
 */
public class NewCamelRouteClassWizardPage extends NewClassWizardPage {

    private static final String SERVICE_INTERFACE = "SERVICE_INTERFACE"; //$NON-NLS-1$
    private static final String CLASS_NAME_DEFAULT = "CamelServiceRoute"; //$NON-NLS-1$

    private ContractControl _serviceInterfaceControl;
    private IStatus _serviceInterfaceStatus;

    /**
     * Create a new NewCamelRouteClassWizardPage.
     */
    public NewCamelRouteClassWizardPage() {
        super();

        setTitle(Messages.title_newCamelRouteService);
        setDescription(Messages.description_newCamelRouteService);

        _serviceInterfaceControl = new ContractControl(ScaPackage.eINSTANCE.getComponentService(), getJavaProject(),
                EnumSet.of(InterfaceType.Java, InterfaceType.WSDL, InterfaceType.ESB));
        _serviceInterfaceControl.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                handleFieldChanged(SERVICE_INTERFACE);
            }
        });
    }

    /**
     * @param selection the current selection
     */
    public void init(IStructuredSelection selection) {
        super.init(selection);
        setModifiers(Flags.AccPublic, false);
        setMethodStubSelection(false, false, false, false);
        if (getTypeName().length() == 0) {
            setTypeName(CLASS_NAME_DEFAULT, true);
        }
        setSuperClass("org.apache.camel.builder.RouteBuilder", false); //$NON-NLS-1$

        doStatusUpdate();
    }

    /**
     * Forces the bean to implement the specified interface.
     * 
     * @param serviceInterface the service interface type.
     */
    public void forceServiceInterfaceType(Contract serviceInterface) {
        if (serviceInterface == null) {
            return;
        }
        _serviceInterfaceControl.init(serviceInterface, null);
        _serviceInterfaceControl.setEnabled(false);
        if (getTypeName().length() == 0 || getTypeName().equals(CLASS_NAME_DEFAULT)) {
            setTypeName("" + serviceInterface.getName() + "Route", true); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    @Override
    public void createControl(Composite parent) {
        initializeDialogUnits(parent);

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setFont(parent.getFont());

        int nColumns = 4;

        GridLayout layout = new GridLayout();
        layout.numColumns = nColumns;
        composite.setLayout(layout);

        // pick & choose the wanted UI components

        createContainerControls(composite, nColumns);
        createPackageControls(composite, nColumns);

        createSeparator(composite, nColumns);

        createTypeNameControls(composite, nColumns);
        createSeparator(composite, nColumns);
        createServiceInterfaceControls(composite, nColumns);

        createSeparator(composite, nColumns);

        createModifierControls(composite, nColumns);

        createSuperClassControls(composite, nColumns);

        createSeparator(composite, nColumns);

        createCommentControls(composite, nColumns);
        enableCommentControl(true);

        setControl(composite);

        Dialog.applyDialogFont(composite);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            setFocus();
        }
    }

    @Override
    protected void createTypeMembers(IType type, ImportsManager imports, IProgressMonitor monitor) throws CoreException {
        super.createTypeMembers(type, imports, monitor);

        final String methodName = "configure"; //$NON-NLS-1$
        final StringBuffer buf = new StringBuffer();
        final String lineDelim = getJavaProject().getJavaModel().findRecommendedLineSeparator();
        buf.append("/**").append(lineDelim); //$NON-NLS-1$
        buf.append(" * ").append(Messages.comment_camelConfigureMethod).append(lineDelim); //$NON-NLS-1$
        buf.append(" */").append(lineDelim); //$NON-NLS-1$
        buf.append("public void ").append(methodName).append("() {"); //$NON-NLS-1$ //$NON-NLS-2$
        buf.append(lineDelim);

        String serviceName = getService().getName();
        StringBuffer body = new StringBuffer();
        body.append("from(\"switchyard://").append(serviceName).append("\")"); //$NON-NLS-1$ //$NON-NLS-2$
        body.append(lineDelim);
        body.append(".log(\"Received message for '").append(serviceName).append("' : ${body}\");"); //$NON-NLS-1$ //$NON-NLS-2$
        body.append(lineDelim);

        try {
            buf.append(CodeGeneration.getMethodBodyContent(type.getCompilationUnit(), type.getFullyQualifiedName(),
                    methodName, false, body.toString(), lineDelim));
        } catch (CoreException e) {
            buf.append(body);
        }

        buf.append("}"); //$NON-NLS-1$
        type.createMethod(buf.toString(), null, false, null);
    }

    @Override
    protected void handleFieldChanged(String fieldName) {
        super.handleFieldChanged(fieldName);

        if (fieldName == SERVICE_INTERFACE) {
            serviceInterfaceChanged();
        } else if (fieldName == CONTAINER) {
            _serviceInterfaceControl.setProject(getJavaProject());
            serviceInterfaceChanged();
        }
        doStatusUpdate();
    }

    private void doStatusUpdate() {
        // status of all used components
        IStatus[] status = new IStatus[] {fContainerStatus, fPackageStatus, fTypeNameStatus, fModifierStatus,
                fSuperClassStatus, fSuperInterfacesStatus, _serviceInterfaceStatus };

        updateStatus(status);
    }

    /**
     * @return the selected service.
     */
    public ComponentService getService() {
        return (ComponentService) _serviceInterfaceControl.getContract();
    }

    private void createServiceInterfaceControls(Composite composite, int nColumns) {
        _serviceInterfaceControl.createControl(composite, nColumns);
    }

    private void serviceInterfaceChanged() {
        _serviceInterfaceStatus = _serviceInterfaceControl.getStatus();
    }

}
