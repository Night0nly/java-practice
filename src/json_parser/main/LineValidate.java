import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LineValidate {
	private static final int NUMBER_OF_COLUMN = 24;
	private static final EnumMap<Column, Predicate<String>> CHECKER_MAP = makeCheckerMap();

	/**
	 * 行のフォーマットをチェックするメソッド。
	 * 
	 * @param line
	 *            行。
	 * @param index
	 *            行の位置。
	 * @return エラーがない場合は{@code Optional.empty()}を戻す。
	 */
	public static Optional<ErrorLine> validateLine(final Line line) {

		if (line.getColumnsSize() != NUMBER_OF_COLUMN) {
			return Optional.of(ErrorLine.newErrorNumberOfLine(line));
		}

		final List<Column> errorColumnList = CHECKER_MAP.entrySet().stream()
				.filter(e -> e.getValue().test(line.getColumnValue(e.getKey())))
				.map(e -> e.getKey())
				.collect(Collectors.toList());

		return errorColumnList.isEmpty() ? Optional.empty() : Optional.of(ErrorLine.of(line, errorColumnList));

	}

	private static EnumMap<Column, Predicate<String>> makeCheckerMap() {
		final ColumnValidate columnValidator = new ColumnValidate();
		final EnumMap<Column, Predicate<String>> checkerMap = new EnumMap<>(Column.class);
		checkerMap.put(Column.TITLE_ID, columnValidator.isEmpty());
		checkerMap.put(Column.FORMAT, columnValidator.isEmpty());
		checkerMap.put(Column.TYPE, columnValidator.isNotTypeFormat());
		checkerMap.put(Column.VOLUME, columnValidator.isNotANumber());
		checkerMap.put(Column.PRICE, columnValidator.isNotANumber());
		checkerMap.put(Column.DL_PERIOD, columnValidator.isNotPeriodColumnFormat());
		checkerMap.put(Column.DELIVERY_START_DATE, columnValidator.isNotParseableDate());
		checkerMap.put(Column.DELIVERY_END_DATE, columnValidator.isNotParseableDate());
		checkerMap.put(Column.PERMISSION_START_DATE, columnValidator.isNotParseableDate());
		checkerMap.put(Column.PERMISSION_END_DATE, columnValidator.isNotParseableDate());
		checkerMap.put(Column.UPDATE_TIME, columnValidator.isNotParseableDateTime());
		checkerMap.put(Column.REGISTRATION_TIME, columnValidator.isNotParseableDateTime());
		return checkerMap;
	}

}
