package name.ianmorgan.bbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RomanNumeralGeneratorTest {

	@Test
	public void myTestCasesBuiltUpAsIGoAlong() {
		// numbers less than 10
		assertValidRomanNumeral("I", 1);
		assertValidRomanNumeral("III", 3);
		assertValidRomanNumeral("IV", 4);
		assertValidRomanNumeral("V", 5);
		assertValidRomanNumeral("VIII", 8);
		assertValidRomanNumeral("IX", 9);

		// numbers less than 100
		assertValidRomanNumeral("X", 10);
		assertValidRomanNumeral("XVIII", 18);
		assertValidRomanNumeral("XXX", 30);
		assertValidRomanNumeral("XL", 40);
		assertValidRomanNumeral("XLI", 41);
		assertValidRomanNumeral("XLIX", 49);
		assertValidRomanNumeral("XC", 90);
		assertValidRomanNumeral("XCIX", 99);

		// numbers less than 1000
		assertValidRomanNumeral("C", 100);
		assertValidRomanNumeral("CI", 101);
		assertValidRomanNumeral("CCC", 300);
		assertValidRomanNumeral("CD", 400);
		assertValidRomanNumeral("CDXCIX", 499);
		assertValidRomanNumeral("DCCC", 800);
		assertValidRomanNumeral("CMXCIX", 999);

		// numbers less than 3999
		assertValidRomanNumeral("M", 1000);
		assertValidRomanNumeral("MCMXCIX", 1999);
		assertValidRomanNumeral("MMMCCCXCIX", 3399);
	}

	@Test
	public void shouldPassCommonExamplesFromWikipedia() {
		assertValidRomanNumeral("MDCCLXXVI", 1776);
		assertValidRomanNumeral("MCMLIV", 1954);
		assertValidRomanNumeral("MCMXC", 1990);
		assertValidRomanNumeral("MCMIII", 1903);
	}

	@Test
	public void shouldPassBBCProvidedTestCases() {
		assertValidRomanNumeral("I", 1);
		assertValidRomanNumeral("V", 5);
		assertValidRomanNumeral("X", 10);
		assertValidRomanNumeral("XX", 20);
		assertValidRomanNumeral("MMMCMXCIX", 3999);
	}

	@Test
	public void shouldThrowExceptionForOutOfRangeNumbers() {
		try {
			new FunctionalRomanNumeralGenerator().generate(0);
			fail("Exception expected");
		} catch (IllegalArgumentException ex) {
			// expected
		}

		try {
			new FunctionalRomanNumeralGenerator().generate(4000);
			fail("Exception expected");
		} catch (IllegalArgumentException ex) {
			// expected
		}

	}

	private void assertValidRomanNumeral(String expected, int actual) {
		assertEquals(expected, new FunctionalRomanNumeralGenerator().generate(actual));
	}

}
