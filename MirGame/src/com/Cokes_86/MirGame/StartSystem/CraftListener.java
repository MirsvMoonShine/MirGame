package com.Cokes_86.MirGame.StartSystem;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.Cokes_86.MirGame.MirGame;

public class CraftListener implements Listener{
	final MirGame m;
	
	public CraftListener(MirGame m){
		this.m = m;
	}
	
	@EventHandler
	public void dontcraft(PrepareItemCraftEvent e) {
		for (int a = 0 ; a<9;a++){
			if (e.getInventory().getItem(a) != null){
				ItemStack s = e.getInventory().getItem(a);
				if (s.hasItemMeta() && s.getItemMeta().hasDisplayName()){
					if (s.getType() == Material.GOLD_NUGGET && s.getItemMeta().getDisplayName().equals("§e금화")){
						e.getInventory().setResult(null);
						break;
					} else if (s.getType() == Material.IRON_NUGGET && s.getItemMeta().getDisplayName().equals("§e초월석")){
						e.getInventory().setResult(null);
						break;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void newcraft(PrepareItemCraftEvent e){
		
	}
}
