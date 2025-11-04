package org.emp.gl.timer.service;

import java.util.List;

public interface TimerService extends TimeChangeProvider {
    void start();
    void stop();
    List<TimerChangeListener> getListeners();

    int getHeures();
    int getMinutes();
    int getSecondes();
    int getDixiemes();
}