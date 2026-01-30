public class MaximumTime {

    /*
     QUESTION:
     ---------
     LeetCode: Latest Time by Replacing Hidden Digits

     You are given a time string in the format "HH:MM".
     Some digits are hidden and represented by '?'.

     Replace every '?' with a digit (0–9) so that:
     1. The resulting time is valid (00:00 to 23:59)
     2. The time is the LATEST possible

     ------------------------------------------------
     HOW IT WORKS (GREEDY EXPLANATION):
     ------------------------------------------------
     We want the latest time, so we always choose the
     largest possible valid digit at each position.

     Time format: H1 H2 : M1 M2

     1) H1 (first hour digit)
        - If H2 is '?' or <= 3 → we can use '2'
        - Otherwise → we must use '1'
        Reason: Max hour is 23

     2) H2 (second hour digit)
        - If H1 == '2' → max is '3'
        - Else → max is '9'

     3) M1 (first minute digit)
        - Max allowed is '5'

     4) M2 (second minute digit)
        - Max allowed is '9'

     ------------------------------------------------
     EXAMPLE TRACE:
     ------------------------------------------------
     Input: "?4:5?"

     Step 1: H1 = '?' and H2 = '4' (>3)
             → H1 = '1'
     Step 2: H2 = '4' (already given)
     Step 3: M1 = '5' (already given)
     Step 4: M2 = '?' → '9'

     Output: "14:59"
     ------------------------------------------------
     WHY GREEDY WORKS:
     ------------------------------------------------
     Earlier digits (hours) have more impact than later
     ones (minutes), so choosing the largest valid digit
     at each step always produces the latest valid time.
    */

    public static String maximumTime(String time) {
        char[] t = time.toCharArray();

        // First hour digit
        if (t[0] == '?') {
            if (t[1] == '?' || t[1] <= '3') {
                t[0] = '2';
            } else {
                t[0] = '1';
            }
        }

        // Second hour digit
        if (t[1] == '?') {
            if (t[0] == '2') {
                t[1] = '3';
            } else {
                t[1] = '9';
            }
        }

        // First minute digit
        if (t[3] == '?') {
            t[3] = '5';
        }

        // Second minute digit
        if (t[4] == '?') {
            t[4] = '9';
        }

        return new String(t);
    }

    public static void main(String[] args) {
        String time1 = "?4:5?";
        String time2 = "2?:?0";
        String time3 = "??:??";

        System.out.println(maximumTime(time1)); // 14:59
        System.out.println(maximumTime(time2)); // 23:50
        System.out.println(maximumTime(time3)); // 23:59
    }
}
