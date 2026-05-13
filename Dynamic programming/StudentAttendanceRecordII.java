/*
Question:
---------
LeetCode 552 - Student Attendance Record II

Given an integer n, return the number of possible attendance records
of length n that are valid.

A record is valid if:
1. The student was absent (A) for fewer than 2 days total.
2. The student was never late (L) for 3 or more consecutive days.

Characters:
P = Present
A = Absent
L = Late

Return the answer modulo 10^9 + 7.


How Dynamic Programming Works:
------------------------------
We use DP with 3 states:

dp[day][absentCount][consecutiveLate]

Meaning:
- day              -> current day
- absentCount      -> number of absences used (0 or 1)
- consecutiveLate  -> current consecutive late count (0, 1, 2)

Transitions:
1. Add 'P'
   - Resets consecutive late count to 0

2. Add 'A'
   - Allowed only if absentCount < 1

3. Add 'L'
   - Allowed only if consecutiveLate < 2

We iterate day by day and count all valid possibilities.


Time Complexity:
----------------
O(n × 2 × 3) = O(n)

Because:
- n days
- 2 absence states
- 3 late states


Space Complexity:
-----------------
O(n × 2 × 3) = O(n)

Can be optimized to O(1) using rolling variables.
*/

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

        int n = 5;

        System.out.println(checkRecord(n));
    }
}