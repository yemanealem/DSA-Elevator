import java.util.*;

public class WordSearchII {

    // Trie Node
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    // Main solution method
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

    // DFS Backtracking
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

        // Already visited or not in Trie
        if (ch == '#' || node.children[ch - 'a'] == null) {
            return;
        }

        node = node.children[ch - 'a'];
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        board[r][c] = '#';

        dfs(board, r + 1, c, node, result);
        dfs(board, r - 1, c, node, result);
        dfs(board, r, c + 1, node, result);
        dfs(board, r, c - 1, node, result);
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