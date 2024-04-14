package edu.princeton.cs.algs4;

import java.util.Arrays;

public class TransitiveClosure2 {
    private static final int NO = -2;
    private static final int YES = -1;
    private final int[][] edges;
    public TransitiveClosure2(Digraph G) {
        edges = new int[G.V()][G.V()];
        for (int i = 0; i < G.V(); i++) {
            Arrays.fill(edges[i], NO); // -2 means no correction
            for (int j : G.adj(i)) {
                edges[i][j] = YES;
            }
        }
        for (int k = 0; k < G.V(); k++) {
            for (int i = 0; i < G.V(); i++) {
                for (int j = 0; j < G.V(); j++) {
                    if (i != j) {
                        if (edges[i][j] <= NO) {
                            if (edges[i][k] > NO && edges[k][j] > NO) {
                                edges[i][j] = k;
                            }
                        } // already have connection, do nothing

                    }
                }
            }
        }
    }

    public boolean reachable(int v, int w) {
        return v == w || edges[v][w] > NO;
    }

    public void findPath(int from, int to) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(from);
        boolean isFound = findPath(from, to, queue);
        queue.enqueue(to);
        if (isFound) {
            System.out.print("found ");
            for(int e : queue) {
                System.out.print(e + " ");
            }
            System.out.println();
        } else {
            System.out.println("no found from " + from + " to " + to);
        }
    }
    private boolean findPath(int from, int to, Queue<Integer> queue) {
        int path = edges[from][to];
        if (path <= NO) {
            return false;
        }
        if (path == YES) {
            return true;
        }
        findPath(from, path, queue);
        queue.enqueue(edges[from][to]);
        findPath(path, to, queue);
        return true;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(9);
        G.addEdge(0, 1);
        G.addEdge(0, 4);
        G.addEdge(0, 5);
        G.addEdge(1, 2);
        G.addEdge(1, 4);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(5, 6);
        G.addEdge(5, 7);
        G.addEdge(5, 8);
        G.addEdge(7, 8);
        Digraph G2 = new Digraph(4);
        G2.addEdge(0, 1);
        G2.addEdge(1, 2);
        G2.addEdge(3, 1);
        G2.addEdge(2, 3);

        Digraph G3 = new Digraph(3);
        G3.addEdge(1, 0);
        G3.addEdge(2, 1);
        TransitiveClosure2 tc = new TransitiveClosure2(G2);

        tc.findPath(0, 1);
        tc.findPath(0, 2);
        tc.findPath(1, 0);
        tc.findPath(2, 0);
        tc.findPath(3, 0);
        tc.findPath(0, 3);
    }
}