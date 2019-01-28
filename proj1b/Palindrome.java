public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        if(word.length() == 0) {
            return res;
        }
        for(int i = 0; i < word.length(); i++) {
            res.addFirst(word.charAt(i));
        }
        return res;
    }

    /** checks if the given word is a Palindrome
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        Deque<Character> temp = wordToDeque(word);
        return isPalindromeHelper(temp);
    }

    private boolean isPalindromeHelper(Deque deque) {
        if(deque.size() <= 1) {
            return true;
        }
        if(deque.removeFirst() != deque.removeLast()) {
            return false;
        }
        return isPalindromeHelper(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> temp = wordToDeque(word);
        return isPalindromeHelper(temp, cc);
    }

    private boolean isPalindromeHelper(Deque deque, CharacterComparator cc) {
        if(deque.size() <= 1) {
            return true;
        }
        if(!cc.equalChars((char) deque.removeFirst(), (char) deque.removeLast())) {
            return false;
        }
        return isPalindromeHelper(deque, cc);
    }
}
