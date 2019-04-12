import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class ErrorLineTest {
	private static final Line LINE1 = Line.of(12,
			"id1	cp1	store1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line LINE2 = Line.of(12,
			"id1	cp1	store1	Toyboo!	電子書籍	author1	   	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/aa	2017/07/11 06:06:52	2017/07/11 06:06:52");

	@Test
	public void カラム範囲のエラーの文字列になるテスト() {
		assertThat(newColumnScopeErrorLine().toString()).isEqualTo("12\tカラム\tタイトルID,配信終了日");
	}

	@Test
	public void 行範囲のエラーの文字列になるテスト() {
		assertThat(ErrorLine.newErrorNumberOfLine(LINE1).toString()).isEqualTo("12\t行");
	}

	@Test
	public void エラーメッセージの作成の列Scopeの場合() {
		assertThat(newColumnScopeErrorLine().getErrorMessageList()).containsExactly("12行目の「タイトルID」は空文字です。",
				"12行目の「配信終了日」はおかしいです。2999/12/aaになっています。");
	}

	@Test
	public void エラーメッセージの作成の行Scopeの場合() {
		assertThat(ErrorLine.newErrorNumberOfLine(LINE1).getErrorMessageList())
				.containsExactly("12行目の列数はおかしいです。20になっています。");
	}

	private ErrorLine newColumnScopeErrorLine() {
		return ErrorLine.of(LINE2, Arrays.asList(Column.TITLE_ID, Column.DELIVERY_END_DATE));
	}
}
