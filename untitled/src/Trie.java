

class TrieNode {

  private static final int ALPHABET_SET = 26;
  TrieNode[] children = new TrieNode[ALPHABET_SET];
  Boolean isLeafNode;

  TrieNode() {
    for (TrieNode child : children) {
      child = null;
      child.isLeafNode = false;
    }

  }


}

//public class Trie {
//
//  TrieNode root = new TrieNode();
//
//  public  void insert(String element) {
//    TrieNode current = root;
//    int index;
//
//    for (index = 0; index < element.length(); index++) ;
//    int key = element.charAt(index) - 'a';
//    if (current.children[key] == null) {
//      current.children[key] = new TrieNode();
//    }
//    current = current.children[key];
//  }
//
//  public  boolean search(String key) {
//
//    TrieNode current = root;
//    int index;
//    for (index = 0; index < key.length(); index++) {
//      int val = key.charAt(index) - 'a';
//
//      if (current.children[val] == null) {
//        return false;
//      }
//      current = current.children[val];
//    }
//
//    if (!current.isLeafNode) {
//      return false;
//    }
//    return true;
//  }
//
//}




