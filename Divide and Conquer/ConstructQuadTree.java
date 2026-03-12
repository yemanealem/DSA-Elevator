/*
 // Definition for a QuadTree node.
 class Node {
     public boolean val;
     public boolean isLeaf;
     public Node topLeft;
     public Node topRight;
     public Node bottomLeft;
     public Node bottomRight;

     public Node() {}
     public Node(boolean val, boolean isLeaf) {
         this.val = val;
         this.isLeaf = isLeaf;
     }
     public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
         this.val = val;
         this.isLeaf = isLeaf;
         this.topLeft = topLeft;
         this.topRight = topRight;
         this.bottomLeft = bottomLeft;
         this.bottomRight = bottomRight;
     }
 }
*/

public class ConstructQuadTree {

    // Main function to construct the quad tree
    public Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }

    // Recursive helper function
    private Node build(int[][] grid, int r, int c, int size) {
        // Check if all values in this subgrid are the same
        boolean same = true;
        int val = grid[r][c];
        outer:
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (grid[i][j] != val) {
                    same = false;
                    break outer; // early exit
                }
            }
        }

        if (same) {
            return new Node(val == 1, true);
        }

        // Divide the grid into four quadrants
        int half = size / 2;
        Node topLeft = build(grid, r, c, half);
        Node topRight = build(grid, r, c + half, half);
        Node bottomLeft = build(grid, r + half, c, half);
        Node bottomRight = build(grid, r + half, c + half, half);

        // If all four children are leaves and have same value, merge them
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
            topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        }

        // Otherwise, create a parent node with four children
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    // Helper to print quad tree (preorder)
    public void printQuadTree(Node node, String indent) {
        if (node == null) return;
        System.out.println(indent + "Node(isLeaf=" + node.isLeaf + ", val=" + node.val + ")");
        if (!node.isLeaf) {
            printQuadTree(node.topLeft, indent + "  topLeft-> ");
            printQuadTree(node.topRight, indent + "  topRight-> ");
            printQuadTree(node.bottomLeft, indent + "  bottomLeft-> ");
            printQuadTree(node.bottomRight, indent + "  bottomRight-> ");
        }
    }

    // Main method to test
    public static void main(String[] args) {
        int[][] grid = {
            {1,1,0,0},
            {1,1,0,0},
            {0,0,1,1},
            {0,0,1,1}
        };

        ConstructQuadTree quadTree = new ConstructQuadTree();
        Node root = quadTree.construct(grid);

        System.out.println("Quad Tree Structure:");
        quadTree.printQuadTree(root, "");
    }
}
