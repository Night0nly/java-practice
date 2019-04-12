import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class LineTest {
	private static final String BOOK_LINE = "id1	cp1	store1	Toyboo!	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52";
	private static final Line LINE = Line.of(1, BOOK_LINE);

	@Test
	public void ID列の値の確認() {
		assertThat(LINE.getColumnValue(Column.ID)).isEqualTo("id1");
	}

	@Test
	public void 出版社列の値の確認() {
		assertThat(LINE.getColumnValue(Column.PUBLISHER)).isEqualTo("Toyboo!");
	}

	@Test
	public void 作者列の値の確認() {
		assertThat(LINE.getColumnValue(Column.AUTHOR)).isEqualTo("author1");
	}

	@Test
	public void タイトルID列の値の確認() {
		assertThat(LINE.getColumnValue(Column.TITLE_ID)).isEqualTo("titleId1");
	}

	@Test
	public void タイトル列の値の確認() {
		assertThat(LINE.getColumnValue(Column.TITLE)).isEqualTo("titleName1");
	}

	@Test
	public void フォーマット列の値の確認() {
		assertThat(LINE.getColumnValue(Column.FORMAT)).isEqualTo("EPUB3.0");
	}

	@Test
	public void DL区分列の値の確認() {
		assertThat(LINE.getColumnValue(Column.TYPE)).isEqualTo("DL");
	}

	@Test
	public void 通番列の値の確認() {
		assertThat(LINE.getColumnValue(Column.VOLUME)).isEqualTo("1");
	}

	@Test
	public void 配信許諾名列の値の確認() {
		assertThat(LINE.getColumnValue(Column.SUBTITILE)).isEqualTo("本編");
	}

	@Test
	public void 価格列の値の確認() {
		assertThat(LINE.getColumnValue(Column.PRICE)).isEqualTo("1000");
	}

	@Test
	public void DL期限列の値の確認() {
		assertThat(LINE.getColumnValue(Column.DL_PERIOD)).isEqualTo("無期限");
	}

	@Test
	public void 配信開始日列の値の確認() {
		assertThat(LINE.getColumnValue(Column.DELIVERY_START_DATE)).isEqualTo("2017/06/01");
	}

	@Test
	public void 配信終了日列の値の確認() {
		assertThat(LINE.getColumnValue(Column.DELIVERY_END_DATE)).isEqualTo("2999/12/31");
	}

	@Test
	public void 許諾開始日列の値の確認() {
		assertThat(LINE.getColumnValue(Column.PERMISSION_START_DATE)).isEqualTo("2017/06/01");
	}

	@Test
	public void 許諾終了日列の値の確認() {
		assertThat(LINE.getColumnValue(Column.PERMISSION_END_DATE)).isEqualTo("2999/12/31");
	}

	@Test
	public void 更新日時列の値の確認() {
		assertThat(LINE.getColumnValue(Column.UPDATE_TIME)).isEqualTo("2017/07/11 06:06:52");
	}

	@Test
	public void 登録日時列の値の確認() {
		assertThat(LINE.getColumnValue(Column.REGISTRATION_TIME)).isEqualTo("2017/07/11 06:06:52");
	}
}