package person.txm.test.thread;

public class Entity {
	private volatile int a = 0;
	private volatile boolean flag = false;
	
	public void write() throws Exception{
		//this.wait();
		a = 2;
		Thread.currentThread().sleep(10);
		flag = true;
	}
	
	public void read(){
		System.out.println(a+"....");
		if(flag){
			int i = a*a;
			System.out.println(i);
		}
	}
}
