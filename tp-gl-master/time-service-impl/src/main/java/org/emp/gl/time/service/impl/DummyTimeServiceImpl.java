package org.emp.gl.time.service.impl;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DummyTimeServiceImpl implements TimerService {
    private final PropertyChangeSupport pcs;
    private final List<TimerChangeListener> listeners;
    private Timer timer;
    private int heures;
    private int minutes;
    private int secondes;
    private int dixiemes;

    public DummyTimeServiceImpl() {
        pcs = new PropertyChangeSupport(this);
        listeners = new ArrayList<>();
        initTime();
    }

    private void initTime() {
        heures = 0;
        minutes = 0;
        secondes = 0;
        dixiemes = 0;
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        listeners.add(pl);
        pcs.addPropertyChangeListener(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        listeners.remove(pl);
        pcs.removePropertyChangeListener(pl);
    }

    @Override
    public List<TimerChangeListener> getListeners() {
        return new ArrayList<>(listeners);
    }

    @Override
    public void start() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        }, 100, 100);
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void updateTime() {
        int oldDixiemes = dixiemes;
        int oldSecondes = secondes;
        int oldMinutes = minutes;
        int oldHeures = heures;

        dixiemes = (dixiemes + 1) % 10;
        if (dixiemes == 0) {
            secondes = (secondes + 1) % 60;
            if (secondes == 0) {
                minutes = (minutes + 1) % 60;
                if (minutes == 0) {
                    heures = (heures + 1) % 24;
                }
            }
        }

        if (oldDixiemes != dixiemes) {
            pcs.firePropertyChange(TimerChangeListener.DIXIEME_DE_SECONDE_PROP, oldDixiemes, dixiemes);
        }
        if (oldSecondes != secondes) {
            pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, oldSecondes, secondes);
        }
        if (oldMinutes != minutes) {
            pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, oldMinutes, minutes);
        }
        if (oldHeures != heures) {
            pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, oldHeures, heures);
        }
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }

    @Override
    public int getDixiemes() {
        return dixiemes;
    }
}