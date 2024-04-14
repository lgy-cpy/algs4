package edu.princeton.cs.algs4;

/**
 * Inspired from
 * Articulation point: https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 * Bridge: https://www.geeksforgeeks.org/bridge-in-a-graph/
 * Also: https://bbs.huaweicloud.com/blogs/240222
 * https://zhuanlan.zhihu.com/p/101923309
 */
public class TarjanGraphAP {
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int[] low; // u 或 u的子树能够追溯到的最早的栈中节点的次序号;
    private final int[] disc; // 节点u 搜索的次序编号(时间戳);
    private final boolean[] ap;
    private int order;

    private final Bag<int[]> bridge;

    public TarjanGraphAP(Graph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        low = new int[G.V()];
        disc = new int[G.V()];
        ap = new boolean[G.V()];
        bridge = new Bag<>();
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) {
                edgeTo[v] = -1;
                dfs(G, v);
            }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        disc[v] = low[v] = order++;
        System.out.println("visit " + v + " order " + disc[v]);
        int children = 0;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                children++;
                dfs(G, w);
                System.out.println("Visit new child " + w + " low is " + low[w]);
                low[v] = Math.min(low[v], low[w]);
                System.out.println(v + ": order is " + disc[v] + " low is " + low[v]);
                if (edgeTo[v] != -1 && low[w] >= disc[v]) { // 必须是>= 因为有可能是等于，比如说形成一个环，但是没有超过，即没有连接到更远的地方
                    System.out.println(v + " is AP");
                    ap[v] = true;
                }
                if (low[w] > disc[v]) {
                    System.out.println(v + " " + w + " is bridge");
                    int[] edge = {v, w};
                    bridge.add(edge);
                }
            } else if (edgeTo[v] != w) { // 不能直接反方向回去
                System.out.println("Visit old child " + w + " low is " + low[w]);
                low[v] = Math.min(low[v], disc[w]); // 用子结点的访问次序，来更新父节点的low值
            }
        }
        if (edgeTo[v] == -1 && children > 1) {
            ap[v] = true;
        }
    }

    public static void main(String[] args) {
        Graph G = new Graph(9);
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
        Graph G2 = new Graph(4);
        G2.addEdge(0, 1);
        G2.addEdge(1, 2);
        G2.addEdge(1, 3);
        G2.addEdge(2, 3);

        TarjanGraphAP tarjanGraphAP = new TarjanGraphAP(G2);
        System.out.println("AP list :");
        for (int i = 0; i < tarjanGraphAP.ap.length; i++) {
            if (tarjanGraphAP.ap[i])
                System.out.println(i);
        }
        System.out.println("bridge list :");
        for (int[] edge : tarjanGraphAP.bridge) {
            System.out.println(edge[0] + "-" + edge[1]);
        }
    }
}
