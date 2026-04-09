import java.util.Arrays;

public class BeautifulArray {

    public static int[] beautifulArray(int n) {
        int[] res = new int[n];
        res[0] = 1;

        int size = 1;

        while (size < n) {
            int[] temp = new int[n];
            int idx = 0;

            // Generate odd numbers
            for (int i = 0; i < size; i++) {
                int val = 2 * res[i] - 1;
                if (val <= n) {
                    temp[idx++] = val;
                }
            }

            // Generate even numbers
            for (int i = 0; i < size; i++) {
                int val = 2 * res[i];
                if (val <= n) {
                    temp[idx++] = val;
                }
            }

            res = temp;
            size = idx;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 5; // you can change this value

        int[] result = beautifulArray(n);

        System.out.println("Beautiful Array for n = " + n + ":");
        System.out.println(Arrays.toString(result));
    }
}
