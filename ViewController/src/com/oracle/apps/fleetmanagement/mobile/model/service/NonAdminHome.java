package com.oracle.apps.fleetmanagement.mobile.model.service;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class NonAdminHome {
    boolean renderComplete;
    boolean renderInTransit;
    boolean renderLoaded;
    boolean renderProcessed;
    boolean renderSubmitted;

    String receiverCity;

 
    String receiverState;
    String senderCity;
    String senderState;
    String shipmentHeading;
    String date;
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public NonAdminHome() throws AdfException{
        super();
        renderComplete = false;
        renderInTransit = false;
        renderLoaded = false;
        renderProcessed = false;
        renderSubmitted = true;
        
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        
        ValueExpression ve;
        try{
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverState}", String.class);
        receiverState = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        }catch(Exception e){
            e.printStackTrace();
            //throw new AdfException(e);
        }finally{
            if(receiverState == null || receiverState.trim().length() == 0)
            receiverState = "CA";
        }
        
        try{
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.receiverCity}", String.class);
        receiverCity = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        }catch(Exception e){
            e.printStackTrace();
           // throw new AdfException(e);
        }finally{
            if(receiverCity == null || receiverCity.trim().length() == 0)
                receiverCity = "San Francisco";
        }
        
        try{
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderCity}", String.class);
        senderCity = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        }catch(Exception e){
            e.printStackTrace();
            //throw new AdfException(e);
        }finally{
            if(senderCity == null || senderCity.trim().length() == 0)
            senderCity = "Pittsburgh";
        }
        
        try{
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SenderDetail.senderState}", String.class);
        senderState = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        }catch(Exception e){
            e.printStackTrace();
            //throw new AdfException(e);
        }finally{
            if(senderState == null || senderState.trim().length() == 0)
                senderState = "PA"; 
        }
        
        try{
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.shipmentHeading}", String.class);
        shipmentHeading = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        }catch(Exception e){
            e.printStackTrace();
            //throw new AdfException(e);
        }finally{
            if(shipmentHeading == null || shipmentHeading.trim().length() == 0){
                shipmentHeading = "Last Shipment Overview"; 
            }
        }
    }

    public void setRenderComplete(boolean renderComplete) {
        boolean oldRenderComplete = this.renderComplete;
        this.renderComplete = renderComplete;
        propertyChangeSupport.firePropertyChange("renderComplete", oldRenderComplete, renderComplete);
    }

    public boolean isRenderComplete() {
        return renderComplete;
    }

    public void setRenderInTransit(boolean renderInTransit) {
        boolean oldRenderInTransit = this.renderInTransit;
        this.renderInTransit = renderInTransit;
        propertyChangeSupport.firePropertyChange("renderInTransit", oldRenderInTransit, renderInTransit);
    }

    public boolean isRenderInTransit() {
        return renderInTransit;
    }

    public void setRenderLoaded(boolean renderLoaded) {
        boolean oldRenderLoaded = this.renderLoaded;
        this.renderLoaded = renderLoaded;
        propertyChangeSupport.firePropertyChange("renderLoaded", oldRenderLoaded, renderLoaded);
    }

    public boolean isRenderLoaded() {
        return renderLoaded;
    }

    public void setRenderProcessed(boolean renderProcessed) {
        boolean oldRenderProcessed = this.renderProcessed;
        this.renderProcessed = renderProcessed;
        propertyChangeSupport.firePropertyChange("renderProcessed", oldRenderProcessed, renderProcessed);
    }

    public boolean isRenderProcessed() {
        return renderProcessed;
    }

    public void setRenderSubmitted(boolean renderSubmitted) {
        boolean oldRenderSubmitted = this.renderSubmitted;
        this.renderSubmitted = renderSubmitted;
        propertyChangeSupport.firePropertyChange("renderSubmitted", oldRenderSubmitted, renderSubmitted);
    }

    public boolean isRenderSubmitted() {
        return renderSubmitted;
    }
    public void setReceiverCity(String receiverCity) {
        String oldReceiverCity = this.receiverCity;
        this.receiverCity = receiverCity;
        propertyChangeSupport.firePropertyChange("receiverCity", oldReceiverCity, receiverCity);
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverState(String receiverState) {
        String oldReceiverState = this.receiverState;
        this.receiverState = receiverState;
        propertyChangeSupport.firePropertyChange("receiverState", oldReceiverState, receiverState);
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setSenderCity(String senderCity) {
        String oldSenderCity = this.senderCity;
        this.senderCity = senderCity;
        propertyChangeSupport.firePropertyChange("senderCity", oldSenderCity, senderCity);
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderState(String senderState) {
        String oldSenderState = this.senderState;
        this.senderState = senderState;
        propertyChangeSupport.firePropertyChange("senderState", oldSenderState, senderState);
    }

    public String getSenderState() {
        return senderState;
    }

    public void setShipmentHeading(String shipmentHeading) {
        String oldShipmentHeading = this.shipmentHeading;
        this.shipmentHeading = shipmentHeading;
        propertyChangeSupport.firePropertyChange("shipmentHeading", oldShipmentHeading, shipmentHeading);
    }

    public String getShipmentHeading() {
        return shipmentHeading;
    }

    public void setDate(String date) {
        String oldDate = this.date;
        this.date = date;
        propertyChangeSupport.firePropertyChange("date", oldDate, date);
    }

    public String getDate() {
        return date;
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

    public void refreshCheckMark(ActionEvent x){
        boolean renderCompletetemp = false;
        boolean renderInTransittemp = false;
        boolean renderLoadedtemp = false;
        boolean renderProcessedtemp = false;
        boolean renderSubmittedtemp = false;
        
        if(renderSubmitted){
            renderCompletetemp = false;
            renderInTransittemp = false;
            renderLoadedtemp = false;
            renderProcessedtemp = true;
            renderSubmittedtemp = false;
        }else if(renderProcessed){
            renderCompletetemp = false;
            renderInTransittemp = false;
            renderLoadedtemp = true;
            renderProcessedtemp = false;
            renderSubmittedtemp = false;
            
        }else if(renderLoaded){
            renderCompletetemp = false;
            renderInTransittemp = true;
            renderLoadedtemp = false;
            renderProcessedtemp = false;
            renderSubmittedtemp = false;
            
        }else if(renderInTransit){
            renderCompletetemp = true;
            renderInTransittemp = false;
            renderLoadedtemp = false;
            renderProcessedtemp = false;
            renderSubmittedtemp = false;
            
        }else if(renderComplete){
            renderCompletetemp = false;
            renderInTransittemp = false;
            renderLoadedtemp = false;
            renderProcessedtemp = false;
            renderSubmittedtemp = true;
        }
       
        this.setRenderComplete(renderCompletetemp);
        this.setRenderInTransit(renderInTransittemp);
        this.setRenderLoaded(renderLoadedtemp);
        this.setRenderProcessed(renderProcessedtemp);
        this.setRenderSubmitted(renderSubmittedtemp);
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
