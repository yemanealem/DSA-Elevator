public class FindCenterOfStarGraph {

    public int findCenter(int[][] edges) {
        int a = edges[0][0];
        int b = edges[0][1];

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

    /*
     * Question:
     * Find the center of a star graph. A star graph has one center node connected
     * to all other nodes. Given the edges of the graph, return the center node.
     *
     * How it works (Graph Theory):
     * In a star graph, the center node appears in every edge.
     * So the center must be either edges[0][0] or edges[0][1].
     * To determine which one, we check the second edge:
     * - If edges[1] contains edges[0][0], that is the center.
     * - Otherwise, edges[0][1] is the center.
     *
     * Trace:
     * edges[0] = {1,2} -> candidates: 1 or 2
     * edges[1] = {2,3} -> contains 2, so center = 2
     * edges[2] = {2,4} -> also contains 2 (valid star)
     *
     * Running Time:
     * O(1) because we check only two edges.
     * Space Complexity: O(1)
     */
}
