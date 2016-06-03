import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class AllGenes {

	public static void main(String[] args) {
		Writer w = null;

		try {
			w = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("dataset.arff"), "utf-8"));
			w.write("@Relation probe_gene_expression");
			w.write(System.getProperty("line.separator"));
			Scanner scanner = new Scanner(new File("src/all_genes_expressive.csv"));
			scanner.useDelimiter(",");

			String x=scanner.nextLine();
			String[] arr=x.split(",");
			for(int i = 0; i < 9; i++){
				w.write("@Attribute " + arr[i] + " NUMERIC");
				w.write(System.getProperty("line.separator"));
			}

			//Data Section
			w.write("@Data");
			w.write(System.getProperty("line.separator"));

			while(scanner.hasNext()){
				x=scanner.nextLine();
				String[] arr2=x.split(",");
				for(int i = 0; i < 9; i++)
					w.write(arr2[i] + ", ");
				w.write(System.getProperty("line.separator"));
			}
			scanner.close();
			System.out.println("The arff file was created successfully!");
		} catch (IOException ex) {
			// report
		} finally {
			try {w.close();} catch (Exception ex) {/*ignore*/}
		}
	}

}
