package person.txm.test.thread;

import java.util.concurrent.locks.ReentrantLock;

public class EntityRunner {
	public static void main(String[] args) {
		Entity entity = new Entity();
		Thread t1 = new Thread(new Twrite(entity));
		Thread t2 = new Thread(new Tread(entity));
		t1.start();
		t2.start();
	}
}
