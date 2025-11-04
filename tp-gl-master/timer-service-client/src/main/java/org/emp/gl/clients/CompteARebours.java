package org.emp.gl.timer.service.client;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {

    String name;
    int nombre;
    TimerService timerService;

    public CompteARebours(String name, int nombre, TimerService timerService) {
        this.name = name;
        this.nombre = nombre;
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);
        System.out.println("Compte à rebours " + name + " initialisé avec " + nombre + " secondes!");
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if ((nombre > 0) && (prop.equals(TimerChangeListener.SECONDE_PROP))) {
            this.nombre--;
            System.out.println("Compte à rebours " + name + " affiche " + timerService.getHeures()
                    + ":" + timerService.getMinutes() + ":" + timerService.getSecondes() + " --> " + nombre);
            
            // Désinscription quand le compte arrive à 0 (étape d.2)
            if (nombre == 0) {
                System.out.println("Compte à rebours " + name + " TERMINÉ! Désinscription...");
                timerService.removeTimeChangeListener(this);
            }
        }
    }

    // Implémentation de PropertyChangeListener (étape e)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        propertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }

    // Getters pour accéder à l'état
    public int getNombre() {
        return nombre;
    }

    public String getName() {
        return name;
    }
}