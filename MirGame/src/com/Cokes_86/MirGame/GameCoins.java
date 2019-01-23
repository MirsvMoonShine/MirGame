package com.Cokes_86.MirGame;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameCoins {
	
	public ItemStack getCoin(int ammount){
		ItemStack Result = new ItemStack(Material.GOLD_NUGGET,ammount);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName("¡×e±ÝÈ­");
		Result.setItemMeta(meta);
		
		return Result;
	}
	
	public ItemStack getEye(int ammount){
		ItemStack Result = new ItemStack(Material.EYE_OF_ENDER,ammount);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName("¡×dÄ¸½¶");
		Result.setItemMeta(meta);
		
		return Result;
	}
	
	public ItemStack get¿ÏµÎÄá(int ammount){
		ItemStack Result = new ItemStack(Material.SLIME_BALL,ammount);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName("¡×a¿ÏµÎÄá");
		Result.setItemMeta(meta);
		
		return Result;
	}
	
	public ItemStack getCloth(int amount){
		ItemStack Result = new ItemStack(Material.DEAD_BUSH,amount);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName("¡×6Á¶ÀâÇÑ ¼¶À¯");
		Result.setItemMeta(meta);
		
		return Result;
	}
}
