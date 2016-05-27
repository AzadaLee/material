package person.txm.test.thread;

public class WaitMethodTest {
	public static void main(String[] args) {
		System.out.println("start");
		try {
			new Object().wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
