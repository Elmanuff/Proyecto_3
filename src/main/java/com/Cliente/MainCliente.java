package com.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Cliente HTTP que envía una solicitud GET al servidor y muestra la respuesta
 */
public class MainCliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 8080;

        try (Socket socket = new Socket(host, puerto);
             OutputStream salida = socket.getOutputStream();
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Envía una solicitud HTTP GET al servidor
            String solicitud = "GET /api/saludo HTTP/1.1\r\nHost: " + host + "\r\n\r\n";
            salida.write(solicitud.getBytes("UTF-8"));

            // Lee la respuesta del servidor
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = entrada.readLine()) != null) {
                respuesta.append(linea).append("\n");
            }

            System.out.println("Respuesta del servidor:\n" + respuesta.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
