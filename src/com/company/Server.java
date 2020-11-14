package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ServerSocket server;
    private int counter;

    public Server (int port)
    {
        this.counter=0;

        this.port = port;
        if(!startServer())
        {
            System.err.println("Errore durante la creazione del Server");
        }
    }

    private boolean startServer()
    {
        try
        {
            server = new ServerSocket(port);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return false;
        }
        System.out.println("Server creato con successo!");
        return true;
    }

    public void runServer()
    {
        while (true)
        {
            try
            {
                // Il server resta in attesa di una richiesta
                System.out.println("Server in attesa di richieste");
                Socket s1 = server.accept();
                this.counter++;
                System.out.println("Un client si e' connesso: utente "+this.counter);

                // Il server legge la stringa arrivata
                InputStream ic = s1.getInputStream();
                BufferedReader dic = new BufferedReader( new InputStreamReader(ic) );
                String stringa = dic.readLine();

                /*
                -- Ricava lo stream di output associate al socket
                -- e definisce una classe wrapper di tipo
                -- BufferedWriter per semplificare le operazioni di scrittura
                */
                OutputStream s1out = s1.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s1out));

                bw.write(elaborazione(Integer.parseInt(stringa)));

                bw.close();
                s1.close();
                System.out.println("Chiusura connessione effettuata\n");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String elaborazione(int num)
    {
        String sequenza = "-- ";

        for(int i = 0; i < num; i++)
            sequenza += fibonacci(i) + " -- ";

        return sequenza;
    }

    public int fibonacci(int x)
    {
        if(x == 0 || x == 1)
            return x;
        else
            return fibonacci(x-1) + fibonacci(x-2);
    }

    public static void main(String[] args) {

        /*
        -- Crea un oggetto di tipo SimpleServer in ascolto
        -- sulla porta 7778
         */
        Server ss = new Server(7778);
        ss.runServer();
    }
}
