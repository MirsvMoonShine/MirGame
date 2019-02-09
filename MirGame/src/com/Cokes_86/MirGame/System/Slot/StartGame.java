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
	
	// �Ʒ��� (��)����
	public Material materialOldSlot(){
		if (!m.hottime) {
			int a = r.nextInt(64*9);
			if (a >= 0 && a < 64){
				return Material.DIAMOND;
			} else {
				return Material.COAL;
			}
		} else {
			int a = r.nextInt(100);
			if (a >= 0 && a < 18){
				return Material.DIAMOND;
			} else {
				return Material.COAL;
			}
		}
	}
	
	public void startOldSlot(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		World w = p.getWorld();
		m.u.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "��6[��9�̸� ���ӡ�6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null); m.ci.start.put(p, false); return;}
		int coin = coins.getAmount();
		if (!(coins.hasItemMeta() && coins.getItemMeta().hasDisplayName() && coins.getItemMeta().getDisplayName().equals(m.gc.getCoin(1).getItemMeta().getDisplayName()))) {m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null); m.ci.start.put(p, false); return;}
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
				m.u.setItem(i, 29, materialOldSlot(), 1, 0, " ", null);
				if(sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.u.setItem(i, 31, materialOldSlot(), 1, 0, " ", null);
				if(sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*2);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.u.setItem(i, 33, materialOldSlot(), 1, 0, " ", null);
				if(sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*3);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (i.getItem(29).getType() == i.getItem(31).getType() &&  i.getItem(31).getType() ==  i.getItem(33).getType() && i.getItem(29).getType() == Material.DIAMOND){
					Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� (��)���Կ� ��÷�Ǿ����ϴ�.");
					p.getInventory().addItem(m.getBox("��l(��)���� ����").getBox(p));
					p.giveExp(325);
					
					m.firework(p);
					if (sound) w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
				}
				
				m.u.setItem(i, 29,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 31,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 33,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("��r1ȸ ����")){
					m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
					m.ci.start.put(p, false);
				} else if (repeat.getItemMeta().getDisplayName().equals("��r�ݺ� ����") && m.ci.start.get(p)){
					startOldSlot(e);
				}
			}
        }, speed*4);
	}
	
	//�Ʒ��� ����
	public Material materialSlot(){
		int a = r.nextInt(100);
		if (!m.hottime) {
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
		} else {
			if (a >= 0 && a<25) {
				return Material.IRON_INGOT;
			} else if (a>= 25 && a< 47) {
				return Material.GOLD_INGOT;
			} else if (a>=47 && a<68) {
				return Material.DIAMOND;
			} else if (a>=68 && a<85) {
				return Material.EMERALD;
			} else {
				return Material.NETHER_STAR;
			}
		}
	}
	
	public void startSlot(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		m.u.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "��6[��9�̸� ���ӡ�6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null); return;}
		int coin = coins.getAmount();
		if (!(coins.hasItemMeta() && coins.getItemMeta().hasDisplayName() && coins.getItemMeta().getDisplayName().equals(m.gc.getCoin(1).getItemMeta().getDisplayName()))) {m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null); m.ci.start.put(p, false); return;}
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
				m.u.setItem(i, 28, materialSlot(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.u.setItem(i, 30, materialSlot(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*2);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.u.setItem(i, 32, materialSlot(), 1, 0, " ", null);
				if (sound) w.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0);
			}
        }, speed*3);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.u.setItem(i, 34, materialSlot(), 1, 0, " ", null);
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
					if (br) p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���Կ��� ö 3���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��r���� ���� <ö 3��>").getBox(p));
				} else if (iron ==4) {
					if (br) p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���Կ��� ö 4���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��r���� ���� <ö 4��>").getBox(p));
				} else if (gold == 3) {
					if (br) p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���Կ��� �� 3���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��r���� ���� <�� 3��>").getBox(p));
				} else if (gold ==4) {
					Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� ���Կ��� �� 4���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��l���� ���� <�� 4��>").getBox(p));
				} else if (dia == 3) {
					if (br) p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���Կ��� ���̾Ƹ�� 3���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��r���� ���� <���̾Ƹ�� 3��>").getBox(p));
				} else if (dia ==4) {
					Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� ���Կ��� ���̾Ƹ�� 4���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��l���� ���� <���̾Ƹ�� 4��>").getBox(p));
				} else if (emerald == 3) {
					if (br) p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���Կ��� ���޶��� 3���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��r���� ���� <���޶��� 3��>").getBox(p));
				} else if (emerald ==4) {
					Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� ���Կ��� ���޶��� 4���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��l���� ���� <���޶��� 4��>").getBox(p));
				} else if (star == 3) {
					Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� ���Կ��� �״��� �� 3���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��l���� ���� <�״��� �� 3��>").getBox(p));
				} else if (star ==4) {
					Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� ���Կ��� �״��� �� 4���� ���Խ��ϴ�.");
					p.getInventory().addItem(m.getBox("��a��l���� ���� <�״��� �� 4��>").getBox(p));
				} 
				
				m.u.setItem(i, 28,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 30,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 32,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.u.setItem(i, 34,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("��r1ȸ ����")){
					m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
					m.ci.start.put(p, false);
				} else if (repeat.getItemMeta().getDisplayName().equals("��r�ݺ� ����") && m.ci.start.get(p)){
					startSlot(e);
				}
			}
        }, speed*5);
	}
}
