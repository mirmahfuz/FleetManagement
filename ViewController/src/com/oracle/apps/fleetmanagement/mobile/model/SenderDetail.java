package com.oracle.apps.fleetmanagement.mobile.model;

import com.oracle.apps.fleetmanagement.mobile.bean.PreviewBean;
import com.oracle.apps.fleetmanagement.mobile.model.service.userDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class SenderDetail {
    
    protected transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    protected transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    /* Sender Attributes*/
    private String senderName;
    private String senderAddr1;
    private String senderCity;
    private String senderState;
    private String senderZip;
    private String senderCountry;
    private String senderEmail;
    private String senderPhoneNumber;
    /* Receiver Attributes*/
    private String receiverName;
    private String receiverAddr1;
    private String receiverCity;
    private String receiverState;
    private String receiverZip;
    private String receiverCountry;
    private String receiverEmail;
    private String receiverPhoneNumber;
    
    /* Shipment */
    private String numOfBoxes;
    private String shippingMethod;
    private String numOfTrainCarts;
    private String shipmentType;    
   
    
    /* Preferences */
    private boolean theftProtection;/* Ultrasound & Light Sensor */
    private boolean shipmentLoadUnload;/* Light sensor */
    private boolean temperatureMonitoring;
    private long tempThreshold;
    private boolean humidityMonitoring;
    private long humThreadshold;
    private String typeOfShipment;
    private boolean monitorCollision;
    private boolean monitorShakes;
    private boolean monitorAtmosphericPressure;
    private long total;

    /* Card Details */
    private String ccType;


    private String ccNum;
    private String ccName;
    private String cvv;
    private String ccExp;
    private String ccBillingAddress;
    private String ccCity;
    private String ccState;
    private String ccZip;
    private String ccCountry;

   

    private boolean readOnly;
    private boolean disabled;

    public SenderDetail(){
        super();
        total = 20; 
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.total}", Long.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), total);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.shipmentLoadUnloadvaluechangenew}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "$0");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangenew}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "$0");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.temperatureMonitoringvaluechangenew}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "$0");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.monitorCollisionvaluechangenew}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "$0");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.monitorAtmosphericPressurevaluechangenew}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "$0");
        
    }
    
    public void defaultFillSender(ActionEvent x){
     /*   ValueExpression ve;
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderPhoneNumber}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "4087869884");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderEmail}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "anil.ranka@gmail.com");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderCountry}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "United States"); 
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderZip}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "94114"); 
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderState}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "California");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderCity}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Palo Alto");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderAddr1}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "400 Oracle Parkway");
*/
        
        
   //     ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderName}", String.class);
     //   ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Anil Ranka");
        
       
        this.setSenderName("Lisa Ray");
        this.setSenderAddr1("300 Oracle Parkway");
        this.setSenderCity("Redwood City");
        this.setSenderState("California");
        this.setSenderZip("94065");
        this.setSenderCountry("United States");
        this.setSenderPhoneNumber("4087869884");
        this.setSenderEmail("lisa.ray@company.com");
        
        this.setReceiverName("John Smith");
        this.setReceiverAddr1("11401 Century Oaks Terrace");
        this.setReceiverCity("Austin");
        this.setReceiverState("Texas");
        this.setReceiverZip("78758");
        this.setReceiverCountry("United States");
        this.setReceiverPhoneNumber("7034561234");
        this.setReceiverEmail("john.smith@company.com");
           
        this.setShipmentType("Perishable");
        this.setNumOfBoxes("2");
        this.setNumOfTrainCarts("Train cart 1");
        this.setShippingMethod("Rail");
        
        this.setCcNum("xxxxxxxxxxxxxxxx");
        this.setCcType("Visa");
        this.setCvv("xxx");
        this.setCcBillingAddress("300 Oracle Parkway");
        this.setCcState("Redwood City");
        this.setCcState("California");      
        this.setCcZip("94043");
        this.setCcCountry("United States");
        
    }
    public void defaultFillReceiver(ActionEvent x){
        ValueExpression ve;
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverPhoneNumber}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "4187969884");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverEmail}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "john.smith@gmail.com");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverCountry}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "United States"); 
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverZip}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "94004"); 
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverState}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "California");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverCity}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "San Francisco");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverAddr1}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "1344 Market Street");
        
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverName}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "John Smith");
        
       
    }
    
    public void gotoHomePage(ActionEvent x){
        try {
                AdfmfContainerUtilities.resetFeature("Home");
             }catch(AdfException e) {
               throw new AdfException(e);
             }
    }
    public void defaultFillPayment(ActionEvent x){
        this.generatePreview(x);
    }
    
    public void save(ActionEvent x){
        
    }
    public void edit(ActionEvent x){
        
    }
    
    public void ValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.valuechanged}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      String oldstr = "";
      String newstr = "";
      Object value = null;

      value = valueChangeEvent.getOldValue();
      if (value != null)
      {
        oldstr = value.toString();
      }

      value = valueChangeEvent.getNewValue();
      if (value != null)
      {
        newstr = value.toString();
      }

      String oldval = "Old Value: " + oldstr;
      String newval = "New Value: " + newstr;

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.valuechangeold}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.valuechangenew}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newval);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############ValueChangeHandler completed");
    }


    public void setSenderName(String senderName) {
        String oldSenderName = this.senderName;
        this.senderName = senderName;
        propertyChangeSupport.firePropertyChange("senderName", oldSenderName, senderName);
    }

    public String getSenderName() {
        return senderName;
    }


    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setSenderAddr1(String senderAddr1) {
        String oldSenderAddr1 = this.senderAddr1;
        this.senderAddr1 = senderAddr1;
        propertyChangeSupport.firePropertyChange("senderAddr1", oldSenderAddr1, senderAddr1);
    }

    public String getSenderAddr1() {
        return senderAddr1;
    }

    public void setSenderCity(String senderCity) {
        String oldSenderCity = this.senderCity;
        this.senderCity = senderCity;
        propertyChangeSupport.firePropertyChange("senderCity", oldSenderCity, senderCity);
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderState(String senderState) {
        String oldSenderState = this.senderState;
        this.senderState = senderState;
        propertyChangeSupport.firePropertyChange("senderState", oldSenderState, senderState);
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderZip(String senderZip) {
        String oldSenderZip = this.senderZip;
        this.senderZip = senderZip;
        propertyChangeSupport.firePropertyChange("senderZip", oldSenderZip, senderZip);
    }

    public String getSenderZip() {
        return senderZip;
    }

    public void setSenderCountry(String senderCountry) {
        String oldSenderCountry = this.senderCountry;
        this.senderCountry = senderCountry;
        propertyChangeSupport.firePropertyChange("senderCountry", oldSenderCountry, senderCountry);
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderEmail(String senderEmail) {
        String oldSenderEmail = this.senderEmail;
        this.senderEmail = senderEmail;
        propertyChangeSupport.firePropertyChange("senderEmail", oldSenderEmail, senderEmail);
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        String oldSenderPhoneNumber = this.senderPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;
        propertyChangeSupport.firePropertyChange("senderPhoneNumber", oldSenderPhoneNumber, senderPhoneNumber);
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverAddr1(String receiverAddr1) {
        this.receiverAddr1 = receiverAddr1;
    }

    public String getReceiverAddr1() {
        return receiverAddr1;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setTheftProtection(boolean theftProtection) {
        this.theftProtection = theftProtection;
    }

    public boolean isTheftProtection() {
        return theftProtection;
    }
    public void TheftValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.theftProtection}", boolean.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      boolean oldstr = false;
      boolean newstr = false;
      Object value = null;

      value = valueChangeEvent.getOldValue();
      if (value != null)
      {
        oldstr = (Boolean)value;
      }

      value = valueChangeEvent.getNewValue();
      if (value != null)
      {
        newstr = (Boolean)value;
      }

      //String oldval = "Old Value: " + oldstr;
      String newval = "$0";

      if(newstr == true){
          newval = "$5.00";
          total += 5;
      }else{
          total -= 5;
      }
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangenew}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newval);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "TheftValueChangeHandler",
                "##############ValueChangeHandler completed");
      
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.total}", Long.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), total);
    }

    public void setShipmentLoadUnload(boolean shipmentLoadUnload) {
        this.shipmentLoadUnload = shipmentLoadUnload;
    }

    public boolean isShipmentLoadUnload() {
        return shipmentLoadUnload;
    }

    public void ShipmentLoadUnloadValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.theftProtection}", boolean.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      boolean oldstr = false;
      boolean newstr = false;
      Object value = null;

      value = valueChangeEvent.getOldValue();
      if (value != null)
      {
        oldstr = (Boolean)value;
      }

      value = valueChangeEvent.getNewValue();
      if (value != null)
      {
        newstr = (Boolean)value;
      }

      //String oldval = "Old Value: " + oldstr;
      String newval = "$0";

      if(newstr == true){
          newval = "$1.00";
          total +=  1;
      }else{
          total -= 1;
      }
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.shipmentLoadUnloadvaluechangenew}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newval);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ShipmentLoadUnloadValueChangeListener",
                "##############ValueChangeHandler completed");
      
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.total}", Long.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), total);
    }
    
    public void setTemperatureMonitoring(boolean temperatureMonitoring) {
        this.temperatureMonitoring = temperatureMonitoring;
    }

    public boolean isTemperatureMonitoring() {
        return temperatureMonitoring;
    }

    public void TemperatureMonitoringValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.temperatureMonitoring}", boolean.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      boolean oldstr = false;
      boolean newstr = false;
      Object value = null;

      value = valueChangeEvent.getOldValue();
      if (value != null)
      {
        oldstr = (Boolean)value;
      }

      value = valueChangeEvent.getNewValue();
      if (value != null)
      {
        newstr = (Boolean)value;
      }

      //String oldval = "Old Value: " + oldstr;
      String newval = "$0";

      if(newstr == true){
          newval = "$5.00";
          total += 5;
      }else{
          total -= 5;
      }
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.temperatureMonitoringvaluechangenew}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newval);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "TemperatureMonitoringValueChangeHandler",
                "##############ValueChangeHandler completed");
      
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.total}", Long.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), total);
    }
    
    public void setTempThreshold(long tempThreshold) {
        this.tempThreshold = tempThreshold;
    }

    public long getTempThreshold() {
        return tempThreshold;
    }
    public void TempThresholdValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.tempThreshold}", int.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      int oldstr = 0;
      int newstr = 0;
      Object value = null;

      value = valueChangeEvent.getOldValue();
      if (value != null)
      {
        oldstr = (Integer)value;
      }

      value = valueChangeEvent.getNewValue();
      if (value != null)
      {
        newstr = (Integer)value;
      }

      
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.tempThresholdvaluechangenew}", int.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newstr);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "TempThresholdValueChangeHandler",
                "##############ValueChangeHandler completed");
    }

    public void setHumidityMonitoring(boolean humidityMonitoring) {
        this.humidityMonitoring = humidityMonitoring;
    }

    public boolean isHumidityMonitoring() {
        return humidityMonitoring;
    }


    public void setHumThreadshold(long humThreadshold) {
        this.humThreadshold = humThreadshold;
    }

    public long getHumThreadshold() {
        return humThreadshold;
    }

    public void setTypeOfShipment(String typeOfShipment) {
        this.typeOfShipment = typeOfShipment;
    }

    public String getTypeOfShipment() {
        return typeOfShipment;
    }

    public void setMonitorCollision(boolean monitorCollision) {
        this.monitorCollision = monitorCollision;
    }

    public boolean isMonitorCollision() {
        return monitorCollision;
    }
    public void MonitorCollisionValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "MonitorCollisionValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.monitorCollision}", boolean.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      boolean oldstr = false;
      boolean newstr = false;
      Object value = null;

      value = valueChangeEvent.getOldValue();
      if (value != null)
      {
        oldstr = (Boolean)value;
      }

      value = valueChangeEvent.getNewValue();
      if (value != null)
      {
        newstr = (Boolean)value;
      }

      //String oldval = "Old Value: " + oldstr;
      String newval = "$0";

      if(newstr == true){
          newval = "$5.00";
          total += 5;
      }else{
          total -= 5;
      }
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.monitorCollisionvaluechangenew}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newval);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "MonitorCollisionValueChangeHandler",
                "##############ValueChangeHandler completed");
      
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.total}", Long.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), total);
    }
    
    public void setMonitorShakes(boolean monitorShakes) {
        this.monitorShakes = monitorShakes;
    }

    public boolean isMonitorShakes() {
        return monitorShakes;
    }

    public void MonitorShakesValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "MonitorShakesValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.monitorShakes}", boolean.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      boolean oldstr = false;
      boolean newstr = false;
      Object value = null;

      value = valueChangeEvent.getOldValue();
      if (value != null)
      {
        oldstr = (Boolean)value;
      }

      value = valueChangeEvent.getNewValue();
      if (value != null)
      {
        newstr = (Boolean)value;
      }

      //String oldval = "Old Value: " + oldstr;
      String newval = "$0";

      if(newstr == true){
          total += 0;
          
          //Enable the picture
          AdfmfJavaUtilities.setELValue("#{viewScope.enableMonitorImage}", true);
          AdfmfJavaUtilities.flushDataChangeEvent();
      }
      else{
          AdfmfJavaUtilities.setELValue("#{viewScope.enableMonitorImage}", false);
          AdfmfJavaUtilities.flushDataChangeEvent();
      }
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.monitorShakesvaluechangenew}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newval);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "MonitorShakesValueChangeHandler",
                "##############ValueChangeHandler completed");
      
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.total}", Long.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), total);
    }
    
    public void setMonitorAtmosphericPressure(boolean monitorAtmosphericPressure) {
        this.monitorAtmosphericPressure = monitorAtmosphericPressure;
    }

    public boolean isMonitorAtmosphericPressure() {
        return monitorAtmosphericPressure;
    }

    public void MonitorAtmosphericPressureValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "MonitorAtmosphericPressureValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.monitorAtmosphericPressure}", boolean.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      boolean oldstr = false;
      boolean newstr = false;
      Object value = null;

      value = valueChangeEvent.getOldValue();
      if (value != null)
      {
        oldstr = (Boolean)value;
      }

      value = valueChangeEvent.getNewValue();
      if (value != null)
      {
        newstr = (Boolean)value;
      }

      //String oldval = "Old Value: " + oldstr;
      String newval = "$0";

      if(newstr == true){
          newval = "$5.00";
          total += 5;
      }else{
          total -= 5;
      }
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.monitorAtmosphericPressurevaluechangenew}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newval);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "MonitorAtmosphericPressureValueChangeHandler",
                "##############ValueChangeHandler completed");
      
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.total}", Long.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), total);
    }
    

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }
    
    
    public void setCcNum(String ccNum) {
        this.ccNum = ccNum;
    }

    public String getCcNum() {
        return ccNum;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCcExp(String ccExp) {
        this.ccExp = ccExp;
    }

    public String getCcExp() {
        return ccExp;
    }

    public void setCcBillingAddress(String ccBillingAddress) {
        this.ccBillingAddress = ccBillingAddress;
    }

    public String getCcBillingAddress() {
        return ccBillingAddress;
    }

    public void setCcCity(String ccCity) {
        this.ccCity = ccCity;
    }

    public String getCcCity() {
        return ccCity;
    }

    public void setCcState(String ccState) {
        this.ccState = ccState;
    }

    public String getCcState() {
        return ccState;
    }

    public void setCcZip(String ccZip) {
        this.ccZip = ccZip;
    }

    public String getCcZip() {
        return ccZip;
    }
    public void setCcCountry(String ccCountry) {
        this.ccCountry = ccCountry;
    }

    public String getCcCountry() {
        return ccCountry;
    }
    
    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcType() {
        return ccType;
    }


    public void setNumOfBoxes(String numOfBoxes) {
        this.numOfBoxes = numOfBoxes;
    }

    public String getNumOfBoxes() {
        return numOfBoxes;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setNumOfTrainCarts(String numOfTrainCarts) {
        this.numOfTrainCarts = numOfTrainCarts;
    }

    public String getNumOfTrainCarts() {
        return numOfTrainCarts;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getShipmentType() {
        return shipmentType;
    }
    
    public void ShipmentTypeValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ShipmentTypeValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.shipmentType}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Value Changed!");

      Object[]  oldVal = (Object[]) valueChangeEvent.getOldValue();
      Object[] newVal = (Object[]) valueChangeEvent.getNewValue();
      
        ArrayList<String> selectedShipmentType = new ArrayList<String>();  
        StringBuffer selectedShipmentTypeString = new StringBuffer();
        for (int i = 0; i < newVal.length; i++) {
            selectedShipmentType.add((String)newVal[i]);
            selectedShipmentTypeString.append((String)newVal[i]+" ,");
        }
        
        String tempString = selectedShipmentTypeString.substring(0, selectedShipmentTypeString.length()-1);
        setShipmentType(tempString);
      
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);
      
    }
    
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public void setProviderChangeSupport(ProviderChangeSupport providerChangeSupport) {
        ProviderChangeSupport oldProviderChangeSupport = this.providerChangeSupport;
        this.providerChangeSupport = providerChangeSupport;
        propertyChangeSupport.firePropertyChange("providerChangeSupport", oldProviderChangeSupport,
                                                 providerChangeSupport);
    }

    public ProviderChangeSupport getProviderChangeSupport() {
        return providerChangeSupport;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    public void generatePreview(ActionEvent actionEvent){
        PreviewBean pageOne = new PreviewBean();
        pageOne.setItem1(this.getSenderName()==null?"":this.getSenderName());
        pageOne.setItem1Label("Sender Name");
        pageOne.setItem2(this.getSenderAddr1()==null?"":this.getSenderAddr1());
        pageOne.setItem2Label("Address");
        pageOne.setItem3(this.getSenderCity()==null?"":this.getSenderCity());
        pageOne.setItem3Label("City");
        pageOne.setItem4(this.getSenderState()==null?"":this.getSenderState());
        pageOne.setItem4Label("State");    
        pageOne.setItem5(this.getSenderZip()==null?"":this.getSenderZip());
        pageOne.setItem5Label("Zip");
        pageOne.setItem6(this.getSenderCountry()==null?"":this.getSenderCountry());
        pageOne.setItem6Label("Country");
        pageOne.setItem7(this.getSenderEmail()==null?"":this.getSenderEmail());
        pageOne.setItem7Label("Email");
        pageOne.setItem8(this.getSenderPhoneNumber()==null?"":this.getSenderPhoneNumber());
        pageOne.setItem8Label("Phone");
        
        
        PreviewBean pageTwo = new PreviewBean();
        pageTwo.setItem1(this.getReceiverName()==null?"":this.getReceiverName());
        pageTwo.setItem1Label("Receiver Name");
        pageTwo.setItem2(this.getReceiverAddr1()==null?"":this.getReceiverAddr1());
        pageTwo.setItem2Label("Address");
        pageTwo.setItem3(this.getReceiverCity()==null?"":this.getReceiverCity());
        pageTwo.setItem3Label("City");
        pageTwo.setItem4(this.getReceiverState()==null?"":this.getReceiverState());
        pageTwo.setItem4Label("State");
        pageTwo.setItem5(this.getReceiverZip()==null?"":this.getReceiverZip());
        pageTwo.setItem5Label("Zip");
        pageTwo.setItem6(this.getReceiverCountry()==null?"":this.getReceiverCountry());
        pageTwo.setItem6Label("Country");
        pageTwo.setItem7(this.getReceiverEmail()==null?"":this.getReceiverEmail());
        pageTwo.setItem7Label("Email");
        pageTwo.setItem8(this.getReceiverPhoneNumber()==null?"":this.getReceiverPhoneNumber());
        pageTwo.setItem8Label("Phone");
        
        PreviewBean pageThree = new PreviewBean();
        pageThree.setItem1(this.getNumOfBoxes()==null?"":this.getNumOfBoxes());
        pageThree.setItem1Label("Number of boxes");
        pageThree.setItem2(this.getShippingMethod()==null?"":this.getShippingMethod());
        pageThree.setItem2Label("Select shipping method");
        pageThree.setItem3(this.getNumOfTrainCarts()==null?"":this.getNumOfTrainCarts());
        pageThree.setItem3Label("No of train cars");
        pageThree.setItem4(this.getShipmentType()==null?"":this.getShipmentType());
        pageThree.setItem4Label("Nature of shipment");
        
        PreviewBean pageFour = new PreviewBean();
        pageFour.setItem1(isMonitorShakes()==true?"Selected":"Not selected");
        pageFour.setItem1Label("Location Tracking");
        pageFour.setItem2(isTheftProtection()==true?"Selected":"Not selected");
        pageFour.setItem2Label("Theft Protection");
        pageFour.setItem3(isShipmentLoadUnload()==true?"Selected":"Not selected");
        pageFour.setItem3Label("Shipment Load and Unload notification");
        pageFour.setItem4(isMonitorCollision()==true?"Selected":"Not selected");
        pageFour.setItem4Label("Collision Monitoring");
        pageFour.setItem5(isMonitorAtmosphericPressure()==true?"Selected":"Not selected");
        pageFour.setItem5Label("Humidity Monitoring");
        pageFour.setItem6(isTemperatureMonitoring()==true?"Selected":"Not selected");
        pageFour.setItem6Label("Temperature Monitoring");
        pageFour.setItem7(String.valueOf(this.getTempThreshold()));
        pageFour.setItem7Label("Temperature Threshold:");
        pageFour.setItem8("$ " + String.valueOf(this.getTotal()));
        pageFour.setItem8Label("Total");
        
        PreviewBean pageFive = new PreviewBean();
        pageFive.setItem1(this.getCcType()==null?"":this.getCcType());
        pageFive.setItem1Label("Card Type");
        pageFive.setItem2(this.getCcNum()==null?"":this.getCcNum());
        pageFive.setItem2Label("Card Num");
        pageFive.setItem3(this.getCvv()==null?"":this.getCvv());
        pageFive.setItem3Label("CVV");
        pageFive.setItem4(this.getCcBillingAddress()==null?"":this.getCcBillingAddress());
        pageFive.setItem4Label("Billing Address");
        pageFive.setItem5(this.getCcCity()==null?"":this.getCcCity());
        pageFive.setItem5Label("City");
        pageFive.setItem6(this.getCcState()==null?"":this.getCcState());
        pageFive.setItem6Label("State");
        pageFive.setItem7(this.getCcZip()==null?"":this.getCcZip());
        pageFive.setItem7Label("Zip");
        pageFive.setItem8(this.getCcCountry()==null?"":this.getCcCountry());
        pageFive.setItem8Label("Country");
       
        
        userDetailsService x = new userDetailsService();
        userDetailsService ctx = userDetailsService.context;
        List<PreviewBean> tempList = ctx.getPreviewList();
        
        tempList = new ArrayList<PreviewBean>();
        tempList.add(0,pageOne);
        tempList.add(1, pageTwo);
        tempList.add(2,pageThree);
        tempList.add(3, pageFour);
        tempList.add(4,pageFive);
        ctx.setPreviewList(tempList);
       
    }

    private boolean showTempPopup;

    public void setShowTempPopup(boolean showTempPopup) {
        boolean oldShowTempPopup = this.showTempPopup;
        this.showTempPopup = showTempPopup;
        propertyChangeSupport.firePropertyChange("showTempPopup", oldShowTempPopup, showTempPopup);
    }

    public boolean isShowTempPopup() {
        return showTempPopup;
    }
}
