package br.udesc.ceavi.dsd.model.casa;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public class CasaSemaforo extends Casa {

    private Semaphore mutex;

    public CasaSemaforo() {
        super();
        mutex = new Semaphore(1);
    }
}
