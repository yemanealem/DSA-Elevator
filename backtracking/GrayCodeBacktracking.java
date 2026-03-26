import java.util.*;

public class GrayCodeBacktracking {

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        result.add(0);
        visited.add(0);

        backtrack(n, result, visited);
        return result;
    }

    private boolean backtrack(int n, List<Integer> result, Set<Integer> visited) {
        // Base case: if we got all 2^n numbers
        if (result.size() == (1 << n)) {
            return true;
        }

        int current = result.get(result.size() - 1);

        // Try flipping each bit
        for (int i = 0; i < n; i++) {
            int next = current ^ (1 << i); // flip ith bit

            if (!visited.contains(next)) {
                visited.add(next);
                result.add(next);

                if (backtrack(n, result, visited)) {
                    return true;
                }

                // backtrack
                visited.remove(next);
                result.remove(result.size() - 1);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GrayCodeBacktracking obj = new GrayCodeBacktracking();
        System.out.println(obj.grayCode(2)); // Example
    }
}
