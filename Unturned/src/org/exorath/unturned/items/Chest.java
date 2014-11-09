package org.exorath.unturned.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.exorath.unturned.Main;
import org.exorath.unturned.libraries.Particles.ParticleEffects;
import org.exorath.unturned.managers.LootManager;

public class Chest {
	private Location loc;
	private ChestType type;
	private int cooldown = 5;
	private boolean enabled = true;
	public Chest(Location loc, ChestType type){
		this.loc = loc;
		this.type = type;
	}
	public ChestType getType() {
		return type;
	}
	public Location getLoc() {
		return loc;
	}
	public void breakNaturally(){
		loc.getBlock().breakNaturally();
		removeChest();
	}
	public void removeChest(){
		enabled = false;
		ParticleEffects.sendToLocation(ParticleEffects.CRITICAL_HIT, loc.add(0.5f,0.5f,0.5f), 0.5f, 0.5f, 0.5f, 1, 20);
		loc.getBlock().setType(Material.AIR);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){
			@Override
			public void run(){
				enabled = true;
				loc.getBlock().setType(Material.CHEST);
				org.bukkit.block.Chest chest = (org.bukkit.block.Chest) loc.getBlock();
				Main.getLootManager();
				LootManager.generateInventory(chest.getInventory(), type);

			}
		},cooldown * 20);
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
