package com.chortitzer.cin.model.bascula;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.persistence.Transient;

public class AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Transient
    public BooleanProperty selected = new SimpleBooleanProperty();

    @Transient
    public Boolean getSelected() {
        return this.selected.get();
    }

    public void setSelected(Boolean selected) {
        this.selected.set(selected);
    }
    @Transient
    public BooleanProperty selectedProperty(){
        return this.selected;
    }

}
