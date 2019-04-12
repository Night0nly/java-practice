import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

public class BookTest {

	private static final String BOOK_LINE = "00287ako	Amazon.com, Inc.(国内)	Kindle(国内納品用)	Toyboo!	電子書籍	かさやゆきふみ･もろえみこ	00005luv	きたかぜとたいよう	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52";
	private static final String SAMPLE_LINE = "00287akn	Amazon.com, Inc.(国内)	Kindle(国内納品用)	Toyboo!	電子書籍	かさやゆきふみ･もろえみこ	00005luv	きたかぜとたいよう	EPUB3.0	汎用	サンプル	1	サンプル	○	○	○	0	1	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52";

	@Test
	public void 行からBookになる確認の本編場合() {
		final Book book = new Book("00287ako", "Toyboo!", "かさやゆきふみ･もろえみこ", "00005luv", "きたかぜとたいよう", "本編", "EPUB3.0", 1,
				1000, LocalDate.of(2017, 6, 1), LocalDate.of(2999, 12, 31), false);
		assertThat(Book.of(Line.of(1, BOOK_LINE))).isEqualToComparingFieldByField(book);
	}

	@Test
	public void 行からBookになる確認のサンプル場合() {
		final Book sample = new Book("00287akn", "Toyboo!", "かさやゆきふみ･もろえみこ", "00005luv", "きたかぜとたいよう", "サンプル", "EPUB3.0",
				1, 0, LocalDate.of(2017, 6, 1), LocalDate.of(2999, 12, 31), true);
		assertThat(Book.of(Line.of(2, SAMPLE_LINE))).isEqualToComparingFieldByField(sample);
	}
	
	@Test
	public void サンプル紐付け用キーの確認(){
		assertThat(Book.of(Line.of(1, BOOK_LINE)).getSampleIdentificationKey())
		.isEqualTo("EPUB3.000005luv1");
	}
}
