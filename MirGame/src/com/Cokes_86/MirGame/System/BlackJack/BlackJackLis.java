package com.Cokes_86.MirGame.System.BlackJack;

import java.util.ArrayList;
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

import com.Cokes_86.MirGame.MirGame;

public class BlackJackLis implements Listener {
	MirGame m;
	BlackJack bj;
	Random r = new Random();
	
	public BlackJackLis(MirGame m) {
		this.m = m;
		this.bj = m.bj;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Inventory i = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (i.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - ����"))){
			boolean start = bj.start.getOrDefault(p, false);
			e.setCancelled(true);
			m.ci.menu(e);
			
			if (Click.hasItemMeta() && Click.getItemMeta().hasDisplayName()) {
				if (Click.getType() == Material.WOOL && !start) {
					bj.start.put(p, true);
					m.gi.setItem(i, 52, Material.STONE_BUTTON, 1, 0, "��r��ο�", null);
					m.gi.setItem(i, 53, Material.WOOD_BUTTON, 1, 0, "��r��ž", null);
					ArrayList<String> Deck = bj.Deck;
					String[] player = new String[4];
					String dealer = "";
					String[] drawdealer = new String[2];
					int playerdeck = 0, dealerdeck = 0;
					int draw = r.nextInt(Deck.size());
					
					player[0] = Deck.get(draw);
					String st = player[0].split(" ")[1];
					playerdeck += returnNumber(st);
					Deck.remove(draw);
					
					draw = r.nextInt(Deck.size());
					drawdealer[0] = Deck.get(draw);
					st = drawdealer[0].split(" ")[1];
					dealerdeck += returnNumber(st);
					Deck.remove(draw);
					
					draw = r.nextInt(Deck.size());
					player[1] = Deck.get(draw);
					st = player[1].split(" ")[1];
					playerdeck += returnNumber(st);
					Deck.remove(draw);
					
					draw = r.nextInt(Deck.size());
					drawdealer[1] = Deck.get(draw);
					st = drawdealer[1].split(" ")[1];
					dealerdeck += returnNumber(st);
					Deck.remove(draw);
					
					player[3] = "��r���� ��: "+playerdeck;
					dealer = "��r���� ��: "+dealerdeck;
					m.gi.setItem(i, 31, Material.PAPER, 1, 0, "��r��", new String[] {"��r���� �ż�: "+Deck.size()});
					
					m.gi.setItem(i, 22, Material.PAPER, 1, 0, "��r������ ��", new String[] {dealer});
					m.gi.setItem(i, 40, Material.PAPER, 1, 0, "��r���� ��", player);
					
					bj.gamedeck.put(p, Deck);
					
					if (playerdeck == 21) {
						if (i.getItem(40).getLore().get(0).split(" ")[1].equals("J") || i.getItem(40).getLore().get(0).split(" ")[1].equals("Q") || i.getItem(40).getLore().get(0).split(" ")[1].equals("K") ||
								i.getItem(40).getLore().get(1).split(" ")[1].equals("J") || i.getItem(40).getLore().get(1).split(" ")[1].equals("Q") || i.getItem(40).getLore().get(1).split(" ")[1].equals("K")) {
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� ����!");
							Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� ����! ���� ������ ȹ���ϼ̽��ϴ�!");
							reset(e);
						} else {
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r 21 �ϼ�!");
							Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��e��l"+p.getName()+"��r���� ���� 21 ������ ȹ���ϼ̽��ϴ�.");
							reset(e);
						}
					}
					else if (dealerdeck == 21) {
						if (drawdealer[0].split(" ")[1].equals("J") || drawdealer[0].split(" ")[1].equals("Q") || drawdealer[0].split(" ")[1].equals("K") ||
								drawdealer[1].split(" ")[1].equals("J") || drawdealer[1].split(" ")[1].equals("Q") || drawdealer[1].split(" ")[1].equals("K")) {
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��l������r�� ����!");
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ����� �й��Դϴ�.");
							reset(e);
						}
						else {
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��l������r�� 21!");
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ����� �й��Դϴ�.");
							reset(e);
						}
					}
				} else if (Click.getType() == Material.STONE_BUTTON && start) {
					List<String> players = i.getItem(40).getItemMeta().getLore();
					ArrayList<String> Deck = bj.gamedeck.get(p);
					int draw = r.nextInt(Deck.size());
					players.set(2, Deck.get(draw));
					int playercount = Integer.parseInt(players.get(3).split(" ")[2]);
					playercount += returnNumber(Deck.get(draw).split(" ")[1]);
					Deck.remove(draw);
					p.sendMessage("��6[��9�̸� ���ӡ�6]��r �ڽ��� ��: "+players.get(0)+"��r, "+players.get(1)+"��r, "+players.get(2)+" ��r����: "+playercount);
					if (playercount >= 22) {
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ����� ������ 22 �̻��Դϴ�. ����� �й��Դϴ�.");
						reset(e);
						return;
					}
					
					int dealer = Integer.parseInt(i.getItem(22).getItemMeta().getLore().get(0).split(" ")[2]);
					if (dealer <= 16) {
						draw = r.nextInt(Deck.size());
						dealer += returnNumber(Deck.get(draw).split(" ")[1]);
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ������ ���� ��: "+dealer);
					} else {
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ������ ���� ��: "+dealer);
					}
					if (dealer >= 22) {
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ������ ������ 22 �̻��Դϴ�. ����� �¸��Դϴ�! ���� "+playercount+" ������ ȹ���մϴ�.");
						reset(e);
						return;
					}
					
					if (dealer < playercount) {
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ����� �¸��Դϴ�! ���� "+playercount+" ������ ȹ���մϴ�.");
					} else {
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ����� �й��Դϴ�.");
					}
					reset(e);
				} else if (Click.getType() == Material.WOOD_BUTTON && start) {
					int playercount = Integer.parseInt(i.getItem(40).getItemMeta().getLore().get(3).split(" ")[2]);
					int dealercount = Integer.parseInt(i.getItem(22).getItemMeta().getLore().get(0).split(" ")[2]);
					
					p.sendMessage("��6[��9�̸� ���ӡ�6]��r �ڽ��� ��: "+i.getItem(40).getItemMeta().getLore().get(0)+"��r, "+i.getItem(40).getItemMeta().getLore().get(1)+"��r ����: "+playercount);
					p.sendMessage("��6[��9�̸� ���ӡ�6]��r ������ ���� ��: "+dealercount);
					if (dealercount < playercount) {
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ����� �¸��Դϴ�! ���� "+playercount+" ������ ȹ���մϴ�.");
					} else {
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ����� �й��Դϴ�.");
					}
					reset(e);
				}
			}
		}
	}
	
	public void reset(InventoryClickEvent e) {
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		ItemStack gold = i.getItem(45);
		bj.deckReset();
		m.gi.setMenu(i);
		m.gi.setItem(i, 22, Material.PAPER, 1, 0, "��r������ ��", null);
		m.gi.setItem(i, 31, Material.PAPER, 1, 0, "��r��", new String[] {"��r���� �ż�: "+bj.Deck.size()});
		m.gi.setItem(i, 40, Material.PAPER, 1, 0, "��r���� ��", new String[] {"��r���� ��: 0"});
		i.setItem(45, gold);
		m.gi.setItem(i, 46, Material.BOOK,1,0,"��r����",new String[]{"��r�� 1. ���ʿ� ��ȭ�� ���´�.", "��r�� 2. ������ ������ ������.", "��r�� 3. �ڽŰ� ������ ���� 2�徿 �޴´�.", "��r�� ������ 21�� ����������� ī�带 �̰ų� ����.", "��r�� 21�� ������ �й�. �� �����Ͻ� ������ ���ڸ� ��.", "��r�� �������� ���ڰ� Ŭ �� �¸�, ���ų� ������ �й�"});
		m.gi.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
		bj.start.put(p, false);
		bj.gamedeck.put(p, null);
	}
	
	public int returnNumber(String before) {
		int a = 0;
		if (before.equals("2")) {
			a = 2;
		} else if (before.equals("3")) {
			a = 3;
		} else if (before.equals("4")) {
			a = 4;
		} else if (before.equals("5")) {
			a = 5;
		} else if (before.equals("6")) {
			a = 6;
		} else if (before.equals("7")) {
			a = 7;
		} else if (before.equals("8")) {
			a = 8;
		} else if (before.equals("9")) {
			a = 9;
		} else if (before.equals("10") || before.equals("J") || before.equals("Q") || before.equals("K")) {
			a = 10;
		} else if (before.equals("A")) {
			a = 11;
		} 
		
		return a;
	}
}
