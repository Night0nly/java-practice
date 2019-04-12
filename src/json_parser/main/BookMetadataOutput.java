import java.io.IOException;
import java.time.YearMonth;

/** JSON形式ファイルを出力できるクラス。 */
public class BookMetadataOutput {

	private final FileManager fileManager;
	private final Books books;

	private BookMetadataOutput(final FileManager fileManager, final Books books) {
		this.fileManager = fileManager;
		this.books = books;
	}

	public static BookMetadataOutput of(final FileManager fileManager, final Books books) {
		return new BookMetadataOutput(fileManager, books);
	}

	/**
	 * 出版社がToyboo!の作品のみファイルに指定のJSON形式で出力します
	 * 
	 * @throws IOException
	 */
	public void toybooJSON() throws IOException {
		fileManager.writeFileJSON(books.getBooksPublishedBy("Toyboo!"), "publisher_toyboo.json");
	}

	/**
	 * 配信開始日が2018年4月の作品のみファイルに指定のJSON形式で出力します
	 * 
	 * @throws IOException
	 */
	public void deliveryStart201804JSON() throws IOException {
		fileManager.writeFileJSON(books.getBooksDeliveredInMonth(YearMonth.of(2018, 4)), "delivery_starts_201804.json");
	}

	/**
	 * 価格が500円以下の作品のみファイルに指定のJSON形式で出力します
	 * 
	 * @throws IOException
	 */
	public void price500OrLess() throws IOException {
		fileManager.writeFileJSON(books.getBooksPriceLessOrEqualTo(500), "price_500_or_less_main.json");
	}

}
