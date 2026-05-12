
public class SuperUglyNumber {

    public static int nthSuperUglyNumber(int n, int[] primes) {

        int k = primes.length;

        long[] dp = new long[n];
        int[] index = new int[k];
        long[] value = new long[k];

        dp[0] = 1;

        for (int i = 0; i < k; i++) {
            value[i] = primes[i];
        }

        for (int i = 1; i < n; i++) {

            long min = value[0];
            for (int j = 1; j < k; j++) {
                min = Math.min(min, value[j]);
            }

            dp[i] = min;

            for (int j = 0; j < k; j++) {
                if (value[j] == min) {
                    index[j]++;
                    value[j] = dp[index[j]] * primes[j];
                }
            }
        }

        return (int) dp[n - 1];
    }

    public static void main(String[] args) {
        int n = 5911;
        int[] primes = {2, 3, 5, 7};

        System.out.println(nthSuperUglyNumber(n, primes));
    }
}