package com.Cokes_86.MirGame.System.Shop;

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

public class ShopInventory implements Listener{
	MirGame m;
	
	public ShopInventory(MirGame m) {
		this.m = m;
	}
	
	public void openCoinShop(Player p){
		Inventory i = Bukkit.createInventory(null, 54, "§l미르 게임 - 상점");
		
		m.gi.setMenu(i);
		//재화
		m.gi.setItem(i, 18, Material.GOLD_NUGGET, 1, 0, "§r금화 1개 구입", new String[]{"§r⇒ 좌클릭시 금화 1개 구입","§r⇒ 가격: 1000원"});
		m.gi.setItem(i, 19, Material.GOLD_NUGGET, 10, 0, "§r금화 10개 구입", new String[]{"§r⇒ 좌클릭시 금화 10개 구입","§r⇒ 가격: 9500원"});
		m.gi.setItem(i, 20, Material.GOLD_NUGGET, 64, 0, "§r금화 64개 구입", new String[]{"§r⇒ 좌클릭시 금화 64개 구입","§r⇒ 가격: 60000원"});
		m.gi.setItem(i, 21, Material.IRON_NUGGET, 1, 0, "§r초월석 구입", new String[]{"§r⇒ 좌클릭시 초월석 구입", "§r⇒ 가격: 캡슐 5개"});
		m.gi.setItem(i, 22, Material.SLIME_BALL, 10, 0, "§r완두콩 10개 구입", new String[]{"§r⇒ 좌클릭시 완두콩 10개 구입", "§r⇒ 가격: 캡슐 1개"});
		m.gi.setItem(i, 23, Material.EYE_OF_ENDER, 1, 0, "§r캡슐 구입", new String[]{"§r⇒ 좌클릭시 캡슐 구입", "§r⇒ 가격: 완두콩 10개"});
		//도구
		m.gi.setItem(i, 27, Material.DIAMOND_SWORD, 1, 0, "§r§4봉인된 드래곤 슬레이어 §r구입", new String[]{"§r⇒ 좌클릭시 봉인된 드래곤 슬레이어 구입", "§r⇒ 가격: 캡슐 15개, 완두콩 60개"});
		m.gi.setItem(i, 28, Material.DIAMOND_SWORD, 1, 0, "§r커먼 수박아저씨의 칼 구입", new String[]{"§r⇒ 좌클릭시 커먼 수박아저씨의 칼 구입", "§r⇒ 가격: 완두콩 20개"});
		m.gi.setItem(i, 29, Material.BOW,1 ,0, "§r커먼 사냥터지기의 활 구입",new String[]{"§r⇒ 좌클릭시 커먼 사냥터지기의 활 구입", "§r⇒ 가격: 완두콩 30개"});
		//그외
		m.gi.setItem(i, 45, Material.WORKBENCH, 1, 0, "§r합성", new String[]{"§r⇒ 좌클릭시 합성 창으로 이동"});
		m.gi.setItem(i, 46, Material.ANVIL, 1, 0, "§r초월", new String[]{"§r⇒ 좌클릭시 초월 창으로 이동"});
		m.gi.setItem(i, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
		
		if (p.isOp()){ //테스트 물품
			
		}
		
		p.openInventory(i);
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
						if (m.eco.getBalance(p) >= 1000.00){
							Inventory playerinv = p.getInventory();
							playerinv.addItem(m.gc.getCoin(1));
							m.eco.withdrawPlayer(p, 1000);
							p.sendMessage("§6[§9미르 게임§6]§r §e금화 1개§r를 구입하였습니다.");
							
							m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
						}
					} else if (Click.getItemMeta().getDisplayName().equals("§r금화 10개 구입")){
						if (m.eco.getBalance(p) >= 9500.00){
							Inventory playerinv = p.getInventory();
							playerinv.addItem(m.gc.getCoin(10));
							m.eco.withdrawPlayer(p, 9500);
							p.sendMessage("§6[§9미르 게임§6]§r §e금화 10개§r를 구입하였습니다.");
							
							m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
						}
					} else if (Click.getItemMeta().getDisplayName().equals("§r금화 64개 구입")){
						if (m.eco.getBalance(p) >= 60000.00){
							Inventory playerinv = p.getInventory();
							playerinv.addItem(m.gc.getCoin(64));
							m.eco.withdrawPlayer(p, 60000);
							p.sendMessage("§6[§9미르 게임§6]§r §e금화 64개§r를 구입하였습니다.");
							
							m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
						}
					}
				}
				else if (Click.getType() == Material.WORKBENCH){
					e.setCancelled(true);
					m.mr.fug.openReadyFusion(p);
				}
				else if (Click.getType() == Material.ANVIL){
					e.setCancelled(true);
					m.mr.fug.openReadyUpgrade(p);
				}
				else if (Click.getType() == Material.IRON_NUGGET){
					Inventory playeriv = p.getInventory();
					boolean eye = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()){
							if (!eye && stack.getItemMeta().getDisplayName().equals("§d캡슐") && stack.getAmount()>= 5){
								eye = true;
							}
						}
						
						if (eye){
							playeriv.addItem(m.mr.gr.UpgradeStone(1));
							p.sendMessage("§6[§9미르 게임§6]§r §e초월석§r을 구입하였습니다.");
							if (stack.getAmount() == 5) playeriv.setItem(s,null);
							else {
								ItemStack o = stack;
								stack.setAmount(o.getAmount()-5);
							}
							eye = false;
							break;
						}
					}
				}
				else if (Click.getType() == Material.BOW){
					Inventory playeriv = p.getInventory();
					boolean ball = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()){
							if (!ball && stack.getItemMeta().getDisplayName().equals("§a완두콩") && stack.getAmount()>= 30){
								ball = true;
							}
						}
						
						if (ball){
							playeriv.addItem(m.mr.gr.CrokersBow(0));
							p.sendMessage("§6[§9미르 게임§6]§r 커먼 §3사냥터지기의 활§r을 구입하였습니다.");
							if (stack.getAmount() == 30) playeriv.setItem(s,null);
							else {
								ItemStack o = stack;
								stack.setAmount(o.getAmount()-30);
							}
							ball = false;
							break;
						}
					}
				}
				else if (Click.getType() == Material.DIAMOND_SWORD){
					if (Click.getItemMeta().getDisplayName().equals("§r§4봉인된 드래곤 슬레이어 §r구입")){
						Inventory playeriv = p.getInventory();
						boolean eye = false;
						boolean bean = false;
						for (int s=0;s<36;s++){
							ItemStack stack = playeriv.getItem(s);
							
							if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()){
								if (!eye && stack.getItemMeta().getDisplayName().equals("§d캡슐") && stack.getAmount()>= 15){
									eye = true;
									if (stack.getAmount() == 15) playeriv.setItem(s,null);
									else if (stack.getAmount() > 15) {
										ItemStack o = stack;
										stack.setAmount(o.getAmount()-15);
									}
								}
								if (!bean && stack.getItemMeta().getDisplayName().equals("§a완두콩") && stack.getAmount()>= 60){
									bean = true;
									if (stack.getAmount() == 60) playeriv.setItem(s,null);
									else if (stack.getAmount() > 60) {
										ItemStack o = stack;
										stack.setAmount(o.getAmount()-60);
									}
								}
							}
							
							if (eye && bean){
								playeriv.addItem(m.mr.gr.DragonSlayer(0));
								p.sendMessage("§6[§9미르 게임§6]§r §4봉인된 드래곤 슬레이어§r를 구입하였습니다.");
								break;
							}
						}
					} else if (Click.getItemMeta().getDisplayName().equals("§r커먼 수박아저씨의 칼 구입")){
						Inventory playeriv = p.getInventory();
						boolean bean = false;
						for (int s=0;s<36;s++){
							ItemStack stack = playeriv.getItem(s);
							
							if (!bean && stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && 
									stack.getItemMeta().getDisplayName().equals("§a완두콩") && stack.getAmount() >= 20) bean = true;
							
							if (bean){
								playeriv.addItem(m.mr.gr.WatermelonSword(0));
								p.sendMessage("§6[§9미르 게임§6]§r 커먼 §r§2수박아저씨의 칼§r을 구입하였습니다.");
								if (stack.getAmount() == 20){
									playeriv.setItem(s, null);
								} else if (stack.getAmount()>20){
									ItemStack s2 = stack;
									stack.setAmount(s2.getAmount() -20);
								}
								break;
							}
						}
					}
				}
				else if (Click.getItemMeta().getDisplayName().equals("§r완두콩 10개 구입")) {
					Inventory playeriv = p.getInventory();
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && 
								stack.getItemMeta().getDisplayName().equals("§d캡슐") && stack.getAmount() >= 1) {
							if (stack.getAmount() == 1) playeriv.setItem(s, null);
							else {
								ItemStack s2 = stack;
								stack.setAmount(s2.getAmount() - 1);
							}
							
							playeriv.addItem(m.gc.getBean(10));
							p.sendMessage("§6[§9미르 게임§6]§r §a완두콩§r 10개를 구입하였습니다.");
							break;
						}
						
					}
				}
				else if (Click.getItemMeta().getDisplayName().equals("§r캡슐 구입")) {
					Inventory playeriv = p.getInventory();
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && 
								stack.getItemMeta().getDisplayName().equals("§a완두콩") && stack.getAmount() >= 10) {
							if (stack.getAmount() == 10) playeriv.setItem(s, null);
							else {
								ItemStack s2 = stack;
								stack.setAmount(s2.getAmount() - 10);
							}
							
							playeriv.addItem(m.gc.getEye(1));
							p.sendMessage("§6[§9미르 게임§6]§r §d캡슐§r을 구입하였습니다.");
							break;
						}
						
					}
				}
				
			}
		}
	}
}
