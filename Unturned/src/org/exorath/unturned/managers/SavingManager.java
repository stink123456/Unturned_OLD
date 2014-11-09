package org.exorath.unturned.managers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.exorath.unturned.Main;
import org.exorath.unturned.Survivors.Survivor;
import org.exorath.unturned.libraries.InventorySerializer;

import com.rit.sucy.sql.ColumnType;
import com.rit.sucy.sql.direct.SQLEntry;
import com.rit.sucy.sql.direct.SQLTable;
import com.sucy.sql.SQLManager;

public class SavingManager implements Listener{
	private SQLTable table;
	private HashMap<Player,Survivor> survivors= new HashMap<Player,Survivor>();
	private HashMap<Player,String> playersInvs= new HashMap<Player,String>();
	
	public SavingManager() {
		table = SQLManager.getTable(Main.getInstance(), "players");
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
		//if(!table.columnExists("experience"))
				table.createColumn("experience", ColumnType.INT);
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
	public void removePlayer(Survivor survivor){
		new SaveTask(survivor).run();
		survivors.remove(survivor.getPlayer());
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		e.getPlayer().sendMessage("Loading survivor...");
		new LoadTask(e.getPlayer()).run();
		e.getPlayer().sendMessage("survivor loaded!");
	}
	class LoadTask extends BukkitRunnable {
		Survivor survivor;
		Player player;
		
		public LoadTask(Player player) {
			this.player= player;
			survivor = new Survivor(player);
			Bukkit.broadcastMessage("Loading survivor " + survivor.getPlayer().getName());
			survivors.put(player, survivor);
		}

		@Override
		public void run() {
			boolean exist = table.entryExists(player.getUniqueId().toString());
			SQLEntry entry = table.createEntry(player.getUniqueId().toString());
			player.setHealth(entry.getDouble("health"));
			
			survivor.setThirst(entry.getInt("thirst"));
			survivor.setHunger(entry.getInt("hunger"));
			survivor.setStamina(entry.getInt("stamina"));
			
			if(entry.getInt("bleeding") == 0) survivor.setBleeding(false); else survivor.setBleeding(true);
			//set skill levels
			survivor.setSkillSurvival(entry.getInt("skillSurvival"));
			survivor.setSkillEndurance(entry.getInt("skillEndurance"));
			survivor.setSkillSneakyBeaky(entry.getInt("skillSneakyBeaky"));
			survivor.setSkillMarksman(entry.getInt("skillMarksman"));
			survivor.setSkillWarrior(entry.getInt("skillWarrior"));
			survivor.setSkillOutdoors(entry.getInt("skillOutdoors"));
			survivor.setSkillCraftsman(entry.getInt("skillCraftsman"));
			
			survivor.setPlayer(player);
			
			//set the players inventory
			entry.set("username", survivor.getPlayer().getName());
			if(exist){
			Bukkit.broadcastMessage("entry exists!");
			playersInvs.put(player, entry.getString("inventory"));
			}
			

			
		}
}
	class SaveTask extends BukkitRunnable {
		Survivor survivor;

		public SaveTask(Survivor survivor) {
			this.survivor = survivor;
			Bukkit.getServer().getLogger().info("Saving survivor " + survivor.getPlayer().getName());
		}

		@Override
		public void run() {

			SQLEntry entry = table.createEntry(survivor.getPlayer().getUniqueId().toString());
			//save properties
			entry.set("username",survivor.getPlayer().getName());
			entry.set("health",((Damageable) survivor.getPlayer()).getHealth());
			entry.set("thirst", survivor.getThirst());
			entry.set("hunger", survivor.getHunger());
			entry.set("stamina",survivor.getStamina());
			if(survivor.isBleeding())entry.set("bleeding", 1); else entry.set("bleeding", 0);
			//save skills
			entry.set("experience", survivor.getExperience());
			entry.set("skillSurvival", survivor.getSkillSurvival());
			entry.set("skillEndurance", survivor.getSkillEndurance());
			entry.set("skillSneakyBeaky", survivor.getSkillSneakyBeaky());
			entry.set("skillMarksman", survivor.getSkillMarksman());
			entry.set("skillWarrior", survivor.getSkillWarrior());
			entry.set("skillOutdoors", survivor.getSkillOutdoors());
			entry.set("skillCraftsman", survivor.getSkillCraftsman());
			
			//save inventory
			entry.set("inventory", InventorySerializer.serialize(survivor.getPlayer().getInventory()));
			Bukkit.getServer().getLogger().info("saved");
			
			
		}
}
	public HashMap<Player, Survivor> getsurvivors() {
		return survivors;
	}
	public void setsurvivors(HashMap<Player, Survivor> survivors) {
		this.survivors = survivors;
	}
	public HashMap<Player, String> getPlayersInvs() {
		return playersInvs;
	}
	public void setPlayersInvs(HashMap<Player, String> playersInvs) {
		this.playersInvs = playersInvs;
	}
}