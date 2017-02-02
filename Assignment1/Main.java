import java.util.ArrayList;

public class Main {
	
    public static void main(String[] args) {
    	State s1 = new State();
    	State s2 = (State) s1.clone();
    	int identity = 1;
    	Point myAction = new Point(3,2);
    	s2.updateState(myAction, identity);
    	int[][] matrix1 = s1.getMatrix();
    	int[][] matrix2 = s2.getMatrix();
    	System.out.println(matrix1[3][3]);
    	System.out.println(matrix2[3][3]);
    	

    }
}
