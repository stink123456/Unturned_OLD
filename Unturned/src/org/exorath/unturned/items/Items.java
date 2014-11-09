package org.exorath.unturned.items;

import org.bukkit.inventory.ItemStack;
import org.exorath.unturned.libraries.CustomItems;

public enum Items {

	RAG(CustomItems.RAG),
	BANDAGE(CustomItems.BANDAGE),
	DRESSING(CustomItems.DRESSING),
	ANTIBIOTICS(CustomItems.ANTIBIOTICS),
	BASEBALLBAT(CustomItems.BASEBALLBAT),
	KITCHENKNIFE(CustomItems.KITCHENKNIFE),
	BUTCHERKNIFE(CustomItems.BUTCHERKNIFE),
	KATANA(CustomItems.KATANA),
	BRANCH(CustomItems.BRANCH),
	AXE(CustomItems.AXE),
	FIREAXE(CustomItems.FIREAXE),
	PICKAXE(CustomItems.PICKAXE),
	BLOWTORCH(CustomItems.BLOWTORCH),
	FLASHLIGHT(CustomItems.FLASHLIGHT),
	LEBELMAGAZINE(CustomItems.LEBELMAGAZINE),
	SWIFTMAGAZINE(CustomItems.SWIFTMAGAZINE),
	WINCHESTERCLIP(CustomItems.WINCHESTERCLIP),
	BONJOURCLIP(CustomItems.BONJOURCLIP),
	SAVAGEMAGAZINE(CustomItems.SAVAGEMAGAZINE),
	YURIMAGAZINE(CustomItems.YURIMAGAZINE),
	PDWMAGAZINE(CustomItems.PDWMAGAZINE),
	NATOMAGAZINE(CustomItems.NATOMAGAZINE),
	LAPUAMAGAZINE(CustomItems.LAPUAMAGAZINE),
	FRAGGRENADE(CustomItems.FRAGGRENADE),
	MOAB(CustomItems.MOAB),
	SMOKEGRENADE(CustomItems.SMOKEGRENADE),
	GLOWSTICK(CustomItems.GLOWSTICK),
	FRESHPOTATO(CustomItems.FRESHPOTATO),
	FRESHAPPLE(CustomItems.FRESHAPPLE),
	FRESHCARROT(CustomItems.FRESHCARROT),
	RAWBEEF(CustomItems.RAWBEEF),
	FRESHWATERMELON(CustomItems.FRESHWATERMELON),
	CAKE(CustomItems.CAKE),
	MOLDYPOTATO(CustomItems.MOLDYPOTATO),
	MOLDYBEEF(CustomItems.MOLDYBEEF),
	SODA(CustomItems.SODA),
	MOLDYWATER(CustomItems.MOLDYWATER),
	WATER(CustomItems.WATER),
	WORKLIGHT(CustomItems.WORKLIGHT),
	WOODEN_TRAP(CustomItems.WOODEN_TRAP);
	private ItemStack stack;
	Items(ItemStack stack){
		this.stack = stack;
	}
	public ItemStack getStack() {
		return stack;
	}
	public void setStack(ItemStack stack) {
		this.stack = stack;
	}
}
