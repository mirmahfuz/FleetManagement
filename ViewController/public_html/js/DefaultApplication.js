var DEFAULT_APPLE_MAP_URL = "http://maps.apple.com/?q=";
var APPLE_MAP_DESTINATION_URL = "http://maps.apple.com/?daddr=";
var APPLE_MAP_SOURCE_URL = "&saddr=Current Location";
var DEFAULT_EMAIL_URL = "mailto:";
var DEFAULT_TEL_URL = "tel:";
var DEFAULT_MAP_VAL = "MAPS";
var DEFAULT_EMAIL_VAL = "EMAIL";
var DEFAULT_TEL_VAL = "TEL";
var DEFAULT_FACETIME_VAL = "FACETIME";
var DEFAULT_FACETIME_URL = "facetime-audio:";

(function () {

    // Function to launch default apps in the IOS / andriod Device.
    launchDefaultApp = function () {
        // Get the arguments, passed from the API.
        var mArgs = arguments;
        // Get the Type of App
        var mType = mArgs[0];
        // Get the coressponding value
        var mVal = mArgs[1];
        // Check if Type is not null
        
        if(mType != null){
        
            // Launch Map App
           if(DEFAULT_MAP_VAL == mType){
                // Check if value is not null
                if(mVal != null){
                
                    window.location=APPLE_MAP_DESTINATION_URL + mVal + APPLE_MAP_SOURCE_URL;
                    
                }else{
                
                    window.location=DEFAULT_APPLE_MAP_URL;
                }
                
            // Launch Email App    
            }else if(DEFAULT_EMAIL_VAL == mType){
            
                window.location=DEFAULT_EMAIL_URL + mVal;
                
            // Launch Dialer for Telephone.    
            }else if(DEFAULT_TEL_VAL == mType){
            
                window.location=DEFAULT_TEL_URL + mVal;
            
            // Launch Facetime for iPad.    
            }else if(DEFAULT_FACETIME_VAL == mType){
            
                window.location=DEFAULT_FACETIME_URL + mVal;
            } 
        }
        
    }

})();