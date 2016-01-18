package org.bosco.algorithm.lv1;

public class StringCompressor {

	public String compress(String source) {
		
		char previousValue = '\0';
		char currentValue = '\0';
		int stringCount = 0;
		StringBuilder builder = new StringBuilder();
		
		for (int index = 0; index < source.length(); index ++) {
			
			currentValue = source.charAt(index);
			if (previousValue != currentValue) {
				if (index == 0) {
					previousValue = currentValue;
					stringCount = 1;
				} else {
					builder.append(previousValue);
					builder.append(Integer.valueOf(stringCount).toString());
					previousValue = currentValue;
					stringCount = 1;					
				}
			}
			else {
				stringCount ++;
			}
		}
		builder.append(previousValue);
		builder.append(Integer.valueOf(stringCount).toString());
		
		return builder.toString();
	}
}
