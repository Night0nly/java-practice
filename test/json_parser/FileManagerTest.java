import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileManagerTest {

	private static final Line BOOK_LINE_1 = Line.of(1,
			"id1	cp1	store1	Toyboo!	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_2 = Line.of(2,
			"id2	cp2	store2	Toyboo!	電子書籍	author2	titleId2	titleName2	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line SAMPLE_LINE_1 = Line.of(6,
			"id6	cp1	store1	Toyboo!	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	サンプル	1	サンプル	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");

	private static final Books BOOKS = Books.of(Lines.of(Arrays.asList(BOOK_LINE_1, BOOK_LINE_1, BOOK_LINE_2, SAMPLE_LINE_1)));

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	@Test
	public void ファイル読み込みの確認() throws IOException {
		final Path workingDirPath = tmpFolder.newFolder().toPath();
		final String fileName = "testfile";
		final String line1 = "aaaaあいうえお";
		final String line2 = "bbbb12345";
		Files.write(workingDirPath.resolve(fileName), Arrays.asList(line1, line1, line2));
		assertThat(FileManager.of(workingDirPath).readFile(workingDirPath.resolve(fileName).toAbsolutePath()).getNoHeaderLineList())
		.usingFieldByFieldElementComparator()
		.containsExactly(Line.of(2, line1), Line.of(3, line2));
	}

	@Test
	public void ファイル書き込みの確認() throws IOException {
		final Path workingDirPath = tmpFolder.newFolder().toPath();
		final String fileName = "testfile";
		final String line1 = "aaaaあいうえお";
		final String line2 = "bbbb12345";

		FileManager.of(workingDirPath).writeFile(Arrays.asList(line1, line2).stream().collect(Collectors.joining("\n")),
				fileName);

		assertThat(Files.readAllLines(workingDirPath.resolve(fileName))).containsExactly(line1, line2);
	}

	@Test
	public void JSONファイル書き込みの確認() throws IOException {
		final Path workingDirPath = tmpFolder.newFolder().toPath();

		FileManager.of(workingDirPath).writeFileJSON(BOOKS, "testFile.json");
		assertThat(Files.readAllLines(workingDirPath.resolve("testFile.json")))
				.isEqualTo(Files.readAllLines(
						Paths.get(Paths.get(System.getProperty("user.dir")).getParent().getParent().toString(),
								"test", "json_parser", "resources", "test.json")));
	}

}
