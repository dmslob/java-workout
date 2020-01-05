package com.dmslob.graph.shortestpath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WeighedDigraph {

    private HashMap<Integer, ArrayList<WeighedDigraphEdge>> adj = new HashMap(); // adjacency-list

    WeighedDigraph() {
    }

    /**
     * Instantiate graph from file with data
     *
     * @param file
     * @throws IOException
     */
    WeighedDigraph(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s");

            if (parts.length == 3) {
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                double weight = Double.parseDouble(parts[2]);

                addEdge(new WeighedDigraphEdge(from, to, weight));
            }
        }
    }

    /**
     * @param vertex
     * @return list of edges vertex is connected to.
     */
    ArrayList<WeighedDigraphEdge> edgesOf(int vertex) {
        return adj.get(vertex);
    }

    /**
     * @return list of all edges in the graph.
     */
    ArrayList<WeighedDigraphEdge> edges() {
        ArrayList list = new ArrayList<WeighedDigraphEdge>();

        for (int from : adj.keySet()) {
            ArrayList<WeighedDigraphEdge> currentEdges = adj.get(from);
            for (WeighedDigraphEdge e : currentEdges) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * @return iterable of all vertices in the graph.
     */
    Iterable<Integer> vertices() {
        HashSet set = new HashSet();
        for (WeighedDigraphEdge edge : edges()) {
            set.add(edge.from());
            set.add(edge.to());
        }

        return set;
    }

    /**
     * @return size of adjacency list
     */
    public int size() {
        return adj.size();
    }

    /**
     * @return string representation of digraph
     */
    public String toString() {
        String out = "";
        for (int from : adj.keySet()) {
            ArrayList<WeighedDigraphEdge> currentEdges = adj.get(from);
            out += from + " -> ";

            if (currentEdges.size() == 0)
                out += "-,";

            for (WeighedDigraphEdge edge : currentEdges)
                out += edge.to() + " @ " + edge.weight() + ", ";

            out += "\n";
        }

        return out;
    }

    /**
     * Add new edge to the system.
     *
     * @param newEdge
     */
    void addEdge(WeighedDigraphEdge newEdge) {
        // create empty connection set
        if (!adj.containsKey(newEdge.from()))
            adj.put(newEdge.from(), new ArrayList<WeighedDigraphEdge>());

        ArrayList<WeighedDigraphEdge> currentEdges = adj.get(newEdge.from());

        /* Check if edge already exists,
         * if it is, replace it with new one assuming it needs to be updated */
        boolean edgeExists = false;
        for (int i = 0; i < currentEdges.size(); i++) {
            if (currentEdges.get(i).to() == newEdge.to()) {
                currentEdges.set(i, newEdge);
                edgeExists = true;
                break;
            }
        }

        if (!edgeExists)
            currentEdges.add(newEdge);

        adj.put(newEdge.from(), currentEdges);
    }

    public static void main(String args[]) {

        WeighedDigraph graph = new WeighedDigraph();

        WeighedDigraphEdge edge1 = new WeighedDigraphEdge(1, 2, 2.0);
        WeighedDigraphEdge edge2 = new WeighedDigraphEdge(2, 1, 2.0);

        graph.addEdge(edge1);
        graph.addEdge(edge2);

        System.out.print(graph);
    }
}
