package person.txm.test.thread;

public class Tread implements Runnable{
	private Entity entity;
	public Tread(Entity entity){
		this.entity = entity;
	}
	@Override
	public void run() {
		entity.read();
	}

}
