import static org.assertj.core.api.Assertions.assertThat;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BooksTest {

	private static final Line BOOK_LINE_1 = Line.of(1,
			"id1	cp1	store1	Toyboo!	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_2 = Line.of(2,
			"id2	cp2	store2	Toyboo!	電子書籍	author2	titleId2	titleName2	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_3 = Line.of(3,
			"id3	cp3	store3	publisher3	電子書籍	author3	titleId3	titleName3	EPUB3.0	汎用	DL	1	本編	○	○	○	400	無期限	2017/06/01	2999/12/31	2018/06/06	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_4 = Line.of(4,
			"id4	cp4	store4	publisher4	電子書籍	author4	titleId4	titleName4	EPUB3.0	汎用	DL	1	本編	○	○	○	500	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_5 = Line.of(5,
			"id5	cp5	store5	publisher5	電子書籍	author5	titleId5	titleName5	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");

	private static final Line SAMPLE_LINE_1 = Line.of(6,
			"id6	cp1	store1	Toyboo!	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	サンプル	1	サンプル	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line SAMPLE_LINE_2 = Line.of(7,
			"id7	cp1	store2	Toyboo!	電子書籍	author2	titleId2	titleName2	EPUB3.0	汎用	サンプル	1	サンプル	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");

	private static final Book BOOK1 = Book.of(BOOK_LINE_1);
	private static final Book BOOK2 = Book.of(BOOK_LINE_2);
	private static final Book BOOK3 = Book.of(BOOK_LINE_3);
	private static final Book BOOK4 = Book.of(BOOK_LINE_4);
	private static final Book BOOK5 = Book.of(BOOK_LINE_5);

	private static final Book SAMPLE1 = Book.of(SAMPLE_LINE_1);
	private static final Book SAMPLE2 = Book.of(SAMPLE_LINE_2);


	private static final Books BOOKS = Books.of(Lines.of(Arrays.asList(SAMPLE_LINE_2, SAMPLE_LINE_2, SAMPLE_LINE_1, BOOK_LINE_5,
			BOOK_LINE_4, BOOK_LINE_3, BOOK_LINE_2, BOOK_LINE_1)));

	@Test
	public void サンプルマップテスト() {
		final List<Book> bookList = BOOKS.getBookList();
		BOOK1.setSample(SAMPLE1);
		BOOK2.setSample(SAMPLE2);
		
		assertThat(bookList).hasSize(5).usingFieldByFieldElementComparator().contains(BOOK3, BOOK4, BOOK5);
		assertThat(isEqualToBook(bookList.get(4), BOOK1)).isTrue();
		assertThat(isEqualToBook(bookList.get(3), BOOK2)).isTrue();
	}

	@Test
	public void 出版社よるフィルターの確認() {
		final List<Book> bookList = BOOKS.getBooksPublishedBy("Toyboo!").getBookList();

		assertThat(bookList).hasSize(2);
		assertThat(isEqualToBook(bookList.get(1), BOOK1)).isTrue();
		assertThat(isEqualToBook(bookList.get(0), BOOK2)).isTrue();
	}

	@Test
	public void 価格よるフィルターの確認() {
		assertThat(BOOKS.getBooksPriceLessOrEqualTo(500).getBookList())
		.usingFieldByFieldElementComparator()
		.containsExactly(BOOK4, BOOK3);
	}

	@Test
	public void 配信開始日よるフィルターの確認() {
		assertThat(BOOKS.getBooksDeliveredInMonth(YearMonth.of(2018, 6)).getBookList())
		.usingFieldByFieldElementComparator()
		.containsExactly(BOOK3);
	}
	
	private boolean isEqualToBook(Book book1, Book book2) {
		if (book1.getAuthor().equals(book2.getAuthor()) && book1.getId().equals(book2.getId()) && book1.getPrice() == book2.getPrice()
				&& book1.getPublisher().equals(book2.getPublisher()) && book1.getTitle().equals(book2.getTitle())
				&& book1.getVolume() == book2.getVolume() && book1.getSubtitle().equals(book2.getSubtitle())
				&& book1.getDeliveryEndsAt().equals(book2.getDeliveryEndsAt())
				&& book1.getDeliveryStartsAt().equals(book2.getDeliveryStartsAt())) {
			return book1.getSample() == book2.getSample()
					|| (book1.getSample() != null && book2.getSample() != null && isEqualToBook(book1.getSample(), book2.getSample()));
		} else {
			return false;
}
	}
}
