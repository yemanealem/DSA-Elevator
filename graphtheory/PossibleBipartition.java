/*
Problem: Possible Bipartition

Question:
Given n people labeled from 1 to n and an array dislikes where dislikes[i] = [a, b]
indicates that person a and person b dislike each other, determine if it is possible
to split everyone into two groups such that no two people who dislike each other
are in the same group.

How it works (Union-Find approach):
- Think of this as a bipartite graph problem.
- If a person dislikes multiple people, all those disliked people must be in the SAME group.
- For each person:
    1. If the person and their neighbor belong to the same set → conflict → return false
    2. Otherwise, union all neighbors together (group enemies into one set)
- We use Union-Find (Disjoint Set) with path compression to efficiently manage groups.

Key Insight:
- Instead of coloring nodes (BFS/DFS), we group enemies together.
- If a person ends up in the same group as their enemy → impossible.

Time Complexity:
- O(n + e * α(n))
  where:
    n = number of people
    e = number of dislikes
    α(n) = inverse Ackermann function (very small, almost constant)

Space Complexity:
- O(n + e) for graph + parent array
*/

import java.util.*;

class PossibleBipartition {

    int[] parent;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] d : dislikes) {
            graph[d[0]].add(d[1]);
            graph[d[1]].add(d[0]);
        }

        for (int i = 1; i <= n; i++) {
            for (int neighbor : graph[i]) {
                // If same parent → conflict
                if (find(i) == find(neighbor)) return false;

                // Union all neighbors together
                union(graph[i].get(0), neighbor);
            }
        }

        return true;
    }

    private int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    private void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}
