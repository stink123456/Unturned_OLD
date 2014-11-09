package org.exorath.unturned.managers;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.exorath.unturned.Main;
import org.exorath.unturned.items.Chest;
import org.exorath.unturned.items.ChestType;
import org.exorath.unturned.items.Items;
import org.exorath.unturned.libraries.Util;

public class LootManager implements Listener {
	FileConfiguration config;
	FileConfiguration chestsConfig;
	private File chestsConfigFile = null;
	private HashMap<String, ChestType> chestTypes = new HashMap<String, ChestType>();
	private List<Chest> chests = new ArrayList<Chest>();

	public LootManager() {
		load();
	}
	public void load(){
		chestTypes.clear();
		chests.clear();
		setupConfig();
		loadChests();
	}
	private void setupConfig() {
		config = Main.getInstance().getConfig();
		config.addDefault("cooldown", "60");
		config.addDefault("chests.civilian.min-items", 1);
		config.addDefault("chests.civilian.max-items", 3);
		config.addDefault("chests.civilian.items.KATANA", 1);
		config.addDefault("chests.civilian.items.BLOWTORCH", 0.5f);
		config.addDefault("chests.civilian.items.SMOKEGRENADE", 0.5f);
		loadChestTypes();
	}

	// load all the chest types from config.yml (civilian, military,food...)
	private void loadChestTypes() {
		for (String chestType : config.getConfigurationSection("chests").getKeys(false)) {
			String prefix = "chests." + chestType + ".";
			if (!config.contains(prefix + "min-items") || !config.contains(prefix + "max-items") || !config.contains(prefix + "items")) {
				Bukkit.getLogger().warning("[Unturned] error while loading chests in config.yml. " + chestType + " does not contain min-items, max-items and items values");
				continue;
			}
			HashMap<Items, Float> items = new HashMap<Items, Float>();
			for (String item : config.getConfigurationSection(prefix + "items").getKeys(false)) {
				items.put(Items.valueOf(item), Float.valueOf(config.getString(prefix + "items." + item)));
			}
			ChestType chest = new ChestType(chestType, config.getInt(prefix + "min-items"), config.getInt(prefix + "max-items"), items);
			chestTypes.put(chest.getKey(), chest);
		}

	}

	// load all chests from chests.yml
	private void loadChests() {
		loadChestsFile();
		for (String key : chestsConfig.getConfigurationSection("chests").getKeys(false)) {
			String prefix = "chests." + key + ".";
			if (!chestsConfig.contains(prefix + "location") || !chestsConfig.contains(prefix + "type")) {
				Bukkit.getLogger().warning("[Unturned] error while loading chests in chests.yml. " + key + " does not contain a location and type");
				continue;
			}

			if (chestTypes.containsKey(chestsConfig.getString(prefix + "type"))) {
				Chest chest = new Chest(Util.getLocation(chestsConfig.getString(prefix + "location")), chestTypes.get(chestsConfig.getString(prefix + "type")));
				chests.add(chest);
				chest.getLoc().getBlock().setType(Material.CHEST);
				org.bukkit.block.Chest chestBlock = (org.bukkit.block.Chest) chest.getLoc().getBlock();
				generateInventory(chestBlock.getInventory(), chest.getType());
			} else {
				Bukkit.getLogger().warning("[Unturned] error while loading chests in chests.yml. " + key + "'s type value is not registered in config.yml");
			}
		}

	}
	public void addChestToConfig(Player p,ChestType type){
		Set<String> keys = chestsConfig.getConfigurationSection("chests").getKeys(false);
		Integer id = 0;
		if(keys.size() > 0){
			id = chestsConfig.getInt((String)keys.toArray()[keys.size() -1]) + 1;
		}
		@SuppressWarnings("deprecation")
		Block b = p.getTargetBlock(null, 10);
		if(b != null && b.getType() == Material.CHEST){
		setChestLocationInConfig(b.getLocation(), id, type);
		}else{
			p.sendMessage(ChatColor.RED + "Please look at a chest.");
		}
	}
	public static void generateInventory(Inventory inv, ChestType type) {
		int amount = (int) (type.getMinItems() + Math.random() * type.getMaxItems());
		// compute total weight
		Float totalWeight = 0f;
		for (Items i : type.getItems().keySet()) {
			totalWeight += type.getItems().get(i);
		}
		// chose a random item index
		for (int i = 0; i < amount; i++) {
			double random = Math.random() * totalWeight;
			for (Items item : type.getItems().keySet()) {
				random -= type.getItems().get(item);
				if (random <= 0.0d) {
					// the randomly selected item is in type.getItems().
					inv.addItem(item.getStack());
					break;
				}
			}
		}

	}

	// custom chests config stuff
	private void loadChestsFile() {
		if (chestsConfigFile == null) {
			chestsConfigFile = new File(Main.getInstance().getDataFolder(), "chests.yml");
		}
		chestsConfig = YamlConfiguration.loadConfiguration(chestsConfigFile);
		// Look for defaults in the jar
		Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(Main.getInstance().getResource("chests.yml"), "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			chestsConfig.setDefaults(defConfig);
		}
	}

	public FileConfiguration getChestsConfig() {
		if (chestsConfig == null) {
			loadChestsFile();
		}
		return chestsConfig;
	}

	public void saveChestsConfig() {
		if (chestsConfig == null || chestsConfigFile == null) {
			return;
		}
		try {
			getChestsConfig().save(chestsConfigFile);
		} catch (IOException ex) {
			Main.getInstance().getLogger().log(Level.SEVERE, "Could not save config to " + chestsConfigFile, ex);
		}
	}

	public void getChestTypes(Player p) {
		p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "=============================");
		p.sendMessage(ChatColor.GOLD + "Chest types:");
		for (String key : chestTypes.keySet()) {
			p.sendMessage(ChatColor.GRAY + " - " + key);
		}
		p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "=============================");
	}
	public void getChests(Player p) {
		p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "=============================");
		p.sendMessage(ChatColor.GOLD + "Chests:");
		for (String key : config.getConfigurationSection("chests").getKeys(false)) {
			p.sendMessage(ChatColor.GRAY + " - " + key + ": " + config.getString("chests." + key + ".location") + " " + config.getString("chests." + key + ".type"));
		}
		p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "=============================");
	}

	@EventHandler()
	public void onInventoryClosed(InventoryCloseEvent e) {
		if (e.getInventory().getType() == InventoryType.CHEST) {
			ItemStack[] items = e.getInventory().getContents();

			for (ItemStack item : items) {
				if (item != null) {
					return;
				}
			}
			// it is a chest inventory
			if (isChest(e.getInventory())) {
				getChest(e.getInventory()).removeChest();
			}
		}
	}

	@EventHandler()
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.LEFT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() == Material.CHEST){
				getChest(e.getClickedBlock().getLocation());
			}
		}
	}

	public boolean isChest(Inventory inv) {
		for (Chest c : chests) {
			if (c.isEnabled()) {
				if (c.getLoc().getBlock().getType() == Material.CHEST) {
					org.bukkit.block.Chest bukkitChest = (org.bukkit.block.Chest) c.getLoc().getBlock().getState();
					if (bukkitChest.getBlockInventory().equals(inv) || bukkitChest.getBlockInventory().equals(inv))
						return true;
				}
			}
		}
		return false;
	}

	public Chest getChest(Inventory inv) {
		for (Chest c : chests) {
			if (c.isEnabled()) {
				if (c.getLoc().getBlock().getType() == Material.CHEST) {
					org.bukkit.block.Chest bukkitChest = (org.bukkit.block.Chest) c.getLoc().getBlock().getState();
					if (bukkitChest.getBlockInventory().equals(inv) || bukkitChest.getBlockInventory().equals(inv))
						return c;
				}
			}
		}
		return null;
	}

	public boolean isChest(Location loc) {
		for (Chest c : chests) {
			if (c.getLoc().equals(loc))
				return true;
		}
		return false;
	}

	public Chest getChest(Location loc) {
		for (Chest c : chests) {
			if (c.getLoc().equals(loc))
				return c;
		}
		return null;
	}
	public void setChestLocationInConfig(Location loc, int id,ChestType type) {
		chestsConfig.set("chests." + id + ".loc", loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
		chestsConfig.set("chests." + id + ".type", type);
		saveChestsConfig();
	}

	public HashMap<String, ChestType> getChestTypes() {
		return chestTypes;
	}

	public void setChestTypes(HashMap<String, ChestType> chestTypes) {
		this.chestTypes = chestTypes;
	}
}
