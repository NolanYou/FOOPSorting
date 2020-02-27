package util;

public class RandomArray {
	private int[] randVals;
	
	public RandomArray(int n, int max) {
		randVals = new int[n];
		for(int i = 0; i < n; i++) {
			randVals[i] = (int) (Math.random() * max);
		}
	}
	
	public int[] getRandVals() {
		return randVals;
	}

}
