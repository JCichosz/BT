import java.util.ArrayList;
import java.util.List;

/*	
 * Created by: Justyna Cichosz
 * 	Date: 2/05/2016
 * 	Purpose: Defines a 'Package' object that contains the package name and a list of primary dependencies.
 */


public class Package {

	private String name;					// Name of the package
	private List<String> dependencies;		// Stores the list of primary dependencies of the package
	
	// Initialises a 'Package' object with a name 
	public Package(String name) {
		this.name = name;
		dependencies = new ArrayList<String>();
	}
	
	
	// Adds package to list of dependencies only if not a duplicate entry.
	public void addDependency(String pckgName) {
		if ( !dependencies.contains(pckgName)) {
			dependencies.add(pckgName);
		}
	}
	
	
	// Returns the name of the package.
	public String getName() {
		return name;
	}

	
	// Changes the name of the package.
	public void setName(String name) {
		this.name = name;
	}

	
	// Returns the list of dependencies.
	public List<String> getDependencies() {
		return dependencies;
	}

	
	// Changes the list of dependencies.
	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}

}
