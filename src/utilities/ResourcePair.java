package utilities;

public class ResourcePair {
	
	public final ResourceTypes type;
	
	public double quantity;
	
	public ResourcePair(ResourceTypes type, double quantity) {
		this.type = type;
		this.quantity = quantity;
	}
	
	public ResourcePair(ResourcePair source) {
		type = source.type;
		quantity= source.quantity;
	}
	

}
