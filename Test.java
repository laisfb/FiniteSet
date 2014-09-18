import java.util.Random;
import java.lang.Math;

public class Test {

    public static int randomInt(int max) {
	return (new Random()).nextInt(max+1);
    }
    
    public static FiniteSet randomSet() {
	int size = randomInt(20);
	if(size==0)
	    return new Leaf();
	else {
	    Random rand = new Random();
	    int num = randomInt(100);
	    Branch b = new Branch(num);
	    for(int i=0; i<size; i++) {
		num = randomInt(100);
		b = (Branch) b.add(num);
	    }
	    return b;
	}
    }

    public static FiniteSet mySet(int num) {
	Branch b = new Branch(10);
	
	switch(num) {
	  case 1:
	      b = (Branch) b.add(5);
	      b = (Branch) b.add(7);
	      b = (Branch) b.add(25);
	      b = (Branch) b.add(43);
	      b = (Branch) b.add(14);
	      b = (Branch) b.add(37);
	      b = (Branch) b.remove(10);
	    break;
	    
	  case 2:
	      b = (Branch) b.add(7);
	      b = (Branch) b.add(25);
	      b = (Branch) b.add(37);
	      b = (Branch) b.remove(10);
	    break;
	    
	  case 3:
	      b = (Branch) b.add(5);
	      b = (Branch) b.add(43);
	      b = (Branch) b.add(14);
	      b = (Branch) b.add(59);
	      b = (Branch) b.remove(10);
	    break;
	    
	  default:
	      return randomSet();
	}

	return b;
    }

    
    
    public static void testEmpty(FiniteSet set) {
	System.out.println("After empting the set: " + set.empty());
    }

    public static void testCardinality(FiniteSet set) {
	System.out.println("Cardinality: " + set.cardinality());
    }
   
    public static void testIsEmpty(FiniteSet set) {
	System.out.println("Is this set empty?: " + set.isEmpty());
    }

    public static void testMember(FiniteSet set, int elem) {
	System.out.println("Is " + elem + " in the set?: " + set.member(elem));
    }

    public static FiniteSet testAdd(FiniteSet set, int elem) {
	System.out.println("Set after adding " + elem + ": " + set.add(elem));
	return set.add(elem);
    }
    
    public static FiniteSet testRemove(FiniteSet set, int elem) {
	System.out.println("Set after removing " + elem + ": " + set.remove(elem));
	return set.remove(elem);
    }
    
    public static void testUnion(FiniteSet s, FiniteSet t) {
	System.out.println(s.union(t));
    }

    public static void testInter(FiniteSet s, FiniteSet t) {
	System.out.println(s.inter(t));
    }

    public static void testDiff(FiniteSet s, FiniteSet t) {
	System.out.println(s.diff(t));
    }

    public static void testEqual(FiniteSet s, FiniteSet t) {
	System.out.println(s.equal(t));
    }
    
    public static void testSubset(FiniteSet s, FiniteSet t) {
	System.out.println(s.subset(t));	
    }
    
}
