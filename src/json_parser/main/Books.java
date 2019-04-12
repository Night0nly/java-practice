import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Books {
	private final List<Book> bookList;

	/**
	 * サンプルを本編のオブジェクトを入れて、入れた本編を戻すメソッド。
	 * 
	 * @param bookList
	 *            サンプルと本編のBookがあるリスト。
	 * @return あるサンプルを入れた本編。
	 */
	public static Books of(final Lines lines) {
		final List<Book> bookList = lines.getNoHeaderLineList().stream()
				.map(Book::of)
				.collect(Collectors.toList());

		final Map<String, Book> sampleMap = bookList.stream()
				.filter(b -> b.isSample())
				.collect(Collectors.toMap(Book::getSampleIdentificationKey, b -> b));

		final Function<Book, Book> sampleMapper = book -> {
			book.setSample(sampleMap.get(book.getSampleIdentificationKey()));
			return book;
		};

		final List<Book> originalBookList = bookList.stream()
				.filter(book -> !book.isSample())
				.map(sampleMapper)
				.collect(Collectors.toList());

		return new Books(originalBookList);
	}

	private Books(final List<Book> bookList) {
		this.bookList = Collections.unmodifiableList(bookList);
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public Books getBooksPriceLessOrEqualTo(final int price) {
		return getFilteredBooks(b -> b.getPrice() <= price);
	}

	public Books getBooksDeliveredInMonth(final YearMonth yearMonth) {
		return getFilteredBooks(b -> YearMonth.from(b.getDeliveryStartsAt()).equals(yearMonth));
	}

	public Books getBooksPublishedBy(final String publisher) {
		return getFilteredBooks(b -> b.getPublisher().equals(publisher));
	}

	private Books getFilteredBooks(final Predicate<Book> condition) {
		return new Books(bookList.stream().filter(condition).collect(Collectors.toList()));
	}
}
