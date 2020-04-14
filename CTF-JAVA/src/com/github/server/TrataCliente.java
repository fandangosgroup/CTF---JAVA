package com.github.server;

import java.io.InputStream;
import java.util.Scanner;

public class TrataCliente implements Runnable {

	private int id;
    private InputStream cliente;
    private Server servidor;

    public TrataCliente(InputStream cliente, int id, Server servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
        this.id = id;
    }

    public void run() {
        // quando chegar uma msg, distribui pra todos
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine()) {
            servidor.distribuiMensagem(s.nextLine(), this.id);
        }
        s.close();
    }
}