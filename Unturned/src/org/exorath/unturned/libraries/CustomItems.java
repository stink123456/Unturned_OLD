package org.exorath.unturned.libraries;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems {
	private static final String RUINED = ChatColor.RED + "70% damaged";
	private static final String DAMAGED = ChatColor.GOLD + "30% damaged";
	private static final String PRISTINE = ChatColor.GREEN + "0% damaged";
	
	//medical
	public static final ItemStack RAG = new ItemStack(Material.PAPER, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Rag");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Right click to heal yourself");
			l.add(" ");
			l.add(RUINED);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack BANDAGE = new ItemStack(Material.PAPER, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE + "Bandage");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Right click to heal yourself");
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack DRESSING = new ItemStack(Material.PAPER, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Dressing");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Right click to heal yourself");
			l.add(" ");
			l.add(PRISTINE);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack ANTIBIOTICS = new ItemStack(Material.COAL, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Antibiotics");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Right click to heal yourself");
			l.add(" ");
			l.add(PRISTINE);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	//melee's
	public static final ItemStack BASEBALLBAT = new ItemStack(Material.STICK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Baseball bat");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
			this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		}
	};
	public static final ItemStack KITCHENKNIFE = new ItemStack(Material.SHEARS, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Shears");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
			this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		}
	};
	public static final ItemStack BUTCHERKNIFE = new ItemStack(Material.STONE_SWORD, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Shears");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack KATANA = new ItemStack(Material.GOLD_SWORD, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Katana");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
			this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		}
	};
	public static final ItemStack BRANCH = new ItemStack(Material.STICK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Branch");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(RUINED);
			meta.setLore(l);
			this.setItemMeta(meta);
			this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		}
	};
	public static final ItemStack AXE = new ItemStack(Material.STONE_AXE, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Axe");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack FIREAXE = new ItemStack(Material.IRON_AXE, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Fire axe");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(PRISTINE);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack PICKAXE = new ItemStack(Material.STONE_PICKAXE, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Broken pickaxe");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(RUINED);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack BLOWTORCH = new ItemStack(Material.BLAZE_ROD, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Blowtorch");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(PRISTINE);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack FLASHLIGHT = new ItemStack(Material.TORCH, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Flashlight");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	//ammo
	public static final ItemStack LEBELMAGAZINE = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Lebel Magazine");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Civilian");
			l.add(ChatColor.GOLD + "Capacity - 13");
			meta.setLore(l);
			this.setItemMeta(meta);
			//pink
			this.setDurability((short)9);
		}
	};
	public static final ItemStack SWIFTMAGAZINE = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Swift Magazine");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Civilian");
			l.add(ChatColor.GOLD + "Capacity - 7");
			meta.setLore(l);
			this.setItemMeta(meta);
			//dark gray
			this.setDurability((short)8);
		}
	};
	public static final ItemStack WINCHESTERCLIP = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Winchester Clip");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Civilian");
			l.add(ChatColor.GOLD + "Capacity - 6");
			meta.setLore(l);
			this.setItemMeta(meta);
			//light gray
			this.setDurability((short)7);
		}
	};
	public static final ItemStack BONJOURCLIP = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Bonjour Clip");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Civilian");
			l.add(ChatColor.GOLD + "Capacity - 5");
			meta.setLore(l);
			this.setItemMeta(meta);
			//aqua
			this.setDurability((short)6);
		}
	};
	public static final ItemStack SAVAGEMAGAZINE = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Savage Magazine");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Civilian");
			l.add(ChatColor.GOLD + "Capacity - 30");
			meta.setLore(l);
			this.setItemMeta(meta);
			//dark green
			this.setDurability((short)2);
		}
	};
	public static final ItemStack YURIMAGAZINE = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Yuri Magazine");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Civilian");
			l.add(ChatColor.GOLD + "Capacity - 40");
			meta.setLore(l);
			this.setItemMeta(meta);
			//black
			this.setDurability((short)0);
		}
	};
	public static final ItemStack PDWMAGAZINE = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("PDW Magazine");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Military");
			l.add(ChatColor.GOLD + "Capacity - 50");
			meta.setLore(l);
			this.setItemMeta(meta);
			//orange
			this.setDurability((short)14);
		}
	};
	public static final ItemStack NATOMAGAZINE = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Nato Magazine");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Military");
			l.add(ChatColor.GOLD + "Capacity - 30");
			meta.setLore(l);
			this.setItemMeta(meta);
			//red
			this.setDurability((short)1);
		}
	};
	public static final ItemStack LAPUAMAGAZINE = new ItemStack(Material.INK_SACK, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Lapua Magazine");
			List<String> l = new ArrayList<String>();
			l.add(ChatColor.GOLD + "Type - Military");
			l.add(ChatColor.GOLD + "Capacity - 6");
			meta.setLore(l);
			this.setItemMeta(meta);
			//yellow
			this.setDurability((short)11);
		}
	};
	//THROWABLES
	public static final ItemStack FRAGGRENADE = new ItemStack(Material.SLIME_BALL, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Frag Grenade");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack MOAB = new ItemStack(Material.TNT, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("MOAB");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(PRISTINE);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack SMOKEGRENADE = new ItemStack(Material.FLINT, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Smoke Grenade");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(PRISTINE);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack GLOWSTICK = new ItemStack(Material.REDSTONE_TORCH_OFF, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Glowstick");
			List<String> l = new ArrayList<String>();
			l.add(" ");
			l.add(DAMAGED);
			meta.setLore(l);
			this.setItemMeta(meta);
		}
	};
	//CONSUMABLES
	public static final ItemStack FRESHPOTATO = new ItemStack(Material.BAKED_POTATO, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Fresh potato");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack FRESHAPPLE = new ItemStack(Material.APPLE, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Fresh Apple");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack FRESHCARROT = new ItemStack(Material.CARROT, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Fresh Carrot");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack RAWBEEF = new ItemStack(Material.RAW_BEEF, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Raw Beef");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack FRESHWATERMELON = new ItemStack(Material.MELON, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Fresh Watermelon");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack CAKE = new ItemStack(Material.CAKE, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Cake - 100%");
			this.setItemMeta(meta);
		}
	};
	//MOLDY FOOT
	public static final ItemStack MOLDYPOTATO = new ItemStack(Material.POISONOUS_POTATO, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "" + ChatColor.ITALIC + "Moldy Potato");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack MOLDYBEEF = new ItemStack(Material.ROTTEN_FLESH, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "" + ChatColor.ITALIC + "Moldy Beef");
			this.setItemMeta(meta);
		}
	};
	//DRINKS
	public static final ItemStack SODA = new ItemStack(Material.WATER_BUCKET, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Soda");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack MOLDYWATER = new ItemStack(Material.POTION, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "" + ChatColor.ITALIC + "Moldy Water");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack WATER = new ItemStack(Material.POTION, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Clean Water");
			this.setItemMeta(meta);
		}
	};
	//PLACEABLES
	public static final ItemStack WORKLIGHT = new ItemStack(Material.GLOWSTONE, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Worklight");
			this.setItemMeta(meta);
		}
	};
	public static final ItemStack WOODEN_TRAP = new ItemStack(Material.WOOD_PLATE, 1) {
		{
			ItemMeta meta = this.getItemMeta();
			meta.setDisplayName("Wooden trap");
			this.setItemMeta(meta);
		}
	};
}
