import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** 書籍 */
public class Book {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	/** サンプルだったらsampleFlagはtrueです。 */
	private final boolean sampleFlag;

	/** Id */
	@JsonProperty("id")
	private final String id;

	/** 出版社 */
	@JsonProperty("publisher")
	private final String publisher;

	/** 作者 */
	@JsonProperty("author")
	private final String author;

	/** タイトル名 */
	@JsonProperty("title")
	private final String title;

	/** 配信許諾名 */
	@JsonProperty("subtitle")
	private final String subtitle;

	/** 通番 */
	@JsonProperty("volume")
	private final int volume;

	/** 価格 */
	@JsonProperty("price")
	private final int price;

	/** 配信開始日 */
	@JsonProperty("delivery_starts_at")
	private final LocalDate deliveryStartsAt;

	/** 配信終了日 */
	@JsonProperty("delivery_ends_at")
	private final LocalDate deliveryEndsAt;

	@JsonProperty("sample")
	private Book sample;

	@JsonIgnore
	private final String format;

	@JsonIgnore
	private final String titleId;

	/**
	 * 行を読み込んで、Bookを戻すメソッド。
	 * 
	 * @param line
	 *            読み込む行。
	 * @return Book
	 */
	public static Book of(final Line line) {
		final LocalDate deliveryStartDate = LocalDate.from(FORMATTER.parse(line.getColumnValue(Column.DELIVERY_START_DATE)));
		final LocalDate deliveryEndDate = LocalDate.from(FORMATTER.parse(line.getColumnValue(Column.DELIVERY_END_DATE)));
		final String id = line.getColumnValue(Column.ID);
		final String publisher = line.getColumnValue(Column.PUBLISHER);
		final String author = line.getColumnValue(Column.AUTHOR);
		final String title = line.getColumnValue(Column.TITLE);
		final String titleId = line.getColumnValue(Column.TITLE_ID);
		final String subtitle = line.getColumnValue(Column.SUBTITILE);
		final String format = line.getColumnValue(Column.FORMAT);
		final int volume = Integer.parseInt(line.getColumnValue(Column.VOLUME));
		final int price = Integer.parseInt(line.getColumnValue(Column.PRICE));
		final boolean sampleFlag = line.getColumnValue(Column.TYPE).equals("サンプル");
		return new Book(id, publisher, author, titleId, title, subtitle, format, volume, price, deliveryStartDate,
				deliveryEndDate, sampleFlag);
	}

	public Book(final String id, final String publisher, final String author, final String titleId, final String title,
			final String subtitle, final String format, final int volume, final int price,
			final LocalDate deliveryStartsAt, final LocalDate deliveryEndsAt, final boolean sampleFlag) {
		this.id = id;
		this.publisher = publisher;
		this.author = author;
		this.titleId = titleId;
		this.title = title;
		this.subtitle = subtitle;
		this.format = format;
		this.volume = volume;
		this.price = price;
		this.deliveryStartsAt = deliveryStartsAt;
		this.deliveryEndsAt = deliveryEndsAt;
		this.sampleFlag = sampleFlag;
	}

	@JsonIgnore
	public String getSampleIdentificationKey() {
		return format + titleId + volume;
	}

	public String getId() {
		return id;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public int getVolume() {
		return volume;
	}

	public int getPrice() {
		return price;
	}

	public LocalDate getDeliveryStartsAt() {
		return deliveryStartsAt;
	}

	public LocalDate getDeliveryEndsAt() {
		return deliveryEndsAt;
	}

	public Book getSample() {
		return sample;
	}

	public void setSample(Book sample) {
		this.sample = sample;
	}

	public boolean isSample() {
		return sampleFlag;
	}

	public String getFormat() {
		return format;
	}

	public String getTitleId() {
		return titleId;
	}
	
}
