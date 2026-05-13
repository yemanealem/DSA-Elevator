public class StudentAttendanceRecordII {

    static final int MOD = 1_000_000_007;

    public static int checkRecord(int n) {

        // dp[day][absentCount][consecutiveLate]
        long[][][] dp = new long[n + 1][2][3];

        // Base case
        dp[0][0][0] = 1;

        for (int day = 0; day < n; day++) {

            for (int absent = 0; absent <= 1; absent++) {

                for (int late = 0; late <= 2; late++) {

                    long current = dp[day][absent][late];

                    if (current == 0)
                        continue;

                    // 1. Add Present (P)
                    dp[day + 1][absent][0] =
                            (dp[day + 1][absent][0] + current) % MOD;

                    // 2. Add Absent (A)
                    if (absent < 1) {
                        dp[day + 1][absent + 1][0] =
                                (dp[day + 1][absent + 1][0] + current) % MOD;
                    }

                    // 3. Add Late (L)
                    if (late < 2) {
                        dp[day + 1][absent][late + 1] =
                                (dp[day + 1][absent][late + 1] + current) % MOD;
                    }
                }
            }
        }

        long answer = 0;

        // Sum all valid states on day n
        for (int absent = 0; absent <= 1; absent++) {
            for (int late = 0; late <= 2; late++) {
                answer = (answer + dp[n][absent][late]) % MOD;
            }
        }

        return (int) answer;
    }

    public static void main(String[] args) {

        int n = 2;

        System.out.println("Valid attendance records = " + checkRecord(n));
    }
}