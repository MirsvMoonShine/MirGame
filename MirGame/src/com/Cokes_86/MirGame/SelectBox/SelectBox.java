package com.Cokes_86.MirGame.SelectBox;

import org.bukkit.inventory.ItemStack;

public class SelectBox {
	final String BoxName;
	final ItemStack[] list;
	
	public SelectBox(String name, ItemStack[] list){
		this.BoxName = name;
		this.list = list;
	}
	
	public String getBoxName(){
		return BoxName;
	}
	
	public ItemStack[] getList(){
		return list;
	}
}
