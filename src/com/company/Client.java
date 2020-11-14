package com.company;

import java.net.*;
import java.io.*;
public class Client {

    public static void main(String[] args) {

        try
        {
            //Apro una connessione alla porta 7778 del server 127.0.0.1 (localhost)
            System.out.println("Mi sto connettendo...");
            Socket s1 = new Socket ("127.0.0.1", 7778);
            System.out.println("Connesso!\n");

            // crea l'oggetto tastiera e lo stream per l'output verso il server
            InputStreamReader In = new InputStreamReader(System.in);
            BufferedReader Tastiera = new BufferedReader(In);
            DataOutputStream os = new DataOutputStream(s1.getOutputStream());

            //Mando al server il parametro
            os.writeBytes(args[0] + "\n");

            /*
            -- Ricava lo stream di input dal socket s1
            -- ed utilizza un oggetto wrapper di classe BufferedReader
            -- per semplificare le operazioni di lettura
            */
            InputStream is = s1.getInputStream();
            BufferedReader dis = new BufferedReader(new InputStreamReader(is));

            // Legge l'input e lo visualizza sullo schermo
            System.out.println("Risposta del server:\n " + dis.readLine());

            // Al termine, chiude lo stream di comunicazione e il socket.
            dis.close();
            s1.close();
            System.out.println("Chiusura connessione effettuata");
        }
        catch(ConnectException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
