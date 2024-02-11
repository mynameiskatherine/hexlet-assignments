package exercise;

import java.lang.StringBuilder;
import java.lang.String;

// BEGIN
public class ReversedSequence implements CharSequence {

    private char[] reversed;

    public ReversedSequence(String string) {
        reversed = new StringBuilder(string).reverse().toString().toCharArray();
    }

    @Override
    public char charAt(int index) {
        return reversed[index];
    }

    @Override
    public int length() {
        return reversed.length;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return String.valueOf(reversed).subSequence(start, end);
    }

    @Override
    public String toString() {
        return String.valueOf(reversed);
    }
}
// END
