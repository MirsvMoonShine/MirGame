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
		if (inv == p.getInventory() && inv.getName().contains(ChatColor.translateAlternateColorCodes('&', "&l�̸� ����"))) {e.setCancelled(false);}
		else if (inv == null) { return; }
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ����"))){
			e.setCancelled(true);
			menu(e);
		}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - ����"))){
			e.setCancelled(true);
			menu(e);
			if (Click.getType() == Material.GOLD_NUGGET){
				if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 1�� ����")){
					if (m.eco.getBalance(p) >= 1000.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(1));
						m.eco.withdrawPlayer(p, 1000);
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��ȭ 1����r�� �����Ͽ����ϴ�.");
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
					}
				} else if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 10�� ����")){
					if (m.eco.getBalance(p) >= 9500.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(10));
						m.eco.withdrawPlayer(p, 9500);
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��ȭ 10����r�� �����Ͽ����ϴ�.");
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
					}
				} else if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 64�� ����")){
					if (m.eco.getBalance(p) >= 60000.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(64));
						m.eco.withdrawPlayer(p, 60000);
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e��ȭ 64����r�� �����Ͽ����ϴ�.");
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
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
						if (!eye && stack.getItemMeta().getDisplayName().equals("��dĸ��") && stack.getAmount()>= 5){
							eye = true;
						}
					}
					
					if (eye){
						playeriv.addItem(m.mr.gr.UpgradeStone(1));
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��e�ʿ�����r�� �����Ͽ����ϴ�.");
						
						for (int k =0;k<36;k++){
							ItemStack stack2 = playeriv.getItem(k);
							
							if (stack2 != null && stack2.hasItemMeta()){
								if (eye && stack2.getItemMeta().hasDisplayName() && stack2.getItemMeta().getDisplayName().equals("��dĸ��")){
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
				if (Click.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾� ����")){
					Inventory playeriv = p.getInventory();
					boolean eye = false;
					boolean bean = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()){
							if (!eye && stack.getItemMeta().getDisplayName().equals("��dĸ��") && stack.getAmount()>= 15){
								eye = true;
							}
							if (!bean && stack.getItemMeta().getDisplayName().equals("��a�ϵ���") && stack.getAmount()>= 60){
								bean = true;
							}
						}
						
						if (eye && bean){
							playeriv.addItem(m.mr.gr.DragonSlayer(0));
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��4���ε� �巡�� �����̾��r�� �����Ͽ����ϴ�.");
							
							for (int k =0;k<36;k++){
								ItemStack stack2 = playeriv.getItem(k);
								if (stack2 != null && stack2.hasItemMeta()){
									if (eye && stack2.getItemMeta().hasDisplayName() && stack2.getItemMeta().getDisplayName().equals("��dĸ��")){
										if (stack2.getAmount() == 15) playeriv.setItem(k,null);
										else {
											ItemStack o = stack2;
											stack2.setAmount(o.getAmount()-15);
										}
										eye = false;
									} else if (eye && stack2.getItemMeta().hasLocalizedName() && stack2.getItemMeta().getLocalizedName().equals("��dĸ��")){
										if (stack2.getAmount() == 15) playeriv.setItem(k,null);
										else {
											ItemStack o = stack2;
											stack2.setAmount(o.getAmount()-15);
										}
										eye = false;
									} else if (bean && stack2.getItemMeta().hasDisplayName() && stack2.getItemMeta().getDisplayName().equals("��a�ϵ���")){
										if (stack2.getAmount() == 60) playeriv.setItem(k,null);
										else {
											ItemStack o = stack2;
											stack2.setAmount(o.getAmount()-60);
										}
										bean = false;
									} else if (bean && stack2.getItemMeta().hasLocalizedName() && stack2.getItemMeta().getLocalizedName().equals("��a�ϵ���")){
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
				} else if (Click.getItemMeta().getDisplayName().equals("��rĿ�� ���ھ������� Į ����")){
					Inventory playeriv = p.getInventory();
					boolean bean = false;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						
						if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && 
								stack.getItemMeta().getDisplayName().equals("��a�ϵ���") && stack.getAmount() >= 20) bean = true;
						
						if (bean){
							playeriv.addItem(m.mr.gr.WatermelonSword(0));
							p.sendMessage("��6[��9�̸� ���ӡ�6]��r Ŀ�� ��r��2���ھ������� Į��r�� �����Ͽ����ϴ�.");
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
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - (��)����"))){
			e.setCancelled(true);
			menu(e);
			if (e.getClickedInventory() == p.getInventory()) e.setCancelled(false);
			if (e.getRawSlot() == 45){
				e.setCancelled(false);
			}
			else if (Click.getType() == Material.WOOL && Click.getItemMeta().getDisplayName().equals("��r��l����")){
				ItemStack coin = inv.getItem(45);
				
				if (coin != null){
					if (coin.getType() == Material.GOLD_NUGGET){
						if (coin.hasItemMeta()){
							if (coin.getItemMeta().hasDisplayName() && coin.getItemMeta().getDisplayName().equals("��e��ȭ")){
								start.put(p, true);
								m.sg.startOldSlot(e);
							}
						}
					}
				}
			} else if (Click.getType() == Material.ANVIL){
				if (Click.getItemMeta().getDisplayName().equals("��r1ȸ ����")){
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"��r�ݺ� ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
				} else if (Click.getItemMeta().getDisplayName().equals("��r�ݺ� ����")){
					Click.getItemMeta().setDisplayName("��r1ȸ ����");
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"��r1ȸ ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
				}
			}
		} else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - ����"))){
			e.setCancelled(true);
			menu(e);
			if (e.getClickedInventory() == p.getInventory()) e.setCancelled(false);
			if (e.getRawSlot() == 45){
				e.setCancelled(false);
			}
			
			else if (Click.getType() == Material.WOOL && Click.getItemMeta().getDisplayName().equals("��r��l����")){
				ItemStack coin = inv.getItem(45);
				
				if (coin != null){
					if (coin.getType() == Material.GOLD_NUGGET){
						if (coin.hasItemMeta()){
							if (coin.getItemMeta().hasDisplayName() && coin.getItemMeta().getDisplayName().equals("��e��ȭ")){
								start.put(p, true);
								m.sg.startSlot(e);
							}
						}
					}
				}
			} else if (Click.getType() == Material.ANVIL){
				if (Click.getItemMeta().getDisplayName().equals("��r1ȸ ����")){
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"��r�ݺ� ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
				} else if (Click.getItemMeta().getDisplayName().equals("��r�ݺ� ����")){
					Click.getItemMeta().setDisplayName("��r1ȸ ����");
					m.gi.setItem(inv, 52, Material.ANVIL,1,0,"��r1ȸ ����",new String[]{"��r�� ��Ŭ���� 1ȸ ����, �ݺ� ���� ���� ����"});
				}
			}
		} else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - �����̵�"))){
			e.setCancelled(true);
			menu(e);
		} else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - ���� ���"))){
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
			if (Click.getType() == Material.CHEST && Click.getItemMeta().getDisplayName().equals("��r- �̸� ���� ���� -")){
				m.gi.openCoinShop(p);
			} else if (Click.getType() == Material.DIAMOND &&  Click.getItemMeta().getDisplayName().equals("��a- (��)���� -")){
				m.gi.openOldSlot(p);
			} else if (Click.getType() == Material.NETHER_STAR && p.isOp()){
				m.gi.openSlot(p);
			} else if (Click.getType() == Material.REDSTONE_LAMP_OFF){
				m.sl.openSliding(p);
			} else if (Click.getType() == Material.TRAPPED_CHEST){
				m.gi.openRewards(p);
			} else if (Click.getType() == Material.ANVIL && Click.getItemMeta().getDisplayName().equals("��r- ���� -")){
				m.st.openSetting(p);
			}
		}
	}
	
	@EventHandler
	public void closeInventory(InventoryCloseEvent e){
		Inventory i = e.getInventory();
		if (i.getTitle().contains("��l�̸� ����") && !i.getTitle().equals("��l�̸� ���� - ����") && !i.getTitle().equals("��l�̸� ���� - ����")){
			ItemStack returnitem = i.getItem(45);
			if (returnitem != null){
				if (!(returnitem.hasItemMeta() && returnitem.getItemMeta().hasDisplayName() && returnitem.getItemMeta().getDisplayName().equals("��6[��9�̸� ���ӡ�6]"))){
					start.put((Player) e.getPlayer(), false);
					e.getPlayer().getInventory().addItem(returnitem);
				}
			}
		}
	}
}
