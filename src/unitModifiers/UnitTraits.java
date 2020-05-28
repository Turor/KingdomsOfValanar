package unitModifiers;

public enum UnitTraits {	
	AMPHIBIOUS,
	BRED_FOR_WAR,
	BRUTAL,
	COURAGEOUS,
	ETERNAL,
	FRIENDLY,
	HORRIFYING,
	MARTIAL,
	MINDLESS,
	REGENERATE,
	RAVENOUS,
	ROCK_HURLER,
	SAVAGE,
	STALWART,
	TWISTING_ROOTS,
	UNDEAD;

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
