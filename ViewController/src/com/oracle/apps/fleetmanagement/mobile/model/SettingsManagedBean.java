package com.oracle.apps.fleetmanagement.mobile.model;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class SettingsManagedBean {
    private String tempThresh;
    private String humidityThresh;
    private String lightThresh;
    private String usThresh;



    public SettingsManagedBean() {
        super();
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.humidityThresh}", Integer.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), 5);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.tempThresh}", Integer.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), 5);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.lightThresh}", Integer.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), 5);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.usThresh}", Integer.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), 5);
    }
    
    public void setTempThresh(String tempThresh) {
        this.tempThresh = tempThresh;
    }

    public String getTempThresh() {
        return tempThresh;
    }

    public void setHumidityThresh(String humidityThresh) {
        this.humidityThresh = humidityThresh;
    }

    public String getHumidityThresh() {
        return humidityThresh;
    }

    public void setLightThresh(String lightThresh) {
        this.lightThresh = lightThresh;
    }

    public String getLightThresh() {
        return lightThresh;
    }

    public void setUsThresh(String usThresh) {
        this.usThresh = usThresh;
    }

    public String getUsThresh() {
        return usThresh;
    }
    
    public void TempThresholdValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SettingsManagedBean.tempThresh}", int.class);
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
          tempThresh = String.valueOf(newstr);
      }

      
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.tempThresholdvaluechangenew}", int.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newstr);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "TempThresholdValueChangeHandler",
                "##############ValueChangeHandler completed");
    }
    
    public void HumidityThresholdValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SettingsManagedBean.humidityThresh}", int.class);
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
          humidityThresh = String.valueOf(newstr);
      }

      
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.humidityThresholdvaluechangenew}", int.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newstr);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "HumidityThresholdValueChangeHandler",
                "##############ValueChangeHandler completed");
    }
    
    public void LightThresholdValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SettingsManagedBean.lightThresh}", int.class);
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
          lightThresh = String.valueOf(newstr);
      }
      
      
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.lightThresholdvaluechangenew}", int.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newstr);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "LightThresholdValueChangeHandler",
                "##############ValueChangeHandler completed");
    }
    
    public void UsThresholdValueChangeHandler(ValueChangeEvent valueChangeEvent)
    {
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "ValueChangeHandler",
                "##############Inside ValueChangeHandler");
      ValueExpression ve;

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.SettingsManagedBean.usThresh}", int.class);
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
          usThresh = String.valueOf(newstr);
      }

      
      //ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.theftvaluechangeold}", String.class);
      //ve.setValue(AdfmfJavaUtilities.getAdfELContext(), oldval);

      ve = AdfmfJavaUtilities.getValueExpression("#{viewScope.usThresholdvaluechangenew}", int.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), newstr);
      Trace.log(Utility.ApplicationLogger, Level.INFO, SenderDetail.class, "USThresholdValueChangeHandler",
                "##############ValueChangeHandler completed");
    }
    
    public void saveValue(ActionEvent x){
        /*
        try{
            
            //AdfmfJavaUtilities.setELValue("#{applicationScope.humidityThresh", humidityThresh);
            //AdfmfJavaUtilities.setELValue("#{applicationScope.temperaturThresh", tempThresh);
            //AdfmfJavaUtilities.setELValue("#{applicationScope.usThresh", usThresh);
            //AdfmfJavaUtilities.setELValue("#{applicationScope.lightThresh", lightThresh);
            
            int humidity = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.humidityThresh}");
            int temp = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.tempThresh}");
            int light = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.lightThresh}");
            int us = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.usThresh}");
            String message = "Threshold Value :\n Humidity : " + humidity + "\nTemperature : " + temp + "\n Light : " + light + "\n Ultra Sound : " + us;
            
        }catch(Exception e){
            throw new AdfException(e);
        } 
*/
    }
}
