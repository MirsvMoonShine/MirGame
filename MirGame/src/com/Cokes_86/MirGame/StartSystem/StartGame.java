package com.Cokes_86.MirGame.StartSystem;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
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
		if (coins == null) {m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null); return;}
		int coin = coins.getAmount();
		if (coin >1) i.setItem(45, m.gc.getCoin(coin-1));
		else if (coin == 1) i.setItem(45, null);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 29, materialOldSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 29, materialOldSlot_HotTime(), 1, 0, " ", null);
				w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, 15);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 31, materialOldSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 31, materialOldSlot_HotTime(), 1, 0, " ", null);
				w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, 30);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 33, materialOldSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 33, materialOldSlot_HotTime(), 1, 0, " ", null);
				w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, 45);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (i.getItem(29).getType() == i.getItem(31).getType() &&  i.getItem(31).getType() ==  i.getItem(33).getType() && i.getItem(29).getType() == Material.DIAMOND){
					Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §l"+p.getName()+"§r님이 (구)슬롯에 당첨되었습니다.");
					p.getInventory().addItem(m.gc.getEye(1));
					p.getInventory().addItem(m.gc.get완두콩(15));
					p.giveExp(325);
					
					m.firework(p);
					w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
				}
				
				m.gi.setItem(i, 29,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 31,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 33,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("§r1회 실행")){
					m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
				} else if (repeat.getItemMeta().getDisplayName().equals("§r반복 실행")){
					startOldSlot(e);
				}
			}
        }, 60);
	}
	
	//아래는 슬롯
	public Material materialSlot(){
		int a = r.nextInt(10000);
		if (a >= 0 && a < 4215){
			return Material.DIRT;
		} else if (a >= 4215 && a < 7155){
			return Material.WOOD;
		} else if (a >= 7155 && a < 7155+1360){
			return Material.STONE;
		} else if (a >= 8515 && a < 9337){
			return Material.IRON_INGOT;
		} else if (a >= 9337 && a < 9640){
			return Material.GOLD_INGOT;
		} else if (a >= 9640 && a < 9817){
			return Material.DIAMOND;
		} else if (a >= 9817 && a < 9917){
			return Material.OBSIDIAN;
		} else if (a >= 9917 && a < 9967){
			return Material.EMERALD;
		} else if (a >= 9967 && a < 9997){
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
		if (coin >1) i.setItem(45, m.gc.getCoin(coin-1));
		else if (coin == 1) i.setItem(45, null);
		World w = p.getWorld();
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 29, materialSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 29, materialSlot_HotTime(), 1, 0, " ", null);
				w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, 15);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 31, materialSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 31, materialSlot_HotTime(), 1, 0, " ", null);
				w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, 30);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (!m.hottime) m.gi.setItem(i, 33, materialSlot(), 1, 0, " ", null);
				else m.gi.setItem(i, 33, materialSlot_HotTime(), 1, 0, " ", null);
				w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, 45);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (i.getItem(29).getType() == i.getItem(31).getType() &&  i.getItem(31).getType() ==  i.getItem(33).getType()){
					if (i.getItem(29).getType() == Material.DIRT){
						p.sendMessage("§6[§9미르 게임§6]§r 흙 3개 맞추기 성공!");
						p.getInventory().addItem(m.gc.get완두콩(1));
					} else if (i.getItem(29).getType() == Material.WOOD){
						p.sendMessage("§6[§9미르 게임§6]§r 나무 3개 맞추기 성공!");
					} else if (i.getItem(29).getType() == Material.STONE){
						p.sendMessage("§6[§9미르 게임§6]§r 돌 3개 맞추기 성공!");
					} else if (i.getItem(29).getType() == Material.IRON_INGOT){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §l"+p.getName()+"§r님이 슬롯 철 3개를 맞추었습니다!");
					} else if (i.getItem(29).getType() == Material.GOLD_INGOT){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §l"+p.getName()+"§r님이 슬롯 금 3개를 맞추었습니다!");
					} else if (i.getItem(29).getType() == Material.DIAMOND){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §l"+p.getName()+"§r님이 슬롯 다이아몬드 3개를 맞추었습니다!");
					} else if (i.getItem(29).getType() == Material.OBSIDIAN){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §l"+p.getName()+"§r님이 슬롯 옵시디언 3개를 맞추었습니다!");
					} else if (i.getItem(29).getType() == Material.EMERALD){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §l"+p.getName()+"§r님이 슬롯 에메랄드 3개를 맞추었습니다!");
					} else if (i.getItem(29).getType() == Material.BEDROCK){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §l"+p.getName()+"§r님이 슬롯 배드락 3개를 맞추었습니다!");
					} else if (i.getItem(29).getType() == Material.NETHER_STAR){
						Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §l"+p.getName()+"§r님이 슬롯 네더의 별 3개를 맞추었습니다!");
						p.getInventory().addItem(new ItemStack(Material.BEACON,1));
						p.getInventory().addItem(new ItemStack(Material.TOTEM,5));
						
						m.firework(p);
						w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
					}
				}
				
				m.gi.setItem(i, 29,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 31,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 33,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("§r1회 실행")){
					m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
				} else if (repeat.getItemMeta().getDisplayName().equals("§r반복 실행")){
					startSlot(e);
				}
			}
        }, 60);
	}
}
