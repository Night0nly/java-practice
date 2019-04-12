import java.util.List;

/**
 * FizzBuzzのリストをコンソールに出力する。
 */
public class FizzBuzzResultOutput {

	public static void main(String[] args) {

		final FizzBuzz fizzBuzzA = new FizzBuzz(3, 5, 15);
		printList(fizzBuzzA.getResultList(1, 100), "A");

		final FizzBuzz fizzBuzzB = new FizzBuzz(5, 7, 35);
		printList(fizzBuzzB.getResultList(101, 200), "B");
	}

	/**
	 * コンソールにリストを出力するメソッド
	 * 
	 * @param list
	 *            出力するリスト
	 * @param listName
	 *            リストの名前
	 */
	private static void printList(List<String> list, String listName) {
		System.out.println("リスト" + listName);
		list.forEach(System.out::println);
		System.out.println("---------------------------------------");
	}

}
