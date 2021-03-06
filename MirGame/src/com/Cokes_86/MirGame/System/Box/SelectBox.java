package com.Cokes_86.MirGame.System.Box;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SelectBox extends Box{
	final String BoxName;
	final ItemStack[] list;
	
	public SelectBox(String name, ItemStack[] list) {
		super(name, list, 1);
		this.BoxName=name;
		this.list = list;
	}
	
	public String getBoxName(){
		return BoxName;
	}
	
	public ItemStack[] getList(){
		return list;
	}
	
	public ItemStack getBox(){
		ItemStack result = new ItemStack(Material.CHEST,1);
		ItemMeta m = result.getItemMeta();
		m.setDisplayName("§r"+BoxName);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§a§l미르게임 선택 상자");
		lore.add("");
		lore.add("§r《구성품 중 하나 선택 획득》");
		for (ItemStack list : this.list){
			if (list.hasItemMeta() && list.getItemMeta() != null) lore.add("§r- "+list.getItemMeta().getDisplayName()+ " "+ list.getAmount()+"개");
			else lore.add("§r- "+list.getType().toString().replace("_", " ").toLowerCase()+ " "+ list.getAmount()+"개");
		}
		m.setLore(lore);
		result.setItemMeta(m);
		return result;
	}
}
