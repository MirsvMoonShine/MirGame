package com.Cokes_86.MirGame.System.FusionUpgrade;

import java.util.Random;

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
		m.bg.open.put(p, true);
		
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
		m.bg.open.put(p, false);
		p.getInventory().addItem(result);
		p.openInventory(i);
	}
	
	public void openReadyUpgrade(Player p){
		Inventory i = Bukkit.createInventory(null, 27, "§l초월");
		
		//아이템
		m.gi.setItem(i, 0, Material.STAINED_GLASS_PANE, 1, 3, "§r아이템", null);
		m.gi.setItem(i, 1, Material.STAINED_GLASS_PANE, 1, 3, "§r아이템", null);
		m.gi.setItem(i, 2, Material.STAINED_GLASS_PANE, 1, 3, "§r아이템", null);
		m.gi.setItem(i, 9, Material.STAINED_GLASS_PANE, 1, 3, "§r아이템", null);
		m.gi.setItem(i, 11, Material.STAINED_GLASS_PANE, 1, 3, "§r아이템", null);
		m.gi.setItem(i, 18, Material.STAINED_GLASS_PANE, 1, 3, "§r아이템", null);
		m.gi.setItem(i, 19, Material.STAINED_GLASS_PANE, 1, 3, "§r아이템", null);
		m.gi.setItem(i, 20, Material.STAINED_GLASS_PANE, 1, 3, "§r아이템", null);
		
		//결과물
		m.gi.setItem(i, 3, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
		m.gi.setItem(i, 4, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
		m.gi.setItem(i, 5, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
		m.gi.setItem(i, 12, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
		m.gi.setItem(i, 13, Material.STAINED_GLASS_PANE, 1, 0, "§r결과", new String[]{"§r양옆에 아이템과 초월석이 채워지면 결과 아이템을 출력합니다."});
		m.gi.setItem(i, 14, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
		m.gi.setItem(i, 21, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
		m.gi.setItem(i, 22, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
		m.gi.setItem(i, 23, Material.STAINED_GLASS_PANE, 1, 7, " ", null);
		
		//초월석
		m.gi.setItem(i, 6, Material.STAINED_GLASS_PANE, 1, 14, "§r초월석", null);
		m.gi.setItem(i, 7, Material.STAINED_GLASS_PANE, 1, 14, "§r초월석", null);
		m.gi.setItem(i, 8, Material.STAINED_GLASS_PANE, 1, 14, "§r초월석", null);
		m.gi.setItem(i, 15, Material.STAINED_GLASS_PANE, 1, 14, "§r초월석", null);
		m.gi.setItem(i, 17, Material.STAINED_GLASS_PANE, 1, 14, "§r초월석", null);
		m.gi.setItem(i, 24, Material.STAINED_GLASS_PANE, 1, 14, "§r초월석", null);
		m.gi.setItem(i, 25, Material.STAINED_GLASS_PANE, 1, 14, "§r초월석", null);
		m.gi.setItem(i, 26, Material.STAINED_GLASS_PANE, 1, 14, "§r초월석", null);
		
		p.openInventory(i);
	}
	
	public void openLoadingUpgrade(Player p, ItemStack success, ItemStack fail, int percent){
		Inventory i = Bukkit.createInventory(null, 27, "§l초월 진행중");
		
		loadingUpgrade(0,p,i,success,fail,percent);
		m.bg.open.put(p, true);
		
		p.openInventory(i);
	}
	
	public void loadingUpgrade(int a, Player p, Inventory i, ItemStack success, ItemStack fail, int percent){
		ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)13);
		ItemMeta itemmeta = pane.getItemMeta();
		itemmeta.setDisplayName(" ");
		pane.setItemMeta(itemmeta);
		i.setItem(a, pane); i.setItem(9+a, pane); i.setItem(18+a, pane);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (a!=8){
					loadingUpgrade(a+1,p,i,success,fail,percent);
				} else {
					Random r = new Random();
					int a = r.nextInt(100);
					if (a < percent){
						openSuccessUpgrade(p, success);
					} else {
						openFailedUpgrade(p, fail);
					}
				}
			}
		},10);
	}
	
	public void openSuccessUpgrade(Player p, ItemStack success){
		Inventory i = Bukkit.createInventory(null, 9, "§l초월 성공");
		for (int a = 0;a<9;a++){
			m.gi.setItem(i, a, Material.STAINED_GLASS_PANE, 1, 13, " ", null);
		}
		
		i.setItem(4, success);
		m.bg.open.put(p, false);
		p.getInventory().addItem(success);
		p.openInventory(i);
	}
	
	public void openFailedUpgrade(Player p, ItemStack fail){
		Inventory i = Bukkit.createInventory(null, 9, "§l초월 실패");
		for (int a = 0;a<9;a++){
			m.gi.setItem(i, a, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
		}
		
		i.setItem(4, fail);
		m.bg.open.put(p, false);
		p.getInventory().addItem(fail);
		p.openInventory(i);
	}
}
