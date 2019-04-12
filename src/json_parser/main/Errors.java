import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Errors {
	private final List<ErrorLine> errorList;

	public static Errors of(final Lines lines) {
		return new Errors(lines.getNoHeaderLineList().stream()
				.map(LineValidate::validateLine)
				.flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
				.collect(Collectors.toList()));
	}

	private Errors(final List<ErrorLine> errorList) {
		this.errorList = Collections.unmodifiableList(errorList);
	}

	public List<ErrorLine> getErrorList() {
		return errorList;
	}

	public boolean isExist() {
		return !errorList.isEmpty();
	}
}
