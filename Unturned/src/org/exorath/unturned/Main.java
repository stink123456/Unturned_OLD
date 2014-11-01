package org.exorath.unturned;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.exorath.unturned.Survivors.Survivor;
import org.exorath.unturned.managers.GameManager;
import org.exorath.unturned.managers.LobbyManager;
import org.exorath.unturned.managers.SavingManager;
import org.exorath.unturned.managers.SpawnManager;
import org.exorath.unturned.zombies.ZombieTrait;

public class Main extends JavaPlugin {
	private static LobbyManager lobbyManager;
	private static SavingManager savingManager;
	private static GameManager gameManager;
	private static SpawnManager spawnManager;

	@Override
	public void onEnable() {
		lobbyManager = new LobbyManager(this);
		savingManager = new SavingManager(this);
		gameManager = new GameManager(this);
		spawnManager = new SpawnManager(this);

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
}
