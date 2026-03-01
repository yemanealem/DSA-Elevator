import java.util.HashSet;

public class UniqueEmailAddresses {

    // Function to count unique emails
    public static int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();

        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];

            // Remove part after '+' if exists
            int plusIndex = local.indexOf('+');
            if (plusIndex != -1) {
                local = local.substring(0, plusIndex);
            }

            // Remove dots from local name
            local = local.replace(".", "");

            // Rebuild normalized email
            String normalized = local + "@" + domain;
            set.add(normalized);
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

        int result = numUniqueEmails(emails);
        System.out.println("Unique Emails: " + result);
    }
}
