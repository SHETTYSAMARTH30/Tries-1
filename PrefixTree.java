//TC:- O(L) where L is length
class Trie {

    class TrieNode {

        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        } 
    }

    TrieNode root;

    public Trie() {
        
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        
        //we will insert word to Trie
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);
            
            //if char is not present :- Eg:- app, apps
            if(curr.children[ch - 'a'] == null) {
                //if there is node at particular index, then it means that character is present
                curr.children[ch - 'a'] = new TrieNode();
            }

            curr = curr.children[ch - 'a'];
        }

        //we mark the end of word
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        
        //we need to search from the root of trie
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);
            
            //if letter is not present then word is not present
            if(curr.children[ch - 'a'] == null)
                return false;
            
            //go to next letter in the word
            curr = curr.children[ch - 'a'];
        }

        //if we find the entire word then check the flag 
        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) {

        TrieNode curr = root;

        //search for word similar to search
        for(int i = 0; i < prefix.length(); i++) {

            char ch = prefix.charAt(i);
            if(curr.children[ch - 'a'] == null)
                return false;
            
            curr = curr.children[ch - 'a'];
        }
        
        //we are just checking prefix so does not matter flag is true or false
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */