import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.TemporaryFolder;

public class ErrorOutputTest {
	private static final Line LINE1 = Line.of(50,
			"id1	cp1	store1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line LINE2 = Line.of(12,
			"id1	cp1	store1	Toyboo!	電子書籍	author1	   	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/aa	2017/07/11 06:06:52	2017/07/11 06:06:52");
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Rule
	public final TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void エラーファイルの作成確認() throws IOException {
		newErrorOutput().writeToErrorFile();

		assertThat(Files.readAllLines(temporaryFolder.getRoot().toPath().resolve("error.tsv")))
				.usingFieldByFieldElementComparator().isEqualTo(Files.readAllLines(
						Paths.get(Paths.get(System.getProperty("user.dir")).getParent().getParent().toString(),
								"test", "json_parser", "resources", "error.tsv")));
	}

	@Test
	public void コンソールにエラー情報を出力することの確認() {
		String errorLog = "50行目の列数はおかしいです。20になっています。\r\n" + 
				"12行目の「タイトルID」は空文字です。\r\n" +
				"12行目の「配信終了日」はおかしいです。2999/12/aaになっています。\r\n";
		newErrorOutput().printErrorList();
		assertThat(systemOutRule.getLog()).isEqualTo(errorLog);
	}

	private ErrorOutput newErrorOutput() {
		final FileManager fileManager = FileManager.of(temporaryFolder.getRoot().toPath());
		return ErrorOutput.of(fileManager, Errors.of(Lines.of(Arrays.asList(LINE1, LINE1, LINE2))));
	}
}
