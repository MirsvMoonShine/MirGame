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
		if (i.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 블랙잭"))){
			boolean start = bj.start.getOrDefault(p, false);
			e.setCancelled(true);
			m.ci.menu(e);
			
			if (Click.hasItemMeta() && Click.getItemMeta().hasDisplayName()) {
				if (Click.getType() == Material.WOOL && !start) {
					bj.start.put(p, true);
					m.gi.setItem(i, 52, Material.STONE_BUTTON, 1, 0, "§r드로우", null);
					m.gi.setItem(i, 53, Material.WOOD_BUTTON, 1, 0, "§r스탑", null);
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
					
					player[3] = "§r숫자 합: "+playerdeck;
					dealer = "§r숫자 합: "+dealerdeck;
					m.gi.setItem(i, 31, Material.PAPER, 1, 0, "§r덱", new String[] {"§r남은 매수: "+Deck.size()});
					
					m.gi.setItem(i, 22, Material.PAPER, 1, 0, "§r상대방의 패", new String[] {dealer});
					m.gi.setItem(i, 40, Material.PAPER, 1, 0, "§r나의 패", player);
					
					bj.gamedeck.put(p, Deck);
					
					if (playerdeck == 21) {
						if (i.getItem(40).getLore().get(0).split(" ")[1].equals("J") || i.getItem(40).getLore().get(0).split(" ")[1].equals("Q") || i.getItem(40).getLore().get(0).split(" ")[1].equals("K") ||
								i.getItem(40).getLore().get(1).split(" ")[1].equals("J") || i.getItem(40).getLore().get(1).split(" ")[1].equals("Q") || i.getItem(40).getLore().get(1).split(" ")[1].equals("K")) {
							p.sendMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님의 블랙잭!");
							Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님의 블랙잭! 블랙잭 보상을 획득하셨습니다!");
							reset(e);
						} else {
							p.sendMessage("§6[§9미르 게임§6]§r 21 완성!");
							Bukkit.broadcastMessage("§6[§9미르 게임§6]§r §e§l"+p.getName()+"§r님이 블랙잭 21 보상을 획득하셨습니다.");
							reset(e);
						}
					}
					else if (dealerdeck == 21) {
						if (drawdealer[0].split(" ")[1].equals("J") || drawdealer[0].split(" ")[1].equals("Q") || drawdealer[0].split(" ")[1].equals("K") ||
								drawdealer[1].split(" ")[1].equals("J") || drawdealer[1].split(" ")[1].equals("Q") || drawdealer[1].split(" ")[1].equals("K")) {
							p.sendMessage("§6[§9미르 게임§6]§r §e§l딜러§r의 블랙잭!");
							p.sendMessage("§6[§9미르 게임§6]§r 당신의 패배입니다.");
							reset(e);
						}
						else {
							p.sendMessage("§6[§9미르 게임§6]§r §e§l딜러§r의 21!");
							p.sendMessage("§6[§9미르 게임§6]§r 당신의 패배입니다.");
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
					p.sendMessage("§6[§9미르 게임§6]§r 자신의 패: "+players.get(0)+"§r, "+players.get(1)+"§r, "+players.get(2)+" §r총합: "+playercount);
					if (playercount >= 22) {
						p.sendMessage("§6[§9미르 게임§6]§r 당신의 총합이 22 이상입니다. 당신의 패배입니다.");
						reset(e);
						return;
					}
					
					int dealer = Integer.parseInt(i.getItem(22).getItemMeta().getLore().get(0).split(" ")[2]);
					if (dealer <= 16) {
						draw = r.nextInt(Deck.size());
						dealer += returnNumber(Deck.get(draw).split(" ")[1]);
						p.sendMessage("§6[§9미르 게임§6]§r 딜러의 숫자 합: "+dealer);
					} else {
						p.sendMessage("§6[§9미르 게임§6]§r 딜러의 숫자 합: "+dealer);
					}
					if (dealer >= 22) {
						p.sendMessage("§6[§9미르 게임§6]§r 딜러의 총합이 22 이상입니다. 당신의 승리입니다! 블랙잭 "+playercount+" 보상을 획득합니다.");
						reset(e);
						return;
					}
					
					if (dealer < playercount) {
						p.sendMessage("§6[§9미르 게임§6]§r 당신의 승리입니다! 블랙잭 "+playercount+" 보상을 획득합니다.");
					} else {
						p.sendMessage("§6[§9미르 게임§6]§r 당신의 패배입니다.");
					}
					reset(e);
				} else if (Click.getType() == Material.WOOD_BUTTON && start) {
					int playercount = Integer.parseInt(i.getItem(40).getItemMeta().getLore().get(3).split(" ")[2]);
					int dealercount = Integer.parseInt(i.getItem(22).getItemMeta().getLore().get(0).split(" ")[2]);
					
					p.sendMessage("§6[§9미르 게임§6]§r 자신의 패: "+i.getItem(40).getItemMeta().getLore().get(0)+"§r, "+i.getItem(40).getItemMeta().getLore().get(1)+"§r 총합: "+playercount);
					p.sendMessage("§6[§9미르 게임§6]§r 딜러의 숫자 합: "+dealercount);
					if (dealercount < playercount) {
						p.sendMessage("§6[§9미르 게임§6]§r 당신의 승리입니다! 블랙잭 "+playercount+" 보상을 획득합니다.");
					} else {
						p.sendMessage("§6[§9미르 게임§6]§r 당신의 패배입니다.");
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
		m.gi.setItem(i, 22, Material.PAPER, 1, 0, "§r상대방의 패", null);
		m.gi.setItem(i, 31, Material.PAPER, 1, 0, "§r덱", new String[] {"§r남은 매수: "+bj.Deck.size()});
		m.gi.setItem(i, 40, Material.PAPER, 1, 0, "§r나의 패", new String[] {"§r숫자 합: 0"});
		i.setItem(45, gold);
		m.gi.setItem(i, 46, Material.BOOK,1,0,"§r도움말",new String[]{"§r⇒ 1. 왼쪽에 금화를 놓는다.", "§r⇒ 2. 오른쪽 양털을 누른다.", "§r⇒ 3. 자신과 딜러는 각각 2장씩 받는다.", "§r⇒ 총합이 21에 가까워지도록 카드를 뽑거나 멈춤.", "§r⇒ 21을 넘을시 패배. 그 이하일시 딜러와 숫자를 비교.", "§r⇒ 딜러보다 숫자가 클 시 승리, 같거나 작으면 패배"});
		m.gi.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
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
