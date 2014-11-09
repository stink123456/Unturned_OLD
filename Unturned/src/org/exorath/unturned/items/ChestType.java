package org.exorath.unturned.items;

import java.util.HashMap;

public class ChestType {
	private String key;
	private HashMap<Items,Float> items = new HashMap<Items, Float>();
	private int minItems;
	private int maxItems;
	public ChestType(String key,int minItems, int maxItems, HashMap<Items, Float> items){
		this.key = key;
		this.minItems = minItems;
		this.maxItems = maxItems;
		this.items = items;
	}
	public int getMaxItems() {
		return maxItems;
	}
	public int getMinItems() {
		return minItems;
	}
	public HashMap<Items,Float> getItems() {
		return items;
	}
	public String getKey() {
		return key;
	}
}
