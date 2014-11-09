package org.exorath.unturned.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.exorath.unturned.Main;
import org.exorath.unturned.Survivors.Survivor;
import org.exorath.unturned.libraries.SpawnArea;

import com.rit.sucy.region.Sphere;

@SuppressWarnings("deprecation")
public class GameManager implements Listener {
	public GameManager() {
		checkArea();
	}
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		if (e.getMessage().contains("hunger")) {
			e.getPlayer().sendMessage("you are 2% hungrier!");
			Main.getLobbyManager().getsurvivor(e.getPlayer()).setHunger(Main.getLobbyManager().getsurvivor(e.getPlayer()).getHunger() - 2);
		}
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (Main.getLobbyManager().isSurvivor(p)) {
				Main.getLobbyManager().getsurvivor(p).updateHunger();
				e.setCancelled(true);
			}
		}
	}

	// TODO: too inefficient, crack brain for better efficiency
	private void checkArea() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				for (SpawnArea area : Main.getSpawnManager().getSpawnAreas()) {
					Sphere sphere = area.getSphere();
					// check if player is in area.
					boolean empty = true;
					for (Survivor s : Main.getLobbyManager().getSurvivors()) {
						if (sphere.contains(s.getPlayer().getLocation())) {
							empty = false;
							// survivor is in area area
							if (!area.isSpawned()) {
								area.setSpawned(true);
								s.setCurrentArea(area);
							}
						}
					}
						if(empty){
						area.setSpawned(false);
						}
				}
			}
		}, 20, 60);
	}
}
