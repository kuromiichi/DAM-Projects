package dev.kuromiichi.transferencias;

import java.util.concurrent.locks.ReentrantLock;

public class CuentaBanco {
    private int saldo;
    private final ReentrantLock lock = new ReentrantLock();

    public CuentaBanco() {
        this.saldo = 100;
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

    public int getSaldo() {
        return this.saldo;
    }
}
