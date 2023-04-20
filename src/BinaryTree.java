import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node node, int value) {
        if (node == null) {
            return false;
        } else if (node.value == value) {
            return true;
        } else if (value < node.value) {
            return contains(node.left, value);
        } else {
            return contains(node.right, value);
        }
    }

    public void delete(int val){
        Node deleted = delete(root,val );
        System.out.println("Deleted:"+deleted.value);
    }

    public Node delete(Node root, int data) {
        if (root == null) {
            return root;
        }
        if (data < root.value) {
            root.left = delete(root.left, data);
        }
        else if (data > root.value) {
            root.right = delete(root.right, data);
        }
        else {
            // Case 1: Node has no child
            if (root.left == null && root.right == null) {
                root = null;
            }
            // Case 2: Node has one child
            else if (root.left == null) {
                root = root.right;

            }
            else if (root.right == null) {
                root = root.left;
            }
            // Case 3: Node has two children
            else {
                // Find the minimum value in the right subtree
                Node minValueNode = findMin(root.right);
                // Replace the node's data with the minimum value in the right subtree
                root.value = minValueNode.value;
                // Delete the minimum value node in the right subtree
                root.right = delete(root.right, minValueNode.value);
            }
        }

        return root;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    public void printTree() {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (Node node : nodes) {
                printWhitespaces(firstSpaces - i);
                if (node == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (node.left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (node.right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private boolean isAllElementsNull(List<Node> list) {
        for (Node node : list) {
            if (node != null)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);
        tree.insert(10);


        System.out.print("In-order traversal:");
        tree.traverseInOrder();

        System.out.print("\nPre-order traversal:");
        tree.traversePreOrder();

        System.out.print("\nPost-order traversal:");
        tree.traversePostOrder();
        System.out.println();
        tree.printTree();


        tree.traverseInOrder();
        System.out.println();
        tree.delete(9);
        tree.traverseInOrder();
        System.out.println();
        tree.printTree();
    }
}

