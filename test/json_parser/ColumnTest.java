import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ColumnTest {

	@Test
	public void エラー名の確認() {
		assertThat(Column.TITLE_ID.getName()).isEqualTo("タイトルID");
		assertThat(Column.FORMAT.getName()).isEqualTo("フォーマット");
		assertThat(Column.TYPE.getName()).isEqualTo("DL区分");
		assertThat(Column.REGISTRATION_TIME.getName()).isEqualTo("登録日時");
		assertThat(Column.VOLUME.getName()).isEqualTo("通番");
		assertThat(Column.PRICE.getName()).isEqualTo("価格");
		assertThat(Column.DL_PERIOD.getName()).isEqualTo("DL期限");
		assertThat(Column.DELIVERY_START_DATE.getName()).isEqualTo("配信開始日");
		assertThat(Column.DELIVERY_END_DATE.getName()).isEqualTo("配信終了日");
		assertThat(Column.PERMISSION_START_DATE.getName()).isEqualTo("許諾開始日");
		assertThat(Column.PERMISSION_END_DATE.getName()).isEqualTo("許諾終了日");
		assertThat(Column.UPDATE_TIME.getName()).isEqualTo("更新日時");
	}

}
