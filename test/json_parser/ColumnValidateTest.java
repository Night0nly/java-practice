import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ColumnValidateTest {
	private static final ColumnValidate VALIDATOR = new ColumnValidate();

	@Test
	public void 文字列の数字が数字でないものの判定でfalseとなることを確認() {
		assertThat(VALIDATOR.isNotANumber().test("333")).isFalse();
	}

	@Test
	public void 文字列の文字が数字でないものの判定でtrueとなることを確認() {
		assertThat(VALIDATOR.isNotANumber().test("aa")).isTrue();
	}

	@Test
	public void 文字列の記号が数字でないものの判定でtrueとなることを確認() {
		assertThat(VALIDATOR.isNotANumber().test("#%")).isTrue();
	}

	@Test
	public void 空文が空文の判定でtrueとなることを確認() {
		assertThat(VALIDATOR.isEmpty().test("   ")).isTrue();
	}

	@Test
	public void 文字列が空文の判定でfalseとなることを確認() {
		assertThat(VALIDATOR.isEmpty().test("abc")).isFalse();
	}

	@Test
	public void 間違い日付フォーマットの文字列が日時フォーマットでないものの判定でtrueになることの確認() {
		assertThat(VALIDATOR.isNotParseableDateTime().test("2000/11/99 11:22:22")).isTrue();
	}

	@Test
	public void 間違い時間フォーマットの文字列が日時フォーマットでないものの判定でtrueになることの確認() {
		assertThat(VALIDATOR.isNotParseableDateTime().test("2000/11/1 11:99:22")).isTrue();
	}

	@Test
	public void 日時フォーマットの文字列が日時フォーマットでないものの判定でfalseになることの確認() {
		assertThat(VALIDATOR.isNotParseableDateTime().test("2000/11/1 11:11:22")).isTrue();
	}

	@Test
	public void 間違い日にちフォーマットの文字烈が日付フォーマットでないものの判定でtrueとなることの確認() {
		assertThat(VALIDATOR.isNotParseableDate().test("2000/11/1")).isTrue();
	}

	@Test
	public void 正しい日にちフォーマットの文字烈が日付フォーマットでないものの判定でtrueとなることの確認() {
		assertThat(VALIDATOR.isNotParseableDate().test("abc")).isTrue();
	}

	@Test
	public void 無期限の文字列が期限フォーマットでないものの判定でfalseとなることの確認() {
		assertThat(VALIDATOR.isNotPeriodColumnFormat().test("無期限")).isFalse();
	}

	@Test
	public void 数字の文字列が期限フォーマットでないものの判定でfalseとなることの確認() {
		assertThat(VALIDATOR.isNotPeriodColumnFormat().test("22")).isFalse();
	}

	@Test
	public void 無期限と数字以外の文字列が期限フォーマットでないものの判定でtrueとなることの確認() {
		assertThat(VALIDATOR.isNotPeriodColumnFormat().test("aaa")).isTrue();
	}

	@Test
	public void DLの文字列が期限フォーマットでないものの判定でfalseとなることの確認() {
		assertThat(VALIDATOR.isNotTypeFormat().test("DL")).isFalse();
	}

	@Test
	public void サンプルの文字列が期限フォーマットでないものの判定でfalseとなることの確認() {
		assertThat(VALIDATOR.isNotTypeFormat().test("サンプル")).isFalse();
	}

	@Test
	public void DLとサンプル文字列が期限フォーマットでないものの判定でtrueとなることの確認() {
		assertThat(VALIDATOR.isNotTypeFormat().test("aa")).isTrue();
	}
}
