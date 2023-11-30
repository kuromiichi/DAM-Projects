package dev.kuromiichi.transferencias;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numCuentas = 100;

        try (ExecutorService executor = Executors.newFixedThreadPool(16)) {
            CuentaBanco[] cuentas = new CuentaBanco[numCuentas];
            for (int i = 0; i < cuentas.length; i++) {
                cuentas[i] = new CuentaBanco();
            }

            int numTransferencias = 5_000_000;
            AtomicInteger transferenciasRealizadas = new AtomicInteger(0);
            IntStream.range(0, numTransferencias).forEach(t -> executor.execute(() -> {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int origen = random.nextInt(numCuentas);
                int destino = random.nextInt(numCuentas);
                cuentas[origen].transferir(cuentas[destino]);
                transferenciasRealizadas.incrementAndGet();
                System.out.println(origen + " -> " + destino);
            }));

            executor.shutdown();
            boolean res = executor.awaitTermination(1, TimeUnit.MINUTES);

            if (res) {
                System.out.println("Transferencias finalizadas");
            } else {
                System.out.println("Transferencias no finalizadas (timeout)");
            }

            System.out.println("Transferencias realizadas: " + transferenciasRealizadas.get());

            if (dineroCorrecto(cuentas)) {
                System.out.println("Dinero correcto");
            } else {
                System.out.println("Dinero incorrecto");
            }
        }
    }

    private static boolean dineroCorrecto(CuentaBanco[] cuentas) {
        int total = 0;
        int esperado = 100 * cuentas.length;
        for (CuentaBanco cuenta : cuentas) {
            total += cuenta.getSaldo();
        }
        return total == esperado;
    }
}
