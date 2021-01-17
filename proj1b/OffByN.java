public class OffByN implements CharacterComparator {
    private int gap;
    public OffByN(int n) {
        gap = n;
    }

    /** Returns true if characters are equal by the rules of the implementing class. */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == gap;
    }
}
