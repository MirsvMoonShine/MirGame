package com.Cokes_86.MirGame.StartSystem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import com.Cokes_86.MirGame.MirGame;

public class FusionUpgradeListener implements Listener {
	final MirGame m;
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	
	public FusionUpgradeListener (MirGame main){
		this.m = main;
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (inv == p.getInventory()) {e.setCancelled(false);}
		else if (inv == null) { return; }
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l합성"))){
			if (Click.getType() == Material.STAINED_GLASS_PANE) {e.setCancelled(true);}
			else if (Click.getType() == Material.WOOL){
				
			} else {
				ArrayList<ItemStack> list = new ArrayList<>();
				scheduler.scheduleSyncDelayedTask(m, new Runnable(){
					public void run() {
						for (int a= 0;a<18;a++){
							if (e.getInventory().getItem(a) != null) {
								list.add(e.getInventory().getItem(a));
							}
						}
					}
		        }, 1);
				scheduler.scheduleSyncDelayedTask(m, new Runnable(){
					public void run() {
						if (list.size() == 3 && list.get(0) == list.get(1) && list.get(0) == list.get(2)){
							System.out.println("ok");
							if (list.get(0).hasItemMeta() && list.get(0).getItemMeta().hasDisplayName() && list.get(0).getItemMeta().getDisplayName().equals(m.gr.DragonSlayer(0).getItemMeta().getDisplayName())){
								m.gi.setItem(inv, 22, Material.WOOL, 1, 10, "§r합성 시작", new String[]{"§r결과물: 드래곤 슬레이어"});
							}
						}
					}
		        }, 2);
			}
		}
	}
}
