package org.emp.gl.timer.service.client;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;

public class Horloge implements TimerChangeListener {
    private String name;
    private TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        System.out.println("Horloge " + name + " initialized!");
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            afficherHeure();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        propertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }

    public void afficherHeure() {
        int heures = timerService.getHeures();
        int minutes = timerService.getMinutes();
        int secondes = timerService.getSecondes();
        System.out.printf("%s - %02d:%02d:%02d%n", name, heures, minutes, secondes);
    }

    public void stop() {
        if (timerService != null) {
            timerService.removeTimeChangeListener(this);
        }
    }
}