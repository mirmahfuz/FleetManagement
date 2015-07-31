package com.oracle.apps.fleetmanagement.mobile.bean;

import com.oracle.apps.fleetmanagement.mobile.model.ManagedBean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
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

public class TrackShipmentRailBean implements MqttCallback{
    
    private void setDefaultSettings(){
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.humidityThresh}", Integer.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), 5);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.tempThresh}", Integer.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), 5);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.lightThresh}", Integer.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), 5);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.usThresh}", Integer.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), 5);
    }
    
    public TrackShipmentRailBean() {
        super();
        //this.setDefaultSettings();
        qos = 2;
        sampleClient = null;
        broker = "tcp://m11.cloudmqtt.com:18230";
        password = "6iKNcYELOyts";
        uri = "tcp://m11.cloudmqtt.com:18230";
        username = "kwfrisal";
    }
    
    public void init_trackingShipkment(){
        System.out.println("Taskflow Initializer in Progress");
    }
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    MqttClient sampleClient;
    int qos;
    String broker;   
    String password;
    String uri;
    String username;
    String topic = "on_rail";
    String inputMessage;
    String oldInputMessage;
    String latitude;
    String longitude;
    String temperature;
    String humidity;
    String ultrasonic;
    String light;
    String customerId;
    String shipmentId;
    boolean showLightSensor;
    boolean showUltraSensor;
    boolean showTempSensor;
    boolean showHumidSensor;
    String macAddress;

    boolean theftPopupOpen = false;
    boolean tempPopupOpen = false;
    boolean humidPopupOpen = false;
    boolean loadunloadPopupOpen = false;

    public void setMacAddress(String macAddress) {
        String oldMacAddress = this.macAddress;
        this.macAddress = macAddress;
        propertyChangeSupport.firePropertyChange("macAddress", oldMacAddress, macAddress);
        AdfmfJavaUtilities.flushDataChangeEvent();
    }

    public String getMacAddress() {
        return macAddress;
    }


    public void setShowTempSensor(boolean showTempSensor) {
        boolean oldShowTempSensor = this.showTempSensor;
        this.showTempSensor = showTempSensor;
        propertyChangeSupport.firePropertyChange("showTempSensor", oldShowTempSensor, showTempSensor);
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        if(showTempSensor&&!isTempPopupOpen()){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "showTempPopup", new Object[] { });
            setTempPopupOpen(true);
        }
        else if(!showTempSensor&&isTempPopupOpen()){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "closeTempPopup", new Object[] { });
            setTempPopupOpen(false);
        }
    }

    public boolean isShowTempSensor() {
        return showTempSensor;
    }

    public void setShowHumidSensor(boolean showHumidSensor) {
        boolean oldShowHumidSensor = this.showHumidSensor;
        this.showHumidSensor = showHumidSensor;
        propertyChangeSupport.firePropertyChange("showHumidSensor", oldShowHumidSensor, showHumidSensor);
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        if(showHumidSensor&&!isHumidPopupOpen()){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "showHumidPopup", new Object[] { });
            setHumidPopupOpen(true);
        }
        else if(!showHumidSensor&&isHumidPopupOpen()){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "closeHumidPopup", new Object[] { });
            setHumidPopupOpen(false);
        }
        
    }

    public boolean isShowHumidSensor() {
        return showHumidSensor;
    }

    public void setShowLightSensor(boolean showLightSensor) {
        boolean oldShowLightSensor = this.showLightSensor;
        this.showLightSensor = showLightSensor;
        propertyChangeSupport.firePropertyChange("showLightSensor", oldShowLightSensor, showLightSensor);
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        
        if(showLightSensor&&!isLoadunloadPopupOpen()){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "showLoadUnloadPopup", new Object[] { });
            setLoadunloadPopupOpen(true);
        }
        else if(!showLightSensor&&isLoadunloadPopupOpen()){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "closeLoadUnloadPopup", new Object[] { });
            setLoadunloadPopupOpen(false);
        }
    }

    public boolean isShowLightSensor() {
        return showLightSensor;
    }

    public void setShowUltraSensor(boolean showUltraSensor) {
        boolean oldShowUltraSensor = this.showUltraSensor;
        this.showUltraSensor = showUltraSensor;
        propertyChangeSupport.firePropertyChange("showUltraSensor", oldShowUltraSensor, showUltraSensor);
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        if(showUltraSensor&&!isTheftPopupOpen()){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "showTheftPopup", new Object[] { });
            setTheftPopupOpen(true);
        }
        else if(!showUltraSensor&&isTheftPopupOpen()){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "closeTheftPopup", new Object[] { });
            setTheftPopupOpen(false);
        }
    }

    public boolean isShowUltraSensor() {
        return showUltraSensor;
    }


    public void setLatitude(String latitude) {
        String oldLatitude = this.latitude;
        this.latitude = latitude;
        propertyChangeSupport.firePropertyChange("latitude", oldLatitude, latitude);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        String oldLongitude = this.longitude;
        this.longitude = longitude;
        propertyChangeSupport.firePropertyChange("longitude", oldLongitude, longitude);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getLongitude() {
        return longitude;
    }

    public void setTemperature(String temperature) {
        String oldTemperature = this.temperature;
        this.temperature = temperature;
        propertyChangeSupport.firePropertyChange("temperature", oldTemperature, temperature);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getTemperature() {
        return temperature;
    }

    public void setHumidity(String humidity) {
        String oldHumidity = this.humidity;
        this.humidity = humidity;
        propertyChangeSupport.firePropertyChange("humidity", oldHumidity, humidity);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getHumidity() {
        return humidity;
    }

    public void setUltrasonic(String ultrasonic) {
        String oldUltrasonic = this.ultrasonic;
        this.ultrasonic = ultrasonic;
        propertyChangeSupport.firePropertyChange("ultrasonic", oldUltrasonic, ultrasonic);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getUltrasonic() {
        return ultrasonic;
    }

    public void setLight(String light) {
        String oldLight = this.light;
        this.light = light;
        propertyChangeSupport.firePropertyChange("light", oldLight, light);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getLight() {
        return light;
    }

    public void setCustomerId(String customerId) {
        String oldCustomerId = this.customerId;
        this.customerId = customerId;
        propertyChangeSupport.firePropertyChange("customerId", oldCustomerId, customerId);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setShipmentId(String shipmentId) {
        String oldShipmentId = this.shipmentId;
        this.shipmentId = shipmentId;
        propertyChangeSupport.firePropertyChange("shipmentId", oldShipmentId, shipmentId);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getShipmentId() {
        return shipmentId;
    }
    
    private MqttConnectOptions getMqttConnOptions(){
        MqttConnectOptions connOpts = null;
        try{
        connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setPassword(password.toCharArray());
        String[] serverURIs = new String[1];
        serverURIs[0] = uri;
        connOpts.setServerURIs(serverURIs);
        connOpts.setUserName(username);
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
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = getMqttConnOptions();
            sampleClient.setCallback(this);
            sampleClient.connect(connOpts);
           
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
        message.setQos(qos);
        sampleClient.publish(topic, message);
        
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
        if(sampleClient != null){
            sampleClient.disconnect();
            sampleClient = null;
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
       
       // if(connect(topic)){
            String msg = "Message from MqttPublishSample";
            publish(topic, msg);
          //  disconnect();
      //  }
    }
    
//    public void subscribe(ActionEvent x){
//        if(connect(topic)){
//            MqttTopic mqttTopic = sampleClient.getTopic(topic);
//            try {
//                int subQoS = 0;
//                sampleClient.subscribe(topic,subQoS);
//                String defaultPublish = "{\"fleet\": {\"customer_id\": 10, \"sensor\": {\"light\": 300, \"long\": -122.2646400,\n" + 
//                "\"humidity\": 47.0, \"ultrasound\": 6, \"lat\": 37.5311940, \"temperature\": 78.1},\n" + 
//                "\"shipment_id\": \"ML-10\"}}";
//                publish(topic, defaultPublish);
//              //  Thread.sleep(5000);
//                } catch (Exception e) {
//                e.printStackTrace();
//            }finally{
//             //   disconnect();
//            }
//            
//        }
//    }
    
    public void subscribeToMQTT(){
        if(connect(topic)){
            MqttTopic mqttTopic = sampleClient.getTopic(topic);
            try {
                int subQoS = 0;
                sampleClient.subscribe(topic,subQoS);
                String defaultPublish = "{\"fleet\": {\"customer_id\": 10, \"sensor\": {\"light\": 20, \"long\": -122.2646400,\n" + 
                "\"humidity\": 47.0, \"ultrasound\": 2, \"lat\": 37.5311940, \"temperature\": 78.1},\n" + 
                "\"shipment_id\": \"ML-1\"}}";
                publish(topic, defaultPublish);
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
        if(mPayload != null && mPayload.size() > 0){
            
        try{
            
            setCustomerId(toProperObjectType(mPayload.get("CustomerId"), null));
            setHumidity(toProperObjectType(mPayload.get("Humidity"), "Humidity"));
            setLatitude(toProperObjectType(mPayload.get("Latitude"), null));
            setLongitude(toProperObjectType(mPayload.get("Longitude"), null));
            setLight(toProperObjectType(mPayload.get("Light"), "Light"));
            setShipmentId(toProperObjectType(mPayload.get("ShipmentId"), null));
            setTemperature(toProperObjectType(mPayload.get("Temperature"), "Temperature"));
            setUltrasonic(toProperObjectType(mPayload.get("Ultrasound"),"Ultrasound"));
            setMacAddress(toProperObjectType(mPayload.get("MacAddress"),null));
            
        }catch(Exception e){
            e.printStackTrace();
            throw new AdfException (e);
        }

      }
        System.out.println("-------------------------------------------------");

    }

    public void setInputMessage(String inputMessage) {
        this.oldInputMessage = this.inputMessage;
        this.inputMessage = inputMessage;
        propertyChangeSupport.firePropertyChange("inputMessage", oldInputMessage, inputMessage);
        AdfmfJavaUtilities.flushDataChangeEvent(); 
    }

    public String getInputMessage() {
        return this.inputMessage;
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
            pPayload = "{\"fleet\": {\"customer_id\": 10, \"sensor\": {\"light\": 20, \"long\": -122.2646400,\n" + 
                "\"humidity\": 47.0, \"ultrasound\": 2, \"lat\": 37.5311940, \"temperature\": 78.1},\n" + 
                "\"shipment_id\": \"ML-1\"}}";
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
                               
                               setShowLightSensor(false);
                               
                           }else{
                               
                               setShowLightSensor(true);
                           }
                       }else{
                           
                           setShowLightSensor(true);
                       }
                   }
                   
                   
                   if(pAttrName.equalsIgnoreCase("Ultrasound")){
                       
                       Object usThreshNum =  AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.usThresh}");
                       
                       if(usThreshNum != null && usThreshNum instanceof Integer){
                           
                           int usThresh = (Integer) usThreshNum;
                           
                           if(i < usThresh){
                               
                               setShowUltraSensor(false);
                               
                           }else{
                               
                               setShowUltraSensor(true);
                           }
                       }else{
                           
                           setShowUltraSensor(true);
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
                               
                               setShowTempSensor(false);
                               
                           }else{
                               
                               setShowTempSensor(true);
                           }
                       }else{
                           
                           setShowTempSensor(true);
                       }
                   }
                   
                   
                   if(pAttrName.equalsIgnoreCase("Humidity")){
                       
                       Object humidThreshNum =  AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.humidityThresh}");
                       
                       if(humidThreshNum != null && humidThreshNum instanceof Integer){
                           
                           int humidThresh = (Integer) humidThreshNum;
                           
                           if((Double)pVal < humidThresh){
                               
                               setShowHumidSensor(false);
                               
                           }else{
                               
                               setShowHumidSensor(true);
                           }
                       }else{
                           
                           setShowHumidSensor(true);
                       }
                   }
                   
               }
               
           }else if(pVal instanceof String){
               
               mVal = pVal.toString();
           }
       }
       
       return mVal;
    }

    public void openIOSEmailClient(ActionEvent actionEvent) {
        
        // Get the Email Address for the Current select row for Appointment - Account Contact Person.
        String mEmail = (String) AdfmfJavaUtilities.evaluateELExpression("#{viewScope.emerEmailId}");
        // Invoke the java script "launchEmailApp", present in CGMapEmailUtil.js
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "launchDefaultApp", new Object[] {
                                                                  "EMAIL", mEmail});
    }
    
    
    public void openIOSFacetimeClient(ActionEvent actionEvent) {
        // Get the Phone Number for the Current select row for Appointment - Account Contact Person.
        String mTel = (String) AdfmfJavaUtilities.evaluateELExpression("#{viewScope.emerTelNum}");
        // Invoke the java script "launchTelApp", present in CGMapEmailUtil.js
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "launchDefaultApp", new Object[] {
                                                                  "FACETIME" , mTel});
        
    }

    public void setTheftPopupOpen(boolean theftPopupOpen) {
        boolean oldTheftPopupOpen = this.theftPopupOpen;
        this.theftPopupOpen = theftPopupOpen;
        propertyChangeSupport.firePropertyChange("theftPopupOpen", oldTheftPopupOpen, theftPopupOpen);
    }

    public boolean isTheftPopupOpen() {
        return theftPopupOpen;
    }


    public void setTempPopupOpen(boolean tempPopupOpen) {
        boolean oldTempPopupOpen = this.tempPopupOpen;
        this.tempPopupOpen = tempPopupOpen;
        propertyChangeSupport.firePropertyChange("tempPopupOpen", oldTempPopupOpen, tempPopupOpen);
    }

    public boolean isTempPopupOpen() {
        return tempPopupOpen;
    }

    public void setHumidPopupOpen(boolean humidPopupOpen) {
        boolean oldHumidPopupOpen = this.humidPopupOpen;
        this.humidPopupOpen = humidPopupOpen;
        propertyChangeSupport.firePropertyChange("humidPopupOpen", oldHumidPopupOpen, humidPopupOpen);
    }

    public boolean isHumidPopupOpen() {
        return humidPopupOpen;
    }

    public void setLoadunloadPopupOpen(boolean loadunloadPopupOpen) {
        boolean oldLoadunloadPopupOpen = this.loadunloadPopupOpen;
        this.loadunloadPopupOpen = loadunloadPopupOpen;
        propertyChangeSupport.firePropertyChange("loadunloadPopupOpen", oldLoadunloadPopupOpen, loadunloadPopupOpen);
    }

    public boolean isLoadunloadPopupOpen() {
        return loadunloadPopupOpen;
    }

}
