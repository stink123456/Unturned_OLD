package org.exorath.unturned.libraries;

import java.util.ArrayList;
import java.util.List;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.exorath.unturned.zombies.ZombieTrait;

import com.rit.sucy.region.Sphere;

public class SpawnArea {
	private List<Location> spawnLocations = new ArrayList<Location>();
	private List<NPC> zombies = new ArrayList<NPC>();
	private String enterMessage = null;
	private Sphere sphere;
	private boolean spawned;
	
	public SpawnArea(Location middle,int range, List<Location> spawnLocations, String enterMessage){
		this.enterMessage = enterMessage;
		this.spawnLocations = spawnLocations;
		this.sphere = new Sphere(middle, range);
		
	}

	public List<Location> getSpawnLocations() {
		return spawnLocations;
	}
	public void setSpawned(boolean spawned) {
		
		if(spawned && !isSpawned()){
			//spawn mobs
			for(Location loc : spawnLocations){
				NPC zombie = CitizensAPI.getNPCRegistry().createNPC(EntityType.ZOMBIE, "");
				zombie.addTrait(ZombieTrait.class);
				zombie.spawn(loc);
				zombies.add(zombie);
			}
		}else if(!spawned && isSpawned()){
			for(NPC npc : zombies){
				npc.destroy();
			}
		}
		this.spawned = spawned;
	}
	public String getEnterMessage() {
		return enterMessage;
	}

	public void setEnterMessage(String enterMessage) {
		this.enterMessage = enterMessage;
	}

	public boolean isSpawned() {
		return spawned;
	}

	public Sphere getSphere() {
		return sphere;
	}

	public void setSphere(Sphere sphere) {
		this.sphere = sphere;
	}
}
