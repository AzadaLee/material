package person.txm.test.math;


public class MathTest {
	public static void main(String[] args) {
		//BigDecimal end = principal.multiply(new BigDecimal(Math.pow(BigDecimal.ONE.add(rate.divide(new BigDecimal(365))).doubleValue(), 364)));
		//System.out.println(end);
		double principal = 350000.00;
		double rate = 0.03;
		double end = principal*(Math.pow((1+rate/365), 365*20));
		System.out.println(end);
	}
}
