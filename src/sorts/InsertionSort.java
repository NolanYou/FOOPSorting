package sorts;

import java.util.*;

public class InsertionSort {
	public static void swapValues(int[] in, int index) {
		int temp = in[index-1];
		in[index-1] = in[index];
		in[index] = temp;
	}
	
	public static Void insertionSort(int[] in) {
		for (int i = 1; i < in.length; i++) {
			boolean lessThan = true;
			if(in[i] >= in[i-1]) {
				lessThan = false;
			}
			int index = i;
			while (lessThan == true && index > 0) {
				swapValues(in,index);
				index -= 1;
				if(index == 0) {
					break;
				}else if(in[index] >= in[index - 1]) {
					lessThan = false;
				}
			}
		}
		return null;
	}
	
}
