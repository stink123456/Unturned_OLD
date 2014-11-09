package org.exorath.unturned.libraries;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class Util {
	public static boolean compareItem(ItemStack stack, ItemStack stack2){
		if(stack.getItemMeta().equals(stack2.getItemMeta()) && stack.getType().equals(stack2.getType()))return true;
		return false;
	}
	public static Location getLocation(String locString) {
		World w = Bukkit.getWorld("world");
		String[] coords = locString.split(",");
		if (coords.length == 3) {
			return new Location(w, Float.valueOf(coords[0]), Float.valueOf(coords[1]), Float.valueOf(coords[2]));
		} else {
			Bukkit.getLogger().warning("Location string does not have 2 commas << String: " + locString);
			return null;
		}
	}
}
