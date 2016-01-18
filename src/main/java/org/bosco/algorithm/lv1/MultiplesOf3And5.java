package org.bosco.algorithm.lv1;

public class MultiplesOf3And5 {

	public long multiplesOf3And5Method1(long max) {
		long first = 3;
		long second = 5;
		long sum = 0;
		long bigO = 0;
		for (int i = 0; i < max; i++) {
			long temp = 0;
			if ((i % first == 0) || (i % second == 0)) {
				temp = i;
			}
			sum += temp;
			bigO++;
		}
		System.out.println("sum : " + sum + ", big(O) : " + bigO);
		return sum;
	}
	
	public long multiplesOf3And5Method2(int max) {

		long first = 3;
		long second = 5;
		long sum = 0;
		long bigO = 0;
		
		long currentPos = 0;
		for (long i = 0; i < max; i = i + first) {
			sum = sum + i;	
			bigO++;
		}
		for (long i = 0; i <max; i = i + second) {
			if (i % first != 0) {
				sum = sum + i;
			}
			bigO++;
		}
		
		System.out.println("sum : " + sum + ", big(O) : " + bigO );

		return sum;

	}
	
	public long multiplesOf3And5Method3(int max) {
		
		long first = 3;
		long second = 5;
		long third = first * second;
		
		long tempMax = getMaxValue(max, first);
		long firstsum = ((tempMax / first) * (first + tempMax)) / 2; //166833
		
		tempMax = getMaxValue(max, second);
		long secondsum = ((tempMax / second) * (second + tempMax)) / 2; //99500
	
		tempMax = getMaxValue(max, third);
		long thirdsum = ((tempMax / third) * (third + tempMax)) / 2; //33165
		
		long sum = firstsum + secondsum - thirdsum ;
		System.out.println("sum : " + sum + ", big(O) : " + 3);
		return sum;
	}

	private long getMaxValue(long max, long first) {
		if (max % first != 0) {
			long temp = (max / first) * first;
			return temp;
		} else { 
			long temp = max - first;
			return temp;
		}
	}
	
	
}
