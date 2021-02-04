package variance;

public class Empty implements IntSet {

    @Override
    public boolean contains(Integer x) {
        return false;
    }

    @Override
    public IntSet include(Integer x) {
        return new NonEmpty(x, new Empty(), new Empty());
    }
}
