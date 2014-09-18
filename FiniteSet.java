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
