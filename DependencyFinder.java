import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DependencyFinder {

	public static void main(String[] args) {
		if ( args.length >= 1) {
				
			DependencyFinder finder = new DependencyFinder(args[0]);
		
			if ( args.length > 1 ) {
				for ( int i = 1; i < args.length; i++ ) {
				finder.addPackage(args[i]);
				}
			} else System.out.println("Specify package names.");
			
		} else System.out.println("Parameters needed: text file and package names.");
	}
	
	
	
	private String fileName;
	private Scanner scan;
	private List<Package> packages;
	
	public DependencyFinder(String fileName) {
		this.fileName = fileName;
	}
	
	// Creates a new 'Package' object and adds it to the list.
	private void addPackage(String name) {
		Package pckg = new Package(name);
		packages.add(pckg);
	}
	
	public void readFile() {
		
		FileReader input;
		
		try {
			
			input = new FileReader(fileName);
			scan = new Scanner(input);
			
			input.close();
			
		} catch ( IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private void parseInput() {
		
		String line;
		
		while ( scan.hasNextLine() ) {
			
			line = scan.nextLine();
			
			if ( line.equals(null) ) {
				line = scan.nextLine();
			}
			
			line.trim();
		}
		
	}
	
	// Prints out the package names and their dependencies.
	public void printAllDependencies() {
		
		for (int i = 0; i < packages.size(); i++ ) {
			System.out.println(packages.get(i).getName() + " -> ");
			packages.get(i).printDependencies();
		}
	}
}
