/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 ******************************************************************************/
package org.switchyard.tools.ui.editor.components.bean;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.tb.IImageDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.switchyard.tools.models.switchyard1_0.bean.BeanImplementationType;
import org.switchyard.tools.ui.common.InterfaceControl.InterfaceType;
import org.switchyard.tools.ui.editor.IComponentTypeExtension;
import org.switchyard.tools.ui.editor.ImageProvider;
import org.switchyard.tools.ui.editor.diagram.component.CreateComponentFeature;
import org.switchyard.tools.ui.editor.diagram.implementation.CreateImplementationFeature;
import org.switchyard.tools.ui.editor.diagram.shared.CompositeCreateFeature;

/**
 * BeanComponentTypeExtension
 * 
 * <p/>
 * Provides support for Bean components in the SwitchYard editor.
 */
public class BeanComponentTypeExtension implements IComponentTypeExtension {

    private static final Set<InterfaceType> TYPES = EnumSet.of(InterfaceType.Java);

    @Override
    public ICreateFeature[] newCreateFeatures(IFeatureProvider fp) {
        return new ICreateFeature[] {new CompositeCreateFeature(fp, "Bean",
                "A Java Bean (CDI)  based component/implementation.", new CreateComponentFeature(fp,
                        new BeanComponentFactory(), "Bean",
                        "Create a component with a Java Bean (CDI) implementation.", ImageProvider.IMG_16_BEAN),
                new CreateImplementationFeature(fp, new BeanImplementationFactory(), "Bean",
                        "An implementation using a Java Bean (CDI).") {
                    @Override
                    public boolean canCreate(ICreateContext context) {
                        if (super.canCreate(context)) {
                            return InterfaceType.interfacesAreCompatible(
                                    (Component) getBusinessObjectForPictogramElement(context.getTargetContainer()),
                                    TYPES);
                        }
                        return false;
                    }
                }) };
    }

    @Override
    public IImageDecorator getImageDecorator(Implementation implementation) {
        return new ImageDecorator(ImageProvider.IMG_16_BEAN);
    }

    @Override
    public boolean supports(Class<? extends Implementation> type) {
        return BeanImplementationType.class.isAssignableFrom(type);
    }

    @Override
    public List<String> getRequiredCapabilities(Implementation object) {
        return Collections.singletonList("org.switchyard.components:switchyard-component-bean");
    }

    @Override
    public Set<InterfaceType> getSupportedInterfaceTypes(Implementation implementation) {
        return TYPES;
    }

    @Override
    public String getTypeName(Implementation object) {
        return "Bean";
    }
}
