import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class BookMetadataOutputTest {
	private static final Line BOOK_LINE_1 = Line.of(1,
			"id1	cp1	store1	Toyboo!	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_2 = Line.of(2,
			"id2	cp2	store2	Toyboo!	電子書籍	author2	titleId2	titleName2	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_3 = Line.of(3,
			"id3	cp3	store3	publisher3	電子書籍	author3	titleId3	titleName3	EPUB3.0	汎用	DL	1	本編	○	○	○	400	無期限	2017/06/01	2999/12/31	2018/04/06	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_4 = Line.of(4,
			"id4	cp4	store4	publisher4	電子書籍	author4	titleId4	titleName4	EPUB3.0	汎用	DL	1	本編	○	○	○	500	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line BOOK_LINE_5 = Line.of(5,
			"id5	cp5	store5	publisher5	電子書籍	author5	titleId5	titleName5	EPUB3.0	汎用	DL	1	本編	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");

	private static final Line SAMPLE_LINE_1 = Line.of(6,
			"id6	cp1	store1	Toyboo!	電子書籍	author1	titleId1	titleName1	EPUB3.0	汎用	サンプル	1	サンプル	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");
	private static final Line SAMPLE_LINE_2 = Line.of(7,
			"id7	cp1	store2	Toyboo!	電子書籍	author2	titleId2	titleName2	EPUB3.0	汎用	サンプル	1	サンプル	○	○	○	1000	無期限	2017/06/01	2999/12/31	2017/06/01	2999/12/31	2017/07/11 06:06:52	2017/07/11 06:06:52");

	private static final Books BOOKS = Books.of(Lines.of(Arrays.asList(SAMPLE_LINE_2, SAMPLE_LINE_2, SAMPLE_LINE_1, BOOK_LINE_5,
			BOOK_LINE_4, BOOK_LINE_3, BOOK_LINE_2, BOOK_LINE_1)));
	


	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void 出版社による絞り込みの確認() throws IOException {
		newOutput().toybooJSON();
		compareFile(
				Paths.get(Paths.get(System.getProperty("user.dir")).getParent().getParent().toString(),
						"test", "json_parser", "resources", "expected_publisher.json"),
				Paths.get(temporaryFolder.getRoot().toString(), "publisher_toyboo.json"));

	}

	@Test
	public void 配信開始日による絞り込みの確認() throws IOException {
		newOutput().deliveryStart201804JSON();
		compareFile(Paths.get(Paths.get(System.getProperty("user.dir")).getParent().getParent().toString(),
					"test", "json_parser", "resources", "expected_date.json"),
				Paths.get(temporaryFolder.getRoot().toString(), "delivery_starts_201804.json"));
	}

	@Test
	public void 価格による絞り込みの確認() throws IOException {
		newOutput().price500OrLess();
		compareFile(Paths.get(Paths.get(System.getProperty("user.dir")).getParent().getParent().toString(),
					"test", "json_parser", "resources", "expected_price.json"),
				Paths.get(temporaryFolder.getRoot().toString(), "price_500_or_less_main.json"));
	}

	private void compareFile(Path path1, Path path2) throws IOException {
		final File file1 = path1.toFile();
		final File file2 = path2.toFile();
		assertTrue(FileUtils.contentEquals(file1, file2));
	}

	private BookMetadataOutput newOutput() {
		final FileManager fileManager = FileManager.of(temporaryFolder.getRoot().toPath());
		return BookMetadataOutput.of(fileManager, BOOKS);
	}
}
