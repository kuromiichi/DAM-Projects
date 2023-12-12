package ejercicio2;

import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio2 {
    public static void main(String[] args) {
        final int NUM_COCHES = 200;
        final int INTERVALO_SEMAFORO = 300;

        Semaforo semaforo = new Semaforo(INTERVALO_SEMAFORO);
        Cruce cruce = new Cruce(semaforo, NUM_COCHES);

        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            // aquí haría el executorService.execute() del productor de coches

            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static class Semaforo {
        boolean verdeParaNS;
        long intervalo;
        ScheduledExecutorService scheduler;
        Runnable cambiarSemaforo = () -> {
            verdeParaNS = !verdeParaNS;
        };

        public Semaforo(long intervalo) {
            this.intervalo = intervalo;
            scheduler.scheduleAtFixedRate(cambiarSemaforo, 0L, intervalo, TimeUnit.NANOSECONDS);
        }
    }

    public static class Cruce {
        Vector<Coche> carreteraNS = new Vector<>();
        Vector<Coche> carreteraEO = new Vector<>();
        Vector<Coche> cochesCruzando = new Vector<>(5);
        Semaforo semaforo;
        int cochesMaximos;
        int cochesTotales = 0;

        public Cruce(Semaforo semaforo, int cochesMaximos) {
            this.semaforo = semaforo;
            this.cochesMaximos = cochesMaximos;
        }

        public synchronized boolean totalAlcanzado() {
            return cochesTotales == 200;
        }

        public synchronized void meterCoche() {
            try {
                while (cochesCruzando.size() != 0)
                    this.wait();
                if (!totalAlcanzado()) {
                    Coche coche = crearCoche();
                    cochesTotales++;
                    if (coche.sentido.equals("NS"))
                        carreteraNS.add(coche);
                    else carreteraEO.add(coche);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Coche crearCoche() {
            return new Coche(cochesTotales + 1);
        }

    }

    public static class Coche {
        int id;
        String sentido;
        boolean esperandoEnCruce = false;

        public Coche(int id) {
            this.id = id;
            this.sentido = ThreadLocalRandom.current().nextBoolean() ? "NS" : "EO";
        }
    }

    public static class ProductorCoches implements Runnable {
        Cruce cruce;

        public ProductorCoches(Cruce cruce) {
            this.cruce = cruce;
        }

        @Override
        public void run() {
            while (!cruce.totalAlcanzado()) {
                cruce.meterCoche();
            }
        }
    }
}
