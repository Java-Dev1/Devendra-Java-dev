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

}
