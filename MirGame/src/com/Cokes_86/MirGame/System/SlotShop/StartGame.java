package com.Cokes_86.MirGame.System.SlotShop;

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
		m.gi.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "§6[§9미르 게임§6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); m.ci.start.put(p, false); return;}
		int coin = coins.getAmount();
		if (!(coins.hasItemMeta() && coins.getItemMeta().hasDisplayName() && coins.getItemMeta().getDisplayName().equals(m.gc.getCoin(1).getItemMeta().getDisplayName()))) {m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); m.ci.start.put(p, false); return;}
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
				if (!m.hottime) m.gi.setItem(i, 29, materialOldSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 29, materialOldSlot_HotTime(), 1, 0, " ", null);
				if(sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 31, materialOldSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 31, materialOldSlot_HotTime(), 1, 0, " ", null);
				if(sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*2);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 33, materialOldSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 33, materialOldSlot_HotTime(), 1, 0, " ", null);
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
				
				m.gi.setItem(i, 29,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 31,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 33,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("§r1회 실행")){
					m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
					m.ci.start.put(p, false);
				} else if (repeat.getItemMeta().getDisplayName().equals("§r반복 실행") && m.ci.start.get(p)){
					startOldSlot(e);
				}
			}
        }, speed*4);
	}
	
	//아래는 슬롯
	public Material materialSlot(){
		int a = r.nextInt(1000);
		if (a >= 0 && a < 284){
			return Material.DIRT;
		} else if (a >= 284 && a < 484){
			return Material.WOOD;
		} else if (a >= 484 && a < 620){
			return Material.STONE;
		} else if (a >= 620 && a < 711){
			return Material.IRON_INGOT;
		} else if (a >= 711 && a < 790){
			return Material.GOLD_INGOT;
		} else if (a >= 790 && a < 850){
			return Material.DIAMOND;
		} else if (a >= 850 && a < 905){
			return Material.OBSIDIAN;
		} else if (a >= 905 && a < 945){
			return Material.EMERALD;
		} else if (a >= 945 && a < 980){
			return Material.BEDROCK;
		} else {
			return Material.NETHER_STAR;
		}
	}
	
	public Material materialSlot_HotTime(){
		int a = r.nextInt(10000);
		if (a >= 0 && a < 4000){
			return Material.DIRT;
		} else if (a >= 4000 && a < 6492){
			return Material.WOOD;
		} else if (a >= 6492 && a < 7852){
			return Material.STONE;
		} else if (a >= 7852 && a < 8674){
			return Material.IRON_INGOT;
		} else if (a >= 8674 && a < 9280){
			return Material.GOLD_INGOT;
		} else if (a >= 9280 && a < 9634){
			return Material.DIAMOND;
		} else if (a >= 9634 && a < 9834){
			return Material.OBSIDIAN;
		} else if (a >= 9834 && a < 9934){
			return Material.EMERALD;
		} else if (a >= 9934 && a < 9994){
			return Material.BEDROCK;
		} else {
			return Material.NETHER_STAR;
		}
	}
	
	public void startSlot(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		m.gi.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "§6[§9미르 게임§6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); return;}
		int coin = coins.getAmount();
		if (!(coins.hasItemMeta() && coins.getItemMeta().hasDisplayName() && coins.getItemMeta().getDisplayName().equals(m.gc.getCoin(1).getItemMeta().getDisplayName()))) {m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); m.ci.start.put(p, false); return;}
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
				if (!m.hottime) m.gi.setItem(i, 29, materialSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 29, materialSlot_HotTime(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 31, materialSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 31, materialSlot_HotTime(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*2);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 33, materialSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 33, materialSlot_HotTime(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*3);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (i.getItem(29).getType() == i.getItem(31).getType() &&  i.getItem(31).getType() ==  i.getItem(33).getType()){
					if (i.getItem(29).getType() == Material.DIRT){
						if (br) p.sendMessage("§6[§9미르 게임§6]§r 흙 3개 맞추기 성공!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <흙>").getBox());
					} else if (i.getItem(29).getType() == Material.WOOD){
						if (br) p.sendMessage("§6[§9미르 게임§6]§r 나무 3개 맞추기 성공!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <나무>").getBox());
					} else if (i.getItem(29).getType() == Material.STONE){
						if (br) p.sendMessage("§6[§9미르 게임§6]§r 돌 3개 맞추기 성공!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <돌>").getBox());
					} else if (i.getItem(29).getType() == Material.IRON_INGOT){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯 철 3개를 맞추었습니다!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <철>").getBox());
					} else if (i.getItem(29).getType() == Material.GOLD_INGOT){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯 금 3개를 맞추었습니다!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <금>").getBox());
						m.firework(p);
						if (sound) w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
					} else if (i.getItem(29).getType() == Material.DIAMOND){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯 다이아몬드 3개를 맞추었습니다!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <다이아몬드>").getBox());
						m.firework(p);
						if (sound) w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
					} else if (i.getItem(29).getType() == Material.OBSIDIAN){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯 옵시디언 3개를 맞추었습니다!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <옵시디언>").getBox());
						m.firework(p);
						if (sound) w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
					} else if (i.getItem(29).getType() == Material.EMERALD){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯 에메랄드 3개를 맞추었습니다!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <에메랄드>").getBox());
						m.firework(p);
						if (sound) w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
					} else if (i.getItem(29).getType() == Material.BEDROCK){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯 배드락 3개를 맞추었습니다!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <배드락>").getBox());
						m.firework(p);
						if (sound) w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
					} else if (i.getItem(29).getType() == Material.NETHER_STAR){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 슬롯 네더의 별 3개를 맞추었습니다!");
						p.getInventory().addItem(m.getBox("§l슬롯 보상 <네더의 별>").getBox());
						m.firework(p);
						if (sound) w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
					}
				}
				
				m.gi.setItem(i, 29,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 31,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 33,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("§r1회 실행")){
					m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
					m.ci.start.put(p, false);
				} else if (repeat.getItemMeta().getDisplayName().equals("§r반복 실행") && m.ci.start.get(p)){
					startSlot(e);
				}
			}
        }, speed*4);
	}
}
