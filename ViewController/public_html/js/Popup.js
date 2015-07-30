(function () {
    showTempPopup = function () {
        var element = document.getElementById("showtemp");
        customTriggerEvent(element, "touchstart");
        customTriggerEvent(element, "touchend");
    }

    closeTempPopup = function () {
        var element = document.getElementById("closetemp");
        customTriggerEvent(element, "touchstart");
        customTriggerEvent(element, "touchend");
    }
    
    showHumidPopup = function () {
        var element = document.getElementById("showhumid");
        customTriggerEvent(element, "touchstart");
        customTriggerEvent(element, "touchend");
    }

    closeHumidPopup = function () {
        var element = document.getElementById("closehumid");
        customTriggerEvent(element, "touchstart");
        customTriggerEvent(element, "touchend");
    }

    closePromotionPopup = function () {
        var element = document.getElementById("promotion:closePromotion");
        customTriggerEvent(element, "touchstart");
        customTriggerEvent(element, "touchend");
        setTimeout(function(){showConfirmationPopup();}, 500);
        setTimeout(function(){closeConfirmationPopup();}, 2000);
    }
    var customTriggerEvent = function (eventTarget, eventType, triggerExtra) {
        var evt = document.createEvent("HTMLEvents");
        evt.initEvent(eventType, true, true);
        evt.view = window;
        evt.altKey = false;
        evt.ctrlKey = false;
        evt.shiftKey = false;
        evt.metaKey = false;
        evt.keyCode = 0;
        evt.charCode = 'a';
        if (triggerExtra != null)
            evt.triggerExtra = triggerExtra;
        eventTarget.dispatchEvent(evt);
    };

})();