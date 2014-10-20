package org.exorath.unturned.managers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.exorath.unturned.Main;
import org.exorath.unturned.Survivors.Surviver;
import org.exorath.unturned.libraries.InventorySerializer;

import com.rit.sucy.sql.ColumnType;
import com.rit.sucy.sql.direct.SQLEntry;
import com.rit.sucy.sql.direct.SQLTable;
import com.sucy.sql.SQLManager;

public class SavingManager implements Listener{
	private SQLTable table;
	private Main main;
	private HashMap<Player,Surviver> survivers= new HashMap<Player,Surviver>();
	private HashMap<Player,String> playersInvs= new HashMap<Player,String>();
	
	public SavingManager(Main main) {
		this.main=main;
		table = SQLManager.getTable(main, "players");
		//if(!table.columnExists("username"))
		table.createColumn("username", ColumnType.STRING_32);
		//if(!table.columnExists("health"))
		table.createColumn("health", ColumnType.DOUBLE);
		//if(!table.columnExists("thirst"))
		table.createColumn("thirst", ColumnType.INT);
		//if(!table.columnExists("hunger"))
		table.createColumn("hunger", ColumnType.INT);
		//if(!table.columnExists("stamina"))
		table.createColumn("stamina", ColumnType.INT);
		//if(!table.columnExists("bleeding"))
		table.createColumn("bleeding", ColumnType.INT);
		//if(!table.columnExists("inventory"))
		table.createColumn("inventory", ColumnType.STRING_8000);
		//if(!table.columnExists("skillSurvival"))
		table.createColumn("skillSurvival", ColumnType.INT);
		//if(!table.columnExists("skillEndurance"))
		table.createColumn("skillEndurance", ColumnType.INT);
		//if(!table.columnExists("skillSurvival"))
		table.createColumn("skillSneakyBeaky", ColumnType.INT);
		//if(!table.columnExists("skillMarksman"))
		table.createColumn("skillMarksman", ColumnType.INT);
		//if(!table.columnExists("skillWarrior"))
		table.createColumn("skillWarrior", ColumnType.INT);
		//if(!table.columnExists("skillOutdoors"))
		table.createColumn("skillOutdoors", ColumnType.INT);
		//if(!table.columnExists("skillCraftsman"))
		table.createColumn("skillCraftsman", ColumnType.INT);
	}
	public void removePlayer(Surviver surviver){
		new SaveTask(surviver).run();
		survivers.remove(surviver.getPlayer());
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		e.getPlayer().sendMessage("Loading surviver...");
		new LoadTask(e.getPlayer()).run();
		e.getPlayer().sendMessage("Surviver loaded!");
	}
	class LoadTask extends BukkitRunnable {
		Surviver surviver;
		Player player;
		
		public LoadTask(Player player) {
			this.player= player;
			surviver = new Surviver(player);
			Bukkit.broadcastMessage("Loading surviver " + surviver.getPlayer().getName());
			survivers.put(player, surviver);
		}

		@Override
		public void run() {
			boolean exist = table.entryExists(player.getUniqueId().toString());
			SQLEntry entry = table.createEntry(player.getUniqueId().toString());
			player.setHealth(entry.getDouble("health"));
			
			surviver.setThirst(entry.getInt("thirst"));
			surviver.setHunger(entry.getInt("hunger"));
			surviver.setStamina(entry.getInt("stamina"));
			
			if(entry.getInt("bleeding") == 0) surviver.setBleeding(false); else surviver.setBleeding(true);
			//set skill levels
			surviver.setSkillSurvival(entry.getInt("skillSurvival"));
			surviver.setSkillEndurance(entry.getInt("skillEndurance"));
			surviver.setSkillSneakyBeaky(entry.getInt("skillSneakyBeaky"));
			surviver.setSkillMarksman(entry.getInt("skillMarksman"));
			surviver.setSkillWarrior(entry.getInt("skillWarrior"));
			surviver.setSkillOutdoors(entry.getInt("skillOutdoors"));
			surviver.setSkillCraftsman(entry.getInt("skillCraftsman"));
			
			surviver.setPlayer(player);
			
			//set the players inventory
			entry.set("username", surviver.getPlayer().getName());
			if(exist){
			playersInvs.put(player, entry.getString("inventory"));
			}else{
				playersInvs.put(player, "");
			}
			

			
		}
}
	class SaveTask extends BukkitRunnable {
		Surviver surviver;

		public SaveTask(Surviver surviver) {
			this.surviver = surviver;
			Bukkit.getServer().getLogger().info("Saving surviver " + surviver.getPlayer().getName());
		}

		@Override
		public void run() {

			SQLEntry entry = table.createEntry(surviver.getPlayer().getUniqueId().toString());
			//save properties
			entry.set("username",surviver.getPlayer().getName());
			entry.set("health",((Damageable) surviver.getPlayer()).getHealth());
			entry.set("thirst", surviver.getThirst());
			entry.set("hunger", surviver.getHunger());
			entry.set("stamina",surviver.getStamina());
			if(surviver.isBleeding())entry.set("bleeding", 1); else entry.set("bleeding", 0);
			//save skills
			entry.set("skillSurvival", surviver.getSkillSurvival());
			entry.set("skillEndurance", surviver.getSkillEndurance());
			entry.set("skillSneakyBeaky", surviver.getSkillSneakyBeaky());
			entry.set("skillMarksman", surviver.getSkillMarksman());
			entry.set("skillWarrior", surviver.getSkillWarrior());
			entry.set("skillOutdoors", surviver.getSkillOutdoors());
			entry.set("skillCraftsman", surviver.getSkillCraftsman());
			
			//save inventory
			entry.set("inventory", InventorySerializer.serialize(surviver.getPlayer().getInventory()));
			Bukkit.getServer().getLogger().info("saved");
			
			
		}
}
	public HashMap<Player, Surviver> getSurvivers() {
		return survivers;
	}
	public void setSurvivers(HashMap<Player, Surviver> survivers) {
		this.survivers = survivers;
	}
	public HashMap<Player, String> getPlayersInvs() {
		return playersInvs;
	}
	public void setPlayersInvs(HashMap<Player, String> playersInvs) {
		this.playersInvs = playersInvs;
	}
}