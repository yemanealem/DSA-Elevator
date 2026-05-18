import java.util.*;

public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {

        List<List<Integer>> result = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        // store reversed words for quick lookup
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            for (int j = 0; j <= word.length(); j++) {

                String prefix = word.substring(0, j);
                String suffix = word.substring(j);

                // Case 1: prefix is palindrome
                if (isPalindrome(prefix)) {
                    String revSuffix = new StringBuilder(suffix).reverse().toString();

                    if (map.containsKey(revSuffix) && map.get(revSuffix) != i) {
                        result.add(Arrays.asList(map.get(revSuffix), i));
                    }
                }

                // Case 2: suffix is palindrome (avoid duplicate empty case)
                if (j != word.length() && isPalindrome(suffix)) {
                    String revPrefix = new StringBuilder(prefix).reverse().toString();

                    if (map.containsKey(revPrefix) && map.get(revPrefix) != i) {
                        result.add(Arrays.asList(i, map.get(revPrefix)));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {

        int l = 0, r = s.length() - 1;

        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        PalindromePairs solution = new PalindromePairs();

        String[] words = {"bat", "tab", "cat"};

        List<List<Integer>> result = solution.palindromePairs(words);

        System.out.println(result);
    }
}