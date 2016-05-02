import java.util.ArrayList;
import java.util.List;

public class Package {

	private String name;					// Name of the package
	private List<String> dependencies;		// Stores the list of dependencies of the package
	
		
	public Package(String name) {
		this.name = name;
		dependencies = new ArrayList<String>();
	}
	
	// Adds package to list of dependencies only if not a duplicate entry.
	public void addDependency(String pckgName) {
		
		if ( !dependencies.contains(pckgName)) {
			dependencies.add(pckgName);
			
		} else {}
	}
	
	// Returns a list of packages sorted alphabetically.
	public List<String> getAlphabeticalDependencies() {
		java.util.Collections.sort(dependencies);
		return dependencies;
	}
	
	// Prints out the dependencies in alphabetical order. 
	// Does not include the package itself in case of circular dependencies.
	public void printDependencies() {
		java.util.Collections.sort(dependencies);
		
		for ( int i = 0; i < dependencies.size(); i++ ) {
			
			if ( dependencies.get(i) != name ) {
				System.out.print( dependencies.get(i) + " ");
			}
		}
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}
}
