package com.Cokes_86.MirGame;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameInventory {
	
    final MirGame m;
	
	public GameInventory (MirGame main){
		this.m = main;
	}
	
	public void openMain(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임");
		
		setMenu(i);
		
		p.openInventory(i);
	}
	
	public void openCoinShop(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 코인상점");
		
		setMenu(i);
		setItem(i, 18, Material.GOLD_NUGGET, 1, 0, "§r금화 1개 구입", new String[]{"§r⇒ 좌클릭시 금화 1개 구입","§r⇒ 가격: 1000원"});
		setItem(i, 19, Material.GOLD_NUGGET, 10, 0, "§r금화 10개 구입", new String[]{"§r⇒ 좌클릭시 금화 10개 구입","§r⇒ 가격: 9500원"});
		setItem(i, 20, Material.GOLD_NUGGET, 64, 0, "§r금화 64개 구입", new String[]{"§r⇒ 좌클릭시 금화 64개 구입","§r⇒ 가격: 60000원"});
		
		setItem(i, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
		
		p.openInventory(i);
	}
	
	public void openOldSlot(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - (구)슬롯");
		
		setMenu(i);
		setItem(i, 29, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 31, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 33, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		setItem(i, 46, Material.BOOK,1,0,"§r도움말",new String[]{"§r⇒ 1. 왼쪽에 금화를 놓는다.", "§r⇒ 2. 오른쪽 양털을 누른다.", "§r⇒ 3. 위쪽에 다이아가 3개 나오면 성공.", "§r⇒ 금화 외 다른 아이템을 넣으면 작동 안됨.", "§r⇒ 금화를 넣은 체 종료시 사라짐."});
		
		setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
		setItem(i, 52, Material.ANVIL,1,0,"§r1회 실행",new String[]{"§r⇒ 좌클릭시 1회 실행, 반복 실행 설정 가능"});
		
		p.openInventory(i);
	}
	
	public void openSlot(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 슬롯");
		
		setMenu(i);
		setItem(i, 29, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 31, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 33, Material.STAINED_GLASS_PANE,1,0," ",null);
		setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		setItem(i, 46, Material.BOOK,1,0,"§r도움말",new String[]{"§r⇒ 1. 왼쪽에 금화를 놓는다.", "§r⇒ 2. 오른쪽 양털을 누른다.", "§r⇒ 3. 위쪽에 같은 아이템이 3개 나오면 성공.", "§r⇒ 금화 외 다른 아이템을 넣으면 작동 안됨.", "§r⇒ 금화를 넣은 체 종료시 사라짐."});
		
		setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
		setItem(i, 52, Material.ANVIL,1,0,"§r1회 실행",new String[]{"§r⇒ 좌클릭시 1회 실행, 반복 실행 설정 가능"});
		
		p.openInventory(i);
	}
	
	public void setMenu(Inventory i){
		ItemStack glass1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)1);
		ItemMeta glass1m = glass1.getItemMeta();
		glass1m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6[&9미르 게임&6]"));
		glass1.setItemMeta(glass1m);
		
		ItemStack glass2 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)3);
		ItemMeta glass2m = glass2.getItemMeta();
		glass2m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6[&9미르 게임&6]"));
		glass2.setItemMeta(glass2m);
		
		i.setItem(9, glass1); i.setItem(11, glass1); i.setItem(13, glass1); i.setItem(15, glass1); i.setItem(17, glass1);
		i.setItem(10, glass2); i.setItem(12, glass2); i.setItem(14, glass2); i.setItem(16, glass2);
		i.setItem(45, glass1); i.setItem(47, glass1); i.setItem(49, glass1); i.setItem(51, glass1); i.setItem(53, glass1);
		i.setItem(46, glass2); i.setItem(48, glass2); i.setItem(50, glass2); i.setItem(52, glass2);
		for (int a = 18;a<45;a++){
			setItem(i,a, Material.STAINED_GLASS_PANE,1,10,"§6[§9미르 게임§6]",null);
		}
		
		setItem(i, 0, Material.CHEST, 1, 0, "§r- 코인상점 -", new String[]{"§r⇒ 좌클릭시 코인 상점으로 이동합니다."} );
		setItem(i, 1, Material.DIAMOND, 1, 0, "§a- (구)슬롯 -", new String[]{"§r⇒ 좌클릭시 (구)슬롯으로 이동합니다."});
		setItem(i, 2, Material.NETHER_STAR, 1, 0, "§b- 슬롯 -", new String[]{"§r⇒ 좌클릭시 슬롯으로 이동합니다."});
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
}
