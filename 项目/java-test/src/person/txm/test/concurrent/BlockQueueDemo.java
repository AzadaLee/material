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
	 * ���޶�������֮��ʹ��add()����ʱ�������ʱ���޿��ÿռ䣬����׳��쳣
	 */
	public static void addTest(BlockingQueue<String> blockingQueue){
		for(int i=0;i<=10; i++){
			System.out.println(i);
			blockingQueue.add(i+"");
		}
	}
	/**
	 * @param blockingQueue
	 * ʹ��put����֮�����������û�п��ÿռ䣬����������״̬���ȴ��¿ռ�
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
