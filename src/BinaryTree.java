public class BinaryTree {

    private Node root;

    private class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public BinaryTree() {
        root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        insert(root, value);
    }

    private void insert(Node node, int value) {
        if (node.left == null) {
            node.left = new Node(value);
            return;
        }
        if (node.right == null) {
            node.right = new Node(value);
            return;
        }
        // If both left and right subtrees are already full,
        // recursively insert the value in the left subtree
        // of the node's left child
        insert(node.left, value);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.value);
        }
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);

        System.out.print("In-order traversal:");
        tree.traverseInOrder();

        System.out.print("\nPre-order traversal:");
        tree.traversePreOrder();

        System.out.print("\nPost-order traversal:");
        tree.traversePostOrder();
    }
}

