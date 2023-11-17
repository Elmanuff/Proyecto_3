package com.Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor HTTP que escucha en el puerto 8080 y responde con un mensaje de texto
 */
class MainServidor {
    public static void main(String[] args) {
        int puerto = 1234;
        Grafo grafo = new Grafo(30);

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                new Thread(() -> manejarCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Maneja la solicitud de un cliente
     */
    private static void manejarCliente(Socket clienteSocket) {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
             OutputStream salida = clienteSocket.getOutputStream()) {

            String solicitud = entrada.readLine();
            System.out.println("Solicitud recibida: " + solicitud);

            String respuesta = "HTTP/1.1 200 OK\r\n\r\n¡Hola, este es un servidor REST API con sockets!";
            salida.write(respuesta.getBytes("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
