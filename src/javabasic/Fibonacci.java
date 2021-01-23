package javabasic;

public class Fibonacci {
	public int fibonacci(int K) {
		// base case
		if (K <= 0) {
			return 0;
		}
		if (K == 1) {
			return 1;
		}
		// recursion rule
		return fibonacci(K - 1) + fibonacci(K - 2);
	}
}
