package com.company.Client;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private static final String IP_ADDRESS_DEFAULT = "127.0.0.1";
    private static final int PORT_DEFAULT = 8088;
    /**
     * Command line entry for client
     * @param args cmd args
     */
    public static void main(String[] args) {
        String response;
        String ip = args.length >= 3 ? args[2] : IP_ADDRESS_DEFAULT;
        int port = args.length >= 4 ? Integer.parseInt(args[3]) : PORT_DEFAULT;

        if(args.length >= 2) {
            response = runSocket(new RequestEntity(args[0], args[1]), ip, port);
        } else {
            response = runSocket(new RequestEntity("query", "Tux"), ip, port);
        }
        logger.info(response);
    }

    /**
     * Entry for tests to invoke server
     * @param requst entity of query
     * @return string of responce
     */
    public static String runSocket(RequestEntity requst, String ip, int port) {
        boolean flag = true;
        String result = "";

        try (Socket socket = new Socket(ip, port)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (flag) {
                JSONObject json = new JSONObject();
                json.put("Method", requst.getMethod());
                json.put("toyName", requst.getToyName());
                oos.writeObject(json);
                oos.flush();
                result = buf.readLine();
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String runSocket(RequestEntity request) {
        return runSocket(request, IP_ADDRESS_DEFAULT, PORT_DEFAULT);
    }

}