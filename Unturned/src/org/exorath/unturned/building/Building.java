package org.exorath.unturned.building;

import org.bukkit.Location;
import org.exorath.unturned.Survivors.Survivor;
import org.exorath.unturned.libraries.Schematic;

public class Building {
	private Survivor owner;
	private BuildingTypes type;
    private int health;
    private Location minLoc;
    private Location maxLoc;
    private Schematic schematic;
    private boolean foundation;
	public Building(Survivor owner, Location minLoc, BuildingTypes type) {
		this.owner = owner;
		this.type = type;
		this.minLoc = minLoc;
		this.maxLoc = minLoc.clone().add(type.getWidth(),type.getHeight(),type.getWidth());
		this.health = type.getHealth();
		this.foundation = type.isFoundation();
		placeBuilding();
	}
	private void placeBuilding(){
		Schematic.pasteSchematic(minLoc.getWorld(), minLoc, schematic);
	}
    public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public BuildingTypes getType(){
		return type;
	}

	public Location getMinLoc() {
		return minLoc;
	}

	public Location getMaxLoc() {
		return maxLoc;
	}
	public Schematic getSchematic() {
		return schematic;
	}
	public void setSchematic(Schematic schematic) {
		this.schematic = schematic;
	}
	public Survivor getOwner(){
		return owner;
	}
	public boolean isFoundation() {
		return foundation;
	}
	public boolean contains(Location loc){
		if(loc.getX() > minLoc.getX() && loc.getX() < maxLoc.getX() && loc.getY() > minLoc.getY() && loc.getY() < maxLoc.getY() && loc.getZ() > minLoc.getZ() && loc.getZ() < maxLoc.getZ())
		return true;
		return false;
	}
}
