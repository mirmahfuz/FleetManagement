package com.oracle.apps.fleetmanagement.mobile.bean;

import com.oracle.apps.fleetmanagement.mobile.model.ManagedBean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class TrackingShipmentHomeBean implements MqttCallback{
    
    public TrackingShipmentHomeBean() {
        super();
        qos1 = 2;
        sampleClient1 = null;
        broker1 = "tcp://m11.cloudmqtt.com:16385";
        password1 = "1MSI1ktJ81aY";
        uri1 = "tcp://m11.cloudmqtt.com:16385";
        username1 = "swlrwhmx";
    }
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    MqttClient sampleClient1;
    int qos1;
    String broker1;   
    String password1;
    String uri1;
    String username1;
    String topic1 = "on_rail";
    boolean changeLightIndicator;
    boolean changeHumidIndicator;
    boolean changeUltrasonicIndicator;
    boolean changeTemperatureIndicator;
  

    public void setChangeLightIndicator(boolean changeLightIndicator) {
        boolean oldChangeLightIndicator = this.changeLightIndicator;
        this.changeLightIndicator = changeLightIndicator;
        propertyChangeSupport.firePropertyChange("changeLightIndicator", oldChangeLightIndicator, changeLightIndicator);
        AdfmfJavaUtilities.flushDataChangeEvent();
    }

    public boolean isChangeLightIndicator() {
        return changeLightIndicator;
    }

    public void setChangeHumidIndicator(boolean changeHumidIndicator) {
        boolean oldChangeHumidIndicator = this.changeHumidIndicator;
        this.changeHumidIndicator = changeHumidIndicator;
        propertyChangeSupport.firePropertyChange("changeHumidIndicator", oldChangeHumidIndicator, changeHumidIndicator);
        AdfmfJavaUtilities.flushDataChangeEvent();
    }

    public boolean isChangeHumidIndicator() {
        return changeHumidIndicator;
    }

    public void setChangeUltrasonicIndicator(boolean changeUltrasonicIndicator) {
        boolean oldChangeUltrasonicIndicator = this.changeUltrasonicIndicator;
        this.changeUltrasonicIndicator = changeUltrasonicIndicator;
        propertyChangeSupport.firePropertyChange("changeUltrasonicIndicator", oldChangeUltrasonicIndicator,
                                                 changeUltrasonicIndicator);
        AdfmfJavaUtilities.flushDataChangeEvent();
    }

    public boolean isChangeUltrasonicIndicator() {
        return changeUltrasonicIndicator;
    }

    public void setChangeTemperatureIndicator(boolean changeTemperatureIndicator) {
        boolean oldChangeTemperatureIndicator = this.changeTemperatureIndicator;
        this.changeTemperatureIndicator = changeTemperatureIndicator;
        propertyChangeSupport.firePropertyChange("changeTemperatureIndicator", oldChangeTemperatureIndicator,
                                                 changeTemperatureIndicator);
        AdfmfJavaUtilities.flushDataChangeEvent();
    }

    public boolean isChangeTemperatureIndicator() {
        return changeTemperatureIndicator;
    }

    private MqttConnectOptions getMqttConnOptions(){
        MqttConnectOptions connOpts = null;
        try{
        connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setPassword(password1.toCharArray());
        String[] serverURIs = new String[1];
        serverURIs[0] = uri1;
        connOpts.setServerURIs(serverURIs);
        connOpts.setUserName(username1);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return connOpts;
        }
    }
    
    private boolean connect( String topic ){
        String clientId     = MqttClient.generateClientId();
        MemoryPersistence persistence = new MemoryPersistence();
        String text = "";
        boolean connStatus = false;
        try {
            sampleClient1 = new MqttClient(broker1, clientId, persistence);
            MqttConnectOptions connOpts = getMqttConnOptions();
            sampleClient1.setCallback(this);
            sampleClient1.connect(connOpts);
           
            connStatus = true;
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),"Connected"); 
        }catch(MqttException me) {
            text = "reason "+me.getReasonCode();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "msg "+me.getMessage();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "loc "+me.getLocalizedMessage();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "cause "+me.getCause();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "excep "+me;
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 

            me.printStackTrace();
                    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return connStatus;
        }
    }
    
    private boolean publish(String topic, String content){
        Boolean pubStatus = false;
        String text = "";
        try{
        Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
        
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos1);
        sampleClient1.publish(topic, message);
        
        text = ("Message published");
        Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
        pubStatus = true;
        }catch(MqttException me) {
            text = "reason "+me.getReasonCode();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "msg "+me.getMessage();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "loc "+me.getLocalizedMessage();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "cause "+me.getCause();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "excep "+me;
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 

            me.printStackTrace();
                    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return pubStatus;
        }
    }
    private void disconnect(){
        String text = "";
        try{
        if(sampleClient1 != null){
            sampleClient1.disconnect();
            sampleClient1 = null;
        }
        }catch(MqttException me) {
            text = "reason "+me.getReasonCode();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "msg "+me.getMessage();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "loc "+me.getLocalizedMessage();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "cause "+me.getCause();
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 
            text = "excep "+me;
            Trace.log(Utility.ApplicationLogger, Level.INFO, ManagedBean.class, ManagedBean.class.getName(),text); 

            me.printStackTrace();
                    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void publish(ActionEvent x){
       
      String msg = "Message from MqttPublishSample";
      publish(topic1, msg);
      
    }
        
    public void subscribeToSubscriber(){
        if(connect(topic1)){
            MqttTopic mqttTopic = sampleClient1.getTopic(topic1);
            try {
                int subQoS = 0;
                sampleClient1.subscribe(topic1, subQoS);
                String defaultPublish = "{\"fleet\": {\"customer_id\": 10, \"sensor\": {\"light\": 300, \"long\": -122.2646400,\n" + 
                "\"humidity\": 47.0, \"ultrasound\": 6, \"lat\": 37.5311940, \"temperature\": 78.1},\n" + 
                "\"shipment_id\": \"ML-1\"}}";
                publish(topic1, defaultPublish);
              //  Thread.sleep(5000);
                } catch (Exception e) {
                e.printStackTrace();
            }finally{
             //   disconnect();
            }
            
        }
    }

    @Override
    public void messageArrived(String string, MqttMessage mqttMessage) throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("| Topic:" + string);
        System.out.println("| Message: " + new String(mqttMessage.getPayload()));
        HashMap<String, String> mPayload = parseJSONObjectFromQueue(new String(mqttMessage.getPayload()));
        System.out.println("-------------------------------------------------");

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        try {
            System.out.println("Delivery complete: " + iMqttDeliveryToken.getMessage());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        // TODO Implement this method
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
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
    
    public HashMap parseJSONObjectFromQueue(String pPayload){
        
        HashMap<String, Object> mShipmentValues = new HashMap<String, Object>();
        
        if(pPayload == null){
            pPayload = "{\"fleet\": {\"customer_id\": 4, \"sensor\": {\"light\": 300, \"long\": 0.0, \n" + 
            "\"humidity\": 47.0, \"ultrasound\": 6, \"lat\": 1.0, \"temperature\": 78.8}, \n" + 
            "\"shipment_id\": \"abc\"}}";
        }
        try{
            
            JSONObject topNode = (JSONObject) JSONBeanSerializationHelper.fromJSON(JSONObject.class, pPayload);
            
            if (topNode != null)
            {
                Object fleetValue = topNode.getJSONObject("fleet");
                
                if(fleetValue != null){
                 
                 JSONObject mfleetValue = (JSONObject)fleetValue;
                 JSONObject mJSONObject = mfleetValue.getJSONObject("sensor");
                 
                 Map<String, Object> val1 = toMap(mfleetValue);
                 Map<String, Object> val2 = toMap(mJSONObject);
                 
                 if(val1 != null){
                     
                     mShipmentValues.put("CustomerId", val1.get("customer_id"));
                     mShipmentValues.put("ShipmentId", val1.get("shipment_id")); 
                     mShipmentValues.put("MacAddress", val1.get("mac_id"));
                 }
                 
                 if(val2 != null){
                     
                     mShipmentValues.put("Light", val2.get("light"));
                     mShipmentValues.put("Humidity", val2.get("humidity"));
                     mShipmentValues.put("Temperature", val2.get("temperature"));
                     mShipmentValues.put("Longitude", val2.get("long"));
                     mShipmentValues.put("Latitude", val2.get("lat"));
                     mShipmentValues.put("Ultrasound", val2.get("ultrasound"));
                 }
                
               }
            }
        }catch(Exception ex){
            
            System.out.print(ex.getMessage());
        }
        
        try{
            
            toProperObjectType(mShipmentValues.get("CustomerId"), null);
            toProperObjectType(mShipmentValues.get("Humidity"), "Humidity");
            toProperObjectType(mShipmentValues.get("Latitude"), null);
            toProperObjectType(mShipmentValues.get("Longitude"), null);
            toProperObjectType(mShipmentValues.get("Light"), "Light");
            toProperObjectType(mShipmentValues.get("ShipmentId"), null);
            toProperObjectType(mShipmentValues.get("Temperature"), "Temperature");
            toProperObjectType(mShipmentValues.get("Ultrasound"),"Ultrasound");
            toProperObjectType(mShipmentValues.get("MacAddress"),null);
            
        }catch(Exception e){
            e.printStackTrace();
            throw new AdfException (e);
        }
        
        return mShipmentValues;
    }
    
    private  Map<String, Object> toMap(JSONObject object) throws JSONException {
        
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        
        while(keysItr.hasNext()) {
            
            String key = keysItr.next();
            Object value = object.get(key);
            map.put(key, value);
        }
        
        return map;
    }
    
    private String toProperObjectType(Object pVal, String pAttrName){
       
       String mVal = null; 
       
       if(pVal != null){
           
           if(pVal instanceof Integer){
               
               int i = (Integer) pVal;
               mVal = Integer.toString(i);
               
               if(pAttrName != null){
                   
                   if(pAttrName.equalsIgnoreCase("Light")){
                       
                       Object lightThreshNum =  AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.lightThresh}");
                       
                       if(lightThreshNum != null && lightThreshNum instanceof Integer){
                           
                           int lightThresh = (Integer) lightThreshNum;
                           
                           if(i < lightThresh){
                               
                               setChangeLightIndicator(false);
                               
                           }else{
                               
                               setChangeLightIndicator(true);
                           }
                       }else{
                           
                           setChangeLightIndicator(true);
                       }
                   }
                   
                   
                   if(pAttrName.equalsIgnoreCase("Ultrasound")){
                       
                       Object usThreshNum =  AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.usThresh}");
                       
                       if(usThreshNum != null && usThreshNum instanceof Integer){
                           
                           int usThresh = (Integer) usThreshNum;
                           
                           if(i < usThresh){
                               
                               setChangeUltrasonicIndicator(false);
                               
                           }else{
                               
                               setChangeUltrasonicIndicator(true);
                           }
                       }else{
                           
                           setChangeUltrasonicIndicator(true);
                       }
                   }
                   
               }
               
           }else if(pVal instanceof Double){
               
               mVal = Double.toString((Double)pVal);
               
               if(pAttrName != null){
                   
                   if(pAttrName.equalsIgnoreCase("Temperature")){
                       
                       Object tempThreshNum =  AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.tempThresh}");
                       
                       if(tempThreshNum != null && tempThreshNum instanceof Integer){
                           
                           int tempThresh = (Integer) tempThreshNum;
                           
                           if((Double)pVal < tempThresh){
                               
                               setChangeTemperatureIndicator(false);
                               
                           }else{
                               
                               setChangeTemperatureIndicator(true);
                           }
                       }else{
                           
                           setChangeTemperatureIndicator(true);
                       }
                   }
                   
                   
                   if(pAttrName.equalsIgnoreCase("Humidity")){
                       
                       Object humidThreshNum =  AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.humidityThresh}");
                       
                       if(humidThreshNum != null && humidThreshNum instanceof Integer){
                           
                           int humidThresh = (Integer) humidThreshNum;
                           
                           if((Double)pVal < humidThresh){
                               
                               setChangeHumidIndicator(false);
                               
                           }else{
                               
                               setChangeHumidIndicator(true);
                           }
                       }else{
                           
                           setChangeHumidIndicator(true);
                       }
                   }
                   
               }
               
           }else if(pVal instanceof String){
               
               mVal = pVal.toString();
           }
       }
       
       return mVal;
    }

}