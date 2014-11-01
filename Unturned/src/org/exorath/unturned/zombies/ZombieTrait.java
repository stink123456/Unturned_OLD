package org.exorath.unturned.zombies;

import java.util.HashMap;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.exorath.unturned.Main;
import org.exorath.unturned.Survivors.Survivor;
import org.exorath.unturned.libraries.Particles.ParticleEffects;

public class ZombieTrait extends Trait {
	// max range for checking
	private static final int MAXRANGE = 30;
	// max value to search and aggro is put in sound divided by distance (s/d)
	private static final Float MAXAGGRORANGE = 5.5f;
	private static final Float MAXSEARCHINGRANGE = 2f;
	private HashMap<Survivor, Float> nearbySurvivors = new HashMap<Survivor, Float>();
	private int x = 0;

	public ZombieTrait() {
		super("Zombie");
	}

	@Override
	public void onSpawn() {
		npc.data().set(NPC.DEFAULT_PROTECTED_METADATA, false);
	}

	@Override
	public void run() {
		x++;
		if (x == 30) {
			x = 0;
			find();
		}
	}

	private void find() {
		if (!this.getNPC().isSpawned())
			return;
		Location loc = this.getNPC().getEntity().getLocation();
		for (Survivor s : Main.getLobbyManager().getSurvivors()) {
			if (s.getPlayer().getLocation().distance(loc) < MAXRANGE) {
				nearbySurvivors.put(s, (float) (s.getSound() / s.getPlayer().getLocation().distance(getNPC().getEntity().getLocation())));
				Bukkit.broadcastMessage("You are close: " + s.getPlayer().getName() + " your distance is: " + nearbySurvivors.get(s));
			}
		}
		Survivor highest = null;
		for (Survivor s : nearbySurvivors.keySet()) {
			if (highest == null) {
				highest = s;
			} else {
				if (nearbySurvivors.get(s) >= nearbySurvivors.get(highest)) {
					highest = s;
				}
			}
		}
		Float highestV = nearbySurvivors.get(highest);
		if (highest != null) {
			if (highestV >= MAXAGGRORANGE) {
				npc.getNavigator().getDefaultParameters().baseSpeed(1.3f);
				// npc.getNavigator().getDefaultParameters().useNewPathfinder(true);
				npc.getNavigator().setTarget((Entity) highest.getPlayer(), true);
				ParticleEffects.sendToLocation(ParticleEffects.CRITICAL_HIT, npc.getEntity().getLocation().add(0.5f,0.05f,0.5f), 0.3f, 0.1f, 0.3f, 0.5f, 10);
			} else if (highestV >= MAXSEARCHINGRANGE) {
				npc.getNavigator().getDefaultParameters().baseSpeed(0.75f);
				// npc.getNavigator().getDefaultParameters().useNewPathfinder(true);
				npc.getNavigator().setTarget((Entity) highest.getPlayer(), true);
				ParticleEffects.sendToLocation(ParticleEffects.LARGE_SMOKE, npc.getEntity().getLocation().add(0.5f,0.05f,0.5f), 0.3f, 0.1f, 0.3f, 0.01f, 10);

			} else {
				npc.getNavigator().cancelNavigation();
			}
		}
	}
}

enum ZombieState {
	AGGRO, WANDERING, SEARCHING
}
