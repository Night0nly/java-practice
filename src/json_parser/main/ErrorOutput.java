import java.io.IOException;
import java.util.stream.Collectors;

public class ErrorOutput {

	private static final String HEADER = "行\t範囲\t不正なカラム";
	private static final String LF = "\n";
	private static final String ERROR_FILENAME = "error.tsv";
	private final FileManager fileManager;
	private final Errors errors;

	private ErrorOutput(final FileManager fileManager, final Errors errors) {
		this.fileManager = fileManager;
		this.errors = errors;
	}

	public static ErrorOutput of(final FileManager fileManager, final Errors errors) {
		return new ErrorOutput(fileManager, errors);
	}

	/** コンソールにエラー情報を出力 */
	public void printErrorList() {
		errors.getErrorList().stream()
		.flatMap(error -> error.getErrorMessageList().stream())
		.forEach(System.out::println);
	}

	/**
	 * TSVファイルにエラー情報を出力するメソッド。
	 * @throws IOException
	 */
	public void writeToErrorFile() throws IOException {
		fileManager.writeFile(HEADER + LF
				+ errors.getErrorList().stream().map(error -> error.toString()).collect(Collectors.joining(LF)),
				ERROR_FILENAME);
	}
}
