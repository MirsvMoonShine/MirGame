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
		if (inv == p.getInventory() && inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ռ�"))) {e.setCancelled(false); check(e);}
		else if (inv == null) { return; }
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ռ� ������"))){e.setCancelled(true);}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ռ� �Ϸ�"))){e.setCancelled(true);}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ռ�"))){
			if (Click.getType() == Material.STAINED_GLASS_PANE) {e.setCancelled(true);}
			else if (Click.getType() == Material.WOOL){
				e.setCancelled(true);
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
						if (list.size() == 3 && list.get(0).equals(list.get(1)) && list.get(0).equals(list.get(2))){
							if (list.get(0).hasItemMeta() && list.get(0).getItemMeta().hasDisplayName() && list.get(0).getItemMeta().getDisplayName().equals(m.gr.DragonSlayer(0).getItemMeta().getDisplayName())){
								m.fug.openLoadingFusion(p, m.gr.DragonSlayer(1));
							}
						}
					}
		        }, 2);
			} else {
				check(e);
			}
		}
		
		else if (inv == p.getInventory() && inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ʿ�"))){
			e.setCancelled(true);
			scheduler.scheduleSyncDelayedTask(m, new Runnable(){
				public void run() {
					if (e.getInventory().getItem(10) != null && e.getInventory().getItem(16) != null && e.getInventory().getItem(16).equals(m.gr.UpgradeStone(1))){
						if (e.getInventory().getItem(10).equals(m.gr.WatermelonSword(0))){
							inv.setItem(13, m.gr.WatermelonSword(1));
							m.gi.setItem(inv, 22, Material.WOOL, 1, 13, "��r�ʿ� ����", new String[]{"��r���� Ȯ��: 80%"});
						} else if (e.getInventory().getItem(10).equals(m.gr.WatermelonSword(1))){
							inv.setItem(13, m.gr.WatermelonSword(2));
							m.gi.setItem(inv, 22, Material.WOOL, 1, 13, "��r�ʿ� ����", new String[]{"��r���� Ȯ��: 40%"});
						}
						
						else {
							m.gi.setItem(inv, 13, Material.STAINED_GLASS_PANE, 1, 0, "��r���", new String[]{"��r�翷�� �����۰� �ʿ����� ä������ ��� �������� ����մϴ�."});
							m.gi.setItem(inv, 22, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
						}
					} else {
						m.gi.setItem(inv, 13, Material.STAINED_GLASS_PANE, 1, 0, "��r���", new String[]{"��r�翷�� �����۰� �ʿ����� ä������ ��� �������� ����մϴ�."});
						m.gi.setItem(inv, 22, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
					}
				}
	        }, 2);
		} else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ʿ� ������"))){e.setCancelled(true);}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ʿ� ����"))){e.setCancelled(true);}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ʿ� ����"))){e.setCancelled(true);}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�ʿ�"))){
			if (Click.getType() == Material.STAINED_GLASS_PANE) e.setCancelled(true);
			if (Click.equals(e.getInventory().getItem(13))) e.setCancelled(true);
			
			scheduler.scheduleSyncDelayedTask(m, new Runnable(){
				public void run() {
					if (e.getInventory().getItem(10) != null && e.getInventory().getItem(16) != null && e.getInventory().getItem(16).equals(m.gr.UpgradeStone(1))){
						if (e.getInventory().getItem(10).equals(m.gr.WatermelonSword(0))){
							inv.setItem(13, m.gr.WatermelonSword(1));
							m.gi.setItem(inv, 22, Material.WOOL, 1, 13, "��r�ʿ� ����", new String[]{"��r���� Ȯ��: 80%"});
						} else if (e.getInventory().getItem(10).equals(m.gr.WatermelonSword(1))){
							inv.setItem(13, m.gr.WatermelonSword(2));
							m.gi.setItem(inv, 22, Material.WOOL, 1, 13, "��r�ʿ� ����", new String[]{"��r���� Ȯ��: 40%"});
						}
						
						else {
							m.gi.setItem(inv, 13, Material.STAINED_GLASS_PANE, 1, 0, "��r���", new String[]{"��r�翷�� �����۰� �ʿ����� ä������ ��� �������� ����մϴ�."});
							m.gi.setItem(inv, 22, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
						}
					} else {
						m.gi.setItem(inv, 13, Material.STAINED_GLASS_PANE, 1, 0, "��r���", new String[]{"��r�翷�� �����۰� �ʿ����� ä������ ��� �������� ����մϴ�."});
						m.gi.setItem(inv, 22, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
					}
				}
	        }, 1);
			
			if (Click.getType() == Material.WOOL){
				e.setCancelled(true);
				
				if (e.getInventory().getItem(10) != null && e.getInventory().getItem(16) != null && e.getInventory().getItem(16).equals(m.gr.UpgradeStone(1))){
					if (e.getInventory().getItem(10).equals(m.gr.WatermelonSword(0))){
						m.fug.openLoadingUpgrade(p, m.gr.WatermelonSword(1), m.gr.WatermelonSword(0), 80);
					} else if (e.getInventory().getItem(10).equals(m.gr.WatermelonSword(1))){
						m.fug.openLoadingUpgrade(p, m.gr.WatermelonSword(2), m.gr.WatermelonSword(1), 40);
					}
				}
			}
		}
	}
	
	public void check(InventoryClickEvent e){
		Inventory inv = e.getInventory();
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
				if (list.size() == 3 && list.get(0).equals(list.get(1)) && list.get(0).equals(list.get(2))){
					if (list.get(0).hasItemMeta() && list.get(0).getItemMeta().hasDisplayName() && list.get(0).getItemMeta().getDisplayName().equals(m.gr.DragonSlayer(0).getItemMeta().getDisplayName())){
						m.gi.setItem(inv, 22, Material.WOOL, 1, 10, "��r�ռ� ����", new String[]{"��r�����: ��4��l�巡�� �����̾�"});
					} else {
						m.gi.setItem(inv, 22, Material.STAINED_GLASS_PANE, 1, 0, " ", null);
					}
				} else {
					m.gi.setItem(inv, 22, Material.STAINED_GLASS_PANE, 1, 0, " ", null);
				}
			}
        }, 2);
	}
}
