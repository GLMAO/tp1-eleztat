package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;

public interface TimerChangeListener extends PropertyChangeListener {
    String DIXIEME_DE_SECONDE_PROP = "dixieme";
    String SECONDE_PROP = "seconde";
    String MINUTE_PROP = "minute";
    String HEURE_PROP = "heure";

    void propertyChange(String prop, Object oldValue, Object newValue);
}