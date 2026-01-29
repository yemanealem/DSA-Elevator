import java.util.Arrays;

public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
   Arrays.sort(g);
    Arrays.sort(s);
    int nchil = g.length;
    int ncookies = s.length;
    int res = 0;
    int l = 0;
    int r = 0;

    while(l < nchil && r < ncookies) {
        if(g[l] <= s[r]) {
            res++;
            l++;
        }
        r++;
    }

    return res;
    }
}
