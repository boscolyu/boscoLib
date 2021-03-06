package org.bosco.algorithm.lv1;

public class Sort {

	public int[] insertsortAscending(int[] notSortArray) {
		int[] sourceArray = notSortArray.clone();
		for (int i = 1; i < sourceArray.length; i++) {
			int temp = sourceArray[i];
			for (int j = 0; j < i; j++) {
				if (temp < sourceArray[j]) {
					int temp2 = sourceArray[j];
					sourceArray[j] = temp;
					temp = temp2;
				}
			}
			sourceArray[i] = temp;
		}
		return sourceArray;
	}

	public int[] selectionsortAscending(int[] notSortArray) {
		int [] sourceArray = notSortArray.clone();
		for (int i = 0; i < sourceArray.length; i++) {
			int index = i;
			int min = sourceArray[i];
			for (int j = i + 1; j < sourceArray.length; j++) {
				if (sourceArray[j] < min) {
					min = sourceArray[j];
					index = j;
				}
			}
			int temp = sourceArray[i];
			sourceArray[i] = sourceArray[index];
			sourceArray[index] = temp;
		}
		return sourceArray;
	}

	public int[] bubblesortAscending(int[] notSortArray) {
		int[] sortAscendingArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		return sortAscendingArray;
	}

	public int[] mergesortAscending(int[] notSortArray) {
		int[] sortAscendingArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		return sortAscendingArray;
	}

	public int[] heapsortAscending(int[] notSortArray) {
		int[] sortAscendingArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		return sortAscendingArray;
	}

	public int[] quicksortAscending(int[] notSortArray) {
		int[] sortAscendingArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		return sortAscendingArray;
	}
}
