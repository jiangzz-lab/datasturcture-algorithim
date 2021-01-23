package javabasic;

public class MyInteger {
	public boolean isPowerOfTwo(int number) {
		if (number <= 0) {
	return false;
}
int count = 0;
for (int i = 0; i < 32; i++) {
	count += (number >> i) & 1;
}
return count == 1;
}
	
	public long reverseBits(long n) {
		int left = 31;
		int right = 0;
		while (left >= right) {
			long leftBit = (n >> left) & 1;
			long rightBit = (n >> right) & 1;
			if (leftBit != rightBit) {
				n = swap(n, left, right);
	}
	left--;
	right++;
	}
	return n;
	}
	
	public long swap(long n, int left, int right) {
		return n ^ ((1 << left) | (1 << right));
	}

	public String hex(int number) {
		StringBuilder result = new StringBuilder("0x");
		char[] chars = new char[]{'0', '1', '2', '3', '4', '5','6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		boolean hasHigherDigit = false;
		for (int i = 28; i >= 0; i -= 4) {
			int digit = (number >> i) & 15;
			if (digit != 0 || hasHigherDigit || i == 0) {
		hasHigherDigit = true;
		result.append(chars[digit]);
	}
	}
	return result.toString();
	}


}
