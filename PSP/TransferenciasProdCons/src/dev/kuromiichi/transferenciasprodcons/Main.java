package dev.kuromiichi.transferenciasprodcons;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        BufferCircular buffer = new BufferCircular(200);
        CuentaBanco[] cuentas = new CuentaBanco[100];
        for (int i = 0; i < 100; i++) {
            cuentas[i] = new CuentaBanco();
        }

        int totalOperaciones = 100;

        Productor[] productores = new Productor[3];
        for (int i = 0; i < 3; i++) {
            productores[i] = new Productor(buffer, totalOperaciones, i);
        }

        Consumidor[] consumidores = new Consumidor[10];
        for (int i = 0; i < 10; i++) {
            consumidores[i] = new Consumidor(buffer, totalOperaciones, i);
        }

        ExecutorService execProductores = Executors.newFixedThreadPool(3);
        for (Productor p : productores) {
            execProductores.execute(p);
        }

        ExecutorService execConsumidores = Executors.newFixedThreadPool(10);
        for (Consumidor c : consumidores) {
            execConsumidores.execute(c);
        }

        execProductores.shutdown();
        execConsumidores.shutdown();
        try {
            execProductores.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            execConsumidores.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Productor implements Runnable {
    private final int id;
    private final BufferCircular buffer;
    private final int totalOperaciones;
    private static final AtomicInteger operacionesProducidas = new AtomicInteger(0);

    public Productor(BufferCircular buffer, int totalOperaciones, int id) {
        this.buffer = buffer;
        this.totalOperaciones = totalOperaciones;
        this.id = id;
    }

    @Override
    public void run() {
        while (operacionesProducidas.get() < totalOperaciones) {
            synchronized (buffer) {
                while (buffer.estaLleno()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (operacionesProducidas.get() < totalOperaciones) {
                    int origen = (int) (Math.random() * 100);
                    int destino = (int) (Math.random() * 100);
                    buffer.meter(new Operacion(origen, destino));
                    System.out.println("Productor " + id + ": Producida: " + operacionesProducidas.incrementAndGet() + ": " + origen + " -> " + destino);
                    buffer.notifyAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
        System.out.println("Productor "+id +": se ha terminado de producir");
    }
}

class Consumidor implements Runnable {
    private final int id;
    private final BufferCircular buffer;
    private final int totalOperaciones;
    private static final AtomicInteger operacionesConsumidas = new AtomicInteger(0);

    public Consumidor(BufferCircular buffer, int totalOperaciones, int id) {
        this.buffer = buffer;
        this.totalOperaciones = totalOperaciones;
        this.id = id;
    }

    @Override
    public void run() {
        while (operacionesConsumidas.get() < totalOperaciones) {
            synchronized (buffer) {
                while (buffer.estaVacio()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (operacionesConsumidas.get() < totalOperaciones) {
                    Operacion op = buffer.sacar();
                    System.out.println("Consumidor " + id + ": Consumida: " + operacionesConsumidas.incrementAndGet() + ": " + op.origen + " -> " + op.destino);
                    buffer.notifyAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
        System.out.println("Consumidor " + id + ": se ha terminado de consumir");
    }
}

class BufferCircular {
    private final Operacion[] buffer;
    private int head;
    private int tail;

    public BufferCircular(int capacidad) {
        this.buffer = new Operacion[capacidad];
        this.head = 0;
        this.tail = 0;
    }

    public synchronized boolean estaLleno() {
        return head == tail && buffer[tail] != null;
    }

    public synchronized boolean estaVacio() {
        return Arrays.stream(buffer).allMatch(Objects::isNull);
    }

    public synchronized boolean meter(Operacion op) {
        if (estaLleno()) return false;
        buffer[head] = op;
        head = (head + 1) % buffer.length;
        return true;
    }

    public synchronized Operacion sacar() {
        Operacion op = buffer[tail];
        if (buffer[tail] != null) {
            buffer[tail] = null;
            tail = (tail + 1) % buffer.length;
        }
        return op;
    }
}

class Operacion {
    int origen;
    int destino;

    public Operacion(int origen, int destino) {
        this.origen = origen;
        this.destino = destino;
    }
}
