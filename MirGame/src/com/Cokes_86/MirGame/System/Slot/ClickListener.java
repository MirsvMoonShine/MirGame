package com.Cokes_86.MirGame.System.Slot;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.Cokes_86.MirGame.MirGame;
import com.Cokes_86.MirGame.System.Box.Box;

public class ClickListener implements Listener{
	final MirGame m;
	public HashMap<Player, Boolean> start = new HashMap<>();
	
	public ClickListener (MirGame main){
		this.m = main;
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (inv == p.getInventory() && inv.getName().contains(ChatColor.translateAlternateColorCodes('&', "&l미르 게임"))) {e.setCancelled(false);}
		else if (inv == null) { return; }
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임"))){
			e.setCancelled(true);
			menu(e);
		}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - (구)슬롯"))){
			e.setCancelled(true);
			menu(e);
			if (e.getClickedInventory() == p.getInventory()) e.setCancelled(false);
			if (e.getRawSlot() == 45){
				e.setCancelled(false);
			}
			else if (Click.getType() == Material.WOOL && Click.getItemMeta().getDisplayName().equals("§r§l시작")){
				ItemStack coin = inv.getItem(45);
				
				if (coin != null){
					if (coin.getType() == Material.GOLD_NUGGET){
						if (coin.hasItemMeta()){
							if (coin.getItemMeta().hasDisplayName() && coin.getItemMeta().getDisplayName().equals("§e금화")){
								start.put(p, true);
								m.sg.startOldSlot(e);
							}
						}
					}
				}
			} else if (Click.getType() == Material.ANVIL){
				if (Click.getItemMeta().getDisplayName().equals("§r1회 실행")){
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"§r반복 실행",new String[]{"§r⇒ 좌클릭시 1회 실행, 반복 실행 설정 가능"});
				} else if (Click.getItemMeta().getDisplayName().equals("§r반복 실행")){
					Click.getItemMeta().setDisplayName("§r1회 실행");
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"§r1회 실행",new String[]{"§r⇒ 좌클릭시 1회 실행, 반복 실행 설정 가능"});
				}
			}
		} else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 슬롯"))){
			e.setCancelled(true);
			menu(e);
			if (e.getClickedInventory() == p.getInventory()) e.setCancelled(false);
			if (e.getRawSlot() == 45){
				e.setCancelled(false);
			}
			
			else if (Click.getType() == Material.WOOL && Click.getItemMeta().getDisplayName().equals("§r§l시작")){
				ItemStack coin = inv.getItem(45);
				
				if (coin != null){
					if (coin.getType() == Material.GOLD_NUGGET){
						if (coin.hasItemMeta()){
							if (coin.getItemMeta().hasDisplayName() && coin.getItemMeta().getDisplayName().equals("§e금화")){
								start.put(p, true);
								m.sg.startSlot(e);
							}
						}
					}
				}
			} else if (Click.getType() == Material.ANVIL){
				if (Click.getItemMeta().getDisplayName().equals("§r1회 실행")){
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"§r반복 실행",new String[]{"§r⇒ 좌클릭시 1회 실행, 반복 실행 설정 가능"});
				} else if (Click.getItemMeta().getDisplayName().equals("§r반복 실행")){
					Click.getItemMeta().setDisplayName("§r1회 실행");
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"§r1회 실행",new String[]{"§r⇒ 좌클릭시 1회 실행, 반복 실행 설정 가능"});
				}
			}
		} else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 슬라이딩"))){
			e.setCancelled(true);
			menu(e);
		} else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 보상 목록"))){
			e.setCancelled(true);
			menu(e);
			if (p.isOp()){
				ItemStack s = e.getCurrentItem();
				for (Box b : m.boxs){
					if (s.hasItemMeta() && s.getItemMeta().getDisplayName().equals(b.getBox().getItemMeta().getDisplayName())){
						p.getInventory().addItem(s);
						break;
					}
				}
			}
		}
	}
	
	public void menu(InventoryClickEvent e){
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (Click.hasItemMeta() && Click.getItemMeta().hasDisplayName()) {
			if (Click.getType() == Material.CHEST && Click.getItemMeta().getDisplayName().equals("§r- 미르 게임 상점 -")){
				m.si.openCoinShop(p);
				m.bj.start.put(p, false);
			} else if (Click.getType() == Material.DIAMOND &&  Click.getItemMeta().getDisplayName().equals("§a- (구)슬롯 -")){
				m.gi.openOldSlot(p);
				m.bj.start.put(p, false);
			} else if (Click.getType() == Material.NETHER_STAR && p.isOp()){
				m.gi.openSlot(p);
				m.bj.start.put(p, false);
			} else if (Click.getType() == Material.REDSTONE_LAMP_OFF){
				m.sl.openSliding(p);
				m.bj.start.put(p, false);
			} else if (Click.getType() == Material.TRAPPED_CHEST){
				m.gi.openRewards(p);
				m.bj.start.put(p, false);
			} else if (Click.getType() == Material.ANVIL && Click.getItemMeta().getDisplayName().equals("§r- 설정 -")){
				m.st.openSetting(p);
				m.bj.start.put(p, false);
			} else if (Click.getType() == Material.WOOL && Click.getItemMeta().getDisplayName().equals("§4- 블랙잭 -") && p.isOp()){
				m.bj.openBlackJack(p);
				m.bj.start.put(p, false);
			}
		}
	}
	
	@EventHandler
	public void closeInventory(InventoryCloseEvent e){
		Inventory i = e.getInventory();
		if (i.getTitle().contains("§l미르 게임") && !i.getTitle().equals("§l미르 게임 - 상점") && !i.getTitle().equals("§l미르 게임 - 설정")){
			ItemStack returnitem = i.getItem(45);
			if (returnitem != null){
				if (!(returnitem.hasItemMeta() && returnitem.getItemMeta().hasDisplayName() && returnitem.getItemMeta().getDisplayName().equals("§6[§9미르 게임§6]"))){
					start.put((Player) e.getPlayer(), false);
					e.getPlayer().getInventory().addItem(returnitem);
				}
			}
		}
	}
}
