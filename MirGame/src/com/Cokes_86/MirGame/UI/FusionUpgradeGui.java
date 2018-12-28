package com.Cokes_86.MirGame.UI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.Cokes_86.MirGame.MirGame;

public class FusionUpgradeGui {
    final MirGame m;
	
	public FusionUpgradeGui (MirGame main){
		this.m = main;
	}
	
	public void openReadyFusion(Player p){
		Inventory i = Bukkit.createInventory(null, 27, "¡×lÇÕ¼º");
		
		for (int a = 18; a<27;a++){
			m.gi.setItem(i, a, Material.STAINED_GLASS_PANE, 1, 0, " ", null);
		}
		
		p.openInventory(i);
	}
}
