import java.util.HashSet;

/*
 * Question:
 * Given a list of email addresses, return the number of unique addresses.
 *
 * Rules:
 * - Ignore dots '.' in the local part.
 * - Ignore everything after '+' in the local part.
 * - Domain part remains unchanged.
 *
 * Example:
 * Input:
 * ["test.email+alex@leetcode.com", "test.e.mail@leetcode.com"]
 * Output:
 * 1
 *
 * How it Works (Algorithm):
 * - For each email:
 *   - Split local and domain using '@'.
 *   - Process local part:
 *       - Skip dots.
 *       - Stop if '+' is found.
 *   - Rebuild normalized email: local + domain.
 *   - Store in HashSet (keeps unique values).
 * - Return HashSet size.
 *
 * Trace Example:
 * Input: "test.email+alex@leetcode.com"
 * Local: "test.email+alex"
 * Processing:
 * t e s t e m a i l (skip '.' and stop at '+')
 * Result local -> "testemail"
 * Final -> "testemail@leetcode.com"
 */

public class UniqueEmailAddresses {

    // Function to count unique emails
    public static int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>(emails.length);

        for (String email : emails) {
            int at = email.indexOf('@');
            String domain = email.substring(at); // domain part

            String local = email.substring(0, at);
            StringBuilder sb = new StringBuilder(local.length());

            for (int i = 0; i < local.length(); i++) {
                char c = local.charAt(i);

                if (c == '+') break;      // ignore everything after +
                if (c != '.') sb.append(c); // skip dots
            }

            set.add(sb.append(domain).toString());
        }

        return set.size();
    }

    public static void main(String[] args) {
        String[] emails = {
            "test.email+alex@leetcode.com",
            "test.e.mail@leetcode.com",
            "testemail@leetcode.com",
            "test.email+spam@leetcode.com"
        };

        System.out.println("Unique Emails: " + numUniqueEmails(emails));
    }
}

/*
 * Running Time Complexity:
 * - For each email, we scan the local part once.
 * - Time: O(n * m)  (n = emails, m = length of email)
 *
 * Space Complexity:
 * - HashSet stores unique emails.
 * - Space: O(n)
 */
