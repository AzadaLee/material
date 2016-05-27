package person.txm.test.string;


public class StringTest {
	public static void main(String[] args) throws Exception {
		String str = "操作成功!"  ;
		String str2 = "操作失败!";
		byte[] b1 = str.getBytes("GBK");
		byte[] b2 = str2.getBytes("GBK");
		String s1 = new String(b1, "utf-8");
		String s2 = new String(b2, "utf-8");
		System.out.println(s1);
		System.out.println(s2);
	}
}
