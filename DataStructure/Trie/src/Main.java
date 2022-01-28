public class Main {

    public static void main(String[] args) {
	    Trie trie = new Trie();
	    trie.add("shake");
	    trie.add("shave");
	    System.out.println(trie.contains("shake"));
        System.out.println(trie.contains("sh"));
        System.out.println(trie.contains("wo"));
    }
}
