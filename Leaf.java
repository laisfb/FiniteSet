public class Leaf implements FiniteSet {
    Leaf() { }

    public FiniteSet empty() {
	return new Leaf();
    }

    public int cardinality() {
	return 0;
    }

    public boolean isEmpty() {
	return true;
    }

    public boolean member(int elem) {
	return false;
    }

    public FiniteSet add(int elem) {
	return new Branch(elem, new Leaf(), new Leaf());
    }

    public FiniteSet remove(int elem) {
	return new Leaf();
    }

    public FiniteSet union(FiniteSet s) {
	return s;
    }

    public FiniteSet inter(FiniteSet s) {
	return new Leaf();
    }
    
    public FiniteSet diff(FiniteSet s) {
	return s;
    }

    public boolean equal(FiniteSet s) {
	return s.isEmpty(); //they are only equal if s is also a Leaf
    }

    public boolean subset(FiniteSet s) {
	return s.isEmpty(); //s in only a subset of Leaf if s is also a Leaf
    }

    public String toString() {
	return "";
    }
}
