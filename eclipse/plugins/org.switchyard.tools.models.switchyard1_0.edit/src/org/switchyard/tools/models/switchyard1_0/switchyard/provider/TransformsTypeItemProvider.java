/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_0.switchyard.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchyardPackage;
import org.switchyard.tools.models.switchyard1_0.switchyard.TransformsType;
import org.switchyard.tools.models.switchyard1_0.transform.TransformFactory;

/**
 * This is the item provider adapter for a {@link org.switchyard.tools.models.switchyard1_0.switchyard.TransformsType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TransformsTypeItemProvider
    extends ItemProviderAdapter
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransformsTypeItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

        }
        return itemPropertyDescriptors;
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM_GROUP);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns TransformsType.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TransformsType"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_TransformsType_type");
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(TransformsType.class)) {
            case SwitchyardPackage.TRANSFORMS_TYPE__TRANSFORM_GROUP:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add
            (createChildParameter
                (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM_GROUP,
                 FeatureMapUtil.createEntry
                    (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM,
                     TransformFactory.eINSTANCE.createJavaTransformType1())));

        newChildDescriptors.add
            (createChildParameter
                (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM_GROUP,
                 FeatureMapUtil.createEntry
                    (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM,
                     TransformFactory.eINSTANCE.createJAXBTransformType())));

        newChildDescriptors.add
            (createChildParameter
                (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM_GROUP,
                 FeatureMapUtil.createEntry
                    (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM,
                     TransformFactory.eINSTANCE.createJsonTransformType())));

        newChildDescriptors.add
            (createChildParameter
                (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM_GROUP,
                 FeatureMapUtil.createEntry
                    (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM,
                     TransformFactory.eINSTANCE.createSmooksTransformType1())));

        newChildDescriptors.add
            (createChildParameter
                (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM_GROUP,
                 FeatureMapUtil.createEntry
                    (SwitchyardPackage.Literals.TRANSFORMS_TYPE__TRANSFORM,
                     TransformFactory.eINSTANCE.createXsltTransformType())));
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ((IChildCreationExtender)adapterFactory).getResourceLocator();
    }

}
