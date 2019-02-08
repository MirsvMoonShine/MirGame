package com.Cokes_86.MirGame.System;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class Util {

	public void setItem(Inventory i, int slot, Material m, int ammount, int data, String name, String[] lore){
		ItemStack result = new ItemStack(m,ammount,(short)data);
		ItemMeta resultm = result.getItemMeta();
		resultm.setDisplayName(name);
		if (lore != null){
			List<String> lore2 = Arrays.asList(lore);
			resultm.setLore(lore2);
		}
		result.setItemMeta(resultm);
		
		i.setItem(slot, result);
	}
	
	public void setPotionItem(Inventory i, int slot, int amount, String name, String[] lore, Color color) {
		ItemStack potion = new ItemStack(Material.POTION,amount);
		PotionMeta m = (PotionMeta) potion.getItemMeta();
		m.setDisplayName(name);
		m.setLore(Arrays.asList(lore));
		m.setColor(color);
		potion.setItemMeta(m);
		
		i.setItem(slot, potion);
	}
}
