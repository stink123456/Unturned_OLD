package org.exorath.unturned.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerInteractEvent;
import org.exorath.unturned.Survivors.Survivor;
import org.exorath.unturned.building.Building;
import org.exorath.unturned.building.BuildingTypes;
import org.exorath.unturned.libraries.Schematic;

public class BuildingManager {
	// Schematics: http://unturned.wikia.com/wiki/Category:Bases/Building
	private static HashMap<BuildingTypes, Schematic> buildingSchematics = new HashMap<BuildingTypes, Schematic>();
	private static HashMap<Survivor, Long> lastUse = new HashMap<Survivor, Long>();
	private static List<Building> placedBuildings = new ArrayList<Building>();
	private static Calendar cal = Calendar.getInstance();

	private static final String CANTPLACE = ChatColor.RED + "You can't place the building here. There are blocks in the way.";
	public BuildingManager() {
		File folder = new File("schematics");

		for (File file : folder.listFiles()) {
			if (file.getName().endsWith(".schematic")) {
				Schematic schem = null;
				try {
					schem = Schematic.loadSchematic(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				buildingSchematics.put(BuildingTypes.valueOf(file.getName().split(".schematic")[0]), schem);
			}
		}
	}

	public static void spawnBuilding(Survivor owner, BuildingTypes type, BlockFace face, PlayerInteractEvent e) {
		if (type.isFoundation()) {
			if (canCheck(owner)) {
				if (empty(e.getClickedBlock().getLocation(), type)) {
					Building building = new Building(owner, e.getClickedBlock().getLocation(), type);
					placedBuildings.add(building);
					owner.getPlayer().sendMessage(ChatColor.GREEN + "Your foundation is" + ChatColor.WHITE + "build" + ChatColor.GREEN + ".");
				} else {
					owner.getPlayer().sendMessage(CANTPLACE);
				}
			} else {
				owner.getPlayer().sendMessage(ChatColor.RED + "Please don't spam trying to place a building.");
			}
		} else {
			// no foundation
			isTouchingFoundation(owner, type, e);

		}
	}

	private static void isTouchingFoundation(Survivor owner, BuildingTypes type, PlayerInteractEvent e) {
		Location loc = e.getClickedBlock().getLocation();
		for (Building b : placedBuildings) {
			if (b.contains(loc) && b.isFoundation()) {
				// check the type of building that is placed
				switch (type) {
				case LADDER:
					if(empty(e.getClickedBlock().getRelative(e.getBlockFace()).getLocation(),type)){
						Building building = new Building(owner, e.getClickedBlock().getLocation(), type);
						
					}else{
						owner.getPlayer().sendMessage(CANTPLACE);
					}
					break;
				case METAL_DOOR:
					break;
				case METAL_SHUTTER:
					break;
				case RAMP:
					break;
				case STONE_DOORWAY:
					break;
				case STONE_GARAGE_POST:
					break;
				case STONE_GATE:
					break;
				case STONE_PILLER:
					break;
				case STONE_POST:
					break;
				case STONE_RAMPANT:
					break;
				case STONE_WALL:
					break;
				case STONE_WINDOW:
					break;
				case WOOD_DOOR:
					break;
				case WOOD_DOORWAY:
					break;
				case WOOD_GARAGE_PORT:
					break;
				case WOOD_GATE:
					break;
				case WOOD_HOLE:
					break;
				case WOOD_PILLAR:
					break;
				case WOOD_PLATFORM:
					break;
				case WOOD_POST:
					break;
				case WOOD_RAMPANT:
					break;
				case WOOD_SHUTTER:
					break;
				case WOOD_WALL:
					break;
				case WOOD_WINDOW:
					break;
				default:
					break;

				}
				break;
			}
		}
	}
 
	private static boolean canCheck(Survivor s) {
		if (!lastUse.containsKey(s)) {
			lastUse.put(s, cal.getTimeInMillis());
			return true;
		}
		if (lastUse.get(s) - 3000 > cal.getTimeInMillis()) {
			lastUse.put(s, cal.getTimeInMillis());
			return true;
		}
		return false;
	}

	public static boolean empty(Location loc, BuildingTypes type) {
		World w = loc.getWorld();
		for (int length = 0; length < type.getLength(); length++) {
			for (int height = 0; height < type.getHeight(); height++) {
				for (int width = 0; width < type.getWidth(); width++) {
					if (w.getBlockAt(width, height, length).getType() != Material.AIR) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
