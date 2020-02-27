package sorts;

public class RadixSort {
	public static int max(int[] arr) {
		int max = 0;
		for(int i : arr) {
			if(i > max) {
				max = i;
			}
		}
		return max;
		
	}
	
	public static Void radixSort(int[] in) {
		int[] out = new int[in.length];
		int maxVal = max(in);
		for(int power = 1; maxVal/ power > 0; power *= 10) {
			CountingSort.countingSort(in, power);
		}
		return null;
	}
}
