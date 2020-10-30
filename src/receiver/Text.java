package receiver;

import exception.TextException;

import static exception.ExceptionUtils.WRONG_VAL_REC;

/**
 * Model
 * Implement here how to perform the operations of the request
 */
public class Text {
    private StringBuilder sb;

    public Text() {
        sb = new StringBuilder();
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public void addFirst(String s) {
        sb.insert(0, s);
    }

    public void addLast(String s) {
        sb.append(s);
    }

    public String removeFirstK(int k) {
        String removed;
        if (k > sb.length()) {
            removed = sb.toString();
            sb = new StringBuilder();
        } else {
            removed = sb.substring(0, k);
            sb.delete(0, k);
        }
        return removed;
    }

    public String removeLastK(int k) {
        String removed;
        if (k > sb.length()) {
            removed = sb.toString();
            sb = new StringBuilder();
        } else {
            removed = sb.substring(sb.length() - k, sb.length());
            sb.delete(sb.length() - k, sb.length());
        }
        return removed;
    }

    public void removeFirst(String s) throws TextException {
        if (s.length() > sb.length() || !sb.substring(0, s.length()).equals(s))
            throw new TextException(WRONG_VAL_REC);
        sb.delete(0, s.length());
    }

    public void removeLast(String s) throws TextException {
        if (s.length() > sb.length() || !sb.substring(sb.length() - s.length(), sb.length()).equals(s))
            throw new TextException(WRONG_VAL_REC);
        sb.delete(sb.length() - s.length(), sb.length());
    }
}
