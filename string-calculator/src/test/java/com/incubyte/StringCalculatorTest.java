package com.incubyte;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
