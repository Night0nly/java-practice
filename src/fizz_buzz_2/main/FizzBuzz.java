import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * FizzBuzz問題の各数字（Fizzになる要件、Buzzになる要件、FizzBuzzになる要件）をカスタマイズできるクラス。
 */
public class FizzBuzz {

	private final int fizzNumber;
	private final int buzzNumber;
	private final int fizzBuzzNumber;

	public FizzBuzz(int fizzNumber, int buzzNumber, int fizzBuzzNumber) {
		this.fizzNumber = fizzNumber;
		this.buzzNumber = buzzNumber;
		this.fizzBuzzNumber = fizzBuzzNumber;
	}

	/**
	 * fromの数値からtoの数値までのリストを戻す。
	 * ただしfizzNumberの倍数のときは数字の代わりに{@code Fizz}が入っている。
	 * ただしbuzzNumberの倍数のときは数字の代わりに{@code Buzz}が入っている。
	 * ただしfizzBuzzNumberの倍数のときは数字の代わりに{@code FizzBuzz}が入っている。
	 * 
	 * @param from
	 *            リストの最初の数値。
	 * @param to
	 *            リストの最後の数値。
	 * @return FizzBuzzのルールに合うリストを戻す。
	 */
	public List<String> getResultList(final int from, final int to) {
		return IntStream.rangeClosed(from, to)
				.mapToObj(this::toFizzBuzz).collect(Collectors.toList());
	}

	/**
	 * このメソッドはインプット数字をチェックして条件に合えば該当Stringを返す。
	 * 
	 * @param number
	 *            チェック対象。
	 * @return numberはfizzNumberの倍数の時{@code Fizz}のStringを戻す。
	 *         numberはbuzzNumberの倍数の時{@code Fizz}のStringを戻す。
	 *         numberはfizzBuzzNumberの倍数の時{@code Fizz}のStringを戻す。
	 *         上記の以外場合はその数字を文字型を変えて戻す。
	 */
	private String toFizzBuzz(final int number) {
		if (number % fizzBuzzNumber == 0) {
			return "FizzBuzz";
		} else if (number % fizzNumber == 0) {
			return "Fizz";
		} else if (number % buzzNumber == 0) {
			return "Buzz";
		} else {
			return Integer.toString(number);
		}
	}
}
