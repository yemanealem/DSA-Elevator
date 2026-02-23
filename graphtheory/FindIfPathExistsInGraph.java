class FindIfPathExistsInGrap {

    // Graph Theory - Union Find (DSU)
    // How it works:
    // - Each node starts as its own parent (separate component)
    // - If there is an edge between two nodes, we union them (same component)
    // - After processing all edges, if source and destination have same root,
    //   it means they are connected → path exists.

    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression (optimization)
            }
            return parent[x];
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA != rootB) {
                if (rank[rootA] > rank[rootB]) {
                    parent[rootB] = rootA;
                } else if (rank[rootA] < rank[rootB]) {
                    parent[rootA] = rootB;
                } else {
                    parent[rootB] = rootA;
                    rank[rootA]++;
                }
            }
        }
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        DSU dsu = new DSU(n);

        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        return dsu.find(source) == dsu.find(destination);
    }

    // Main method for testing (not required in LeetCode)
    public static void main(String[] args) {
        FindIfPathExistsInGrap solution = new FindIfPathExistsInGrap();

        int n = 3;
        int[][] edges = {
            {0, 1},
            {1, 2}
        };
        int source = 0;
        int destination = 2;

        boolean result = solution.validPath(n, edges, source, destination);

        System.out.println("Path Exists: " + result);
    }
}
