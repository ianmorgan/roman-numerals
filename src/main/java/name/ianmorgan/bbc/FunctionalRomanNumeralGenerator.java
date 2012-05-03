package name.ianmorgan.bbc;

/**
 * <p>
 * A Roman Numeral generator written in a functional style that recurses
 * through the number applying match patterns.
 * </p>
 * 
 * <p>
 * Uses commonly accepted rules documented on <a
 * href="http://en.wikipedia.org/wiki/Roman_numerals">Wikipedi</a>
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public class FunctionalRomanNumeralGenerator implements RomanNumeralGenerator {

	@Override
	public String generate(int number) {
		if (number < 1 || number > 3999) {
			throw new IllegalArgumentException("Roman numerals must be between 1 and 3999");
		}

		StringBuilder result = new StringBuilder();
		ResultTuple tuple = new ResultTuple("", number);
		do {
			tuple = takeNextNumerals(tuple);
			result.append(tuple.romanNumerals);
		} while (tuple.remaining > 0);

		return result.toString();
	}

	private ResultTuple takeNextNumerals(ResultTuple tuple) {
		if (tuple.remaining < 10) {
			return buildNumber(tuple.remaining, "I", "V", "X", 1);
		} else if (tuple.remaining < 100) {
			return buildNumber(tuple.remaining, "X", "L", "C", 10);
		} else if (tuple.remaining < 1000) {
			return buildNumber(tuple.remaining, "C", "D", "M", 100);
		} else {
			return buildNumber(tuple.remaining, "M", "-", "-", 1000);
		}
	}

	private ResultTuple buildNumber(int number, String lowerNumeral, String midNumeral, String upperNumeral,
			int multiplier) {
		int range = number / multiplier;
		int remainder = number - (range * multiplier);
		// e.g. III
		if (range <= 3) {
			return new ResultTuple(repeat(lowerNumeral, range), remainder);
		}
		// e.g. IV
		else if (range == 4) {
			return new ResultTuple(lowerNumeral + midNumeral, remainder);
		}
		// e.g. V and remainder will be handled in next recursive call
		else if (range <= 8) {
			return new ResultTuple(midNumeral, number - (5 * multiplier));
		}
		// e.g IX and remainder will be handled in next recursive call
		else {
			return new ResultTuple(lowerNumeral + upperNumeral, number - (9 * multiplier));
		}

	}

	private String repeat(String c, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Simple tuple like class for holding data between calls
	 */
	private class ResultTuple {
		public ResultTuple(String romanNumerals, int remaining) {
			this.romanNumerals = romanNumerals;
			this.remaining = remaining;
		}
		public final int remaining;
		public final String romanNumerals;
	}

}
