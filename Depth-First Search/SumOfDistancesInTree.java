/*
===============================================================================
 LeetCode Problem: Sum of Distances in Tree
===============================================================================

Question:
You are given an undirected connected tree with n nodes labeled
from 0 to n - 1 and an array edges where:

edges[i] = [ai, bi]

means there is an edge between ai and bi.

Return an array answer where:

answer[i] = sum of distances between node i and all other nodes.

-------------------------------------------------------------------------------
Example
-------------------------------------------------------------------------------

Input:
n = 6
edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]

Tree:

        0
      /   \
     1     2
          /|\
         3 4 5

Output:
[8,12,6,10,10,10]

Explanation:
Node 0:
distance to 1 = 1
distance to 2 = 1
distance to 3 = 2
distance to 4 = 2
distance to 5 = 2

Total = 8

-------------------------------------------------------------------------------
How This DFS Solution Works
-------------------------------------------------------------------------------

This problem uses TWO DFS traversals.

STEP 1: Postorder DFS
--------------------------------
Goal:
- Count how many nodes exist in each subtree.
- Calculate distance sum for root node.

For every node:
count[node] = size of subtree rooted at node

Initially:
count[node] = 1 (the node itself)

Formula:
result[node] += result[child] + count[child]

Why?
- result[child] gives distances inside child's subtree.
- Every node in child subtree becomes 1 farther from current node.

STEP 2: Preorder DFS (Re-rooting Technique)
--------------------------------
Now move root from parent to child.

Formula:

result[child] =
    result[parent]
    - count[child]
    + (n - count[child])

Meaning:
- Nodes inside child subtree become 1 closer.
- Nodes outside child subtree become 1 farther.

This efficiently computes answer for all nodes.

-------------------------------------------------------------------------------
Time Complexity
-------------------------------------------------------------------------------

Building graph: O(n)

DFS Traversals:
- First DFS  = O(n)
- Second DFS = O(n)

Total:
Time Complexity  = O(n)
Space Complexity = O(n)

-------------------------------------------------------------------------------
*/

import java.util.*;

public class SumOfDistancesInTree {

    private List<Integer>[] graph;
    private int[] count;
    private int[] result;
    private int n;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {

        this.n = n;

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        count = new int[n];
        result = new int[n];

        Arrays.fill(count, 1);

        postOrderDFS(0, -1);

   
        preOrderDFS(0, -1);

        return result;
    }

 
    private void postOrderDFS(int node, int parent) {

        for (int neighbor : graph[node]) {

            if (neighbor == parent) {
                continue;
            }

            postOrderDFS(neighbor, node);

            count[node] += count[neighbor];

            result[node] +=
                    result[neighbor] + count[neighbor];
        }
    }

    
    private void preOrderDFS(int node, int parent) {

        for (int neighbor : graph[node]) {

            if (neighbor == parent) {
                continue;
            }

            result[neighbor] =
                    result[node]
                    - count[neighbor]
                    + (n - count[neighbor]);

            preOrderDFS(neighbor, node);
        }
    }

    public static void main(String[] args) {

        SumOfDistancesInTree solution =
                new SumOfDistancesInTree();

        int n = 6;

        int[][] edges = {
                {0, 1},
                {0, 2},
                {2, 3},
                {2, 4},
                {2, 5}
        };

        int[] answer =
                solution.sumOfDistancesInTree(n, edges);

        System.out.println(
                Arrays.toString(answer)
        );
    }
}