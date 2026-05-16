/*
Problem: Similar String Groups

Approach:
- Treat each string as a graph node.
- If two strings are similar, connect them.
- Use DFS to find connected components.
- Number of connected components = number of groups.

Time Complexity:
O(n^2 * m)

n = number of strings
m = length of each string
*/

public class SimilarStringGroups {

    public int numSimilarGroups(String[] strs) {

        int n = strs.length;

        boolean[] visited = new boolean[n];

        int groups = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                dfs(strs, visited, i);

                groups++;
            }
        }

        return groups;
    }

    private void dfs(String[] strs, boolean[] visited, int index) {

        visited[index] = true;

        for (int j = 0; j < strs.length; j++) {

            if (!visited[j] && isSimilar(strs[index], strs[j])) {

                dfs(strs, visited, j);
            }
        }
    }

    private boolean isSimilar(String a, String b) {

        int diff = 0;

        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) != b.charAt(i)) {

                diff++;

              
                if (diff > 2) {
                    return false;
                }
            }
        }

       
        return diff == 0 || diff == 2;
    }

    public static void main(String[] args) {

        SimilarStringGroups solution = new SimilarStringGroups();

        String[] strs = {"tars", "rats", "arts", "star"};

        int result = solution.numSimilarGroups(strs);

        System.out.println("Number of Groups: " + result);
    }
}