package org.exorath.unturned.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.exorath.unturned.Main;
import org.exorath.unturned.Survivors.Surviver;
import org.exorath.unturned.libraries.InventorySerializer;

public class LobbyManager implements Listener{
	private Main main;
	private boolean isStarted;
	private List<Surviver> survivers = new ArrayList<Surviver>();
	public LobbyManager(Main main){
		this.main=main;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		e.getPlayer().getInventory().clear();
	}
	
	
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK){
			if(e.getClickedBlock().getType()== Material.SIGN || e.getClickedBlock().getType()== Material.SIGN_POST){
				e.getPlayer().sendMessage("Joining game");
				InventorySerializer.deserialize(e.getPlayer().getInventory(), main.getSavingManager().getPlayersInvs().get(e.getPlayer()));
				main.getSavingManager().getPlayersInvs().remove(e.getPlayer());
				addPlayer(e.getPlayer());
			}
		}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		removePlayer(e.getPlayer());
	}
	public void addPlayer(Player p){
		survivers.add(main.getSavingManager().getSurvivers().get(p));
	}
	public void removePlayer(Player p){
		for(Surviver s: survivers){
			if(s.getPlayer().equals(p)){
				survivers.remove(s);
				break;
			}
		}
		if(main.getSavingManager().getSurvivers().containsKey(p)){
			Bukkit.broadcastMessage("yup");
			main.getSavingManager().removePlayer(main.getSavingManager().getSurvivers().get(p));
		}
	}
	public boolean isStarted() {
		return isStarted;
	}
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public Surviver getSurviver(Player p){
		return main.getSavingManager().getSurvivers().get(p);
	}
	public boolean isSurvivor(Player p){
		if(survivers.contains(p))return true;
		return false;
	}
}
