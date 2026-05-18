/*
    LeetCode: Word Search II

    Question:
    Given an m x n board of characters and a list of words,
    return all words that can be formed in the board.

    Rules:
    - Words are formed using adjacent cells
      (up, down, left, right).
    - The same cell cannot be used more than once
      in a single word.

    Example:
    board =
    [
      ['o','a','a','n'],
      ['e','t','a','e'],
      ['i','h','k','r'],
      ['i','f','l','v']
    ]

    words = ["oath","pea","eat","rain"]

    Output:
    ["oath","eat"]


    --------------------------------------------------
    HOW THIS SOLUTION WORKS
    --------------------------------------------------

    This solution uses:
    1. Trie (Prefix Tree)
    2. DFS + Backtracking

    Step 1: Build Trie
    -------------------
    All words are stored in a Trie.

    Example:
    words = ["oath", "eat"]

    Trie helps us:
    - quickly check prefixes
    - stop searching early if no word matches

    Step 2: DFS Search
    -------------------
    Start DFS from every cell in the board.

    During DFS:
    - move in 4 directions
    - mark visited cells using '#'
    - restore cell after recursion (backtracking)

    Step 3: Pruning
    ----------------
    If current character path does not exist
    in Trie, stop immediately.

    This makes the algorithm much faster.


    --------------------------------------------------
    TIME COMPLEXITY
    --------------------------------------------------

    Building Trie:
    O(total characters in all words)

    DFS Search:
    Worst case:
    O(M * N * 4^L)

    Where:
    M = rows
    N = columns
    L = maximum word length

    But Trie pruning reduces unnecessary searches.


    --------------------------------------------------
    SPACE COMPLEXITY
    --------------------------------------------------

    Trie storage:
    O(total characters in all words)

    Recursion stack:
    O(L)

*/

import java.util.*;

public class WordSearchII {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        TrieNode root = buildTrie(words);

        List<String> result = new ArrayList<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board,
                     int r,
                     int c,
                     TrieNode node,
                     List<String> result) {

        // Boundary check
        if (r < 0 || c < 0 ||
            r >= board.length ||
            c >= board[0].length) {
            return;
        }

        char ch = board[r][c];

        // Stop if visited or character not in Trie
        if (ch == '#' || node.children[ch - 'a'] == null) {
            return;
        }

        node = node.children[ch - 'a'];

        // Word found
        if (node.word != null) {
            result.add(node.word);

            // Avoid duplicates
            node.word = null;
        }

        // Mark visited
        board[r][c] = '#';

        // Explore 4 directions
        dfs(board, r + 1, c, node, result);
        dfs(board, r - 1, c, node, result);
        dfs(board, r, c + 1, node, result);
        dfs(board, r, c - 1, node, result);

        // Restore original character
        board[r][c] = ch;
    }

    private TrieNode buildTrie(String[] words) {

        TrieNode root = new TrieNode();

        for (String word : words) {

            TrieNode current = root;

            for (char ch : word.toCharArray()) {

                int index = ch - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                current = current.children[index];
            }

            current.word = word;
        }

        return root;
    }

    public static void main(String[] args) {

        WordSearchII solution = new WordSearchII();

        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> result = solution.findWords(board, words);

        System.out.println("Found Words: " + result);
    }
}