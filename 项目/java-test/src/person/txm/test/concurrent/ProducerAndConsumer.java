package person.txm.test.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ProducerAndConsumer {
	public static final Integer ARRAYCAPCITY = 3;
	BlockingQueue<String> list = new ArrayBlockingQueue<String>(ARRAYCAPCITY);
	public static void main(String[] args) {
		ProducerAndConsumer a = new ProducerAndConsumer();
		a.list.add("aaa");
		a.list.add("bbb");
		new Thread(new Consumer(a.list)).start();
		new Thread(new Producer(a.list)).start();
	}
}
class Producer implements Runnable{
	BlockingQueue<String> list;
	public Producer(BlockingQueue<String> list){
		this.list = list;
	}
	@Override
	public void run() {
		synchronized (list) {
			while(true){
				if(this.list.size() == ProducerAndConsumer.ARRAYCAPCITY){
					System.out.println("队列已满");
					try {
						list.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				list.add("...");
				list.notifyAll();
				try {
					list.wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Consumer implements Runnable{
	private BlockingQueue<String> list;
	public Consumer(BlockingQueue<String> list){
		this.list = list;
	}
	@Override
	public void run() {
		synchronized (list) {
			while(true){
				if(list.size()==0){
					System.out.println("队列中暂无对象可取");
					try {
						list.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					String s = list.take();
					System.out.println(s);
					list.notifyAll();
					try {
						list.wait(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
