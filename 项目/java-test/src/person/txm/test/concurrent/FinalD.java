package person.txm.test.concurrent;

public class FinalD {
	private final Object[] items = {"a","b","c"};
	public static void main(String[] args) {
		FinalD finalD = new FinalD();
		//finalD.doE(finalD.items);
		finalD.doE();
		for(int i=0;i<finalD.items.length;i++){
			System.out.println(finalD.items[i]);
		}
	}
	public void doE(){
		final Object[] items = this.items;
		items[2] = "h";
	}
}

