package utilities;

//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

public class ResourcePackage {
	
	private double[] resources;
	
	//private Set<ResourceTypes> affected;
	
	public final PackageType type;
	
	public ResourcePackage(PackageType type, List<ResourcePair> source) {
		this.resources = new double[ResourceTypes.count()];
		//affected = new HashSet<ResourceTypes>();
		for(ResourcePair pair : source) {
			resources[pair.type.getDbValue()] = pair.quantity;
			//affected.add(pair.type);
		}
		this.type=type;
	}
	
	public ResourcePackage(PackageType type) {
		this.resources = new double[ResourceTypes.count()];
		this.type=type;
		for(int i = 0; i < resources.length;i++) {
			resources[i] = 1;
		}
	}
	
	public ResourcePackage(ResourcePackage source) {
		type = source.type;
		resources = source.getResources();
	}
	
	public ResourcePackage() {
		resources = new double[ResourceTypes.count()];
		type = PackageType.flat;
	}
	
	public void add(ResourceTypes type, double amount) {
		resources[type.getDbValue()]+=amount;
	}
	
	public void addPackage(ResourcePackage source) {
		double[] other = source.getResources();
		for(int i = 0 ; i < other.length;i++)
			resources[i] += other[i];
	}
	
	public void multiplication(ResourcePackage source) {
		double[] other = source.getResources();
		for(int i = 0; i < other.length; i++) {
			resources[i]*=other[i];
		}
	}
	
	public void multiply(ResourceTypes type, double amount) {
		resources[type.getDbValue()]*=amount;
	}
	
	public void scalarMultiplication(double scalar) {
		for(int i = 0; i < resources.length;i++) {
			resources[i]*=scalar;
		}
	}
	
	public double[] getResources() {
		return resources.clone();
	}
	
	public double get(ResourceTypes type) {
		return resources[type.getDbValue()];
	}

	public boolean sufficientResources(ResourcePackage cost) {
		double[] costArray = cost.getResources(); //A throws clause which combines all the different resources that were insufficient would be useful as well
		for(int i = 0; i < resources.length;i++) {
			if(resources[i]-costArray[i] < 0)
				return false; //A throws clause would be better here because it could say which resources was insufficient
				//ResourcesTypes.
		}
		return true;
	}
	
	public String toString() {
		String naive = "";
		for(int i = 0; i < resources.length;i++) {
			if(resources[i]!= 0)
				naive += String.format("%s: %d\t", ResourceTypes.fromDbValue(i).toString(),(int)resources[i]);
		}
		return naive;
	}

}
