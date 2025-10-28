package com.incubyte;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
	private int callCount = 0;

	public int add(String numbers) {
		callCount++;
		if (numbers.isEmpty()) {
			return 0;
		}

		String delimiter = "[,\\n]";
		if (numbers.startsWith("//")) {
			int delimiterIndex = numbers.indexOf("\n");
			String delimiterPart = numbers.substring(2, delimiterIndex);

			List<String> delimiters = new ArrayList<>();
			if (delimiterPart.contains("[")) {
				// Multiple delimiters
				java.util.regex.Matcher m = java.util.regex.Pattern.compile("\\[(.*?)]").matcher(delimiterPart);
				while (m.find()) {
					delimiters.add(java.util.regex.Pattern.quote(m.group(1)));
				}
				delimiter = String.join("|", delimiters);
			} else {
				delimiter = java.util.regex.Pattern.quote(delimiterPart);
			}

			numbers = numbers.substring(delimiterIndex + 1);
		}

		String[] parts = numbers.split(delimiter);
		int sum = 0;
		List<Integer> negatives = new ArrayList<>();

		for (String p : parts) {
			if (p.isEmpty())
				continue; // handle cases like "1,,2"
			int num = Integer.parseInt(p);
			if (num < 0) {
				negatives.add(num);

			}
			if (num <= 1000)
				sum += num;
		}

		if (!negatives.isEmpty()) {
			String message = "negative numbers not allowed "
					+ negatives.stream().map(String::valueOf).collect(Collectors.joining(","));
			throw new RuntimeException(message);
		}

		if (listener != null) {
			listener.onAdd(numbers, sum);
		}

		return sum;
	}

	public int getCalledCount() {
		return callCount;
	}

	@FunctionalInterface
	public interface AddListener {
		void onAdd(String input, int result);
	}

	private AddListener listener;

	public void setAddListener(AddListener listener) {
		this.listener = listener;
	}

}
