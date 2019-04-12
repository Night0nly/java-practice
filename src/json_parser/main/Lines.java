import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lines {
	private final List<Line> lineList;
	
	public static Lines of(final List<Line> lineList) {
		return new Lines(lineList);
	}

	private Lines(final List<Line> lineList) {
		this.lineList = Collections.unmodifiableList(lineList);
	}

	public List<Line> getNoHeaderLineList() {
		  return lineList.stream()
				  .skip(1)
				  .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));	
	}
		
}
