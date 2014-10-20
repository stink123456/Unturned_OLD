package org.exorath.unturned.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.exorath.unturned.Main;
import org.exorath.unturned.Survivors.Surviver;

public class GameManager implements Listener {
	public Main main;

	public GameManager(Main main) {
		this.main = main;
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e){
		if(e.getMessage().contains("hunger")){
			e.getPlayer().sendMessage("works!");
			main.getLobbyManager().getSurviver(e.getPlayer()).setHunger(main.getLobbyManager().getSurviver(e.getPlayer()).getHunger()-2);
			updateHungerLevel(main.getLobbyManager().getSurviver(e.getPlayer()));
		}
	}
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p =(Player) e.getEntity();
			if (main.getLobbyManager().isSurvivor(p)) {
				updateHungerLevel(main.getLobbyManager().getSurviver(p));
			}
		}
	}
	public void updateHungerLevel(Surviver s){
		s.getPlayer().setFoodLevel(main.getLobbyManager().getSurviver(s.getPlayer()).getHunger()*20/100);
	}
}
