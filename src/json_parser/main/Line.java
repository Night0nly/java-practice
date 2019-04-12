public class Line {
	private final int index;
	private final String[] columns;

	public static Line of(final int index, final String value) {
		return new Line(index, value);
	}

	private Line(final int index, final String value) {
		this.index = index;
		this.columns = value.split("\t");
	}

	public int getIndex() {
		return index;
	}

	public String getColumnValue(final Column column) {
		return columns[column.getPosition()];
	}

	public int getColumnsSize() {
		return columns.length;
	}
}
