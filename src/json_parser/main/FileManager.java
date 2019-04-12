import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/** ファイルの読み込みと出力を管理するクラス。 */
public class FileManager {

	private final Path workingDirPath;

	private FileManager(final Path workingDirPath) {
		this.workingDirPath = workingDirPath;
	}

	public static FileManager of(final Path workingDirPath) {
		return new FileManager(workingDirPath);
	}

	/**
	 * 作業ディレクトリにあるファイルを読み込み文字列のリストを取得します
	 * 
	 * @param path
	 *            読むファイルのパス。
	 * @return 読み込んだ文字列のリスト
	 * @throws IOException
	 */
	public Lines readFile(final Path path) throws IOException {
		final List<String> contentList = Collections.unmodifiableList(Files.readAllLines(path));
		final List<Line> lineList = IntStream.range(0, contentList.size())
				.mapToObj(index -> Line.of(index + 1, contentList.get(index)))
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
		return Lines.of(lineList);
	}

	/**
	 * 作業ディレクトリに文字列リストを出力します
	 * 
	 * @param fileName
	 * @param books
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws IOException
	 */
	public void writeFileJSON(final Books books, final String filename) throws JsonGenerationException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		final DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
		printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

		final File file = workingDirPath.resolve(filename).toFile();
		mapper.writer(printer).writeValue(file, books.getBookList());
	}

	public void writeFile(final String content, final String filename) throws IOException  {
		Files.write(workingDirPath.resolve(filename), content.getBytes());
	}
}
