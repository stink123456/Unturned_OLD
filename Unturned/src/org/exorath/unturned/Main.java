package org.exorath.unturned;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.exorath.unturned.Survivors.Survivor;
import org.exorath.unturned.managers.BuildingManager;
import org.exorath.unturned.managers.GameManager;
import org.exorath.unturned.managers.ItemManager;
import org.exorath.unturned.managers.LobbyManager;
import org.exorath.unturned.managers.LootManager;
import org.exorath.unturned.managers.SavingManager;
import org.exorath.unturned.managers.SpawnManager;
import org.exorath.unturned.zombies.ZombieTrait;

public class Main extends JavaPlugin {
	private static LobbyManager lobbyManager;
	private static SavingManager savingManager;
	private static GameManager gameManager;
	private static SpawnManager spawnManager;
	private static BuildingManager buildingManager;
	private static ItemManager itemManager;
	private static LootManager lootManager;
	private static Main instance;
	@Override
	public void onEnable() {
		lobbyManager = new LobbyManager();
		savingManager = new SavingManager();
		gameManager = new GameManager();
		spawnManager = new SpawnManager();
		buildingManager = new BuildingManager();
		itemManager = new ItemManager();
		lootManager = new LootManager();

		Bukkit.getPluginManager().registerEvents(savingManager, this);
		Bukkit.getPluginManager().registerEvents(lobbyManager, this);
		Bukkit.getPluginManager().registerEvents(gameManager, this);

		CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(ZombieTrait.class).withName("Zombie"));
	}
	@Override
	public void onDisable() {
		for (Survivor s : lobbyManager.getSurvivors()) {
			lobbyManager.removePlayer(s.getPlayer());
		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("Chest")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "=============================");
				sender.sendMessage(ChatColor.GOLD + "Chest commands: ");
				sender.sendMessage(ChatColor.GRAY + " - add " + ChatColor.GRAY + "<Type>" + " : Saves a new chest at pointer");
				sender.sendMessage(ChatColor.GRAY + " - remove " + ChatColor.GRAY + "<Type>" + " : remove the chest at pointer");
				sender.sendMessage(ChatColor.GRAY + " - reload "  + " : reloads config and chests");
				sender.sendMessage(ChatColor.GRAY + " - list " + " List of all chests");
				sender.sendMessage(ChatColor.GRAY + " - types " + " List of all types");
				sender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "=============================");
				return true;
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("reload")){
					lootManager.load();
					return true;
				}else if (args[0].equalsIgnoreCase("list")){
					lootManager.getChests((Player) sender);
					return true;
				}else if (args[0].equalsIgnoreCase("types")){
					lootManager.getChestTypes((Player) sender);
					return true;
				}
			}else if(args.length == 2){
				if(args[0].equalsIgnoreCase("add")){
					if(lootManager.getChestTypes().keySet().contains(args[1])){
						lootManager.addChestToConfig((Player) sender, lootManager.getChestTypes().get(args[1]));
					}else{
						sender.sendMessage(ChatColor.RED + "config does not contain your type.");
					}
					return true;
				}else if(args[0].equalsIgnoreCase("remove")){
					return true;
				}
			}
		}
		return false;
	}
	public static LobbyManager getLobbyManager() {
		return lobbyManager;
	}
	public static GameManager getGameManager() {
		return gameManager;
	}
	public static SavingManager getSavingManager() {
		return savingManager;
	}
	public static SpawnManager getSpawnManager() {
		return spawnManager;
	}
	public static Main getInstance() {
		return instance;
	}
	public static LootManager getLootManager() {
		return lootManager;
	}
	public static ItemManager getItemManager() {
		return itemManager;
	}
	public static BuildingManager getBuildingManager() {
		return buildingManager;
	}
}
