package javabasic;

public class Power {
	public long power(int a, int b) {
		// base case
		if (b == 0) {
			return 1;
		}
		// recursive rule
		long half = power(a, b / 2);
		if (b % 2 == 0) {
			return half * half;
		} else {
			return half * half * a;
		}
	}
}
