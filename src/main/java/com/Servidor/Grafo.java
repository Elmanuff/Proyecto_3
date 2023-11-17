package com.Servidor;
import java.util.*;

class Grafo {
    int V; // Número de vértices
    List<List<Integer>> adj_list;
    int[][] distances;

    // Constructor de la clase
    public Grafo(int V) {
        this.V = V;
        adj_list = new ArrayList<>();
        distances = new int[V][V];

        // Inicializa la lista de adyacencia
        for (int i = 0; i < V; i++) {
            adj_list.add(new ArrayList<>());
        }

        // Asigna distancias aleatorias
        Random rand = new Random();
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                int distance = rand.nextInt(10) + 1; // Genera un número aleatorio entre 1 y 10
                distances[i][j] = distance;
                distances[j][i] = distance;
                adj_list.get(i).add(j);
                adj_list.get(j).add(i);
            }
        }
    }
}
