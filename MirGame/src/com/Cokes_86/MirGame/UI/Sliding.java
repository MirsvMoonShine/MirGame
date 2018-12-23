package com.Cokes_86.MirGame.UI;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.Cokes_86.MirGame.MirGame;

public class Sliding {
	final MirGame m;
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	
	public Sliding (MirGame main){
		this.m = main;
	}
	
	public void openSliding(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 슬라이딩");
		/*0 1 2 3 4 5 6 7 8
		   9 0 1 2 3 4 5 6 7
		   8 9 0 1 2 3 4 5 6
		   7 8 9 0 1 2 3 4 5
		   6 7 8 9 0 1 2 3 4
		   5 6 7 8 9 0 1 2 3*/
		m.gi.setMenu(i);
		for (int a=27;a<36;a++){
			setItem(i, a, Material.STAINED_GLASS_PANE, 1, 0, " ", null);
		}
		loading(i,0);
		ItemStack Book = new ItemStack(Material.ENCHANTED_BOOK,1);
		ItemMeta bookmeta = Book.getItemMeta();
		bookmeta.setDisplayName("§r§e수선책");
		bookmeta.addEnchant(Enchantment.MENDING, 1, true);
		Book.setItemMeta(bookmeta);
		i.setItem(26, Book);
		
		setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
		
		p.openInventory(i);
	}
	
	public void loading(Inventory i,int a){
		setItem(i, a+27, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				setItem(i, a+27, Material.STAINED_GLASS_PANE, 1, 0, " ", null);
				if (a==8) loading(i,0);
				else loading(i,a+1);
			}
        }, 5);
	}
	
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
}
