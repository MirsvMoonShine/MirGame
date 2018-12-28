package com.Cokes_86.MirGame.UI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.Cokes_86.MirGame.MirGame;

public class FusionUpgradeGui {
    final MirGame m;
    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	
	public FusionUpgradeGui (MirGame main){
		this.m = main;
	}
	
	public void openReadyFusion(Player p){
		Inventory i = Bukkit.createInventory(null, 27, "§l합성");
		
		for (int a = 18; a<27;a++){
			m.gi.setItem(i, a, Material.STAINED_GLASS_PANE, 1, 0, " ", null);
		}
		
		p.openInventory(i);
	}
	
	public void openLoadingFusion(Player p, ItemStack result){
		Inventory i = Bukkit.createInventory(null, 27, "§l합성 진행중");
		
		loadingFusion(0,p,i,result);
		
		p.openInventory(i);
	}
	
	public void loadingFusion(int a, Player p, Inventory i, ItemStack result){
		ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)13);
		ItemMeta itemmeta = pane.getItemMeta();
		itemmeta.setDisplayName(" ");
		pane.setItemMeta(itemmeta);
		i.setItem(a, pane); i.setItem(9+a, pane); i.setItem(18+a, pane);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (a!=8){
					loadingFusion(a+1,p,i,result);
				} else {
					openCompleteFusion(p, result);
				}
			}
		},10);
	}
	
	public void openCompleteFusion(Player p, ItemStack result){
		Inventory i = Bukkit.createInventory(null, 9, "§l합성 완료");
		for (int a = 0;a<9;a++){
			m.gi.setItem(i, a, Material.STAINED_GLASS_PANE, 1, 5, " ", null);
		}
		
		i.setItem(4, result);
		p.getInventory().addItem(result);
		p.openInventory(i);
	}
}
