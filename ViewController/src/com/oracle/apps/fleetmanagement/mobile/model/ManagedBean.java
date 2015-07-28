package com.oracle.apps.fleetmanagement.mobile.model;

import java.util.logging.Level;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.Utility;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import oracle.adfmf.util.logging.Trace;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ManagedBean implements MqttCallback{
    
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

    public ManagedBean() {
        super();
        qos = 2;
        sampleClient = null;
        broker = "tcp://m11.cloudmqtt.com:16385";
        password = "1MSI1ktJ81aY";
        uri = "tcp://m11.cloudmqtt.com:16385";
        username = "swlrwhmx";
        System.out.println("Test Message");
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
    
    public void subscribe(ActionEvent x){
        if(connect(topic)){
            MqttTopic mqttTopic = sampleClient.getTopic(topic);
            try {
                int subQoS = 0;
                sampleClient.subscribe(topic,subQoS);
                    publish(topic, "10");
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
        setInputMessage(new String(mqttMessage.getPayload()));
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
}
