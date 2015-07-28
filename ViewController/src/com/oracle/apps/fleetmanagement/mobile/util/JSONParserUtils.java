package com.oracle.apps.fleetmanagement.mobile.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

import oracle.ateam.sample.mobile.v2.persistence.manager.RestJSONPersistenceManager;

public class JSONParserUtils extends RestJSONPersistenceManager {

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
                Object fleetValue = getJSONElement(topNode, "fleet", false);
                
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
