package org.exorath.unturned;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.exorath.unturned.managers.GameManager;
import org.exorath.unturned.managers.LobbyManager;
import org.exorath.unturned.managers.SavingManager;

public class Main extends JavaPlugin{
	private LobbyManager lobbyManager;
	private SavingManager savingManager;
	private GameManager gameManager;
	@Override
	public void onEnable(){
		lobbyManager = new LobbyManager(this);
		savingManager = new SavingManager(this);
		gameManager = new GameManager(this);
		
		Bukkit.getPluginManager().registerEvents(savingManager, this);
		Bukkit.getPluginManager().registerEvents(lobbyManager, this);
		Bukkit.getPluginManager().registerEvents(gameManager, this);
	}
	public LobbyManager getLobbyManager() {
		return lobbyManager;
	}
	public void setLobbyManager(LobbyManager lobbyManager) {
		this.lobbyManager = lobbyManager;
	}
	public SavingManager getSavingManager() {
		return savingManager;
	}
	public void setSavingManager(SavingManager savingManager) {
		this.savingManager = savingManager;
	}
	public GameManager getgameManager() {
		return gameManager;
	}
	public void gameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
}
