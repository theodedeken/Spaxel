package code.engine;

public enum SystemType {
	UI("ui"),
	AI("ai"),
	RENDER("render"),
	SOUND("sound"),
	SPAWNER("spawner"),
	AGE("age"),
	VELOCITY("velocity"),
	DAMAGE("damage"),
	HEALTH("health"),
	COOLDOWN("cooldown"),
	HIT("hit"),
	INPUT("input"),
	EQUIP("equip"),
	EXPERIENCE("experience");

	private final String name;

	SystemType(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
