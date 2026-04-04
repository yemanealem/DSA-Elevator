import java.util.*;

/*
LeetCode: Search Suggestions System

QUESTION:
Given an array of strings products and a string searchWord.

Design a system that suggests at most 3 product names from products after each character of searchWord is typed.

Rules:
- Suggested products should have a common prefix with searchWord.
- Return at most 3 lexicographically minimum products.

Return a list of lists of the suggested products after each character of searchWord is typed.

------------------------------------------------------

HOW IT WORKS (SORT + PREFIX MATCH):

1. First, sort the products array lexicographically.
   → This ensures suggestions are already in correct order.

2. For each prefix of searchWord:
   Example: searchWord = "mouse"
   prefixes:
   "m", "mo", "mou", "mous", "mouse"

3. For each prefix:
   - Scan through products
   - Check if product starts with prefix
   - Add up to 3 matches

4. Store results for each prefix.

------------------------------------------------------

EXAMPLE:

Input:
products = ["mobile","mouse","moneypot","monitor","mousepad"]
searchWord = "mouse"

Sorted:
["mobile","moneypot","monitor","mouse","mousepad"]

Steps:
"m"   → ["mobile","moneypot","monitor"]
"mo"  → ["mobile","moneypot","monitor"]
"mou" → ["mouse","mousepad"]
"mous"→ ["mouse","mousepad"]
"mouse"→ ["mouse","mousepad"]

Output:
[
 ["mobile","moneypot","monitor"],
 ["mobile","moneypot","monitor"],
 ["mouse","mousepad"],
 ["mouse","mousepad"],
 ["mouse","mousepad"]
]

------------------------------------------------------

TIME COMPLEXITY:
- Sorting → O(n log n)
- For each prefix → O(n)
- Total → O(n log n + n * m)
  (n = number of products, m = length of searchWord)

SPACE COMPLEXITY:
- O(1) extra (excluding output)

------------------------------------------------------

KEY IDEA:
- Sort first
- Use prefix matching
- Stop after 3 matches

(Advanced version uses Trie for better performance)
*/

public class SearchSuggestionsSystem {

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();

        String prefix = "";

        for (char c : searchWord.toCharArray()) {
            prefix += c;
            List<String> suggestions = new ArrayList<>();

            for (String product : products) {
                if (product.startsWith(prefix)) {
                    suggestions.add(product);
                }
                if (suggestions.size() == 3) break;
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
