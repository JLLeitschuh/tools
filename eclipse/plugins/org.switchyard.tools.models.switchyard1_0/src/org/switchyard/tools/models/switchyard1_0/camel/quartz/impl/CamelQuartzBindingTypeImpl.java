/**
 */
package org.switchyard.tools.models.switchyard1_0.camel.quartz.impl;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.switchyard.tools.models.switchyard1_0.camel.quartz.CamelQuartzBindingType;
import org.switchyard.tools.models.switchyard1_0.camel.quartz.QuartzPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Camel Quartz Binding Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_0.camel.quartz.impl.CamelQuartzBindingTypeImpl#getNameElement <em>Name Element</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_0.camel.quartz.impl.CamelQuartzBindingTypeImpl#getCron <em>Cron</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_0.camel.quartz.impl.CamelQuartzBindingTypeImpl#getStateful <em>Stateful</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_0.camel.quartz.impl.CamelQuartzBindingTypeImpl#getTriggerStartTime <em>Trigger Start Time</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_0.camel.quartz.impl.CamelQuartzBindingTypeImpl#getTriggerEndTime <em>Trigger End Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CamelQuartzBindingTypeImpl extends BaseCamelBindingImpl implements CamelQuartzBindingType {
    /**
     * The default value of the '{@link #getNameElement() <em>Name Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNameElement()
     * @generated
     * @ordered
     */
    protected static final String NAME_ELEMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNameElement() <em>Name Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNameElement()
     * @generated
     * @ordered
     */
    protected String nameElement = NAME_ELEMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getCron() <em>Cron</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCron()
     * @generated
     * @ordered
     */
    protected static final String CRON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCron() <em>Cron</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCron()
     * @generated
     * @ordered
     */
    protected String cron = CRON_EDEFAULT;

    /**
     * The default value of the '{@link #getStateful() <em>Stateful</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateful()
     * @generated
     * @ordered
     */
    protected static final Boolean STATEFUL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStateful() <em>Stateful</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateful()
     * @generated
     * @ordered
     */
    protected Boolean stateful = STATEFUL_EDEFAULT;

    /**
     * This is true if the Stateful attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean statefulESet;

    /**
     * The default value of the '{@link #getTriggerStartTime() <em>Trigger Start Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTriggerStartTime()
     * @generated
     * @ordered
     */
    protected static final XMLGregorianCalendar TRIGGER_START_TIME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTriggerStartTime() <em>Trigger Start Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTriggerStartTime()
     * @generated
     * @ordered
     */
    protected XMLGregorianCalendar triggerStartTime = TRIGGER_START_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #getTriggerEndTime() <em>Trigger End Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTriggerEndTime()
     * @generated
     * @ordered
     */
    protected static final XMLGregorianCalendar TRIGGER_END_TIME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTriggerEndTime() <em>Trigger End Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTriggerEndTime()
     * @generated
     * @ordered
     */
    protected XMLGregorianCalendar triggerEndTime = TRIGGER_END_TIME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CamelQuartzBindingTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNameElement() {
        return nameElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNameElement(String newNameElement) {
        String oldNameElement = nameElement;
        nameElement = newNameElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__NAME_ELEMENT, oldNameElement, nameElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCron() {
        return cron;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCron(String newCron) {
        String oldCron = cron;
        cron = newCron;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__CRON, oldCron, cron));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Boolean getStateful() {
        return stateful;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStateful(Boolean newStateful) {
        Boolean oldStateful = stateful;
        stateful = newStateful;
        boolean oldStatefulESet = statefulESet;
        statefulESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__STATEFUL, oldStateful, stateful, !oldStatefulESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetStateful() {
        Boolean oldStateful = stateful;
        boolean oldStatefulESet = statefulESet;
        stateful = STATEFUL_EDEFAULT;
        statefulESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__STATEFUL, oldStateful, STATEFUL_EDEFAULT, oldStatefulESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetStateful() {
        return statefulESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar getTriggerStartTime() {
        return triggerStartTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTriggerStartTime(XMLGregorianCalendar newTriggerStartTime) {
        XMLGregorianCalendar oldTriggerStartTime = triggerStartTime;
        triggerStartTime = newTriggerStartTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_START_TIME, oldTriggerStartTime, triggerStartTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar getTriggerEndTime() {
        return triggerEndTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTriggerEndTime(XMLGregorianCalendar newTriggerEndTime) {
        XMLGregorianCalendar oldTriggerEndTime = triggerEndTime;
        triggerEndTime = newTriggerEndTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_END_TIME, oldTriggerEndTime, triggerEndTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__NAME_ELEMENT:
                return getNameElement();
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__CRON:
                return getCron();
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__STATEFUL:
                return getStateful();
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_START_TIME:
                return getTriggerStartTime();
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_END_TIME:
                return getTriggerEndTime();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__NAME_ELEMENT:
                setNameElement((String)newValue);
                return;
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__CRON:
                setCron((String)newValue);
                return;
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__STATEFUL:
                setStateful((Boolean)newValue);
                return;
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_START_TIME:
                setTriggerStartTime((XMLGregorianCalendar)newValue);
                return;
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_END_TIME:
                setTriggerEndTime((XMLGregorianCalendar)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__NAME_ELEMENT:
                setNameElement(NAME_ELEMENT_EDEFAULT);
                return;
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__CRON:
                setCron(CRON_EDEFAULT);
                return;
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__STATEFUL:
                unsetStateful();
                return;
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_START_TIME:
                setTriggerStartTime(TRIGGER_START_TIME_EDEFAULT);
                return;
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_END_TIME:
                setTriggerEndTime(TRIGGER_END_TIME_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__NAME_ELEMENT:
                return NAME_ELEMENT_EDEFAULT == null ? nameElement != null : !NAME_ELEMENT_EDEFAULT.equals(nameElement);
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__CRON:
                return CRON_EDEFAULT == null ? cron != null : !CRON_EDEFAULT.equals(cron);
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__STATEFUL:
                return isSetStateful();
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_START_TIME:
                return TRIGGER_START_TIME_EDEFAULT == null ? triggerStartTime != null : !TRIGGER_START_TIME_EDEFAULT.equals(triggerStartTime);
            case QuartzPackage.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_END_TIME:
                return TRIGGER_END_TIME_EDEFAULT == null ? triggerEndTime != null : !TRIGGER_END_TIME_EDEFAULT.equals(triggerEndTime);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (nameElement: ");
        result.append(nameElement);
        result.append(", cron: ");
        result.append(cron);
        result.append(", stateful: ");
        if (statefulESet) result.append(stateful); else result.append("<unset>");
        result.append(", triggerStartTime: ");
        result.append(triggerStartTime);
        result.append(", triggerEndTime: ");
        result.append(triggerEndTime);
        result.append(')');
        return result.toString();
    }

} //CamelQuartzBindingTypeImpl