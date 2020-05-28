package unitModifiers;

public enum UnitTraits{	
	AMPHIBIOUS(50),
	BRED_FOR_WAR(100),
	BRUTAL(200),
	COURAGEOUS(50),
	ETERNAL(50),
	FRIENDLY(5),
	HORRIFYING(200),
	MARTIAL(100),
	MINDLESS(100),
	REGENERATE(200),
	RAVENOUS(50),
	ROCK_HURLER(250),
	SAVAGE(50),
	STALWART(50),
	TWISTING_ROOTS(200),
	UNDEAD(50);

	UnitTraits(int cost) {
		this.cost = cost;
	}
	
	public final int cost;

	public String toName() {
		switch(this) {
		case AMPHIBIOUS:
			return "Amphibious";
		case BRED_FOR_WAR:
			return "Bred for War";
		case BRUTAL:
			return "Brutal";
		case COURAGEOUS:
			return "Courageous";
		case FRIENDLY:
			return "Friendly";
		case HORRIFYING:
			return "Horrifying";
		case MARTIAL:
			return "Martial";
		case RAVENOUS:
			return "Ravenous";
		case REGENERATE:
			return "Regenerate";
		case MINDLESS:
			return "Mindless";
		case ROCK_HURLER:
			return "Rock Hurler";
		case SAVAGE:
			return "Savage";
		case STALWART:
			return "Stalwart";
		case TWISTING_ROOTS:
			return "Twisting Roots";
		case UNDEAD:
			return "Undead";
		default:
			return "";
		}
	}
}
