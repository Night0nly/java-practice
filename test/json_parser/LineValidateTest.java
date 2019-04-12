import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;

public class LineValidateTest {
	private final Line wrongFormatLine1 = Line.of(3,
			"id2	cp2	store2	publisher2	電子書籍	author2	titleId2	titleName2	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 ");
	private final Line wrongFormatLine2 = Line.of(4,
			"id2	cp2	store2	publisher2	電子書籍	author2	    	titleName2	   	汎用	xDL	volume	本編	○	○	○	price	period	2017/06101	2999/31	2341	ooo	2017/07/11 55:06:52	2017/91/11 06:06:52");
	private final Line correctFormatLine = Line.of(2,
			"id1	cp1	store1	publisher1	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");

	@Test
	public void 正しい行の場合() {
		assertThat(LineValidate.validateLine(correctFormatLine)).isEqualTo(Optional.empty());
	}

	@Test
	public void おかしい列数の場合() {
		assertThat(LineValidate.validateLine(wrongFormatLine1).get())
				.isEqualToComparingFieldByField(ErrorLine.newErrorNumberOfLine(wrongFormatLine1));
	}

	@Test
	public void おかしい列の場合() {
		final ErrorLine error = ErrorLine.of(wrongFormatLine2,
				Arrays.asList(Column.TITLE_ID, Column.FORMAT, Column.TYPE, Column.VOLUME, Column.PRICE,
						Column.DL_PERIOD, Column.DELIVERY_START_DATE, Column.DELIVERY_END_DATE,
						Column.PERMISSION_START_DATE, Column.PERMISSION_END_DATE, Column.UPDATE_TIME,
						Column.REGISTRATION_TIME));
		assertThat(LineValidate.validateLine(wrongFormatLine2).get()).isEqualToComparingFieldByField(error);
	}
}
