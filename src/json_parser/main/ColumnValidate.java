import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

public class ColumnValidate {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	private static final String SAMPLE_VALUE = "サンプル";
	private static final String BOOK_VALUE = "DL";
	private static final String NUMBER_REGEX = "\\d+";
	private static final String NO_PERIOD_VALUE = "無期限";

	public Predicate<String> isNotANumber() {
		return value -> !value.matches(NUMBER_REGEX);
	}

	public Predicate<String> isEmpty() {
		return value -> value.trim().isEmpty();
	}

	public Predicate<String> isNotParseableDateTime() {
		return dateTime -> {
			try {
				DATE_TIME_FORMATTER.parse(dateTime);
				return false;
			} catch (DateTimeParseException e) {
				return true;
			}
		};
	}

	public Predicate<String> isNotParseableDate() {
		return date -> {
			try {
				DATE_FORMATTER.parse(date);
				return false;
			} catch (DateTimeParseException e) {
				return true;
			}
		};
	}

	public Predicate<String> isNotPeriodColumnFormat() {
		return value -> !value.matches(NUMBER_REGEX) && !value.equals(NO_PERIOD_VALUE);
	}

	public Predicate<String> isNotTypeFormat() {
		return value -> !value.equals(BOOK_VALUE) && !value.equals(SAMPLE_VALUE);
	}

}
