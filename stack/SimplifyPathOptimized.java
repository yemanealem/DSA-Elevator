import java.util.*;

/**
 * LeetCode 71 - Simplify Path
 *
 * Problem:
 * Given a Unix-style absolute file path, simplify it.
 *
 * Rules:
 * "."  -> current directory (ignore)
 * ".." -> go back to parent directory
 * "//" -> treat as single "/"
 *
 * Example:
 * Input:  "/a/./b/../../c/"
 * Output: "/c"
 *
 * Approach:
 * - Use a Deque as a stack.
 * - Parse the string manually (no split).
 * - Push valid directory names.
 * - Pop when encountering "..".
 * - Ignore "." and empty segments.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class SimplifyPathOptimized {

    public static String simplifyPath(String path) {

        Deque<String> stack = new ArrayDeque<>();
        int n = path.length();
        int i = 0;

        while (i < n) {

            // Skip consecutive '/'
            while (i < n && path.charAt(i) == '/') {
                i++;
            }

            if (i >= n) break;

            int start = i;

            // Find end of current directory name
            while (i < n && path.charAt(i) != '/') {
                i++;
            }

            String part = path.substring(start, i);

            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (!part.equals(".")) {
                stack.offerLast(part);
            }
        }

        // Build result
        if (stack.isEmpty()) return "/";

        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        System.out.println(simplifyPath("/a/./b/../../c/"));  // /c
        System.out.println(simplifyPath("/../"));            // /
        System.out.println(simplifyPath("/home//foo/"));     // /home/foo
        System.out.println(simplifyPath("/a//b////c/d//././/..")); // /a/b/c
    }
}
