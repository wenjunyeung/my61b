import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    */
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator cc = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("abccba"));
        assertTrue(palindrome.isPalindrome("abcfcba"));
        assertFalse(palindrome.isPalindrome("abcdef"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("c"));
        assertTrue(palindrome.isPalindrome("abcdcb", cc));
        assertTrue(palindrome.isPalindrome("abcadcb", cc));
        assertFalse(palindrome.isPalindrome("abcdefg", cc));
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("d", cc));
    }
}
