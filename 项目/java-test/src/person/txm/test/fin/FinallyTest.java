package person.txm.test.fin;


public class FinallyTest {
	public static void main(String[] args) {
		try{
			int a = 1/0;
			System.out.println(1);
		}catch(IndexOutOfBoundsException e){
			System.out.println(2);
		}finally{
			System.out.println(3);
		}
	}
}
