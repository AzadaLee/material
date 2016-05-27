package person.txm.test.abs;

public abstract class AbsClass {
	protected abstract void exe();
	public void test(){
		System.out.println("this is test method !");
	}
	public void invoke(){
		System.out.println("start invoke");
		new Thread(new Runnable() {
			@Override
			public void run() {
				exe();
				test();
			}
		}).start();
	}
}
