package com.Cokes_86.MirGame;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class StartGame {
	final MirGame m;
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	Random r = new Random();
	
	public StartGame(MirGame m){
		this.m = m;
	}
	
	// �Ʒ��� (��)����
	public Material materialOldSlot(){
		int a = r.nextInt(64*9);
		if (a >= 0 && a < 64){
			return Material.DIAMOND;
		} else {
			return Material.COAL;
		}
	}
	
	public void startOldSlot(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		m.gi.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "��6[��9�̸� ���ӡ�6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null); return;}
		int coin = coins.getAmount();
		if (coin >1) i.setItem(45, m.gc.getCoin(coin-1));
		else if (coin == 1) i.setItem(45, null);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.gi.setItem(i, 29, materialOldSlot(), 1, 0, " ", null);
			}
        }, 15);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.gi.setItem(i, 31, materialOldSlot(), 1, 0, " ", null);
			}
        }, 30);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.gi.setItem(i, 33, materialOldSlot(), 1, 0, " ", null);
			}
        }, 45);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (i.getItem(29).getType() == i.getItem(31).getType() &&  i.getItem(31).getType() ==  i.getItem(33).getType()){
					if (i.getItem(29).getType() == Material.DIAMOND){
						Player p = (Player) e.getWhoClicked();
						Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��l"+p.getName()+"��r���� (��)���Կ� ��÷�Ǿ����ϴ�.");
						p.getInventory().addItem(m.gc.getEye(1));
						p.getInventory().addItem(m.gc.get�ϵ���(15));
						p.giveExp(325);
						
					}
				}
				
				m.gi.setItem(i, 29,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 31,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 33,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("��r1ȸ ����")){
					m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
				} else if (repeat.getItemMeta().getDisplayName().equals("��r�ݺ� ����")){
					startOldSlot(e);
				}
			}
        }, 60);
	}
	
	//�Ʒ��� ����
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
	
	public void startSlot(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		m.gi.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "��6[��9�̸� ���ӡ�6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null); return;}
		int coin = coins.getAmount();
		if (coin >1) i.setItem(45, m.gc.getCoin(coin-1));
		else if (coin == 1) i.setItem(45, null);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.gi.setItem(i, 29, materialSlot(), 1, 0, " ", null);
			}
        }, 15);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.gi.setItem(i, 31, materialSlot(), 1, 0, " ", null);
			}
        }, 30);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				m.gi.setItem(i, 33, materialSlot(), 1, 0, " ", null);
			}
        }, 45);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (i.getItem(29).getType() == i.getItem(31).getType() &&  i.getItem(31).getType() ==  i.getItem(33).getType()){
					if (i.getItem(29).getType() == Material.DIRT){
						System.out.println("��");
					} else if (i.getItem(29).getType() == Material.WOOD){
						System.out.println("����");
					} else if (i.getItem(29).getType() == Material.STONE){
						System.out.println("��");
					} else if (i.getItem(29).getType() == Material.IRON_INGOT){
						System.out.println("ö");
					} else if (i.getItem(29).getType() == Material.GOLD_INGOT){
						System.out.println("��");
					} else if (i.getItem(29).getType() == Material.DIAMOND){
						System.out.println("���̾Ƹ��");
					} else if (i.getItem(29).getType() == Material.OBSIDIAN){
						System.out.println("�ɽõ��");
					} else if (i.getItem(29).getType() == Material.EMERALD){
						System.out.println("���޶���");
					} else if (i.getItem(29).getType() == Material.BEDROCK){
						System.out.println("����");
					} else if (i.getItem(29).getType() == Material.NETHER_STAR){
						System.out.println("�״��� ��");
					}
				}
				
				m.gi.setItem(i, 29,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 31,  Material.STAINED_GLASS_PANE,1,0," ",null);
				m.gi.setItem(i, 33,  Material.STAINED_GLASS_PANE,1,0," ",null);
				
				m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
				
				ItemStack repeat = i.getItem(52);
				if (repeat.getItemMeta().getDisplayName().equals("��r1ȸ ����")){
					m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
				} else if (repeat.getItemMeta().getDisplayName().equals("��r�ݺ� ����")){
					startSlot(e);
				}
			}
        }, 60);
	}
}
