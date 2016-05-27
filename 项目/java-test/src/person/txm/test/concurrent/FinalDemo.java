package person.txm.test.concurrent;

public class FinalDemo {
	private final String s;
	final Object[] items = {"a","b","c","d"};
	public FinalDemo(String a){
		this.s = a;
	}
	//public FinalDemo(){}
	public static void main(String[] args) {
		String a = "hello2";
		final String b = "hello";
		String d = "hello";
		String c = b + 2;//相当于"hello"+2,也就是说在用到该final变量的地方，相当于直接访问的这个常量。不过要注意，只有在编译期间能确切知道final变量值的情况下，编译器才会进行这样的优化
		String e = d + 2;//相当于new StringBuder("hello").append(2)
		System.out.println((a == c));
		System.out.println((a == e));
		
		
		
		FinalDemo demo = new FinalDemo("");
		demo.getElement();
		for(int i=0; i<demo.items.length; i++){
			System.out.println(demo.items[i]+"..");
		}
	}
	public Object getElement(){
		final Object[] items = this.items;
		String s = (String) items[3];
		items[3] = null;
		for(int i=0; i<items.length; i++){
			System.out.println(items[i]);
		}
		return s;
	}
}
