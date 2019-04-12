import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

	public static void main(String[] args) {
		List<String> resultList = getResultList();
		resultList.forEach(System.out::println);
	}

	public static List<String> getResultList() {
		List<String> resultList = new ArrayList<>();

		for (int i = 1; i <= 100; i++) {
			if (i % 15 == 0) {
				resultList.add("FizzBuzz");
			} else if (i % 5 == 0) {
				resultList.add("Buzz");
			} else if (i % 3 == 0) {
				resultList.add("Fizz");
			} else {
				resultList.add(Integer.toString(i));
				}
		}
		
		return resultList;
	}

}
