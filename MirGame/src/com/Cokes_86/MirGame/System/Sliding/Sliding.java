package com.Cokes_86.MirGame.System.Sliding;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.Cokes_86.MirGame.MirGame;

public class Sliding implements Listener{
	final MirGame m;
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	
	public Sliding (MirGame main){
		this.m = main;
	}
	
	public void openSliding(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - �����̵�");
		/*0 1 2 3 4 5 6 7 8
		   9 0 1 2 3 4 5 6 7
		   8 9 0 1 2 3 4 5 6
		   7 8 9 0 1 2 3 4 5
		   6 7 8 9 0 1 2 3 4
		   5 6 7 8 9 0 1 2 3*/
		m.gi.setMenu(i);
		for (int a=27;a<36;a++){
			setItem(i, a, Material.STAINED_GLASS_PANE, 1, 0, " ", null);
		}
		//���� ���
		i.setItem(18, new ItemStack(Material.COAL, 20));
		i.setItem(19, new ItemStack(Material.COOKED_BEEF, 5));
		i.setItem(20, new ItemStack(Material.DIAMOND, 1));
		m.gi.setItem(i, 21, Material.GOLD_NUGGET, 1, 0, "��e��ȭ��r", null);
		i.setItem(22, m.gc.get�ϵ���(10));
		i.setItem(23, m.gc.getEye(2));
		i.setItem(24, new ItemStack(Material.EXP_BOTTLE,10));
		i.setItem(25, new ItemStack(Material.TOTEM,1));
		
		i.setItem(26, m.gr.MendingBook(1));
		
		//�⺻Ʋ
		setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		setItem(i, 46, Material.BOOK, 1, 0, "��r����", new String[]{"��r�� ���� �� ������ ��ȭ�� ���� �� ������ ������ Ŭ��", "��r�� �Ͼ� ����â�� ���� ����â���� �ٲ� ������ŭ ����"});
		setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
		setItem(i, 52, Material.ANVIL,1,0,"��r1ȸ ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
		
		p.openInventory(i);
	}
	
	public void setItem(Inventory i, int slot, Material m, int ammount, int data, String name, String[] lore){
		ItemStack result = new ItemStack(m,ammount,(short)data);
		ItemMeta resultm = result.getItemMeta();
		resultm.setDisplayName(name);
		if (lore != null){
			List<String> lore2 = Arrays.asList(lore);
			resultm.setLore(lore2);
		}
		result.setItemMeta(resultm);
		
		i.setItem(slot, result);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (inv == null) { return; }
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - �����̵�"))){
			e.setCancelled(true);
			m.ci.menu(e);
			 
			if (Click.getType() == Material.WOOL && Click.getItemMeta().getDisplayName().equals("��r��l����")){
				ItemStack coin = inv.getItem(45);
				 
				if (coin != null){
					if (coin.getType() == Material.GOLD_NUGGET){
						if (coin.hasItemMeta()){
							if (coin.getItemMeta().hasDisplayName() && coin.getItemMeta().getDisplayName().equals("��e��ȭ")){
								m.ci.start.put(p, true);
								start(e);
							}
						}
					}
				}
			} else if (Click.getType() == Material.ANVIL){
				if (Click.getItemMeta().getDisplayName().equals("��r1ȸ ����")){
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"��r�ݺ� ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
				} else if (Click.getItemMeta().getDisplayName().equals("��r�ݺ� ����")){
					Click.getItemMeta().setDisplayName("��r1ȸ ����");
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"��r1ȸ ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
				}
			} else if (e.getClickedInventory() == p.getInventory()) e.setCancelled(false);
			else if (e.getRawSlot() == 45){
				e.setCancelled(false);
			}
		}
	}
	
	public void start(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		m.gi.setItem(i, 53, Material.STAINED_GLASS_PANE, 1, 1, "��6[��9�̸� ���ӡ�6]", null);
		ItemStack coins = i.getItem(45);
		if (coins == null) {m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null); m.ci.start.put(p, false); return;}
		int coin = coins.getAmount();
		if (!(coins.hasItemMeta() && coins.getItemMeta().hasDisplayName() && coins.getItemMeta().getDisplayName().equals(m.gc.getCoin(1).getItemMeta().getDisplayName()))) {m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null); m.ci.start.put(p, false); return;}
		if (coin >1) i.setItem(45, m.gc.getCoin(coin-1));
		else if (coin == 1) i.setItem(45, null);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				setItem(i, 27, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
				
				Random r = new Random();
				int a = r.nextInt(100);
				if (a < 90){
					scheduler.scheduleSyncDelayedTask(m, new Runnable(){
						@Override
						public void run() {
							setItem(i, 28, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
							int a = r.nextInt(100);
							if (a < 80){
								scheduler.scheduleSyncDelayedTask(m, new Runnable(){
									@Override
									public void run() {
										setItem(i, 29, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
										int a = r.nextInt(100);
										if (a < 70){
											scheduler.scheduleSyncDelayedTask(m, new Runnable(){
												@Override
												public void run() {
													setItem(i, 30, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
													int a = r.nextInt(100);
													if (a < 60){
														scheduler.scheduleSyncDelayedTask(m, new Runnable(){
															@Override
															public void run() {
																setItem(i, 31, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
																int a = r.nextInt(100);
																if (a < 45){
																	scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																		@Override
																		public void run() {
																			setItem(i, 32, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
																			int a = r.nextInt(100);
																			if (a < 30){
																				scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																					@Override
																					public void run() {
																						setItem(i, 33, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
																						int a = r.nextInt(100);
																						if (a < 20){
																							scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																								@Override
																								public void run() {
																									setItem(i, 34, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
																									int a = r.nextInt(100);
																									if (a < 10){
																										scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																											@Override
																											public void run() {
																												setItem(i, 35, Material.STAINED_GLASS_PANE, 1, 14, " ", null);
																												scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																													@Override
																													public void run() {
																														p.getInventory().addItem(m.getBox("��a��l�����̵� ���� <9�ܰ�>").getBox());
																														Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� �����̵� 9�ܰ� ������ ȹ���Ͽ����ϴ�.");
																														reload(e);
																													}
																										        }, 5);
																											}
																								        }, 5);
																									} else {
																										scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																											@Override
																											public void run() {
																												p.getInventory().addItem(m.getBox("��l�����̵� ���� <8�ܰ�>").getBox());
																												Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� �����̵� 8�ܰ� ������ ȹ���Ͽ����ϴ�.");
																												reload(e);
																											}
																								        }, 5);
																									}
																									
																								}
																					        }, 5);
																						} else {
																							scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																								@Override
																								public void run() {
																									p.getInventory().addItem(m.getBox("��l�����̵� ���� <7�ܰ�>").getBox());
																									Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� �����̵� 7�ܰ� ������ ȹ���Ͽ����ϴ�.");
																									reload(e);
																								}
																					        }, 5);
																						}
																					}
																		        }, 5);
																			} else {
																				scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																					@Override
																					public void run() {
																						p.getInventory().addItem(m.getBox("��l�����̵� ���� <6�ܰ�>").getBox());
																						p.sendMessage("��6[��9�̸� ���ӡ�6]��r �����̵� 6�ܰ� ������ ȹ���Ͽ����ϴ�.");
																						reload(e);
																					}
																		        }, 5);
																			}
																		}
															        }, 5);
																} else {
																	scheduler.scheduleSyncDelayedTask(m, new Runnable(){
																		@Override
																		public void run() {
																			p.getInventory().addItem(m.getBox("��l�����̵� ���� <5�ܰ�>").getBox());
																			p.sendMessage("��6[��9�̸� ���ӡ�6]��r �����̵� 5�ܰ� ������ ȹ���Ͽ����ϴ�.");
																			reload(e);
																		}
															        }, 5);
																}
															}
												        }, 5);
													} else {
														scheduler.scheduleSyncDelayedTask(m, new Runnable(){
															@Override
															public void run() {
																p.getInventory().addItem(m.getBox("��r�����̵� ���� <4�ܰ�>").getBox());
																p.sendMessage("��6[��9�̸� ���ӡ�6]��r �����̵� 4�ܰ� ������ ȹ���Ͽ����ϴ�.");
																reload(e);
															}
												        }, 5);
													}
												}
									        }, 5);
										} else {
											scheduler.scheduleSyncDelayedTask(m, new Runnable(){
												@Override
												public void run() {
													p.getInventory().addItem(m.getBox("��r�����̵� ���� <3�ܰ�>").getBox());
													p.sendMessage("��6[��9�̸� ���ӡ�6]��r �����̵� 3�ܰ� ������ ȹ���Ͽ����ϴ�.");
													reload(e);
												}
									        }, 5);
										}
									}
						        }, 5);
							} else {
								scheduler.scheduleSyncDelayedTask(m, new Runnable(){
									@Override
									public void run() {
										p.getInventory().addItem(m.getBox("��r�����̵� ���� <2�ܰ�>").getBox());
										p.sendMessage("��6[��9�̸� ���ӡ�6]��r �����̵� 2�ܰ� ������ ȹ���Ͽ����ϴ�.");
										reload(e);
									}
						        }, 5);
							}
						}
			        }, 5);
				} else {
					scheduler.scheduleSyncDelayedTask(m, new Runnable(){
						@Override
						public void run() {
							p.getInventory().addItem(m.getBox("��r�����̵� ���� <1�ܰ�>").getBox());
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r �����̵� 1�ܰ� ������ ȹ���Ͽ����ϴ�.");
							reload(e);
						}
			        }, 5);
				}
			}
		},5);
	}
	
	public void reload(InventoryClickEvent e){
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		for (int a=27;a<36;a++){
			setItem(i, a, Material.STAINED_GLASS_PANE, 1, 0, " ", null);
		}
		
		ItemStack repeat = i.getItem(52);
		if (repeat.getItemMeta().getDisplayName().equals("��r1ȸ ����")){
			m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
			m.ci.start.put(p, false);
		} else if (repeat.getItemMeta().getDisplayName().equals("��r�ݺ� ����") && m.ci.start.get(p)){
			start(e);
		}
	}
}
