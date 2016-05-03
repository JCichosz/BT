/*	
 * Created by: Justyna Cichosz
 * 	Date: 02/05/2016
 * 	Purpose: Class with a main method to run the dependency finder.
 */

public class DependencyFinderMain {

	public static void main(String[] args) {
		
		if ( args.length >= 1) {										// If at least one parameter was passed in.
				
			DependencyFinder finder = new DependencyFinder(args[0]);	// Create an instance of DependencyFinder.
		
			if ( args.length > 1 ) {									// If package names were specified.
				
				for ( int i = 1; i < args.length; i++ ) {				// For all names passed in - add to list of 
				finder.addToFind(args[i]);								// packages to find.
				}
				
				finder.processData();									// Process the input.
				finder.printAllDependencies();							// Print out dependencies of specified packages.
			
			} else System.out.println("Specify package names.");
			
		} else System.out.println("Parameters needed: text file and package names.");
	}
}
