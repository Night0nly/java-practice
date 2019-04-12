import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class FizzBuzzTest {

	@Test
	public void fizzBuzzテスト() {
		List<String> resultList = FizzBuzz.getResultList();

		for (int i = 0; i < 100; i++) {
			if ((i + 1) % 15 == 0) {
				assertTrue(resultList.get(i).equals("FizzBuzz"));
			} else if ((i + 1) % 5 == 0) {
				assertTrue(resultList.get(i).equals("Buzz"));
			} else if ((i + 1) % 3 == 0) {
				assertTrue(resultList.get(i).equals("Fizz"));
			} else {
				assertEquals((i + 1), Integer.parseInt(resultList.get(i)));
			}
		}
	}
}
