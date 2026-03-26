import java.util.*;

public class GrayCodeOptimizedBacktrack {

    public List<Integer> grayCode(int n) {
        int size = 1 << n; // total 2^n Gray codes
        List<Integer> result = new ArrayList<>(size);
        boolean[] visited = new boolean[size];

        result.add(0);
        visited[0] = true;

        backtrack(n, result, visited, size);
        return result;
    }

    private boolean backtrack(int n, List<Integer> result, boolean[] visited, int size) {
        if (result.size() == size) return true; // done

        int current = result.get(result.size() - 1);

        // Flip bits in reverse order for faster coverage
        for (int i = n - 1; i >= 0; i--) {
            int next = current ^ (1 << i); // flip i-th bit

            if (!visited[next]) {
                visited[next] = true;
                result.add(next);

                if (backtrack(n, result, visited, size)) return true;

                // backtrack
                visited[next] = false;
                result.remove(result.size() - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GrayCodeOptimizedBacktrack obj = new GrayCodeOptimizedBacktrack();
        System.out.println(obj.grayCode(4));
    }
}
