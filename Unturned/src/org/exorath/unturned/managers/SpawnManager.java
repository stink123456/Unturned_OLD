package org.exorath.unturned.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.exorath.unturned.Main;
import org.exorath.unturned.libraries.SpawnArea;

public class SpawnManager {
	private FileConfiguration config;
	private Main main;
	private static World w;
	private List<SpawnArea> spawnAreas = new ArrayList<SpawnArea>();

	public SpawnManager(Main main) {
		this.main = main;
		w = Bukkit.getWorld("world");
		setupConfig();
	}
	@SuppressWarnings("unchecked")
	private void setupConfig() {
		this.config = main.getConfig();
		config.options().copyDefaults(true);
		main.saveConfig();

		// itterate trough all areas.<area>.. in the config
		for (String area : config.getConfigurationSection("areas").getKeys(false)) {
			String path = "areas." + area;
			// TODO:check if config is valid
			if (!config.contains(path + ".middle") || !config.contains(path + ".range") || !config.contains(path + ".spawns")) {
				Bukkit.getLogger().warning("ERROR>> Unturned >> SpawnManager >> setupConfig: Make sure your area has a .middle, .range and .spawns");
				continue;
			}
			//loop trough areas zombie spawn locations
			List<Location> spawnLocs = new ArrayList<Location>();
			for (String locString : (List<String>) config.getList("areas." + area + ".spawns")) {
				spawnLocs.add(getLocation(locString));
			}
			// Generate SpawnArea and add to spawn areasList
			SpawnArea spawnArea = new SpawnArea(getLocation(config.getString(path + ".middle")), config.getInt(config.getString(path + ".range")), spawnLocs, config.getString(path + ".message"));
			spawnAreas.add(spawnArea);
		}
		// looping trough areas is done.
		Bukkit.getLogger().info("< Unturned > Loaded areas: " + spawnAreas.size());
		Bukkit.broadcastMessage("< Unturned > Loaded areas: " + spawnAreas.size());
	}

	public static Location getLocation(String locString) {
		String[] coords = locString.split(",");
		if (coords.length == 3) {
			return new Location(w, Float.valueOf(coords[0]), Float.valueOf(coords[1]), Float.valueOf(coords[2]));
		} else {
			Bukkit.getLogger().warning("ERROR>> Unturned >> SpawnManager >> setupConfig: 'String does not have 2 commas' << String: '" + locString + "'");
			return null;
		}
	}

	public void addSpawn(String area, float x, float y, float z) {
		if (config.getList("areas." + area + ".spawns") == null) {
			@SuppressWarnings("unchecked")
			List<String> spawns = new ArrayList<String>();
			spawns.add(x + "," + y + "," + z);
			config.set("areas." + area + ".spawns", spawns);

		} else {
			@SuppressWarnings("unchecked")
			List<String> spawns = (List<String>) config.getList("areas." + area + ".spawns");
			spawns.add(x + "," + y + "," + z);
		}
	}

	public List<SpawnArea> getSpawnAreas() {
		return spawnAreas;
	}

	public void setSpawnAreas(List<SpawnArea> spawnAreas) {
		this.spawnAreas = spawnAreas;
	}
}
