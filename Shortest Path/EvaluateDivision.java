import java.util.*;

/*
------------------------------------------------------------
🧠 PROBLEM: Evaluate Division (LeetCode 399)

You are given equations like:
    a / b = 2.0
    b / c = 3.0

You must answer queries like:
    a / c = ?
    a / e = ?

If not possible → return -1.0

------------------------------------------------------------
💡 HOW IT WORKS (KEY IDEA)

👉 Treat variables as graph nodes
👉 Each equation creates TWO edges:

    a / b = 2.0
    => a → b (2.0)
    => b → a (1 / 2.0)

So the graph is:
    - weighted
    - directed
    - multiplicative

👉 To answer a query:
We search a path from src → dst
and multiply edge weights along the path.

We use DFS with visited set to avoid cycles.

------------------------------------------------------------
⏱ TIME COMPLEXITY

Let:
    V = number of variables
    E = number of equations
    Q = number of queries

Build graph: O(E)
Each DFS: O(V + E) worst case
Total: O(Q × (V + E))

Works efficiently because graph is small.

------------------------------------------------------------
*/

public class EvaluateDivision {

    Map<String, List<String>> graph = new HashMap<>();
    Map<String, List<Double>> weight = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // build graph
        for (int i = 0; i < equations.size(); i++) {

            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            weight.putIfAbsent(u, new ArrayList<>());
            weight.putIfAbsent(v, new ArrayList<>());

            graph.get(u).add(v);
            weight.get(u).add(val);

            graph.get(v).add(u);
            weight.get(v).add(1.0 / val);
        }

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            String src = queries.get(i).get(0);
            String dst = queries.get(i).get(1);

            if (!graph.containsKey(src) || !graph.containsKey(dst)) {
                result[i] = -1.0;
            } else if (src.equals(dst)) {
                result[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                result[i] = dfs(src, dst, visited, 1.0);
            }
        }

        return result;
    }

    private double dfs(String src, String dst, Set<String> visited, double product) {

        if (src.equals(dst)) return product;

        visited.add(src);

        List<String> neighbors = graph.get(src);
        List<Double> weights = weight.get(src);

        for (int i = 0; i < neighbors.size(); i++) {

            String next = neighbors.get(i);

            if (visited.contains(next)) continue;

            double res = dfs(next, dst, visited, product * weights.get(i));
            if (res != -1.0) return res;
        }

        return -1.0;
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        EvaluateDivision obj = new EvaluateDivision();

        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );

        double[] values = {2.0, 3.0};

        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );

        double[] result = obj.calcEquation(equations, values, queries);

        System.out.println(Arrays.toString(result));
    }
}
