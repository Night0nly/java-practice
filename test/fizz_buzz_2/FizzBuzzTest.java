import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class FizzBuzzTest {
	
	private static final int FIZZ_NUMBER = 3;
	private static final int BUZZ_NUMBER = 5;
	private static final int FIZZ_BUZZ_NUMBER = FIZZ_NUMBER * BUZZ_NUMBER;
	private static final int FROM = 1;
	private static final int TO = FIZZ_BUZZ_NUMBER*3;

	@Test
	public void fizzテスト() {
		final List<String> resultList = newResultList();
		IntStream.rangeClosed(FROM, TO).filter(n -> n % FIZZ_NUMBER == 0 && n % BUZZ_NUMBER != 0)
				.forEach(n -> assertEquals("Fizz", resultList.get(n - FROM)));
	}

	@Test
	public void buzzテスト() {
		final List<String> resultList = newResultList();
		IntStream.rangeClosed(FROM, TO).filter(n -> n % BUZZ_NUMBER == 0 && n % FIZZ_NUMBER != 0)
				.forEach(n -> assertEquals("Buzz", resultList.get(n - FROM)));
	}

	@Test
	public void fizzBuzzテスト() {
		final List<String> resultList = newResultList();
		IntStream.rangeClosed(FROM, TO).filter(n -> n % FIZZ_BUZZ_NUMBER == 0)
				.forEach(n -> assertEquals("FizzBuzz", resultList.get(n - FROM)));
	}

	@Test
	public void 数字テスト() {
		final List<String> resultList = newResultList();
		IntStream.rangeClosed(FROM, TO).filter(n -> n % BUZZ_NUMBER != 0 && n % FIZZ_NUMBER != 0)
				.forEach(n -> assertEquals(n, Integer.parseInt(resultList.get(n - FROM))));
	}

	public List<String> newResultList() {
		final FizzBuzz fizzBuzz = new FizzBuzz(FIZZ_NUMBER, BUZZ_NUMBER, FIZZ_BUZZ_NUMBER);
		return fizzBuzz.getResultList(FROM, TO);

	}

}
