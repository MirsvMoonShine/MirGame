package com.Cokes_86.MirGame.System.SlotShop;

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
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l미르 게임 - 상점"))){
			e.setCancelled(true);
			menu(e);
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
			} else if (Click.getType() == Material.WORKBENCH){
				e.setCancelled(true);
				m.mr.fug.openReadyFusion(p);
			} else if (Click.getType() == Material.ANVIL){
				e.setCancelled(true);
				m.mr.fug.openReadyUpgrade(p);
			} else if (Click.getType() == Material.IRON_NUGGET){
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
						
						for (int k =0;k<36;k++){
							ItemStack stack2 = playeriv.getItem(k);
							
							if (stack2 != null && stack2.hasItemMeta()){
								if (eye && stack2.getItemMeta().hasDisplayName() && stack2.getItemMeta().getDisplayName().equals("§d캡슐")){
									if (stack2.getAmount() == 5) playeriv.setItem(k,null);
									else {
										ItemStack o = stack2;
										stack2.setAmount(o.getAmount()-5);
									}
									eye = false;
								} 
							}
						}
						
						break;
					}
				}
			}
			
			else if (Click.getType() == Material.DIAMOND_SWORD){
				if (Click.getItemMeta().getDisplayName().equals("§r§4봉인된 드래곤 슬레이어 구입")){
					Inventory playeriv = p.getInventory();
					boolean eye = false;
					boolean bean = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()){
							if (!eye && stack.getItemMeta().getDisplayName().equals("§d캡슐") && stack.getAmount()>= 15){
								eye = true;
							}
							if (!bean && stack.getItemMeta().getDisplayName().equals("§a완두콩") && stack.getAmount()>= 60){
								bean = true;
							}
						}
						
						if (eye && bean){
							playeriv.addItem(m.mr.gr.DragonSlayer(0));
							p.sendMessage("§6[§9미르 게임§6]§r §4봉인된 드래곤 슬레이어§r를 구입하였습니다.");
							
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
				} else if (Click.getItemMeta().getDisplayName().equals("§r커먼 수박아저씨의 칼 구입")){
					Inventory playeriv = p.getInventory();
					boolean bean = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && 
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
				m.gi.openCoinShop(p);
			} else if (Click.getType() == Material.DIAMOND &&  Click.getItemMeta().getDisplayName().equals("§a- (구)슬롯 -")){
				m.gi.openOldSlot(p);
			} else if (Click.getType() == Material.NETHER_STAR && p.isOp()){
				m.gi.openSlot(p);
			} else if (Click.getType() == Material.REDSTONE_LAMP_OFF){
				m.sl.openSliding(p);
			} else if (Click.getType() == Material.TRAPPED_CHEST){
				m.gi.openRewards(p);
			} else if (Click.getType() == Material.ANVIL && Click.getItemMeta().getDisplayName().equals("§r- 설정 -")){
				m.st.openSetting(p);
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
