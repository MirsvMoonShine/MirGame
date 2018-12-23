package com.Cokes_86.MirGame.StartSystem;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.Cokes_86.MirGame.MirGame;
import com.Cokes_86.MirGame.Box.Box;

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
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 상점"))){
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
						if (buy == 3) break;
					}
					
					if (buy == 3){
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
					boolean eye = false;
					boolean bean = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						
						if (stack != null && stack.hasItemMeta()){
							if (!eye && stack.getItemMeta().getDisplayName().equals("§d캡슐") && stack.getAmount()>= 15){
								eye = true;
							}
							if (!bean && stack.getItemMeta().getDisplayName().equals("§a완두콩") && stack.getAmount()>= 60){
								bean = true;
							}
						}
						
						if (eye && bean){
							ItemStack dragonslayer = new ItemStack(Material.DIAMOND_SWORD,1);
							ItemMeta m = dragonslayer.getItemMeta();
							m.setDisplayName("§r§4봉인된 드래곤 슬레이어");
							m.setLore(Arrays.asList(new String[]{"§r§l검을 손에 쥐자 엄청난 미지의 힘이 흘러나온다.","§r§l한번만 건들어도 부서질 것만 같다."}));
							m.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
							m.addEnchant(Enchantment.KNOCKBACK, 3, true);
							m.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
							m.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
							m.addEnchant(Enchantment.SWEEPING_EDGE, 3, true);
							dragonslayer.setItemMeta(m);
							dragonslayer.setDurability((short)1561);
							playeriv.addItem(dragonslayer);
							
							for (int k =0;k<36;k++){
								ItemStack stack2 = playeriv.getItem(k);
								if (stack2 != null && stack2.hasItemMeta()){
									if (eye && stack2.getItemMeta().hasDisplayName() && stack2.getItemMeta().getDisplayName().equals("§d캡슐")){
										if (stack2.getAmount() == 15) playeriv.setItem(k,null);
										else {
											ItemStack o = stack2;
											stack2.setAmount(o.getAmount()-15);
										}
										eye = false;
									} else if (eye && stack2.getItemMeta().hasLocalizedName() && stack2.getItemMeta().getLocalizedName().equals("§d캡슐")){
										if (stack2.getAmount() == 15) playeriv.setItem(k,null);
										else {
											ItemStack o = stack2;
											stack2.setAmount(o.getAmount()-15);
										}
										eye = false;
									} else if (bean && stack2.getItemMeta().hasDisplayName() && stack2.getItemMeta().getDisplayName().equals("§a완두콩")){
										if (stack2.getAmount() == 60) playeriv.setItem(k,null);
										else {
											ItemStack o = stack2;
											stack2.setAmount(o.getAmount()-60);
										}
										bean = false;
									} else if (bean && stack2.getItemMeta().hasLocalizedName() && stack2.getItemMeta().getLocalizedName().equals("§a완두콩")){
										if (stack2.getAmount() == 60) playeriv.setItem(k,null);
										else {
											ItemStack o = stack2;
											stack2.setAmount(o.getAmount()-60);
										}
										bean = false;
									}
								}
							}
							break;
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
		if (Click.getType() == Material.CHEST && Click.getItemMeta().getDisplayName().equals("§r- 미르 게임 상점 -")){
			m.gi.openCoinShop(p);
			returnCoin(e);
		} else if (Click.getType() == Material.DIAMOND){
			m.gi.openOldSlot(p);
			returnCoin(e);
		} else if (Click.getType() == Material.NETHER_STAR){
			m.gi.openSlot(p);
			returnCoin(e);
		} else if (Click.getType() == Material.REDSTONE_LAMP_OFF && p.isOp()){
			m.sl.openSliding(p);
			returnCoin(e);
		} else if (Click.getType() == Material.TRAPPED_CHEST){
			m.gi.openRewards(p);
			returnCoin(e);
		}
	}
	
	public void returnCoin(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		ItemStack coin = e.getInventory().getItem(45);
		if (coin != null){
			if (!(coin.hasItemMeta() && coin.getItemMeta().hasDisplayName() && coin.getItemMeta().getDisplayName().equals("§6[§9미르 게임§6]"))){
				p.getInventory().addItem(coin);
			}
		}
	}
	
	@EventHandler
	public void closeInventory(InventoryCloseEvent e){
		Inventory i = e.getInventory();
		if (i.getTitle().contains("§l미르 게임")){
			ItemStack returnitem = i.getItem(45);
			if (returnitem != null){
				if (!(returnitem.hasItemMeta() && returnitem.getItemMeta().hasDisplayName() && returnitem.getItemMeta().getDisplayName().equals("§6[§9미르 게임§6]"))){
					e.getPlayer().getInventory().addItem(returnitem);
				}
			}
		}
	}
}
