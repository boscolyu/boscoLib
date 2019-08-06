package org.bosco.algorithm.lv1;

import org.junit.Test;

import junit.framework.Assert;

public class SortTest {

	
	@Test
	public void testInsertSort() {
		int[] notSortArray = { 5, 100, 23, 2123, 3, 234, 34, 234, 654, 3423, 342};
		Sort sort = new Sort();
		int[] sortArray = sort.insertsortAscending(notSortArray);
		Assert.assertTrue(isSorted(sortArray));
	}

	@Test
	public void testSelectionSort() {
		int[] notSortArray = { 5, 100, 23, 2123, 3, 234, 34, 234, 654, 3423, 342};
		Sort sort = new Sort();
		int[] sortArray = sort.selectionsortAscending(notSortArray);
		Assert.assertTrue(isSorted(sortArray));
	}
	
	
	@Test
	public void testBubbleSort() {
		int[] notSortArray = { 5, 100, 23, 2123, 3, 234, 34, 234, 654, 3423, 342};
		Sort sort = new Sort();
		int[] sortArray = sort.bubblesortAscending(notSortArray);
		Assert.assertTrue(isSorted(sortArray));
	}
	
	
	@Test
	public void testMergeSort() {
		int[] notSortArray = { 5, 100, 23, 2123, 3, 234, 34, 234, 654, 3423, 342};
		Sort sort = new Sort();
		int[] sortArray = sort.mergesortAscending(notSortArray);
		Assert.assertTrue(isSorted(sortArray));
	}
	
	@Test
	public void testHeapSort() {
		int[] notSortArray = { 5, 100, 23, 2123, 3, 234, 34, 234, 654, 3423, 342};
		Sort sort = new Sort();
		int[] sortArray = sort.heapsortAscending(notSortArray);
		Assert.assertTrue(isSorted(sortArray));
	}
	
	@Test
	public void testQuickSort() {
		int[] notSortArray = { 5, 100, 23, 2123, 3, 234, 34, 234, 654, 3423, 342};
		Sort sort = new Sort();
		int[] sortArray = sort.quicksortAscending(notSortArray);
		Assert.assertTrue(isSorted(sortArray));
	}
	
	
	private boolean isSorted(int[] sortArray) {
		int prevValue = 0; 
		for (int value : sortArray) {
			if (prevValue > value) {
				return false;
			}
			prevValue = value;
		}
		return true;
	}
}
