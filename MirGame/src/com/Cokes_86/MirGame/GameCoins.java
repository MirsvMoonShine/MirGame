package com.Cokes_86.MirGame;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameCoins {
	
	public ItemStack getCoin(int ammount){
		ItemStack Result = new ItemStack(Material.GOLD_NUGGET,ammount);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName("��e��ȭ");
		Result.setItemMeta(meta);
		
		return Result;
	}
	
	public ItemStack getEye(int ammount){
		ItemStack Result = new ItemStack(Material.EYE_OF_ENDER,ammount);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName("��dĸ��");
		Result.setItemMeta(meta);
		
		return Result;
	}
	
	public ItemStack get�ϵ���(int ammount){
		ItemStack Result = new ItemStack(Material.SLIME_BALL,ammount);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName("��a�ϵ���");
		Result.setItemMeta(meta);
		
		return Result;
	}
	
	public ItemStack getCloth(int amount){
		ItemStack Result = new ItemStack(Material.DEAD_BUSH,amount);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName("��6������ ����");
		Result.setItemMeta(meta);
		
		return Result;
	}
}
