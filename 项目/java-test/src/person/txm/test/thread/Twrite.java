package person.txm.test.thread;

public class Twrite implements Runnable{
	private Entity entity ;
	public Twrite(Entity entity){
		this.entity = entity ;
	}
	@Override
	public void run() {
		try {
			entity.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
