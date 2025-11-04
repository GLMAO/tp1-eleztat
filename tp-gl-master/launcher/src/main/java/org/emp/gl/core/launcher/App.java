-gl-masterpackage org.emp.gl.core.launcher;
import org.emp.gl.timer.service.client.Horloge;
import org.emp.gl.timer.service.client.CompteARebours;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        TimerService timerService = new DummyTimeServiceImpl();

        // (c.4) Instancier plusieurs Horloges
        Horloge horloge1 = new Horloge("Horloge-1", timerService);
        Horloge horloge2 = new Horloge("Horloge-2", timerService);

        // (d.1) Instancier CompteARebours avec paramètre 5
        CompteARebours compte1 = new CompteARebours("Compte-1", 5, timerService);

        // (d.3) Instancier 10 CompteARebours avec valeurs aléatoires 10-20
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int valeurInitiale = 10 + random.nextInt(11); // 10-20
            new CompteARebours("Compte-" + (i + 2), valeurInitiale, timerService);
        }

        // Démarrer le timer
        timerService.start();

        // Laisser tourner pendant 30 secondes
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Arrêter le timer
        timerService.stop();
        System.out.println("Programme terminé.");
    }
}