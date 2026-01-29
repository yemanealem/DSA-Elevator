import java.util.Arrays;

public class AssignCookies {

    /*
     * Problem: Assign Cookies (LeetCode 455)
     *
     * g[] -> greed factors of children
     * s[] -> sizes of cookies
     *
     * Greedy Idea:
     * - Sort both arrays
     * - Assign the smallest possible cookie that satisfies the least greedy child
     */

    public int findContentChildren(int[] g, int[] s) {

        // Sort greed factors and cookie sizes
        Arrays.sort(g);
        Arrays.sort(s);

        int nchil = g.length;
        int ncookies = s.length;

        int res = 0; // number of content children
        int l = 0;   // pointer for children
        int r = 0;   // pointer for cookies

        /*
         * While we still have children and cookies:
         * - If current cookie can satisfy current child,
         *   assign it and move to next child
         * - Always move to next cookie
         */
        while (l < nchil && r < ncookies) {
            if (g[l] <= s[r]) {
                res++;  // child is satisfied
                l++;    // move to next child
            }
            r++;        // move to next cookie
        }

        return res;
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        AssignCookies ac = new AssignCookies();

        int[] g = {1, 2, 3};
        int[] s = {1, 1};

        int result = ac.findContentChildren(g, s);

        System.out.println("Maximum number of content children: " + result);
    }
}
