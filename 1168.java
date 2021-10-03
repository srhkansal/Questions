import java.util.PriorityQueue;

/**
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 *
 * For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it.
 * The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to
 * connect house1 and house2 together using a pipe. Connections are bidirectional.
 *
 * Find the minimum total cost to supply water to all houses.
 *
 * Example 1:
 *
 * Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * Output: 3
 * Explanation:
 * The image shows the costs of connecting houses using pipes.
 * The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
 *
 * Constraints:
 *
 * 1 <= n <= 10000
 * wells.length == n
 * 0 <= wells[i] <= 10^5
 * 1 <= pipes.length <= 10000
 * 1 <= pipes[i][0], pipes[i][1] <= n
 * 0 <= pipes[i][2] <= 10^5
 * pipes[i][0] != pipes[i][1]
 */
class OptimizeWaterDistribution {
    /**
     * From example graph, we can see that this is the shortest path problem/Minimum spanning tree problem. In this problem, in a graph, view cities as nodes,
     * pipe connects two cities as edges with cost. here, wells costs, it is self connected edge, we can add extra node as root node 0, and connect all 0 and i with costs wells[i].
     * So that we can have one graph/tree, and how to get a minimun spanning trees / the shortest path problem in a graph. Please see below detailed steps for analysis.
     */
    public static void main(String []args) {
        int n = 5;
        int []wells = {1,2,2,3,2};
        int [][]pipes = {{1,2,1},{2,3,1},{4,5,7}};

        OptimizeWaterDistribution optimizeWaterDistribution = new OptimizeWaterDistribution();
        int cost = optimizeWaterDistribution.minCostToSupplyWater(n, wells, pipes);
        System.out.println(cost);
    }
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {

        PriorityQueue<EdgeCost> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);

        for (int i = 1; i <= n; i++) {
            EdgeCost edgeCost = new EdgeCost(0, i, wells[i - 1]);
            pq.offer(edgeCost);
        }

        for (int[] p : pipes) {
            pq.offer(new EdgeCost(p[0], p[1], p[2]));
        }

        int minCosts = 0;
        UnionFind uf = new UnionFind(n);

        while(!pq.isEmpty()) {
            EdgeCost edge = pq.poll();
            if(uf.unify(edge.node1, edge.node2))
                minCosts += edge.cost;
        }
        return minCosts;
    }
}

class EdgeCost {
    int node1;
    int node2;
    int cost;
    public EdgeCost(int node1, int node2, int cost) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
    }
}

class UnionFind {
    int component;
    int []parent;
    int []rank;

    UnionFind(int n) {
        component = n;
        parent = new int[n+1]; // *** n+1 because labels are from 1 to n.
        rank = new int[n+1];

        for(int i=0; i<n+1; ++i) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if(x == parent[x])
            return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean unify(int x, int y) {

        int gpx = find(x);
        int gpy = find(y);

        if(gpx == gpy)
            return false;

        if(rank[gpx] < rank[gpy]) {
            parent[gpx] = gpy;
            rank[gpy] += rank[gpx];
        } else {
            parent[gpy] = gpx;
            rank[gpx] += rank[gpy];
        }
        --component;
        return true;
    }

    public int getComponent() {
        return component;
    }
}
