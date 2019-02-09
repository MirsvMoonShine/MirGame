package com.Cokes_86.MirGame.System.Slot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.Cokes_86.MirGame.MirGame;

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
	
	public void openOldSlot(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - (구)슬롯");
		
		setMenu(i);
		m.u.setItem(i, 29, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 31, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 33, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		m.u.setItem(i, 46, Material.BOOK,1,0,"§r도움말",new String[]{"§r⇒ 1. 왼쪽에 금화를 놓는다.", "§r⇒ 2. 오른쪽 양털을 누른다.", "§r⇒ 3. 위쪽에 다이아가 3개 나오면 성공.", "§r⇒ 금화 외 다른 아이템을 넣으면 작동 안됨.", "§r⇒ 금화를 넣은 체 종료시 사라짐."});
		
		m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
		m.u.setItem(i, 52, Material.ANVIL,1,0,"§r1회 실행",new String[]{"§r⇒ 좌클릭시 1회 실행, 반복 실행 설정 가능"});
		
		p.openInventory(i);
	}
	
	public void openSlot(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 슬롯");
		
		setMenu(i);
		m.u.setItem(i, 28, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 30, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 32, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 34, Material.STAINED_GLASS_PANE,1,0," ",null);
		m.u.setItem(i, 45, Material.BARRIER,1,0," ",null);
		i.remove(Material.BARRIER);
		m.u.setItem(i, 46, Material.BOOK,1,0,"§r도움말",new String[]{"§r⇒ 1. 왼쪽에 금화를 놓는다.", "§r⇒ 2. 오른쪽 양털을 누른다.", "§r⇒ 3. 위쪽에 같은 아이템이 3개 나오면 성공.", "§r⇒ 금화 외 다른 아이템을 넣으면 작동 안됨.", "§r⇒ 금화를 넣은 체 종료시 사라짐."});
		
		m.u.setItem(i, 53, Material.WOOL,1,13,"§r§l시작",null);
		m.u.setItem(i, 52, Material.ANVIL,1,0,"§r1회 실행",new String[]{"§r⇒ 좌클릭시 1회 실행, 반복 실행 설정 가능"});
		
		p.openInventory(i);
	}
	
	public void openRewards(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 보상 목록");
		
		setMenu(i);
		
		for (int a= 0; a<m.boxs.size();a++){
			i.setItem(a+9, m.boxs.get(a).getBox(p));
		}
		
		p.openInventory(i);
	}
	
	public void setMenu(Inventory i){
		ItemStack glass1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)1);
		ItemMeta glass1m = glass1.getItemMeta();
		glass1m.setDisplayName("§6[§9미르 게임§6]");
		glass1.setItemMeta(glass1m);
		
		ItemStack glass2 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)3);
		ItemMeta glass2m = glass2.getItemMeta();
		glass2m.setDisplayName("§6[§9미르 게임§6]");
		glass2.setItemMeta(glass2m);
		
		ItemStack glass3 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)0);
		ItemMeta glass3m =  glass3.getItemMeta();
		glass3m.setDisplayName("§6[§9미르 게임§6]");
		glass3.setItemMeta(glass3m);
		
		for (int a = 0;a<9;a++){
			i.setItem(a, glass3);
		}
		
		i.setItem(9, glass1); i.setItem(11, glass1); i.setItem(13, glass1); i.setItem(15, glass1); i.setItem(17, glass1);
		i.setItem(10, glass2); i.setItem(12, glass2); i.setItem(14, glass2); i.setItem(16, glass2);
		i.setItem(45, glass1); i.setItem(47, glass1); i.setItem(49, glass1); i.setItem(51, glass1); i.setItem(53, glass1);
		i.setItem(46, glass2); i.setItem(48, glass2); i.setItem(50, glass2); i.setItem(52, glass2);
		for (int a = 18;a<45;a++){
			m.u.setItem(i,a, Material.STAINED_GLASS_PANE,1,10,"§6[§9미르 게임§6]",null);
		}
		
		m.u.setItem(i, 0, Material.CHEST, 1, 0, "§r- 미르 게임 상점 -", new String[]{"§r⇒ 좌클릭시 미르 게임 상점으로 이동합니다."} );
		m.u.setItem(i, 1, Material.DIAMOND, 1, 0, "§a- (구)슬롯 -", new String[]{"§r⇒ 좌클릭시 (구)슬롯으로 이동합니다."});
		m.u.setItem(i, 2, Material.NETHER_STAR, 1, 0, "§b- 슬롯 -", new String[]{"§r⇒ 좌클릭시 슬롯으로 이동합니다."});
		m.u.setItem(i, 3, Material.REDSTONE_LAMP_OFF, 1, 0, "§2- 슬라이딩 -", new String[]{"§r⇒ 좌클릭시 슬라이딩으로 이동합니다."});
		m.u.setItem(i, 4, Material.WOOL, 1, 0, "§4- 블랙잭 -", new String[]{"§r⇒ 좌클릭시 블랙잭으로 이동합니다.","§4⇒ 점검중. 이용불가."});
		m.u.setItem(i, 7, Material.ANVIL, 1, 0, "§r- 설정 -", new String[]{"§r⇒ 좌클릭시 설정으로 이동합니다."});
		m.u.setItem(i, 8, Material.TRAPPED_CHEST, 1, 0, "§r- 미르 게임 보상 목록 -", new String[]{"§r⇒ 좌클릭시 미르 게임 보상 목록으로 이동합니다."});
	}
}
