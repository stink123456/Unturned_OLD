package org.exorath.unturned.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.exorath.unturned.Main;
import org.exorath.unturned.Survivors.Survivor;
import org.exorath.unturned.libraries.InventorySerializer;

public class LobbyManager implements Listener {
	private boolean isStarted;
	private List<Survivor> survivors = new ArrayList<Survivor>();

	public LobbyManager() {
		
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().getInventory().clear();
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST) {
				Sign sign = (Sign)e.getClickedBlock().getState();
				if(sign.getLine(0).equals("[Unturned]")){
				if (survivors.contains(Main.getSavingManager().getsurvivors().get(e.getPlayer()))) {
					e.getPlayer().sendMessage("You are already in the game.");
				} else {
					e.getPlayer().sendMessage("Joining game");
					if (Main.getSavingManager().getPlayersInvs().containsKey(e.getPlayer())) {
						InventorySerializer.deserialize(e.getPlayer().getInventory(), Main.getSavingManager().getPlayersInvs().get(e.getPlayer()));
						Main.getSavingManager().getPlayersInvs().remove(e.getPlayer());
					}

					addPlayer(e.getPlayer());
				}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		removePlayer(e.getPlayer());
	}

	public void addPlayer(Player p) {
		if (Main.getSavingManager().getsurvivors().containsKey(p)) {
			survivors.add(Main.getSavingManager().getsurvivors().get(p));
		} else {
			p.sendMessage(ChatColor.RED + "Please rejoin, an error has occured.");
		}
	}

	public void removePlayer(Player p) {
		for (Survivor s : survivors) {
			if (s.getPlayer().equals(p)) {
				survivors.remove(s);
				break;
			}
		}
		if (Main.getSavingManager().getsurvivors().containsKey(p)) {
			Bukkit.broadcastMessage("yup");
			Main.getSavingManager().removePlayer(Main.getSavingManager().getsurvivors().get(p));
		}
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public Survivor getsurvivor(Player p) {
		return Main.getSavingManager().getsurvivors().get(p);
	}

	public boolean isSurvivor(Player p) {
		if (survivors.contains(p))
			return true;
		return false;
	}

	public List<Survivor> getSurvivors() {
		return survivors;
	}

	public void setSurvivors(List<Survivor> survivors) {
		this.survivors = survivors;
	}
}
