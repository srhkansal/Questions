public class SolutionFriends {
    public static void main(String []args) {
        int [][]logs = {{20190101,0,1}, {20190104,3,4}, {20190107,2,3},{20190211,1,5},{20190224,2,4},{20190301,0,3},{20190312,1,2},{20190322,4,5}};
        int N = 6;
        SolutionFriends solutionFriends = new SolutionFriends();
        int time = solutionFriends.earliestAcq(logs, N);
        System.out.println(time);
    }

    public int earliestAcq(int[][] logs, int N) {
        UnionFind uf= new UnionFind(N);
        Arrays.sort(logs, (a, b)-> a[0]-b[0]);

        for(int [] log : logs){
            uf.unify(log[1], log[2]);
            if(uf.component == 1){
                return log[0];
            }
        }
        return -1;
    }
}

class UnionFind {
    int component;
    int []parent;
    int []rank;

    UnionFind(int n) {
        component = n;
        parent = new int[n];
        rank = new int[n];

        for(int i=0; i<n; ++i) {
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
