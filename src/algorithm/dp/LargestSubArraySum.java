package algorithm.dp;

// find the max sum of a subarray in the input array

/*
CA
input int[] array, unsorted, could have duplicate elements; assum array not null, length >= 1
output int max sum of a subarray
corner case: none

R
result = max {maxSumOfSubarrayEndingAt(i)}, i : 0 to n - 1

maxSumOfSubarrayEndingAt(0) = array(0)

maxSumOfSubarrayEndingAt(i) =
case1: maxSumOfSubarrayEndingAt(i - 1) > 0 ? maxSumOfSubarrayEndingAt(i - 1) + array(i)
case2: array(i)

DS: 
int last stores latestly calculated maxSumOfSubarrayEndingAt(i)
int max stores global max

init: last, max = array[0]

in every step update last, max

loop: i = 1 to n - 1

return max
*/

// TC O(input size)
// SC O(1)

public class LargestSubArraySum {
	public int largestSum(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int lastMax = array[0];
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			lastMax = lastMax > 0 ? lastMax + array[i] : array[i];
			max = Math.max(lastMax, max);
		}
		return max;
	}
	
	public static void main(String[] args) {
		LargestSubArraySum solu = new LargestSubArraySum();
		System.out.println(solu.largestSum(null));
		System.out.println(solu.largestSum(new int[1]));
		System.out.println(solu.largestSum(new int[] {2, -1, 4, -2, 1}));
		System.out.println(solu.largestSum(new int[] {-2, -1, -3}));
	} 
}
