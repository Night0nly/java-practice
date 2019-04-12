import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LinesTest {
	private static final Line BOOK_LINE_1 = Line.of(1,
			"id1	cp1	store1	Toyboo!	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_2 = Line.of(2,
			"id2	cp2	store2	Toyboo!	電子書籍	author2	titleId2	titleName2	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line HEADER_LINE = Line.of(1, "header");
	private static final List<Line> LINE_LIST = Arrays.asList(HEADER_LINE, BOOK_LINE_1, BOOK_LINE_2);

	@Test
	public void ヘッダー行がないリストをとることの確認() {
		assertThat(Lines.of(LINE_LIST).getNoHeaderLineList()).containsExactly(BOOK_LINE_1, BOOK_LINE_2);
	}

}
