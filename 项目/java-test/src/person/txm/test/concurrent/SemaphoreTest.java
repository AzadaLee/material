package person.txm.test.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	private final Semaphore semaphore = new Semaphore(1, true);
	
}
class Acquire implements Runnable{
	private final Semaphore s;
	public Acquire(Semaphore s){
		this.s = s;
	}
	@Override
	public void run() {
		try {
			s.acquire();
			System.out.println("acquire semaphore");
			s.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Release implements Runnable{
	private final Semaphore s;
	public Release(Semaphore s){
		this.s = s;
	}
	@Override
	public void run() {
		try {
			s.acquire();
			System.out.println("Release semaphore......");
			s.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}