import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
public class PropuestaSolucionC7_IvanRoncoCebadera {
    private static final int DINERO = 100;
    public static void main(String[] args){

        final int numeroOperaciones = 500;
        final int tamañoBuffer = 2000;
        final int numeroProductores = 3;
        final int numeroConsumidores = 10;
        final int tamañoCuentas = 500;

        Cuenta[] cuentasDisponibles = new Cuenta[tamañoCuentas];
        for (int i = 0; i<tamañoCuentas; i++){
            cuentasDisponibles[i] = new Cuenta(DINERO, i+1);
        }
        Transferencias transferencias = new Transferencias(tamañoBuffer, cuentasDisponibles, numeroOperaciones);

        ExecutorService service = Executors.newFixedThreadPool(numeroConsumidores+numeroProductores);

        Productor[] productores = new Productor[numeroProductores];
        for (int i = 0; i< numeroProductores; i++){
            productores[i] = new Productor(transferencias, i+1);
            service.submit(productores[i]);
        }

        Consumidor[] consumidores = new Consumidor[numeroConsumidores];
        for (int i = 0; i< numeroConsumidores; i++){
            consumidores[i] = new Consumidor(transferencias, i+1);
            service.submit(consumidores[i]);
        }

        try{
            service.shutdown();
            service.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Fin de la simulacion!!!");

    }

    private static class Transferencias{
        private Vector<Transferencia> bufferCircular;
        private int tamañoBuffer;
        private int head;  //posición de inserción
        private int tail;  //posición de extracción
        private Cuenta[] cuentas;
        private int operacionesMaximas;
        private int contadorOperacionesPorMeter;
        private int contadorOperacionesPorEjecutar;
        private ExecutorService transferenciasService;

        public Transferencias(int tamañoBuffer, Cuenta[] cuentas, int operacioneMaximas) {
            this.tamañoBuffer = tamañoBuffer;
            bufferCircular = new Vector<>(tamañoBuffer);
            head = 0;
            tail = -1;
            this.cuentas = cuentas;
            this.operacionesMaximas = operacioneMaximas;
            contadorOperacionesPorMeter = 0;
            contadorOperacionesPorEjecutar = 0;
            transferenciasService = Executors.newFixedThreadPool(tamañoBuffer);
        }

        public synchronized void ejecutarTransferencia(Consumidor consumidor) {
            try {
                Transferencia transferencia = sacarTranferencia();
                while (transferencia == null && quedanOperacionesPorEjecutar()) { //Si no añado el "y si quedan operaciones por ejecutar", el programa nunca cierra!!
                    wait();
                    transferencia = sacarTranferencia();
                }
                if(!quedanOperacionesPorEjecutar()){
                    transferenciasService.shutdownNow();
                }else{
                    System.out.println("El consumidor: " + consumidor + ", va a realizar la transferencia: " + transferencia);
                    transferenciasService.submit(transferencia);
                    contadorOperacionesPorEjecutar++;
                    System.out.println(mostrar());
                    System.out.println(contadorTransferencias());
                    System.out.println();
                    notifyAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        public synchronized void añadirTransferencia(Productor productor) {
            try {
                while (estaLleno())
                    this.wait();
                if(quedanOperacionesPorMeter()){
                    Transferencia transferencia = crearTranferencia();
                    System.out.println("El productor: " + productor + ", va a introducir la transferencia: " + transferencia);
                    meterTransferencia(transferencia);
                    contadorOperacionesPorMeter++;
                    System.out.println(mostrar());
                    System.out.println(contadorTransferencias());
                    System.out.println();
                }
                notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private Transferencia sacarTranferencia(){
            if(!bufferCircular.isEmpty() ){
                return bufferCircular.remove(0);
            }else{
                return null;
            }
        }

        private void meterTransferencia(Transferencia transferencia){
            if(bufferCircular.size() < tamañoBuffer){
                bufferCircular.add(transferencia);
            }

        }

        private boolean estaLleno() {
            return bufferCircular.size() == tamañoBuffer;
        }
        private String  mostrar() {
            return bufferCircular.toString();
        }
        private int contadorTransferencias() { return bufferCircular.size(); }
        public synchronized boolean quedanOperacionesPorMeter(){return contadorOperacionesPorMeter < operacionesMaximas;}
        public synchronized boolean quedanOperacionesPorEjecutar(){return contadorOperacionesPorEjecutar < operacionesMaximas;}

        private Transferencia crearTranferencia() {
            Random rng = new Random(System.currentTimeMillis());
            int primeraCuenta;
            int segundaCuenta;
            do{
                primeraCuenta = rng.nextInt(0,  cuentas.length);
                segundaCuenta = rng.nextInt(0,  cuentas.length);
            }while(primeraCuenta == segundaCuenta);

            return new Transferencia(cuentas[primeraCuenta], cuentas[segundaCuenta], DINERO);
        }
    }

    private static class Consumidor implements Runnable{
        private Transferencias transferencias;
        private int id;

        public Consumidor(Transferencias transferencias , int id) {
            this.transferencias = transferencias;
            this.id = id;
        }

        @Override
        public void run() {
            while(transferencias.quedanOperacionesPorEjecutar()){
                transferencias.ejecutarTransferencia(this);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public String toString() {
            return "Consumidor{" +
                    "id=" + id +
                    '}';
        }
    }

    private static class Productor implements Runnable{
        private Transferencias transferencias;
        private int id;

        public Productor(Transferencias transferencias, int id) {
            this.transferencias = transferencias;
            this.id = id;
        }

        @Override
        public void run() {
            while(transferencias.quedanOperacionesPorMeter()){
                transferencias.añadirTransferencia(this);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public String toString() {
            return "Productor{" +
                    "id=" + id +
                    '}';
        }
    }

    private static class Transferencia implements Runnable{

        private final Cuenta cuenta1;
        private final Cuenta cuenta2;
        private int cantidad;
        private int id;
        private static int contador = 0;
        private static double impuestos;

        public Transferencia(Cuenta cuenta1, Cuenta cuenta2, int cantidad) {
            this.cuenta1 = cuenta1;
            this.cuenta2 = cuenta2;
            this.cantidad = cantidad;
            this.id = ++contador;
            this.impuestos = 0.0;
        }

        @Override
        public void run() {
            if(cuenta1.id < cuenta2.id){ //Aplicamos un poco de orden, intentando blockear primero el del id menor
                synchronized (cuenta1) {
                    synchronized (cuenta2){
                        operar();
                    }
                }
            }else{
                synchronized (cuenta2) {
                    synchronized (cuenta1){
                        operar();
                    }
                }
            }
        }

        private void operar() {
            cuenta2.saldo -= cantidad;
            cuenta2.numeroOperacionesSacar++;
            cuenta1.saldo += cantidad;
            cuenta1.numeroOperacionesAñadir++;

            cuenta2.saldo -= 0.01;
            cuenta1.saldo -= 0.01;

            impuestos += 0.02;
        }

        @Override
        public String toString() {
            return "Transferencia{" +
                    "id=" + id +
                    '}';
        }
    }

    private static class Cuenta{
        private int saldo;
        private int saldoInicial;
        private int numeroOperacionesAñadir;
        private int numeroOperacionesSacar;
        private int id;

        public Cuenta(int saldo, int id) {
            this.saldo = saldo;
            this.id = id;
            this.saldoInicial = saldo;
            this.numeroOperacionesAñadir = 0;
            this.numeroOperacionesSacar = 0;
        }

        @Override
        public String toString() {
            return "Cuenta"+id+"{" +
                    "saldoFinal=" + saldo +
                    ", saldoInicial=" + saldoInicial +
                    ", numeroOperacionesAñadir=" + numeroOperacionesAñadir +
                    ", numeroOperacionesSacar=" + numeroOperacionesSacar +
                    '}';
        }
    }
}
