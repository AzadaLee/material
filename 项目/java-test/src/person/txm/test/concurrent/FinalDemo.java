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
		String c = b + 2;//�൱��"hello"+2,Ҳ����˵���õ���final�����ĵط����൱��ֱ�ӷ��ʵ��������������Ҫע�⣬ֻ���ڱ����ڼ���ȷ��֪��final����ֵ������£��������Ż�����������Ż�
		String e = d + 2;//�൱��new StringBuder("hello").append(2)
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
