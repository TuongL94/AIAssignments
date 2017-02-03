public class Main {
	
    public static void main(String[] args) {
    	State s = new State();
    	int identity = 1;
    	Point myAction = new Point(2,3);
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <= 7; j++){
				if(s.getStateMatrix()[i][j] == -1) {
					System.out.print("w"+"  ");
				} else if(s.getStateMatrix()[i][j] == 1){
					System.out.print("b"+"  ");
				} else {
					System.out.print(s.getStateMatrix()[i][j]+"  ");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
    	s.updateState(myAction, identity);
    	int[][] matrix = s.getStateMatrix();
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <= 7; j++){
				if(s.getStateMatrix()[i][j] == -1) {
					System.out.print("w"+"  ");
				} else if(s.getStateMatrix()[i][j] == 1){
					System.out.print("b"+"  ");
				} else {
					System.out.print(s.getStateMatrix()[i][j]+"  ");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		Point a = new Point(2,2);
		s.updateState(a, -identity);
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <= 7; j++){
				if(s.getStateMatrix()[i][j] == -1) {
					System.out.print("w"+"  ");
				} else if(s.getStateMatrix()[i][j] == 1){
					System.out.print("b"+"  ");
				} else {
					System.out.print(s.getStateMatrix()[i][j]+"  ");
				}
			}
			System.out.print("\n");
		}
    }
}
