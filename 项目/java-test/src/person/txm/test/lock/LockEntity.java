package person.txm.test.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockEntity {
	private int a = 0 ;
	ReentrantLock lock = new ReentrantLock();
	public void set(){
		a++;
		
	}
	public void read(){
		int i = a;
		System.out.println(i);
	}
}
