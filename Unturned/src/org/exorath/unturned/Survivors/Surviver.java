package org.exorath.unturned.Survivors;

import org.bukkit.entity.Player;

public class Surviver {

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
	private int skillSurvival = 0;
	private int skillEndurance = 0;
	private int skillSneakyBeaky = 0;
	private int skillMarksman = 0;
	private int skillWarrior = 0;
	private int skillOutdoors = 0;
	private int skillCraftsman = 0;
	public Surviver(Player player){
		this.player = player;
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
	}
	public int getHunger() {
		return hunger;
	}
	public void setHunger(int hunger) {
		this.hunger = hunger;
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
}