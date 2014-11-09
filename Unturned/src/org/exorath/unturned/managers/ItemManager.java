package org.exorath.unturned.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.exorath.unturned.Main;
import org.exorath.unturned.libraries.CustomItems;
import org.exorath.unturned.libraries.Util;

public class ItemManager implements Listener{
	public ItemManager(){
		
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() ==  Action.RIGHT_CLICK_BLOCK){
			if(Main.getLobbyManager().isSurvivor(e.getPlayer())){
				Player p = e.getPlayer();
				ItemStack item = e.getPlayer().getItemInHand();
				if(Util.compareItem(item, CustomItems.BANDAGE)){
					
				}
			}
		}
	}
	
}
