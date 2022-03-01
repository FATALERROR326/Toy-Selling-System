package com.company.Client;

import java.io.BufferedReader;

public class Receiver extends Thread {

    BufferedReader reader;

    public Receiver(BufferedReader theSocketReader) {

        reader = theSocketReader;

    }

    public void run() {

        while (true) {

            try {

                String words = reader.readLine();
                System.out.println("\r\nRecieved： " + words);

            } catch (Exception e) {

                e.printStackTrace();

                return;

            }
        }
    }
}

