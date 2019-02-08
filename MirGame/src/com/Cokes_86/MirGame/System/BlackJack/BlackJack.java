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
		Deck.add("��r�� A");
		Deck.add("��r�� A");
		Deck.add("��4�� A");
		Deck.add("��4�� A");
		for (int a = 2;a<11;a++) {
			Deck.add("��r�� "+a);
			Deck.add("��r�� "+a);
			Deck.add("��4�� "+a);
			Deck.add("��4�� "+a);
		}
		Deck.add("��r�� J");
		Deck.add("��r�� J");
		Deck.add("��4�� J");
		Deck.add("��4�� J");
		Deck.add("��r�� Q");
		Deck.add("��r�� Q");
		Deck.add("��4�� Q");
		Deck.add("��4�� Q");
		Deck.add("��r�� K");
		Deck.add("��r�� K");
		Deck.add("��4�� K");
		Deck.add("��4�� K");
	}
	
	public void openBlackJack(Player p) {
		Inventory i = Bukkit.createInventory(null, 54, "��l�̸� ���� - ����");
		
		deckReset();
		m.gi.setMenu(i);
		m.u.setItem(i, 22, Material.PAPER, 1, 0, "��r������ ��", null);
		m.u.setItem(i, 31, Material.PAPER, 1, 0, "��r��", new String[] {"��r���� �ż�: "+Deck.size()});
		m.u.setItem(i, 40, Material.PAPER, 1, 0, "��r���� ��", new String[] {"��r���� ��: 0"});
		m.u.setItem(i, 45, Material.BARRIER,1,0," ",null);
		m.u.setItem(i, 46, Material.BOOK,1,0,"��r����",new String[]{"��r�� 1. ���ʿ� ��ȭ�� ���´�.", "��r�� 2. ������ ������ ������.", "��r�� 3. �ڽŰ� ������ ���� 2�徿 �޴´�.", "��r�� ������ 21�� ����������� ī�带 �̰ų� ����.", "��r�� 21�� ������ �й�. �� �����Ͻ� ������ ���ڸ� ��.", "��r�� �������� ���ڰ� Ŭ �� �¸�, ���ų� ������ �й�"});
		m.u.setItem(i, 53, Material.WOOL,1,13,"��r��l����",null);
		i.remove(Material.BARRIER);
		
		p.openInventory(i);
	}
}
