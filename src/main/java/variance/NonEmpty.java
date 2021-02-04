package variance;

public class NonEmpty implements IntSet {

    private Integer element;
    private IntSet left;
    private IntSet right;

    public NonEmpty(Integer element, IntSet left, IntSet right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean contains(Integer x) {
        if (x < element) {
            return left.contains(x);
        } else if (x > element) {
            return right.contains(x);
        } else {
            return true;
        }
    }

    @Override
    public IntSet include(Integer x) {
        if (x < element) {
            return new NonEmpty(element, left.include(x), right);
        } else if (x > element) {
            return new NonEmpty(element, right, right.include(x));
        } else {
            return this;
        }
    }
}
