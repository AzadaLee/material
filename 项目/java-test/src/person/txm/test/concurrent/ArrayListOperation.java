package person.txm.test.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author work
 *ʵ��֤�������̲߳���һ��list,������һ���ڽ��е�������������һ��������ɾ�Ĳ������������������쳣
 *ConcurrentModificationException
 */
public class ArrayListOperation {
	public List<String> list = new ArrayList<String>();
	public static void main(String[] args) {
		ArrayListOperation a = new ArrayListOperation();
		a.list.add("aaa");
		a.list.add("bbb");
		a.list.add("ccc");
		a.list.add("ddd");
		new Thread(new Put(a.list)).start();
		new Thread(new Get(a.list)).start();
	}
}
class Put implements Runnable{
	private List<String> list;
	public Put(List<String> list){
		this.list = list;
	}
	@Override
	public void run() {
		for(int i=0 ; i<5 ; i++){
			list.add(i+"...");
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Get implements Runnable{
	private List<String> list;
	public Get(List<String> list){
		this.list = list;
	}
	
	@Override
	public void run() {
		final List<String> list = this.list;
		try {
			Thread.currentThread().sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s : list){
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(s);
		}
	}
	
}
