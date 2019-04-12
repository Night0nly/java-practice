import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Execute {

	public static void main(String[] args) {

		final JFileChooser chooser = new JFileChooser();
		final FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("TSV file", "tsv");
		chooser.setFileFilter(fileFilter);

		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		final Path path = Paths.get(chooser.getSelectedFile().getPath());
		final FileManager fileManager = FileManager.of(path.getParent());

		try {
			final Lines lines = fileManager.readFile(path);
			final Errors errors = Errors.of(lines);

			if (errors.isExist()) {
				final ErrorOutput errorOutput = ErrorOutput.of(fileManager, errors);
				errorOutput.printErrorList();
				errorOutput.writeToErrorFile();
				return;
			}

			final Books books = Books.of(lines);

			final BookMetadataOutput output = BookMetadataOutput.of(fileManager, books);
			output.toybooJSON();
			output.deliveryStart201804JSON();
			output.price500OrLess();

			System.out.println("Finished");
		} catch (JsonGenerationException e) {
			System.out.println("Fail generate Json" + e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Fail mapping to Json object" + e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}