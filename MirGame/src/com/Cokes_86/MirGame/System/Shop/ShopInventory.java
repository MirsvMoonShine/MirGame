package com.Cokes_86.MirGame.System.Shop;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.Cokes_86.MirGame.MirGame;

public class ShopInventory implements Listener{
	MirGame m;
	
	public ShopInventory(MirGame m) {
		this.m = m;
	}
	
	public void openCoinShop(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 상점");
		
		m.gi.setMenu(i);
		//재화
		m.u.setItem(i, 18, Material.GOLD_NUGGET, 1, 0, "§r금화 1개 구입", new String[]{"§r⇒ 좌클릭시 금화 1개 구입","§r⇒ 가격: 1000원"});
		m.u.setItem(i, 19, Material.GOLD_NUGGET, 10, 0, "§r금화 10개 구입", new String[]{"§r⇒ 좌클릭시 금화 10개 구입","§r⇒ 가격: 9500원"});
		m.u.setItem(i, 20, Material.GOLD_NUGGET, 64, 0, "§r금화 64개 구입", new String[]{"§r⇒ 좌클릭시 금화 64개 구입","§r⇒ 가격: 60000원"});
		m.u.setItem(i, 21, Material.IRON_NUGGET, 1, 0, "§r초월석 구입", new String[]{"§r⇒ 좌클릭시 초월석 구입", "§r⇒ 가격: 캡슐 5개"});
		m.u.setItem(i, 22, Material.SLIME_BALL, 10, 0, "§r완두콩 10개 구입", new String[]{"§r⇒ 좌클릭시 완두콩 10개 구입", "§r⇒ 가격: 캡슐 1개"});
		m.u.setItem(i, 23, Material.EYE_OF_ENDER, 1, 0, "§r캡슐 구입", new String[]{"§r⇒ 좌클릭시 캡슐 구입", "§r⇒ 가격: 완두콩 10개"});
		m.u.setItem(i, 24, Material.STICK, 1, 0, "§r도구 조각 구입", new String[]{"§r⇒ 좌클릭시 도구 조각 구입", "§r⇒ 가격: 조잡한 섬유 1개", "§r⇒ 갑옷 제작 철회로 인해 추가"});
		//도구
		m.u.setItem(i, 27, Material.DIAMOND_SWORD, 1, 0, "§r§4봉인된 드래곤 슬레이어 §r구입", new String[]{"§r⇒ 좌클릭시 봉인된 드래곤 슬레이어 구입", "§r⇒ 가격: 캡슐 15개, 완두콩 60개"});
		m.u.setItem(i, 28, Material.DIAMOND_SWORD, 1, 0, "§r커먼 수박아저씨의 칼 구입", new String[]{"§r⇒ 좌클릭시 커먼 수박아저씨의 칼 구입", "§r⇒ 가격: 완두콩 20개"});
		m.u.setItem(i, 29, Material.BOW,1 ,0, "§r커먼 사냥터지기의 활 구입",new String[]{"§r⇒ 좌클릭시 커먼 사냥터지기의 활 구입", "§r⇒ 가격: 완두콩 30개"});
		//초월
		m.u.setPotionItem(i, 36, 1, "§r초월석 보호 물약 구입", new String[] {"§r⇒ 좌클릭시 초월석 보호 물약 구입", "§r⇒ 가격: 완두콩 30개"}, Color.AQUA);
		m.u.setPotionItem(i, 37, 1, "§r확률 상승 물약 구입", new String[] {"§r⇒ 좌클릭시 확률 상승 물약 구입", "§r⇒ 가격: 완두콩 50개"}, Color.WHITE);
		//기타
		m.u.setItem(i, 45, Material.WORKBENCH, 1, 0, "§r합성", new String[]{"§r⇒ 좌클릭시 합성 창으로 이동"});
		m.u.setItem(i, 46, Material.ANVIL, 1, 0, "§r초월", new String[]{"§r⇒ 좌클릭시 초월 창으로 이동"});
		m.u.setItem(i, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
		
		if (p.getName().equals("Cokes_86")){ //테스트 물품
			//재화
			
			//도구
			
			//초월
			
			//기타
			
		}
		
		p.openInventory(i);
	}
	
	public boolean ShopRecipe() {
		return false;
	}
	
	public void getItemUsingVault(Player p, ItemStack get, double money) {
		if (m.eco.getBalance(p) >= money){
			Inventory playerinv = p.getInventory();
			m.eco.withdrawPlayer(p, money);
			p.sendMessage("§6[§9미르 게임§6]§r "+get.getItemMeta().getDisplayName()+" §r"+get.getAmount()+"개를 구입하였습니다.");
			playerinv.addItem(get);
		} else {
			p.sendMessage("§6[§9미르 게임§6]§r 돈이 부족합니다.");
		}
	}
	
	public void getItemUsingIngradient(Player p, ItemStack get, ItemStack[] input) {
		List<ItemStack> list = Arrays.asList(input);
		boolean[] ok = new boolean[list.size()];
		int[] stack = new int[list.size()];
		boolean result = true;
		Inventory i = p.getInventory();
		for (int a=0;a<list.size();a++) {
			ok[a] = false; stack[a] = 0;
		}
		for (int s=0;s<36;s++){
			ItemStack is = i.getItem(s);
			if (is != null) {
				for (int a = 0;a<list.size();a++) {
					if (!ok[a] && is.hasItemMeta() && is.getItemMeta().hasDisplayName() && is.getItemMeta().getDisplayName().equals(list.get(a).getItemMeta().getDisplayName()) && is.getAmount() >= list.get(a).getAmount()) {
						ok[a] = true; stack[a] = s;
					}
				}
			}
		}
		for (int a=0;a<list.size();a++) {
			if (!ok[a]) result = false;
		}
		
		if (result) {
			for (int a=0;a<list.size();a++) {
				if (i.getItem(stack[a]).getAmount() == list.get(a).getAmount()) i.setItem(stack[a],null);
				else {
					ItemStack o = i.getItem(stack[a]);
					ItemStack bak = list.get(a);
					bak.setAmount(o.getAmount() - list.get(a).getAmount());
					i.setItem(stack[a], bak);
				}
			}
			
			p.sendMessage("§6[§9미르 게임§6]§r "+get.getItemMeta().getDisplayName()+" §r"+get.getAmount()+"개를 구입하였습니다.");
			i.addItem(get);
		} else {
			p.sendMessage("§6[§9미르 게임§6]§r 재료가 부족합니다.");
		}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (inv == null) { return; }
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 상점"))){
			e.setCancelled(true);
			m.ci.menu(e);
			if (Click.hasItemMeta() && Click.getItemMeta().hasDisplayName()) {
				
				if (Click.getType() == Material.GOLD_NUGGET){
					if (Click.getItemMeta().getDisplayName().equals("§r금화 1개 구입")){
						getItemUsingVault(p,m.gc.getCoin(1),1000);
						m.u.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
					} else if (Click.getItemMeta().getDisplayName().equals("§r금화 10개 구입")){
						getItemUsingVault(p,m.gc.getCoin(10),9500);
						m.u.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
					} else if (Click.getItemMeta().getDisplayName().equals("§r금화 64개 구입")){
						getItemUsingVault(p,m.gc.getCoin(64),60000);
						m.u.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
					}
				}
				else if (Click.getType() == Material.WORKBENCH){
					m.mr.fug.openReadyFusion(p);
				}
				else if (Click.getType() == Material.ANVIL){
					m.mr.fug.openReadyUpgrade(p);
				}
				else if (Click.getType() == Material.IRON_NUGGET){
					getItemUsingIngradient(p, m.mr.gr.UpgradeStone(1), new ItemStack[] {m.gc.getEye(5)});
				}
				else if (Click.getType() == Material.BOW){
					getItemUsingIngradient(p, m.mr.gr.CrokersBow(0), new ItemStack[] {m.gc.getBean(30)});
				}
				else if (Click.getType() == Material.DIAMOND_SWORD){
					if (Click.getItemMeta().getDisplayName().equals("§r§4봉인된 드래곤 슬레이어 §r구입")){
						getItemUsingIngradient(p, m.mr.gr.DragonSlayer(0), new ItemStack[] {m.gc.getEye(15), m.gc.getBean(60)});
					} else if (Click.getItemMeta().getDisplayName().equals("§r커먼 수박아저씨의 칼 구입")){
						getItemUsingIngradient(p, m.mr.gr.WatermelonSword(0), new ItemStack[] {m.gc.getBean(20)});
					}
				}
				else if (Click.getItemMeta().getDisplayName().equals("§r완두콩 10개 구입")) {
					getItemUsingIngradient(p, m.gc.getBean(10), new ItemStack[] {m.gc.getEye(1)});
				}
				else if (Click.getItemMeta().getDisplayName().equals("§r캡슐 구입")) {
					getItemUsingIngradient(p, m.gc.getEye(1), new ItemStack[] {m.gc.getBean(10)});
				}
				else if (Click.getType() == Material.POTION) {
					if (Click.getItemMeta().getDisplayName().equals("§r초월석 보호 물약 구입")) {
						getItemUsingIngradient(p, m.mr.gr.RerollStone(), new ItemStack[] {m.gc.getBean(30)});
					} else if (Click.getItemMeta().getDisplayName().equals("§r확률 상승 물약 구입")) {
						getItemUsingIngradient(p, m.mr.gr.SuccessUp(), new ItemStack[] {m.gc.getBean(50)});
					}
				}
				else if (Click.getType() == Material.STICK) {
					getItemUsingIngradient(p, m.gc.getTool(1), new ItemStack[] {m.gc.getCloth(1)});
				}
			}
		}
	}
}
