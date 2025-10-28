package com.incubyte;

public class StringCalculator {
	public int add(String numbers) {
		if (numbers.isEmpty()) {
			return 0;
		}
		String delimator = "[,\\n]";
		if (numbers.startsWith("//")) {
			int delimatorIndex = numbers.indexOf("\n");
			delimator = numbers.substring(2, delimatorIndex);
			numbers = numbers.substring(delimatorIndex + 1);
		}
		String[] parts = numbers.split(delimator);
		int sum = 0;
		for (String p : parts) {
			sum += Integer.parseInt(p);
		}
		return sum;
	}
}
