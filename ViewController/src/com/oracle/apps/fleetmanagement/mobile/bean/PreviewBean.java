package com.oracle.apps.fleetmanagement.mobile.bean;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class PreviewBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PreviewBean() {
        super();
    }
    
    private String item1;
    private String item1Label;
    private String item2;
    private String item2Label;
    private String item3;
    private String item3Label;
    private String item4;
    private String item4Label;
    private String item5;
    private String item5Label;
    private String item6;
    private String item6Label;
    private String item7;
    private String item7Label;
    private String item8;
    private String item8Label;
    private String itemHeading;


    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1Label(String item1Label) {
        this.item1Label = item1Label;
    }

    public String getItem1Label() {
        return item1Label;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2Label(String item2Label) {
        this.item2Label = item2Label;
    }

    public String getItem2Label() {
        return item2Label;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3Label(String item3Label) {
        this.item3Label = item3Label;
    }

    public String getItem3Label() {
        return item3Label;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4Label(String item4Label) {
        this.item4Label = item4Label;
    }

    public String getItem4Label() {
        return item4Label;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5Label(String item5Label) {
        this.item5Label = item5Label;
    }

    public String getItem5Label() {
        return item5Label;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }

    public String getItem6() {
        return item6;
    }

    public void setItem6Label(String item6Label) {
        this.item6Label = item6Label;
    }

    public String getItem6Label() {
        return item6Label;
    }

    public void setItem7(String item7) {
        this.item7 = item7;
    }

    public String getItem7() {
        return item7;
    }

    public void setItem7Label(String item7Label) {
        this.item7Label = item7Label;
    }

    public String getItem7Label() {
        return item7Label;
    }

    public void setItem8(String item8) {
        this.item8 = item8;
    }

    public String getItem8() {
        return item8;
    }

    public void setItem8Label(String item8Label) {
        this.item8Label = item8Label;
    }

    public String getItem8Label() {
        return item8Label;
    }

    public void setItemHeading(String itemHeading) {
        String oldItemHeading = this.itemHeading;
        this.itemHeading = itemHeading;
        propertyChangeSupport.firePropertyChange("itemHeading", oldItemHeading, itemHeading);
    }

    public String getItemHeading() {
        return itemHeading;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
