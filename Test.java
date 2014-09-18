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
    
}
