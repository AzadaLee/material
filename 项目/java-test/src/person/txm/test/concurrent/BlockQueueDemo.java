package person.txm.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockQueueDemo {
	public static void main(String[] args) throws Exception{
		BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>(10);
		blockingQueue.remainingCapacity();
		//addTest(blockingQueue);
//		List<String> list = new ArrayList<String>();
//		drainToTest(blockingQueue,list);
//		for(String s : list){
//			System.out.println(s);
//		}
		takeTest(blockingQueue);
	}
	
	/**
	 * @param blockingQueue
	 * 当限定了容量之后，使用add()方法时，如果此时已无可用空间，则会抛出异常
	 */
	public static void addTest(BlockingQueue<String> blockingQueue){
		for(int i=0;i<=10; i++){
			System.out.println(i);
			blockingQueue.add(i+"");
		}
	}
	/**
	 * @param blockingQueue
	 * 使用put方法之后，如果队列中没有可用空间，则会进入阻塞状态，等待新空间
	 */
	public static void putTest(BlockingQueue<String> blockingQueue){
		for(int i=0;i<=10; i++){
			System.out.println(i);
			try {
				blockingQueue.put(i+"");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void drainToTest(BlockingQueue<String> blockingQueue,List<String> list){
		for(int i = 0;i < 10; i++){
			System.out.println(i);
			try {
				blockingQueue.put(i+"");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int r = blockingQueue.drainTo(list);
		System.out.println(r+"..........");
	}
	
	public static void takeTest(BlockingQueue<String> blockingQueue) throws InterruptedException{
		for(int i = 0;i < 10; i++){
			try {
				blockingQueue.put(i+"");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(blockingQueue.remove());
	}
}
