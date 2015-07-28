package com.oracle.apps.fleetmanagement.mobile.bean;

import com.oracle.apps.fleetmanagement.mobile.model.ManagedBean;
import com.oracle.apps.fleetmanagement.mobile.util.JSONParserUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
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
    
    public TrackShipmentRailBean() {
        super();
        qos = 2;
        sampleClient = null;
        broker = "tcp://m11.cloudmqtt.com:16385";
        password = "1MSI1ktJ81aY";
        uri = "tcp://m11.cloudmqtt.com:16385";
        username = "swlrwhmx";
    }
    
    public void init_trackingShipkment(){
        System.out.println("Test Message");
        //subscribeToMQTT123();
    }
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    MqttClient sampleClient;
    int qos;
    String broker;   
    String password;
    String uri;
    String username;
    String topic = "open_door";
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


    public void setLatitude(String latitude) {
        String oldLatitude = this.latitude;
        this.latitude = latitude;
        propertyChangeSupport.firePropertyChange("latitude", oldLatitude, latitude);
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        String oldLongitude = this.longitude;
        this.longitude = longitude;
        propertyChangeSupport.firePropertyChange("longitude", oldLongitude, longitude);
    }

    public String getLongitude() {
        return longitude;
    }

    public void setTemperature(String temperature) {
        String oldTemperature = this.temperature;
        this.temperature = temperature;
        propertyChangeSupport.firePropertyChange("temperature", oldTemperature, temperature);
    }

    public String getTemperature() {
        return temperature;
    }

    public void setHumidity(String humidity) {
        String oldHumidity = this.humidity;
        this.humidity = humidity;
        propertyChangeSupport.firePropertyChange("humidity", oldHumidity, humidity);
    }

    public String getHumidity() {
        return humidity;
    }

    public void setUltrasonic(String ultrasonic) {
        String oldUltrasonic = this.ultrasonic;
        this.ultrasonic = ultrasonic;
        propertyChangeSupport.firePropertyChange("ultrasonic", oldUltrasonic, ultrasonic);
    }

    public String getUltrasonic() {
        return ultrasonic;
    }

    public void setLight(String light) {
        String oldLight = this.light;
        this.light = light;
        propertyChangeSupport.firePropertyChange("light", oldLight, light);
    }

    public String getLight() {
        return light;
    }

    public void setCustomerId(String customerId) {
        String oldCustomerId = this.customerId;
        this.customerId = customerId;
        propertyChangeSupport.firePropertyChange("customerId", oldCustomerId, customerId);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setShipmentId(String shipmentId) {
        String oldShipmentId = this.shipmentId;
        this.shipmentId = shipmentId;
        propertyChangeSupport.firePropertyChange("shipmentId", oldShipmentId, shipmentId);
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
                String defaultPublish = "{\"fleet\": {\"customer_id\": 10, \"sensor\": {\"light\": 300, \"long\": -122.2646400,\n" + 
                "\"humidity\": 47.0, \"ultrasound\": 6, \"lat\": 37.5311940, \"temperature\": 78.1},\n" + 
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
//        HashMap mPayload = parseJSONObjectFromQueue(new String(mqttMessage.getPayload()));
//        if(mPayload != null && mPayload.size() > 0){
//            setCustomerId((String)mPayload.get("CustomerId"));
//            setHumidity((String)mPayload.get("Humidity"));
//            setLatitude((String)mPayload.get("Latitude"));
//            setLongitude((String)mPayload.get("Longitude"));
//            setLight((String)mPayload.get("light"));
//            setShipmentId((String)mPayload.get("ShipmentId"));
//            setTemperature((String)mPayload.get("Temperature"));
//            setUltrasonic((String)mPayload.get("Ultrasound"));
//        }
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
        
        HashMap mShipmentValues = new HashMap();
        
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
                 
                 Map val1 = toMap(mfleetValue);
                 Map val2 = toMap(mJSONObject);
                 
                 if(val1 != null){
                     
                     mShipmentValues.put("CustomerId", val1.get("customer_id"));
                     mShipmentValues.put("ShipmentId", val1.get("shipment_id")); 
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
}
