package sorts;

import java.util.ArrayList;

//description of implementation taken from wikipedia

public class Quicksort {
	
	public static Void quicksortWhole(int[] arr) {
		quicksort(arr, 0, arr.length - 1);
		return null;
	}
	public static void swap(int[] in, int index1, int index2) {
		int temp;
		temp = in[index1];
		in[index1] = in[index2];
		in[index2] = temp;
	}
	
	public static void quicksort(int[] arr, int lowIndex, int highIndex) {
		if(lowIndex < highIndex) {
			int pivot = partition(arr, lowIndex, highIndex);
			quicksort(arr, lowIndex, pivot - 1);
			quicksort(arr, pivot + 1, highIndex);
		}
	}
	public static int partition(int[] arr, int lowIndex, int highIndex) {
		int i = lowIndex;
		int pivot = arr[highIndex];
		for(int j = lowIndex; j <= highIndex; j++) {
			if(arr[j] < pivot) {
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, highIndex);
		return i;
	}
}
