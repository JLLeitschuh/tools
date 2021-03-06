/*************************************************************************************
 * Copyright (c) 2012 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.switchyard.tools.ui.bpmn2.editor;

import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.runtime.jboss.jbpm5.property.JbpmCustomTaskPropertySection;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * SwitchYardServiceTaskPropertySection
 * 
 * <p/>
 * Custom property section for "SwitchYard Service" tasks.
 * 
 * @author Rob Cernich
 */
public class SwitchYardServiceTaskPropertySection extends JbpmCustomTaskPropertySection {

    /**
     * Create a new SwitchYardServiceTaskPropertySection.
     */
    public SwitchYardServiceTaskPropertySection() {
        super();
    }

    @Override
    public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
        /*
         * only show this property section if the selected ServiceTask is a
         * custom "SwitchYard Service" task. that is, it has an "implementation"
         * of "##SwitchYard".
         */
        BPMN2Editor editor = (BPMN2Editor) part.getAdapter(BPMN2Editor.class);
        if (editor != null) {
            EObject object = BusinessObjectUtil.getBusinessObjectForSelection(selection);
            ModelEnablementDescriptor modelEnablement = editor.getTargetRuntime().getModelEnablements(object);

            return object instanceof ServiceTask
                    && SwitchYardServiceTaskFeatureContainer.IMPLEMENTATION_SWITCHYARD.equals(((ServiceTask) object)
                            .getImplementation()) && modelEnablement.isEnabled(object.eClass());
        }
        return false;
    }

    @Override
    protected AbstractDetailComposite createSectionRoot() {
        return new SwitchYardServiceTaskPropertiesComposite(this);
    }

    @Override
    public boolean doReplaceTab(String id, IWorkbenchPart part, ISelection selection) {
        return appliesTo(part, selection);
    }

}
