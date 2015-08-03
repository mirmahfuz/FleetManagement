# FleetManagement
Online Solution used for asset tracking.
For this project we used following sensors -
1. Light Sensor -- For tracking Shipment Load / Unload
2. Temperature & Humidity Sensor -- For traking temperature & humidity for certain types of items
3. UltraSound Sensor -- For theft protection.
4. Location Sensor -- For tracking asset when in transit

We used CloudMQTT for sending sensor data to the Ipad Hybird App. We used Oracle MAF for developing this app.
Using this app the client can create new shipment and then track his/her shipment.
Using this app the admin can track all the shipments and get notifications whenever there is any state change at the sensor site.
