import java.util.HashSet;

public class UniqueEmailAddresses {

    public static int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>(emails.length);

        for (String email : emails) {
            int at = email.indexOf('@');
            String domain = email.substring(at); 

            String local = email.substring(0, at);
            StringBuilder sb = new StringBuilder(local.length());

            for (int i = 0; i < local.length(); i++) {
                char c = local.charAt(i);
                if (c == '+') break;   
                if (c != '.') sb.append(c);
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
