import java.util.*;

/*
LeetCode: Search Suggestions System

ULTRA-OPTIMIZED APPROACH (TWO POINTERS + SORT)

IDEA:
Instead of doing binary search for every prefix:
- Maintain a valid window [left, right]
- Shrink it as prefix grows

WHY FASTER:
- Each product is eliminated at most once
- No repeated binary search
- No full rescans

------------------------------------------------------

HOW IT WORKS:

1. Sort products
2. Initialize:
   left = 0, right = n - 1

3. For each character index i in searchWord:
   - Move left forward until match
   - Move right backward until match

4. Now valid range is [left, right]
5. Take first 3 elements from that range

------------------------------------------------------

TIME COMPLEXITY:
- Sorting → O(n log n)
- Two pointers total movement → O(n)
- Total → O(n log n + n)

SPACE:
- O(1) extra

------------------------------------------------------

WHY THIS IS FAST:
- Each element checked at most once
- No repeated binary search
- No prefix string building
*/

public class SearchSuggestionsSystem {

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();

        int left = 0;
        int right = products.length - 1;

        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);

            // shrink left boundary
            while (left <= right &&
                  (products[left].length() <= i || products[left].charAt(i) != c)) {
                left++;
            }

            // shrink right boundary
            while (left <= right &&
                  (products[right].length() <= i || products[right].charAt(i) != c)) {
                right--;
            }

            List<String> suggestions = new ArrayList<>();

            for (int j = left; j <= Math.min(left + 2, right); j++) {
                suggestions.add(products[j]);
            }

            result.add(suggestions);
        }

        return result;
    }

    public static void main(String[] args) {
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";

        List<List<String>> result = suggestedProducts(products, searchWord);

        for (List<String> list : result) {
            System.out.println(list);
        }
    }
}
