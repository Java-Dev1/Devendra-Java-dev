package com.incubyte;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
	@Test
	void shouldReturnZeroForEmptyString() {
		StringCalculator calc  = new StringCalculator();
		assertEquals(0, calc.add(""));
	}

}
