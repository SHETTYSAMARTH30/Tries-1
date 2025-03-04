//TC:- (n + m)l

class Solution {

    class TrieNode {

        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public void insert(String word) {

        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);
            if(curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new TrieNode();
            
            curr = curr.children[ch - 'a'];
        }

        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        
        this.root = new TrieNode();

        //First we insert all the strings into Trie
        for(String word: dictionary) { //n * l

            insert(word);
        }

        //Now we will search for each word in trie, if we find word or short word then append
        //If we do not find word then use same word
        String[] strArr = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < strArr.length; i++) { //m * l

            String currWord = strArr[i];
            
            if(i != 0)
                result.append(" ");

            //we will search for the word
            TrieNode curr = root;
            StringBuilder replacement = new StringBuilder();

            for(int j = 0; j < currWord.length(); j++) {
                
                char ch = currWord.charAt(j);

                //a. if the letter is not present
                //b. if dict word found
                if(curr.children[ch - 'a'] == null || curr.isEnd)
                    break;
                
                //keep on adding prefix/characters from dictionary
                replacement.append(ch);
                
                curr = curr.children[ch - 'a'];
            }

            //if shortest word from dictionary present then isEnd = true
            if(curr.isEnd) {
                
                result.append(replacement);
            }
            else {
                
                //if short word not present, then no change
                result.append(currWord);
            }

        }

        return result.toString();
    }
}