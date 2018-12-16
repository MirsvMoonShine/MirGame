package com.Cokes_86.MirGame.StartSystem;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.Cokes_86.MirGame.MirGame;

public class ClickListener implements Listener{
	final MirGame m;
	
	public ClickListener (MirGame main){
		this.m = main;
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (inv == p.getInventory()) {e.setCancelled(false);}
		else if (inv == null) { return; }
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임"))){
			e.setCancelled(true);
			menu(e);
		}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 코인상점"))){
			e.setCancelled(true);
			menu(e);
			if (Click.getType() == Material.GOLD_NUGGET){
				if (Click.getItemMeta().getDisplayName().equals("§r금화 1개 구입")){
					if (m.eco.getBalance(p) >= 1000.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(1));
						m.eco.withdrawPlayer(p, 1000);
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
					}
				} else if (Click.getItemMeta().getDisplayName().equals("§r금화 10개 구입")){
					if (m.eco.getBalance(p) >= 9500.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(10));
						m.eco.withdrawPlayer(p, 9500);
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
					}
				} else if (Click.getItemMeta().getDisplayName().equals("§r금화 64개 구입")){
					if (m.eco.getBalance(p) >= 60000.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(64));
						m.eco.withdrawPlayer(p, 60000);
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "§r보유자산: "+m.eco.getBalance(p), null);
					}
				}
			} else if (Click.getType() == Material.DIAMOND_SWORD){
				if (Click.getItemMeta().getDisplayName().equals("§r§4§l드래곤 슬레이어 구입")){
					Inventory playeriv = p.getInventory();
					int buy = 0;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta()){
							if (stack.getItemMeta().getDisplayName().equals("§r§4봉인된 드래곤 슬레이어")){
								buy += 1;
							}
						}
						if (buy == 2) break;
					}
					
					if (buy == 2){
						ItemStack dragonslayer = new ItemStack(Material.DIAMOND_SWORD,1);
						ItemMeta m = dragonslayer.getItemMeta();
						m.setDisplayName("§r§4§l드래곤 슬레이어");
						m.setLore(Arrays.asList(new String[]{"§r§l전설로 전해지는 고대의 용, 미르를 처치한 검,","§r§l드래곤 슬레이어가 지금 여기에 강림하리라."}));
						m.addEnchant(Enchantment.DAMAGE_ALL, 15, true);
						m.addEnchant(Enchantment.KNOCKBACK, 5, true);
						m.addEnchant(Enchantment.DURABILITY, 10, true);
						m.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
						m.addEnchant(Enchantment.LOOT_BONUS_MOBS, 5, true);
						m.addEnchant(Enchantment.SWEEPING_EDGE, 5, true);
						dragonslayer.setItemMeta(m);
						playeriv.addItem(dragonslayer);
						
						for (int t=0;t<36;t++){
							ItemStack lockdragon = playeriv.getItem(t);
							if (lockdragon != null && lockdragon.hasItemMeta()){
								if (lockdragon.getItemMeta().getDisplayName().equals("§r§4봉인된 드래곤 슬레이어")){
									playeriv.setItem(t, null);
									buy -= 1;
								}
							}
							
							if (buy == 0) {
								break;
							}
						}
					}
				} else if (Click.getItemMeta().getDisplayName().equals("§r§4봉인된 드래곤 슬레이어 구입")){
					Inventory playeriv = p.getInventory();
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta()){
							if (stack.getItemMeta().getDisplayName().equals(m.gc.getEye(1).getItemMeta().getDisplayName()) && stack.getAmount()>= 10){
								ItemStack dragonslayer = new ItemStack(Material.DIAMOND_SWORD,1);
								ItemMeta m = dragonslayer.getItemMeta();
								m.setDisplayName("§r§4봉인된 드래곤 슬레이어");
								m.setLore(Arrays.asList(new String[]{"§r§l검을 손에 쥐자 엄청난 미지의 힘이 흘러나온다.","§r§l한번만 휘둘러도 부서질 것만 같다."}));
								m.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
								m.addEnchant(Enchantment.KNOCKBACK, 3, true);
								m.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
								m.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
								m.addEnchant(Enchantment.SWEEPING_EDGE, 3, true);
								dragonslayer.setItemMeta(m);
								dragonslayer.setDurability((short)1561);
								playeriv.addItem(dragonslayer);
								
								if (stack.getAmount() == 10) playeriv.setItem(s, null);
								else {int a =stack.getAmount(); stack.setAmount(a-10);}
							}
						}
					}
				}
			}
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
		}
	}
	
	public void menu(InventoryClickEvent e){
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		if (Click.getType() == Material.CHEST){
			m.gi.openCoinShop(p);
		} else if (Click.getType() == Material.DIAMOND){
			m.gi.openOldSlot(p);
		} else if (Click.getType() == Material.NETHER_STAR){
			m.gi.openSlot(p);
		} else if (Click.getType() == Material.REDSTONE_LAMP_OFF){
			m.sl.openSliding(p);
		} 
	}
}
