//Time complexity:- 
//Insert = O(n * l) where n = number of words, 
//Search = O(n * l)

class Solution {

    class TrieNode {

        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        } 
    }

    TrieNode root;

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

    public String longestWord(String[] words) {
        
        this.root = new TrieNode();

        //first we insert all the words in the trie
        for(String word: words) {

            insert(word);
        }

        String result = "";
        int max = Integer.MIN_VALUE;

        //search for all the prefixes of a word in trie
        for(String str: words) {

            TrieNode curr = root;
            boolean flag = true;

            for(int i = 0; i < str.length() - 1; i++) {

                char ch = str.charAt(i);

                //prefix not present
                if(curr.children[ch - 'a'] == null) {

                    flag = false;
                    break;
                }

                curr = curr.children[ch - 'a'];

                //prefix present check whether it was part of dictionary
                if(!curr.isEnd) {

                    flag = false;
                    break;
                }
            }

                //word is found
                if(flag) {

                    //check if it is longest
                    if(str.length() > max) {

                        max = str.length();
                        result = str;
                    }

                    //If it is equal to previous result, compare lexicographically
                    if(str.length() == max) {

                        result = str.compareTo(result) < 0 ? str : result;
                    }
                }

        }

        return result;
    }
}


