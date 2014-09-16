import java.util.Random;
import java.lang.Math;

interface FiniteSet {

    //Returns a fresh empty set
    public FiniteSet empty();
    
    //Returns the number of elements in the set
    public int cardinality();

    //Determines if the set is empty
    public boolean isEmpty();
    
    //Determines if elem is in the set
    public boolean member(int elem);

    //Returns a new set containing elem and everything in the set
    public FiniteSet add(int elem);
    
    //Returns a new set containing everything in the set, except elem
    public FiniteSet remove(int elem);
    
    //Returns a new set containing everything in the currenct set and s
    public FiniteSet union(FiniteSet s);

    //Returns a new set containing everything that is both in the current set and in s
    public FiniteSet inter(FiniteSet s);

    //Returns a new set containing everything in s except those that are in the
    //current set. (S - this)
    public FiniteSet diff(FiniteSet s);

    //Determines if the currenct set and s contain the same elements
    public boolean equal(FiniteSet s);
    
    //Determines if the current set is a subset of s
    public boolean subset(FiniteSet s);
    
}

class Leaf implements FiniteSet {
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
}

class Branch implements FiniteSet {
    int elt;
    FiniteSet left;
    FiniteSet right;
    int n = 0;

    private static int NUM_MAX = 100; //Greatest possible value
    
    Branch(int e, FiniteSet l, FiniteSet r) {
	this.elt = e;
	this.left = l;
	this.right = r;
	this.n = l.cardinality() + r.cardinality() + 1;
    }

    public FiniteSet empty() {
	return new Leaf();
    }

    public int cardinality() {
	return this.n;
    }

    public boolean isEmpty() {
	return false;
    }
    
    public boolean member(int elem) {
	if(elem==this.elt)
	    return true;
	else if(elem < this.elt)
	    return (this.left).member(elem);
	else
	    return (this.right).member(elem);
    }

    public FiniteSet add(int elem) {
	if(elem==this.elt)
	    return this;
	else if(elem < this.elt)
	    return new Branch(this.elt, (this.left).add(elem), this.right);
	else
	    return new Branch(this.elt, this.left, (this.right).add(elem));
    }
    
    public FiniteSet remove(int elem) {
        if(elem==this.elt)
	    return (this.left).union(this.right);
	else if(elem < this.elt)
	    return new Branch(this.elt, (this.left).remove(elem), this.right);
	else
	    return new Branch(this.elt, this.left, (this.right).remove(elem));
    }
    
    public FiniteSet union(FiniteSet s) {
	return this;
    }

    public FiniteSet inter(FiniteSet s) {
	return this;
    }
    
    public FiniteSet diff(FiniteSet s) {
	return this;
    }
    
    public boolean equal(FiniteSet s) {
	return false;
    }
    
    public boolean subset(FiniteSet s) {
	return false;
    }

    public static FiniteSet randomSet(int size) {
	if(size==0)
	    return new Leaf();
	else {
	    Random rand = new Random();
	    int num = rand.nextInt(NUM_MAX+1); //Returns a value in the range [0,NUM_MAX]
	    return new Branch(num, randomSet(size/2), randomSet(size/2));
	}
    }
    
    public static void main(String[] args) {

	Random rand = new Random();
	int size = rand.nextInt(21); //Returns a value in the range [0,21)	
	FiniteSet test = randomSet(size);

        System.out.println("--- Created a FiniteSet ---");
    }
}
