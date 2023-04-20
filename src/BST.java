import java.util.*;

public class BST {
    public static void main(String[] args) {
        BT tree = new BT();
        tree.insert(100);
        tree.insert(200);
        tree.insert(33);
        tree.insert(15);
        tree.insert(7);
        tree.insert(30);
        tree.insert(300);
        tree.insert(150);


        tree.traverseInOrder();
        tree.traversePreOrder();
        tree.traversePostOrder();



        tree.getMinimum();
        tree.breadthFirstSearch();
        tree.depthFirstSearch();


        tree.getHeight();
        tree.closestValue(126);
        tree.isValidBst();
    }

    }
    class Node{
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }
    class BT{
        private Node root;

        public BT() {
            this.root = null;
        }
        public void insert(int val){
            if (root == null){
                root = new Node(val);
                return;
            }
            else{
                insert(root,val);
            }
        }
        public Node insert(Node root, int val){
            if(root == null){
                root = new Node(val);
                return root;
            }
            if(val < root.value){
                root.left = insert(root.left, val);
            }
            else if(val > root.value){
                root.right = insert(root.right, val);
            }
            return root;
        }

        public void traverseInOrder(){
            System.out.println("Inorder");
            printTree();

            traverseInOrder(root);
            System.out.println();
        }
        public void traverseInOrder(Node node){
            if(node != null){
                traverseInOrder(node.left);
                System.out.print(node.value+" ");
                traverseInOrder(node.right);
            }
        }
        public void traversePreOrder(){
            System.out.println("Preorder");
            printTree();
            traversePreOrder(root);
            System.out.println();
        }
        public void traversePreOrder(Node node){
            if(node != null){
                System.out.print(node.value + " ");
                traversePreOrder(node.left);
                traversePreOrder(node.right);
            }
        }
        public void traversePostOrder(){
            System.out.println("Postorder");
            printTree();
            traversePostOrder(root);
            System.out.println();

        }
        public void traversePostOrder(Node node){
            if(node != null){
                traversePostOrder(node.left);
                traversePostOrder(node.right);
                System.out.print(node.value + " ");
            }
        }

        public boolean contains(int val){
            return contains(root,val);
        }
        public boolean contains(Node node, int val){
            if(node == null)
                return false;
            else if (node.value == val)
                return true;
             else if (val < node.value)
                 return contains(node.left, val);
             else
                return contains(node.right, val);
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
        public void getMinimum(){
            System.out.println("Minimum:"+getMinimum(root).value);
        }
        public Node getMinimum(Node root){
            if(root.left != null){
               return getMinimum(root.left);
            }
            return root;
        }
        public void breadthFirstSearch(){
            System.out.println("BFS");
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                Node node = queue.poll();
                System.out.print(node.value+" ");
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        public void depthFirstSearch(){
            System.out.println("\nDFS");
            depthFirstSearch(root);
        }
        public void depthFirstSearch(Node node){
            if (node == null) {
                return;
            }

            Stack<Node> stack = new Stack<>();
            stack.push(node);

            while (!stack.isEmpty()) {
                Node current = stack.pop();
                System.out.print(current.value + " ");

                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }

        public void recursiveDFS(){
           recursiveDFS(root);
        }
        public void recursiveDFS(Node node) {
            if (node == null) {
                return;
            }

            // Process the current node
            System.out.print(node.value + " ");

            // Recursively visit the left and right subtrees
            recursiveDFS(node.left);
            recursiveDFS(node.right);
        }
        public void getHeight(){
            System.out.println("\nHeight:"+(getHeight(root)-1));
        }
        public int getHeight(Node node){
            if(node == null)
                return 0;
            else {
                int leftDepth = getHeight(node.left);
                int rightDepth = getHeight(node.right);
                return Math.max(leftDepth,rightDepth)+1;
            }
        }
        public void closestValue(int target){
            Node current = root;
            int closest = current.value;
            while (current != null){
                if(Math.abs(current.value - target)  < Math.abs(closest - target)){
                    closest = current.value;
                }
                if(target>current.value)
                    current = current.right;
                else
                    current = current.left;
            }
            System.out.println("\nClosest value:"+closest);
        }
        public void isValidBst(){
            System.out.println(isValidBst(root, null, null));
        }

        private boolean isValidBst(Node root, Integer min, Integer max) {
            if(root == null)
                return  true;
            if((min != null && root.value <= min) || (max != null && root.value >= max)){
                return false;
            }
            return (isValidBst(root.left, min, root.value)) && (isValidBst(root.right, root.value, max));
        }
        public void delete(int val){
            delete(root, val);
        }

        Node delete(Node root, int val){
            if(root == null)
                return root;
            if(val < root.value){
                root = delete(root.left, val);
            }
            else{
                root = delete(root.right, val);
            }
            if(root.value == val){
                //case 1 - no children
                if(root.left == null && root.right == null){
                    root = null;
                    //case 2 - either left or right child
                } else if (root.left == null) {
                    root = root.right;
                } else if (root.right == null) {
                    root = root.left;
                }//case 3 - has left and right children
                else{
                    Node newNode = getMin(root.right);
                    root.value = newNode.value;
                    root.right = delete(root.right,newNode.value);
                }
            }
            return root;
        }
        Node getMin(Node node){
            if(node.left != null)
                return getMin(node.left);
            else return node;
        }


}