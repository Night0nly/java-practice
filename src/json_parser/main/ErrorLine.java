import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/** エラーの情報 */
public class ErrorLine {
	private static final String COLUMN_SCOPE = "カラム";
	private static final String LINE_SCOPE = "行";
	private static final String TAB = "\t";
	private static final int MAX_COLUMNS = 24;

	private final Line errorLine;
	private final List<Column> errorColumnList;
	
	public static ErrorLine of(final Line errorLine, final List<Column> errorColumnList) {
		return new ErrorLine(errorLine, errorColumnList);
	}
	
	public static ErrorLine newErrorNumberOfLine(final Line errorLine) {
		return new ErrorLine(errorLine, new ArrayList<>());
	}

	private ErrorLine(final Line errorLine, final List<Column> errorColumnList) {
		this.errorLine = errorLine;
		this.errorColumnList = Collections.unmodifiableList(errorColumnList);
	}

	public Line getErrorLine() {
		return errorLine;
	}

	/**
	 * エラーメッセージを作成するクラス。
	 * 
	 * @return エラーメッセージ
	 */
	public List<String> getErrorMessageList() {
		final int lineIndex = errorLine.getIndex();
		return isErrorNumberOfLine() ?
				Arrays.asList(lineIndex + "行目の列数はおかしいです。" + errorLine.getColumnsSize() + "になっています。"):
				errorColumnList.stream()
					.map(column -> lineIndex + "行目の" + makeMessage(column, errorLine.getColumnValue(column)))
					.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}
	
	@Override
	public String toString() {
		return errorLine.getIndex() + TAB + (isErrorNumberOfLine() ?
				LINE_SCOPE:
				COLUMN_SCOPE + TAB
					+ errorColumnList.stream()
					.map(column -> column.getName())
					.collect(Collectors.joining(",")));
	}
	
	private String makeMessage(final Column column, String value) {
		return column == Column.TITLE_ID || column == Column.FORMAT ?
				"「" + column.getName() + "」は空文字です。":
				"「" + column.getName() + "」はおかしいです。" + value + "になっています。";
	}

	private boolean isErrorNumberOfLine() {
		return errorLine.getColumnsSize() != MAX_COLUMNS;
	}

}
