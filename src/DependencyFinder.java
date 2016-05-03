import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*	
 * Created by: Justyna Cichosz
 * 	Date: 02/05/2016
 * 	Purpose: To determine dependencies between packages and print out all of them for selected packages.
 */

public class DependencyFinder {

	private String fileName;				
	private Scanner scan;
	private List<Package> packages;			// List of all packages that have dependencies.
	private List<String> toFind;			// List of packages to find (from passed parameters).
	private String pckgName;				// Package name.
	private String dependencies;			// String of primary dependencies of a package defined in the file.
	
	// Initialises an 'DependencyFinder' object with specified fileName and initialises lists.
	public DependencyFinder(String fileName) {
		this.fileName = fileName;
		packages = new ArrayList<Package>();
		toFind = new ArrayList<String>();
	}
	
	
	// Creates a new 'Package' object and adds it to the list of all packages. 
	// Returns the newly created package.
	private Package addPackage(String name) {
		Package pckg = new Package(name);
		packages.add(pckg);
		return pckg;
	}
	
	
	// Adds the package name from passed parameters to the list of package dependencies to find.
	public void addToFind(String name) {
		toFind.add(name);
	}
	
	// Reads in and processes the data. A FileReader is used to get the information from the file,
	// Calls the parseInput() method which parses the obtained data.
	public void processData() {
		
		FileReader input;
		
		try {
			input = new FileReader(fileName);
			scan = new Scanner(input);
			
			parseInput();
			input.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// Parses the input from the file. 
	// Uses scanners and String methods to modify and extract data.
	private void  parseInput() {
		
		String line;							// References a line of input.
		
		while ( scan.hasNextLine() ) {			// While the is a next line of input.
			
			line = scan.nextLine();	
			line = line.trim();					// Delete leading and trailing spaces at the start/end of line.
			
			if ( !line.equals(null) && !line.equals("")) {		// If the line isn't null or empty.
		
				// If line doesn't contain a '->' - terminate and show an error message.
				if ( !line.contains("->") ) {					
					System.out.println("Invalid line. Missing '->'.");
					System.exit(0);
				}
				
				// If after trimming the line starts with a '->' - the package name is missing.
				// Terminate and display an error message.
				if ( line.startsWith("->") ) {
					System.out.println("Missing package name.");
					System.exit(0);
				}
				
				String[] tokens = line.split("->");		// Separate the package name from dependencies. 
				
				pckgName = tokens[0];					// Set the first argument after split  as the package name.
				pckgName = pckgName.trim();				// Delete leading and trailing white spaces from the package name.
				Package p = addPackage(pckgName);		// Add the package to list of all packages.
					
				if ( tokens.length > 1 ) {				// If there is more than one token (not just a package name).
					dependencies = tokens[1];			// Set dependencies as the String with all primary dependencies.
						
					// Initialise a new scanner to extract package names.
					Scanner dependencyScanner = new Scanner(dependencies);
					dependencyScanner.useDelimiter(" ");
						
					while ( dependencyScanner.hasNext() ) {		// While there are more tokens.
						String dependencyName = dependencyScanner.next();
					    dependencyName = dependencyName.trim();	// Delete leading and trailing spaces from the dependency name.
					    p.addDependency(dependencyName);		// Add the primary dependency to the package.
					}
					dependencyScanner.close();
				}
			} 
		}
	}
	
		
	// Finds package dependencies recursively. Takes in a package and a list of dependencies and returns the list of dependencies.
	public List<Package> findDependencies(Package p, List<Package> listDependencies) {
		
		// Add the package to the list of dependencies.
		listDependencies.add(p);				
		
		// For all dependencies of the package.
		for ( int i = 0; i < p.getDependencies().size(); i++ ) {
			Package current = null;			// Initialise the current package.
			
			// For all packages that have dependencies specified in the text file.
			for ( int j = 0; j < packages.size(); j++ ) {
				
				// If the name of the package on the list of dependencies matches a package
				// on the overall package dependency list - set it as the current one.
				if ( p.getDependencies().get(i).equals(packages.get(j).getName()) ) {
					current = packages.get(j);
				}
			}
				
			// If current package name was not found on the package list (might not have dependencies itself) -
			// add it to the list of packages.
			if ( current == null ) {
				current = addPackage(p.getDependencies().get(i));
			}
			// If current package is on the list of all packages with dependencies and not already on the 
			// list of dependencies of the current package - call the method recursively to find its dependencies.
			if ( packages.contains(current) && !listDependencies.contains(current)) {
					findDependencies(current, listDependencies);
			} 
			// If current package is not on the list of all packages with dependencies and not 
			// on the current's dependency list - add it.
			else if ( !packages.contains(current) && !listDependencies.contains(current)) {
					listDependencies.add(current);
			}
		}
		return listDependencies;		// Return the list of dependencies.
	}
	
	// Prints out the package names and their dependencies.
	public void printAllDependencies() {
		
		// For all packages that were specified.
		for (int i = 0; i < toFind.size(); i++ ) {
			
			List<Package> allDependencies = new ArrayList<Package>();	// List of all dependencies of a package stored as 'Packages'.
			List<String> all = new ArrayList<String>();					// List of all dependencies of a package stored as strings.
			int pckgIndex;												// Index of a package.
			
			// For all packages with dependencies.
			for ( int j = 0; j < packages.size(); j++ ){
					
				// If the package to find is on the list of all packages.
				if ( packages.get(j).getName().equals(toFind.get(i)) ) {
					
					pckgIndex = j;		// Set the package index to the matched one.
					findDependencies(packages.get(pckgIndex), allDependencies);	// Find all dependencies of the package.
					
					// For all dependencies on the list - extract names and add them to the list of Strings.
					for ( int k = 0; k < allDependencies.size(); k++ ) {
						all.add(allDependencies.get(k).getName());
					}
				}
			}
			
			// Print out the name of package that was to be found.
			System.out.print(toFind.get(i) + " -> ");
			java.util.Collections.sort(all);		// Sort the list of names alphabetically.
			
			// For all package names on the String dependency list.
			for ( int l = 0; l < all.size(); l++ ) {
				
				// If the name of the package is not the same as the original - print out the dependency.
				if ( !toFind.get(i).equals(all.get(l))) {
					System.out.print(all.get(l) + " ");
				}
			}
			System.out.println("");	
		}
	}
}
