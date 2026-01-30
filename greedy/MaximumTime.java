public class MaximumTime {
    
    public String maximumTime(String time) {
        char[] t = time.toCharArray();

        // Hour - first digit
        if (t[0] == '?') {
            if (t[1] == '?' || t[1] <= '3') {
                t[0] = '2';
            } else {
                t[0] = '1';
            }
        }

        // Hour - second digit
        if (t[1] == '?') {
            if (t[0] == '2') {
                t[1] = '3';
            } else {
                t[1] = '9';
            }
        }

        // Minute - first digit
        if (t[3] == '?') {
            t[3] = '5';
        }

        // Minute - second digit
        if (t[4] == '?') {
            t[4] = '9';
        }

        return new String(t);
    }
}
