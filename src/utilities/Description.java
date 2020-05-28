package utilities;

public class Description {
	
	
	// I can make these public since Strings are final
	public String name;
	
	public String tooltip;
	
	public String shortFlavor;
	
	public String longFlavor;

	public Description(String name, String tooltip, String flavor) {
		this.name = name;
		this.tooltip = tooltip;
		changeFlavor(flavor);
	}
	
	public Description(Description desc) {
		this.name = desc.name;
		this.tooltip = desc.tooltip;
		this.shortFlavor = desc.shortFlavor;
		this.longFlavor = desc.longFlavor;
	}
	
	public Description() {
		name = "";
		tooltip = "";
		shortFlavor = "";
		longFlavor = "";
	}
	
	public void changeName(String newName) {
		name = newName;
	}
	
	public void changeFlavor(String longFlavor) {
		this.longFlavor = longFlavor;
		if(longFlavor.length() > 64) {
			shortFlavor = new String(longFlavor.substring(0, 61) + "...");
		}else {
			shortFlavor = new String(longFlavor);
		}
	}
	
	
	public String toString() {
		return name + ": "+ shortFlavor;
	}
	
	
	
	
}
