package algorithm.recursion;

public class Power {
	public double power(int a, int b) {
		return b >= 0 ? powerHelper(a, b) : 1 / powerHelper(a, -b);
	}

	double powerHelper(int a, int b) {
		if (b == 0) {
			return 1;
		}
		double half = power(a, b / 2);
		return b % 2 == 0 ? half * half : half * half * a;
	}

	public static void main(String[] args) {
		Power solu = new Power();
		System.out.println(solu.power(3, 4));
		System.out.println(solu.power(3, -4));
	}
}
