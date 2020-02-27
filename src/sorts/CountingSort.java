package sorts;

//Couting sort guide taken from geeks for geeks guide on radix sort

//https://www.geeksforgeeks.org/radix-sort/
//Readme:
//initially coded a non-stable version of counting sort, and looked up how to code a stable version, as that is needed
//For radix sort.
//comments to demonstrate understanding

public class CountingSort {
	public static void countingSort(int[] arr, int power){
		int[] countArr = new int[10];
		
		//fill countArr based on the number of instances in a specific place
		for(int i = 0; i < arr.length; i++) {
			countArr[(arr[i] / power) % 10]++;
		}
		
		//iterate through and create a rolling sum
		for(int i = 1; i < countArr.length; i++) {
			 countArr[i] += countArr[i - 1];
		}
		
		int[] out = new int[arr.length];
		
		//based on the number counted in CountArr, keep filling in the array. It's stable because it iterates through
		//the initial array in reverse order. 
		for (int i = arr.length - 1; i >= 0; i--) { 
	        out[countArr[(arr[i]/power) % 10] - 1] = arr[i]; 
	        countArr[(arr[i] / power) % 10]--; 
	    } 
		//copy the output to the original
		for(int i = 0; i < arr.length; i++) {
			arr[i] = out[i];	
		}
	}
}
