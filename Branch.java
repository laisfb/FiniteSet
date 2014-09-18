public class Branch implements FiniteSet {
    int elt;
    FiniteSet left;
    FiniteSet right;
    
    Branch(int e, FiniteSet l, FiniteSet r) {
	this.elt = e;
	this.left = l;
	this.right = r;
    }
    
    Branch(int e) {
	this.elt = e;
	this.left = new Leaf();
	this.right = new Leaf();
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
	if(s.cardinality() != this.cardinality())
	    return false;
	else
	    return (this.diff(s)).equal(new Leaf()); 
	    //if the difference between them is empty, then they are equal
    }

    //this C s
    public boolean subset(FiniteSet s) {
	//if the difference between (this) and the intersection of them is empty
	//then (this) is a subset of s
	return (this.diff(this.inter(s))).equal(new Leaf());
    }

    public String toString() {
	return ( this.elt + " " + this.left + " " + this.right ) ;
    }
    
    public static void main(String[] args) {

	FiniteSet set = Test.randomSet();

        System.out.println("--- Created a random FiniteSet ---\n");
	System.out.println(set);
	
	
    }
}
