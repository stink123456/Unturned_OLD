package org.exorath.unturned.Survivors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.exorath.unturned.Main;
import org.exorath.unturned.libraries.SpawnArea;

public class Survivor {

	private Player player;
	
	//properties
	private int thirst = 100;
	private int hunger = 100;
	private int stamina = 100;
	private boolean bleeding = false;
	
	private final static int MAXTHIRST = 100;
	private final static int MAXHUNGER = 100;
	private final static int MAXSTAMINA = 100;
	
	//skills
	private int experience = 0;
	private int skillSurvival = 0;
	private int skillEndurance = 0;
	private int skillSneakyBeaky = 0;
	private int skillMarksman = 0;
	private int skillWarrior = 0;
	private int skillOutdoors = 0;
	private int skillCraftsman = 0;
	
	private int sound = 30;
	//current area:
	
	private SpawnArea currentArea = null;
	public Survivor(Player player){
		this.player = player;
		for(SpawnArea area: Main.getSpawnManager().getSpawnAreas()){
			Bukkit.broadcastMessage("Area: " + area.toString());
			Bukkit.broadcastMessage("Location: " + player.getLocation().toString());
			Bukkit.broadcastMessage("Sphere: " + area.getSphere().toString());
			Bukkit.broadcastMessage("Middle: " + area.getSphere().getCenter());
			if(area.getSphere().contains(player.getLocation())){
				currentArea = area;
				if(area.getEnterMessage() != null){
				player.sendMessage(area.getEnterMessage());
				}
				break;
			}
		}
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getThirst() {
		return thirst;
	}
	public void setThirst(int thirst) {
		this.thirst = thirst;
		updateThirst();
	}
	public int getHunger() {
		return hunger;
	}
	public void setHunger(int hunger) {
		this.hunger = hunger;
		updateHunger();
	}
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	public int getSkillSurvival() {
		return skillSurvival;
	}
	public void setSkillSurvival(int skillSurvival) {
		this.skillSurvival = skillSurvival;
	}
	public int getSkillEndurance() {
		return skillEndurance;
	}
	public void setSkillEndurance(int skillEndurance) {
		this.skillEndurance = skillEndurance;
	}
	public int getSkillSneakyBeaky() {
		return skillSneakyBeaky;
	}
	public void setSkillSneakyBeaky(int skillSneakyBeaky) {
		this.skillSneakyBeaky = skillSneakyBeaky;
	}
	public int getSkillMarksman() {
		return skillMarksman;
	}
	public void setSkillMarksman(int skillMarksman) {
		this.skillMarksman = skillMarksman;
	}
	public int getSkillWarrior() {
		return skillWarrior;
	}
	public void setSkillWarrior(int skillWarrior) {
		this.skillWarrior = skillWarrior;
	}
	public int getSkillOutdoors() {
		return skillOutdoors;
	}
	public void setSkillOutdoors(int skillOutdoors) {
		this.skillOutdoors = skillOutdoors;
	}
	public int getSkillCraftsman() {
		return skillCraftsman;
	}
	public void setSkillCraftsman(int skillCraftsman) {
		this.skillCraftsman = skillCraftsman;
	}
	public boolean isBleeding() {
		return bleeding;
	}
	public void setBleeding(boolean bleeding) {
		this.bleeding = bleeding;
	}
	public int getExperience() {
		return experience;
	}
	public void setExp(int experience) {
		this.experience = experience;
		updateExp();
	}	
	public void updateHunger(){
		getPlayer().setFoodLevel(getHunger()*20/100);
	}
	public void updateThirst(){
		getPlayer().setExp(getHunger()/100);
	}
	public void updateExp(){
		getPlayer().setLevel(getExperience());
	}
	public void addExp(int exp){
		experience += exp;
		updateExp();
	}
	public int getSound() {
		return sound;
	}
	public void setSound(int sound) {
		this.sound = sound;
	}
	public SpawnArea getCurrentArea() {
		return currentArea;
	}
	public void setCurrentArea(SpawnArea currentArea) {
		if(currentArea != this.currentArea){
			this.currentArea = currentArea;
			if(currentArea.getEnterMessage() != null){
				player.sendMessage(currentArea.getEnterMessage());
			}
		}
	}
}