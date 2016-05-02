import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class DependencyFinder {

	public static void main(String[] args) {
		
		DependencyFinder finder = new DependencyFinder(args[0]);
	}
	
	private String fileName;
	private Scanner scan;
	private List<Package> packages;
	
	public DependencyFinder(String fileName) {
		this.fileName = fileName;
	}
	

	public void readFile(String fileName) {
		
		FileReader input;
		
		try {
			
			input = new FileReader(fileName);
			scan = new Scanner(input);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void parseInput() {
		
	}
}
