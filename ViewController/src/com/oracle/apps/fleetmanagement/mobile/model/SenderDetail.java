package com.oracle.apps.fleetmanagement.mobile.model;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class SenderDetail {
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
        total = 0; 
    }
    
    public void defaultFillSender(ActionEvent x){
        ValueExpression ve;
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.senderPhoneNumber}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "4087869884");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.senderEmail}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "anil.ranka@gmail.com");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.senderCountry}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "United States"); 
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.senderZip}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "94114"); 
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.senderState}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "California");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.senderCity}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Palo Alto");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.senderAddr1}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "400 Oracle Parkway");
        
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.senderName}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Anil Ranka");
        
    }
    public void defaultFillReceiver(ActionEvent x){
        ValueExpression ve;
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.receiverPhoneNumber}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "4187969884");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.receiverEmail}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "john.smith@gmail.com");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.receiverCountry}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "United States"); 
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.receiverZip}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "94004"); 
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.receiverState}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "California");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.receiverCity}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "San Francisco");
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.receiverAddr1}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "1344 Market Street");
        
        
        ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.receiverName}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "John Smith");
    }
    
    public void defaultFillPayment(ActionEvent x){
        
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
    
    public void setSenderName(String name) {
        this.senderName = name;
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
    public void setSenderAddr1(String addr1) {
        this.senderAddr1 = addr1;
    }

    public String getSenderAddr1() {
        return senderAddr1;
    }
    public void setSenderCity(String city) {
        this.senderCity = city;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderState(String state) {
        this.senderState = state;
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderZip(String zip) {
        this.senderZip = zip;
    }

    public String getSenderZip() {
        return senderZip;
    }

    public void setSenderCountry(String country) {
        this.senderCountry = country;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderEmail(String email) {
        this.senderEmail = email;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderPhoneNumber(String phoneNumber) {
        this.senderPhoneNumber = phoneNumber;
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

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.theftProtection}", boolean.class);
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
          newval = "$100";
          total += 100;
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

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.theftProtection}", boolean.class);
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
          newval = "$100";
          total +=  100;
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

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.temperatureMonitoring}", boolean.class);
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
          newval = "$100";
          total += 100;
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

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.tempThreshold}", int.class);
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

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.monitorCollision}", boolean.class);
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
          newval = "$100";
          total += 100;
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

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.monitorShakes}", boolean.class);
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
          newval = "$100";
          total += 100;
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

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SenderDetail.monitorAtmosphericPressure}", boolean.class);
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
          newval = "$100";
          total += 100;
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
}
