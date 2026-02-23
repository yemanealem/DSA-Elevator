// Find Center of Star Graph - LeetCode
// Question:
// In a star graph, there is one center node connected to all other nodes.
// Given edges of the star graph, return the center node.
//
// How it works (Graph Theory):
// - The center node appears in every edge.
// - So the center must be either edges[0][0] or edges[0][1].
// - Check which one appears in the next edge.
//
// Running Time:
// - O(1) (constant time)
// - Space: O(1)

public class FindCenterOfStarGraph {

    public int findCenter(int[][] edges) {
        int a = edges[0][0];
        int b = edges[0][1];

        // The center must appear in the second edge
        if (edges[1][0] == a || edges[1][1] == a) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        FindCenterOfStarGraph solution = new FindCenterOfStarGraph();

        int[][] edges = {
            {1, 2},
            {2, 3},
            {2, 4}
        };

        int center = solution.findCenter(edges);

        System.out.println("Center of Star Graph: " + center);
    }
}
