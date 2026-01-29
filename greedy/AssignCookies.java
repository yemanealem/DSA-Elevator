import java.util.Arrays;

public class AssignCookies {
    

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int child = 0;
        int cookie = 0;

        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                child++;   // child is satisfied
            }
            cookie++;       // move to next cookie
        }

        return child; // number of satisfied children
    }
}
