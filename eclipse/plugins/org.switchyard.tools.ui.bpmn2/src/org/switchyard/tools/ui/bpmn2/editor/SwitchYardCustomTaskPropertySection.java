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

import java.util.List;

import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.jboss.jbpm5.property.JbpmCustomTaskPropertySection;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

/**
 * SwitchYardServiceTaskPropertySection
 * 
 * <p/>
 * Custom property section for "SwitchYard Service" tasks.
 * 
 * @author Rob Cernich
 */
public class SwitchYardCustomTaskPropertySection extends JbpmCustomTaskPropertySection {

    /**
     * Create a new SwitchYardServiceTaskPropertySection.
     */
    public SwitchYardCustomTaskPropertySection() {
        super();
    }

    @Override
    public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
        // only show this property section if the selected Task is a custom
        // "SwitchYard Service" task. that is, it has a "taskName" extension
        // attribute set to "SwitchYard Service".
        BPMN2Editor editor = (BPMN2Editor) part.getAdapter(BPMN2Editor.class);
        if (editor != null) {
            EObject object = BusinessObjectUtil.getBusinessObjectForSelection(selection);
            ModelEnablementDescriptor modelEnablement = editor.getTargetRuntime().getModelEnablements(object);

            if (object instanceof Task) {
                if (modelEnablement.isEnabled(object.eClass())) {
                    List<EStructuralFeature> features = ModelUtil.getAnyAttributes(object);
                    for (EStructuralFeature f : features) {
                        if ("taskName".equals(f.getName())
                                && ("SwitchYard Service Task".equals(((Task) object).getAnyAttribute().get(f, false)) || "SwitchYard Service"
                                        .equals(((Task) object).getAnyAttribute().get(f, false)))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected AbstractDetailComposite createSectionRoot() {
        return new SwitchYardCustomTaskPropertiesComposite(this);
    }

    @Override
    public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
        return new SwitchYardCustomTaskPropertiesComposite(parent, style);
    }

    @Override
    public boolean doReplaceTab(String id, IWorkbenchPart part, ISelection selection) {
        return appliesTo(part, selection);
    }

}
