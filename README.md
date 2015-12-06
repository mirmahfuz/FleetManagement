# FleetManagement
Online Solution used for asset tracking. Using this application, the client can create new shipment, add various services to the shipments and then track his/her shipment. This application also has an admin mode. The admin can track all the shipments and get notifications whenever there is any state change at the sensor site. Every sensor has preset theshold value, which is set based on client service request.

# Types of Sensors Used for this project  
1. Light Sensor : For tracking Shipment Load / Unload
2. Temperature & Humidity Sensor : For traking temperature & humidity for certain types of items
3. UltraSound Sensor : For theft protection.
4. Location Sensor : For tracking asset when in transit

# Technologies used
1. CloudMQTT for sending sensor data to the Ipad Hybird App. 
2. Oracle MAF (Mobile Application Framework)
