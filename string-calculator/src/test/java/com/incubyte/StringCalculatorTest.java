package com.incubyte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
	@Test
	void shouldReturnZeroForEmptyString() {
		StringCalculator calc = new StringCalculator();
		assertEquals(0, calc.add(""));
	}

	@Test
	void shouldReturnNumberWhenSingleNumberGiven() {
		StringCalculator calc = new StringCalculator();
		assertEquals(5, calc.add("5"));

	}

	@Test
	void shouldReturnSumOfTwoNumbers() {
		StringCalculator calc = new StringCalculator();
		assertEquals(6, calc.add("1,5"));
	}

	@Test
	void shouldHandleNewLinesBetweenNumbers() {
		StringCalculator calc = new StringCalculator();
		assertEquals(6, calc.add("1\n2,3"));
	}

	@Test
	void shouldSupportCustomDelimiter() {
		StringCalculator calc = new StringCalculator();
		assertEquals(3, calc.add("//;\n1;2"));
	}

	@Test
	void shouldThrowExceptionForNegativeNumbers() {
		StringCalculator calc = new StringCalculator();
		Exception exception = assertThrows(RuntimeException.class, () -> {
			calc.add("1,-2,3,-4");
		});
		assertEquals("negative numbers not allowed -2,-4", exception.getMessage());
	}

	@Test
	void shouldReturnHowManyTimesAddWasCalled() {
		StringCalculator calc = new StringCalculator();

		calc.add("1,2");
		calc.add("3");

		assertEquals(2, calc.getCalledCount());
	}

	@Test
	void shouldTriggerEventAfterAddIsCalled() {
		StringCalculator calc = new StringCalculator();

		final String[] receivedInput = new String[1];
		final int[] receivedResult = new int[1];

		calc.setAddListener((input, result) -> {
			receivedInput[0] = input;
			receivedResult[0] = result;
		});

		calc.add("1,2");

		assertEquals("1,2", receivedInput[0]);
		assertEquals(3, receivedResult[0]);
	}

	@Test
	void shouldIgnoreNumbersGreaterThan1000() {
		StringCalculator calc = new StringCalculator();
		assertEquals(2, calc.add("2,1001"));
	}

	@Test
	void shouldSupportDelimitersOfAnyLength() {
		StringCalculator calc = new StringCalculator();
		assertEquals(6, calc.add("//[***]\n1***2***3"));
	}

}
