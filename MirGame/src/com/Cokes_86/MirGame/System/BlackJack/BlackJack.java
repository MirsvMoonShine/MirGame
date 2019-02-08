package com.Cokes_86.MirGame.System.BlackJack;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.Cokes_86.MirGame.MirGame;

public class BlackJack {
	MirGame m;
	ArrayList<String> Deck = new ArrayList<>();
	public HashMap<Player, Boolean> start = new HashMap<>();
	HashMap<Player, ArrayList<String>> gamedeck = new HashMap<>();
	
	public BlackJack (MirGame m) {
		this.m = m;
		
		deckReset();
	}
	
	public void deckReset() {
		Deck.clear();
		Deck.add("§r♠ A");
		Deck.add("§r♣ A");
		Deck.add("§4♥ A");
		Deck.add("§4◆ A");
		for (int a = 2;a<11;a++) {
			Deck.add("§r♠ "+a);
			Deck.add("§r♣ "+a);
			Deck.add("§4♥ "+a);
			Deck.add("§4◆ "+a);
		}
		Deck.add("§r♠ J");
		Deck.add("§r♣ J");
		Deck.add("§4♥ J");
		Deck.add("§4◆ J");
		Deck.add("§r♠ Q");
		Deck.add("§r♣ Q");
		Deck.add("§4♥ Q");
		Deck.add("§4◆ Q");
		Deck.add("§r♠ K");
		Deck.add("§r♣ K");
		Deck.add("§4♥ K");
		Deck.add("§4◆ K");
	}
	
	public void openBlackJack(Player p) {
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 블랙잭");
		
		deckReset();
		m.gi.setMenu(i);
		m.u.setItem(i, 22, Material.PAPER, 1, 0, "§r상대방의 패", null);
		m.u.setItem(i, 31, Material.PAPER, 1, 0, "§r덱", new String[] {"§r남은 매수: "+Deck.size()});
		m.u.setItem(i, 40, Material.PAPER, 1, 0, "§r나의 패", new String[] {"§r숫자 합: 0"});
		m.u.setItem(i, 45, Material.BARRIER,1,0," ",null);
		m.u.setItem(i, 46, Material.BOOK,1,0,"§r도움말",new String[]{"§r⇒ 1. 왼쪽에 금화를 놓는다.", "§r⇒ 2. 오른쪽 양털을 누른다.", "§r⇒ 3. 자신과 딜러는 각각 2장씩 받는다.", "§r⇒ 총합이 21에 가까워지도록 카드를 뽑거나 멈춤.", "§r⇒ 21을 넘을시 패배. 그 이하일시 딜러와 숫자를 비교.", "§r⇒ 딜러보다 숫자가 클 시 승리, 같거나 작으면 패배"});
		m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
		i.remove(Material.BARRIER);
		
		p.openInventory(i);
	}
}
