
class Solution {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (isValid(curr)) {
                result.add(curr);
                found = true;
            }

            if (found) continue;

            for (int i = 0; i < curr.length(); i++) {

                // Only remove parentheses
                if (curr.charAt(i) != '(' && curr.charAt(i) != ')')
                    continue;

                // 🔥 KEY OPTIMIZATION:
                // Skip duplicate removals
                if (i > 0 && curr.charAt(i) == curr.charAt(i - 1))
                    continue;

                String next = curr.substring(0, i) + curr.substring(i + 1);

                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') {
                if (count == 0) return false;
                count--;
            }
        }
        return count == 0; 
    }
}