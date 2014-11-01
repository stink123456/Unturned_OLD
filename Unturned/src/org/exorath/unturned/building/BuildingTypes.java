package org.exorath.unturned.building;

public enum BuildingTypes {
	FOUNDATION(0, "FOUNDATION",7,1,7,true, 100),
	WOOD_PLATFORM(1, "WOOD_PLATFORM",7,1,7,false, 100),
	WOOD_HOLE(2, "WOOD_HOLE",7,1,7,false, 100),
	GREENHOUSE_FOUNDATION(3, "GREENHOUSE_FOUNDATION",7,1,7, true, 100),
	DOCK_FOUNDATION(5, "DOCK_FOUNDATION",7,1,7, true, 100),
	WOOD_PILLAR(6, "WOOD_PILLAR",1,3,1, false, 100),
	STONE_PILLER(7, "STONE_PILLAR",1,2,1, false, 100),
	WOOD_POST(8, "WOOD_POST",1,2,1, false, 100),
	STONE_POST(9, "STONE_POST",1,2,1, false, 100),
	WOOD_WALL(10, "WOOD_WALL",1,3,5, false, 100),
	STONE_WALL(11, "STONE_WALL",1,3,5, false, 100),
	WOOD_RAMPANT(12, "WOOD_RAMPANT",1,2,7, false, 100),
	STONE_RAMPANT(13, "STONE_RAMPANT",1,2,7, false, 100),
	WOOD_DOORWAY(14, "WOOD_DOORWAY",1,3,5, false, 100),
	STONE_DOORWAY(15, "STONE_DOORWAY",1,3,5, false, 100),
	WOOD_DOOR(16, "WOOD_DOOR",1,2,1, false, 100),
	METAL_DOOR(17, "METAL_DOOR",1,2,1, false, 100),
	WOOD_WINDOW(18, "WOOD_WINDOW",1,3,5, false, 100),
	STONE_WINDOW(19, "STONE_WINDOW",1,3,5, false, 100),
	WOOD_SHUTTER(20, "WOOD_SHUTTER",1,3,1, false, 100),
	METAL_SHUTTER(21, "METAL_SHUTTER",1,3,1, false, 100),
	WOOD_GARAGE_PORT(22, "WOOD_GARAGE_PORT",1,3,5, false, 100),
	STONE_GARAGE_POST(23, "STONE_GARAGE_PORT",1,3,5, false, 100),
	WOOD_GATE(24, "WOOD_GATE",1,3,5, false, 100),
	STONE_GATE(25, "STONE_GATE",1,3,5, false, 100),
	RAMP(26, "RAMP",7,3,7, false, 100),
	LADDER(27, "LADDER",1,3,1, false, 100);
	
	private int id;
	private String name;
	private int width;
	private int height;
	private int length;
	private boolean foundation;
	private int health;
	
	BuildingTypes(int id,String name, int width, int height, int length, boolean foundation, int health){
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.length = length;
		this.foundation = foundation;
		this.health = health;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getLength() {
		return length;
	}
	public boolean isFoundation() {
		return foundation;
	}
	public int getHealth(){
		return health;
	}
}
