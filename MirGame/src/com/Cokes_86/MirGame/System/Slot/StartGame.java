package com.Cokes_86.MirGame.System.Slot;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import com.Cokes_86.MirGame.MirGame;

public class StartGame {
	final MirGame m;
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	Random r = new Random();
	
	public StartGame(MirGame m){
		this.m = m;
	}
	
	// 아래는 (구)슬롯
	public Material materialOldSlot(){
		int a = r.nextInt(64*9);
		if (a >= 0 && a < 64){
			return Material.DIAMOND;
		} else {
			return Material.COAL;
		}
	}
	
	public Material materialOldSlot_HotTime(){
		int a = r.nextInt(64*9);
		if (a >= 0 && a < 128){
			return Material.DIAMOND;
		} else {
			return Material.COAL;
		}
	}
	
	public void startOldSlot(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		World w = p.getWorld();
		m.u.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "§6[§9미르 게임§6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); m.ci.start.put(p, false); return;}
		int coin = coins.getAmount();
		if (!(coins.hasItemMeta() && coins.getItemMeta().hasDisplayName() && coins.getItemMeta().getDisplayName().equals(m.gc.getCoin(1).getItemMeta().getDisplayName()))) {m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); m.ci.start.put(p, false); return;}
		if (coin >1) i.setItem(45, m.gc.getCoin(coin-1));
		else if (coin == 1) i.setItem(45, null);
		File f = new File("plugins/MirGame/Setting/"+p.getUniqueId()+".setting");
		FileConfiguration setting = YamlConfiguration.loadConfiguration(f);
		try { 
			setting.load(f);
		} catch (Exception ex){}
		long speed = setting.getLong("Speed.OldSlot");
		boolean sound = setting.getBoolean("Sound");
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.u.setItem(i, 29, materialOldSlot(), 1, 0, " ", null);
				else m.u.setItem(i, 29, materialOldSlot_HotTime(), 1, 0, " ", null);
				if(sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.u.setItem(i, 31, materialOldSlot(), 1, 0, " ", null);
				else m.u.setItem(i, 31, materialOldSlot_HotTime(), 1, 0, " ", null);
				if(sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*2);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.u.setItem(i, 33, materialOldSlot(), 1, 0, " ", null);
				else m.u.setItem(i, 33, materialOldSlot_HotTime(), 1, 0, " ", null);
				if(sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*3);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (i.getItem(29).getType() == i.getItem(31).getType() &&  i.getItem(31).getType() ==  i.getItem(33).getType() && i.getItem(29).getType() == Material.DIAMOND){
					Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 (구)슬롯에 당첨되었습니다.");
					p.getInventory().addItem(m.getBox("§l(구)슬롯 보상").getBox());
					p.giveExp(325);
					
					m.firework(p);
					if (sound) w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
				}
				
				m.u.setItem(i, 29,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 31,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 33,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("§r1회 실행")){
					m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
					m.ci.start.put(p, false);
				} else if (repeat.getItemMeta().getDisplayName().equals("§r반복 실행") && m.ci.start.get(p)){
					startOldSlot(e);
				}
			}
        }, speed*4);
	}
	
	//아래는 슬롯
	public Material materialSlot(){
		int a = r.nextInt(100);
		if (a >= 0 && a<30) {
			return Material.IRON_INGOT;
		} else if (a>= 30 && a< 55) {
			return Material.GOLD_INGOT;
		} else if (a>=55 && a<75) {
			return Material.DIAMOND;
		} else if (a>=75 && a<90) {
			return Material.EMERALD;
		} else {
			return Material.NETHER_STAR;
		}
	}
	
	public Material materialSlot_HotTime(){
		int a = r.nextInt(1000);
		if (a >= 0 && a<275) {
			return Material.IRON_INGOT;
		} else if (a>= 275 && a< 500) {
			return Material.GOLD_INGOT;
		} else if (a>=500 && a<700) {
			return Material.DIAMOND;
		} else if (a>=700 && a<875) {
			return Material.EMERALD;
		} else {
			return Material.NETHER_STAR;
		}
	}
	
	public void startSlot(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		m.u.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "§6[§9미르 게임§6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); return;}
		int coin = coins.getAmount();
		if (!(coins.hasItemMeta() && coins.getItemMeta().hasDisplayName() && coins.getItemMeta().getDisplayName().equals(m.gc.getCoin(1).getItemMeta().getDisplayName()))) {m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); m.ci.start.put(p, false); return;}
		if (coin >1) i.setItem(45, m.gc.getCoin(coin-1));
		else if (coin == 1) i.setItem(45, null);
		World w = p.getWorld();
		File f = new File("plugins/MirGame/Setting/"+p.getUniqueId()+".setting");
		FileConfiguration setting = YamlConfiguration.loadConfiguration(f);
		try { 
			setting.load(f);
		} catch (Exception ex){}
		long speed = setting.getLong("Speed.Slot");
		boolean sound = setting.getBoolean("Sound");
		boolean br = setting.getBoolean("Broadcast");
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.u.setItem(i, 28, materialSlot(), 1, 0, " ", null);
				else m.u.setItem(i, 28, materialSlot_HotTime(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.u.setItem(i, 30, materialSlot(), 1, 0, " ", null);
				else m.u.setItem(i, 30, materialSlot_HotTime(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*2);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.u.setItem(i, 32, materialSlot(), 1, 0, " ", null);
				else m.u.setItem(i, 32, materialSlot_HotTime(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*3);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.u.setItem(i, 34, materialSlot(), 1, 0, " ", null);
				else m.u.setItem(i, 34, materialSlot_HotTime(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*4);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				int iron=0,gold=0,dia=0,emerald=0,star=0;
				for (int a = 28; a< 35;a+=2) {
					if (i.getItem(a).getType() == Material.IRON_INGOT) iron +=1;
					if (i.getItem(a).getType() == Material.GOLD_INGOT) gold +=1;
					if (i.getItem(a).getType() == Material.DIAMOND) dia +=1;
					if (i.getItem(a).getType() == Material.EMERALD) emerald +=1;
					if (i.getItem(a).getType() == Material.NETHER_STAR) star +=1;
				}
				
				if (iron == 3) {
					if (br) p.sendMessage("§6[§9미르 게임§6]§r 슬롯에서 철 3개가 나왔습니다.");
				} else if (iron ==4) {
					if (br) p.sendMessage("§6[§9미르 게임§6]§r 슬롯에서 철 4개가 나왔습니다.");
				} else if (gold == 3) {
					if (br) p.sendMessage("§6[§9미르 게임§6]§r 슬롯에서 금 3개가 나왔습니다.");
				} else if (gold ==4) {
					Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯에서 금 4개가 나왔습니다.");
				} else if (dia == 3) {
					if (br) p.sendMessage("§6[§9미르 게임§6]§r 슬롯에서 다이아몬드 3개가 나왔습니다.");
				} else if (dia ==4) {
					Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯에서 다이아몬드 4개가 나왔습니다.");
				} else if (emerald == 3) {
					if (br) p.sendMessage("§6[§9미르 게임§6]§r 슬롯에서 에메랄드 3개가 나왔습니다.");
				} else if (emerald ==4) {
					Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯에서 에메랄드 4개가 나왔습니다.");
				} else if (star == 3) {
					Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯에서 네더의 별 3개가 나왔습니다.");
				} else if (star ==4) {
					Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯에서 네더의 별 4개가 나왔습니다.");
				} 
				
				m.u.setItem(i, 28,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 30,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 32,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 34,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("§r1회 실행")){
					m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
					m.ci.start.put(p, false);
				} else if (repeat.getItemMeta().getDisplayName().equals("§r반복 실행") && m.ci.start.get(p)){
					startSlot(e);
				}
			}
        }, speed*5);
	}
}
