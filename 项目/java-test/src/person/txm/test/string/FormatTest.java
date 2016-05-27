package person.txm.test.string;

public class FormatTest {
	public static void main(String[] args) {
		String a = "this %s ddd";
		String b = String.format(a, " is");
		System.out.println(b);
	}
}
