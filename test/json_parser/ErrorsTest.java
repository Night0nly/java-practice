import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class ErrorsTest {
	private static final Line DEFAULT_LINE = Line.of(1, "");
	private static final Line CORRECT_FORMAT_LINE = Line.of(2,
			"id1	cp1	store1	publisher1	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line WRONG_FORMAT_LINE1 = Line.of(3,
			"id1	cp1	store1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line WRONG_FORMAT_LINE2 = Line.of(4,
			"id2	cp2	store2	publisher2	電子書籍	author2	    	titleName2	   	汎用	xDL	volume	本編	○	○	○	price	period	2017/06101	2999/31	2341	ooo	2017/07/11 55:06:52	2017/91/11 06:06:52");
	private static final Errors ERRORS = Errors
			.of(Lines.of(Arrays.asList(DEFAULT_LINE, CORRECT_FORMAT_LINE, WRONG_FORMAT_LINE1, WRONG_FORMAT_LINE2)));
	
	@Test
	public void LinesからErrorsオブジェクトを作成することの確認() {
		final ErrorLine line1 = ErrorLine.newErrorNumberOfLine(WRONG_FORMAT_LINE1);
		final ErrorLine line2 = ErrorLine.of(WRONG_FORMAT_LINE2,
				Arrays.asList(Column.TITLE_ID, Column.FORMAT, Column.TYPE, Column.VOLUME, Column.PRICE,
						Column.DL_PERIOD, Column.DELIVERY_START_DATE, Column.DELIVERY_END_DATE,
						Column.PERMISSION_START_DATE, Column.PERMISSION_END_DATE, Column.UPDATE_TIME,
						Column.REGISTRATION_TIME));

		assertThat(ERRORS.getErrorList()).usingFieldByFieldElementComparator().containsExactly(line1, line2);
	}
}
