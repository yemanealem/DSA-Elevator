import java.util.*;

public class CourseScheduleII {

    /*
     * PROBLEM:
     * Given numCourses and a list of prerequisites where [a, b] means
     * you must take course b before course a, return a valid order
     * to finish all courses. If impossible (cycle exists), return empty array.
     *
     * HOW IT WORKS:
     * - Model the problem as a directed graph:
     *     b → a (edge from prerequisite to course)
     * - Use Topological Sorting (Kahn’s Algorithm - BFS):
     *     1. Build adjacency list (graph)
     *     2. Compute indegree of each node (number of prerequisites)
     *     3. Add all nodes with indegree = 0 to queue
     *     4. Process queue:
     *          - Remove node
     *          - Add to result
     *          - Reduce indegree of neighbors
     *          - If indegree becomes 0 → add to queue
     *     5. If all nodes are processed → valid order
     *        Else → cycle exists → return empty array
     *
     * TIME COMPLEXITY:
     * - O(V + E)
     *   V = number of courses
     *   E = number of prerequisites
     *
     * SPACE COMPLEXITY:
     * - O(V + E) for graph + queue + result
     */

    public static int[] findCourseOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build graph
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prereq = pair[1];

            adjacencyList.get(prereq).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // Add all nodes with indegree 0
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] order = new int[numCourses];
        int processedCourses = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[processedCourses++] = current;

            for (int next : adjacencyList.get(current)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // Cycle detection
        if (processedCourses != numCourses) {
            return new int[0];
        }

        return order;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {
            {1, 0}, {2, 0}, {3, 1}, {3, 2}
        };

        System.out.println(Arrays.toString(findCourseOrder(numCourses, prerequisites)));
    }
}
