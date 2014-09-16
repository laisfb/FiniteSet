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

    //Returns a new set containing everything in s except those that are in the current set
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

    public String toString() {
	return "";
    }
}

class Branch implements FiniteSet {
    int elt;
    FiniteSet left;
    FiniteSet right;

    private static int NUM_MAX = 100; //Greatest possible value
    
    Branch(int e, FiniteSet l, FiniteSet r) {
	this.elt = e;
	this.left = l;
	this.right = r;
    }

    public FiniteSet empty() {
	return new Leaf();
    }

    public int cardinality() {
	return (left.cardinality() + right.cardinality() + 1);
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
	//union of left branch with right branch with s
	//then add "elt" ---> func add won't allow it to have duplicates
	return (((this.left).union(this.right)).union(s)).add(this.elt);
    }

    public FiniteSet inter(FiniteSet s) {
	if(s.member(this.elt))
	    return new Branch(this.elt, s.inter(this.left), s.inter(this.right));
	else //union of both intersections
	    return (s.inter(this.left)).union(s.inter(this.right));
    }

    //S - this
    public FiniteSet diff(FiniteSet s) {
	//if "elt" is in s, then it can't be in "diff"
	if(s.member(this.elt))
	    //remove elt from s and find the difference between
	    //s and what's left of (this)
	    return ((this.left).union(this.right)).diff(s.remove(this.elt));
	else
	    return ((this.left).union(this.right)).diff(s);
	    
    }
    
    public boolean equal(FiniteSet s) {
	//if the difference between them is empty, then they are equal
	return (this.diff(s)).equal(new Leaf());
    }

    //this C s
    public boolean subset(FiniteSet s) {
	//if the difference between (this) and the intersection of them is empty
	//then (this) is a subset of s
	return ((this.inter(s)).diff(this)).equal(new Leaf());
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

    public String toString() {
	return ( this.elt + " " + this.left + " " + this.right ) ;
    }
    
    public static void main(String[] args) {

	Random rand = new Random();
	int size = rand.nextInt(21); //Returns a value in the range [0,21)	
	FiniteSet test = randomSet(size);

        System.out.println("--- Created a random FiniteSet ---\n");
	System.out.println("--- Some tests for a leaf ---");

	FiniteSet tree1 = new Leaf();	
	System.out.println("Cardinality: " + tree1.cardinality() + " (should be 0)");
	System.out.println("Is it empty?: " + tree1.isEmpty() + " (should be true)");
	System.out.println("Is 5 a member?: " + tree1.member(5) + " (should be false)");
	

	System.out.println("\n--- Some tests for a branch ---");
	FiniteSet tree2 = new Branch(10, new Leaf(), new Leaf());	
	System.out.println("Cardinality: " + tree2.cardinality() + " (should be 1)");
	System.out.println("Is it empty?: " + tree2.isEmpty() + " (should be false)");

	System.out.println("Adding 8 and 20 to 'tree2' and sending it back to 'tree2");
	tree2 = tree2.add(20);
	tree2 = tree2.add(8);	
	System.out.println("Cardinality: " + tree2.cardinality() + " (should be 3)");

	System.out.println("Removing 15 from 'tree2' and sending it back to 'tree2'");
	tree2 = tree2.remove(15);
	System.out.println("Cardinality: " + tree2.cardinality() + " (should be 3)");

	System.out.println("Removing 15 from 'tree2' and sending it back to 'tree2'");
	tree2 = tree2.remove(15);
	System.out.println("Cardinality: " + tree2.cardinality() + " (should be 3)");

	System.out.println("Trying to add 20 again and sending it back to 'tree2");
	tree2 = tree2.add(20);
	System.out.println("Cardinality: " + tree2.cardinality() + " (should be 3)");

	System.out.println("Removing 8 from 'tree2' and sending it back to 'tree2");
	tree2 = tree2.remove(8);
	System.out.println("Cardinality: " + tree2.cardinality() + " (should be 2)");
	
	System.out.println("Making 'tree2' be empty and sending it to 'tree3'");
	FiniteSet tree3 = tree2.empty();
	System.out.println("Cardinality: " + tree3.cardinality() + " (should be 0)");

	System.out.println("Adding numbers to 'tree3' and sending it back to 'tree3");
	tree3 = tree3.add(20);
	tree3 = tree3.add(10);
	tree3 = tree3.add(8);
	tree3 = tree3.add(15);
	tree3 = tree3.add(3);	
	System.out.println("Cardinality: " + tree3.cardinality() + " (should be 5)");

	System.out.println("\nTree2: " + tree2);
	System.out.println("Tree3: " + tree3);

	System.out.println("\nIntersection of 'tree2' and 'tree3': " + tree2.inter(tree3) + " (should contain 10 20)");
	System.out.println("Union of 'tree2' and 'tree3': " + tree2.union(tree3) + "\n(should be equal to tree3)");

	System.out.println("\nAdding 2 to 'tree2' and sending it back to 'tree2");
	tree2 = tree2.add(2);
	System.out.println("Union of 'tree2' and 'tree3': " + tree2.union(tree3));
	System.out.println("Removing 2 to 'tree2' and sending it back to 'tree2");
	tree2 = tree2.remove(2);
  
	System.out.println("\ntree3 - tree2: " + tree2.diff(tree3) + " (should contain 8 3 15)");
	System.out.println("tree2 - tree3: " + tree3.diff(tree2) + " (should be empty)");
	
	System.out.println("\nIs 'tree2' equal to 'tree3'?: " + tree2.equal(tree3) + " (should be false)");
	System.out.println("Is 'tree2' a subset of 'tree3'?: " + tree2.subset(tree3) + " (should be true)");
	System.out.println("Is 'tree3' a subset of 'tree2'?: " + tree3.subset(tree2) + " (should be false)");
	
	
    }
}
