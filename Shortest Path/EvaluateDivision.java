import java.util.*;

public class EvaluateDivision {

    Map<String, List<String>> graph = new HashMap<>();
    Map<String, List<Double>> weight = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

      
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
