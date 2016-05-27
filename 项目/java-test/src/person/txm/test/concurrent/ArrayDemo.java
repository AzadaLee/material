package person.txm.test.concurrent;

public class ArrayDemo {
	final int[] arr = {1,2,3,4};
	public static void main(String[] args) {
		ArrayDemo arrayDemo = new ArrayDemo();
		int[] arr2 = arrayDemo.arr;
		arr2[0] = 10;
		System.out.println(arrayDemo.arr[0]);
	}
}
