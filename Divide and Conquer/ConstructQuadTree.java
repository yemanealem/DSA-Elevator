public class ConstructQuadTree {

    public Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }

    // Build function for subgrid starting at (r, c) with size n
    private Node build(int[][] grid, int r, int c, int n) {
        if (n == 1) {
            return new Node(grid[r][c] == 1, true);
        }

        int half = n / 2;

        Node topLeft = build(grid, r, c, half);
        Node topRight = build(grid, r, c + half, half);
        Node bottomLeft = build(grid, r + half, c, half);
        Node bottomRight = build(grid, r + half, c + half, half);

        // If all 4 children are leaves and have the same value → merge
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
            topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        } else {
            return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
        }
    }

    // Optional: print Quad Tree (preorder)
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

    // Main method for testing
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
