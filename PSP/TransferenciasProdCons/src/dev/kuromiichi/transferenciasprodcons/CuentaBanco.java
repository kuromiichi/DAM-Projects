package dev.kuromiichi.transferenciasprodcons;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CuentaBanco {
    private int saldo;
    private ReentrantLock lock;

    public CuentaBanco(){
        saldo = 100;
        lock = new ReentrantLock();
    }

    public void transferir(CuentaBanco destino) {
        if (lock.tryLock()) {
            if (this.saldo >= 100) {
                if (destino.lock.tryLock()) {
                    this.saldo -= 100;
                    destino.saldo += 100;
                    destino.lock.unlock();
                }
            }
            lock.unlock();
        }
    }

    public int getSaldo(){
        return this.saldo;
    }
}
