import java.util.HashMap;
import java.util.Map;

public class Trie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("car");
        trie.insert("cap");
        trie.printTrie();

        trie.delete("car");
        trie.printTrie();

        System.out.println(trie.search("cat"));
        System.out.println(trie.search("car"));
        System.out.println(trie.startsWith("ca"));
        System.out.println(trie.search("ce"));

    }
    class Node{
        Map<Character,Node> children = new HashMap<>();
        boolean isWord = false;
    }
    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word){
        Node temp = root;
        for(char c : word.toCharArray()){
            temp.children.putIfAbsent(c, new Node());
            temp = temp.children.get(c);
        }
        temp.isWord = true;
    }
    public boolean delete(String word){
        return delete(word, 0, root);
    }
    public boolean delete(String word, int index, Node node){
        if(index == word.length()){
            if(node.isWord == false) {
                return false; //word doesn't exist
            }
            node.isWord = false;
            if(node.children.isEmpty()) {
                return true; //no other node is using this
            }
            else {
                return false;
            }
        }
        char ch = word.charAt(index);
        Node child = node.children.get(ch);
        if(child == null) {
            return false;
        }
        boolean canDelete = delete(word, index+1, child);
        if(canDelete){
            node.children.remove(ch);
            if(node.children.isEmpty() && !node.isWord){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    public void printTrie() {
        System.out.println("Trie");
        printTrie(root, "");
    }

    private void printTrie(Node node, String prefix) {
        if (node.isWord) {
            System.out.println(prefix);
        }
        for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
            printTrie(entry.getValue(), prefix + entry.getKey());
        }
    }

    public boolean search(String word){
        Node temp = root;
        for(char c : word.toCharArray()){
            if(temp.children.containsKey(c) == false){
                return false;
            }
            temp = temp.children.get(c);
        }
        return temp.isWord;
    }
    public boolean startsWith(String prefix){
        Node temp = root;
        for(char c : prefix.toCharArray()){
            if(temp.children.containsKey(c) == false){
                return false;
            }
            temp = temp.children.get(c);
        }
        return true;
    }




}
