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
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ����"))){
			e.setCancelled(true);
			menu(e);
		}
		else if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&l�̸� ���� - ���λ���"))){
			e.setCancelled(true);
			menu(e);
			if (Click.getType() == Material.GOLD_NUGGET){
				if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 1�� ����")){
					if (m.eco.getBalance(p) >= 1000.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(1));
						m.eco.withdrawPlayer(p, 1000);
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
					}
				} else if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 10�� ����")){
					if (m.eco.getBalance(p) >= 9500.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(10));
						m.eco.withdrawPlayer(p, 9500);
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
					}
				} else if (Click.getItemMeta().getDisplayName().equals("��r��ȭ 64�� ����")){
					if (m.eco.getBalance(p) >= 60000.00){
						Inventory playerinv = p.getInventory();
						playerinv.addItem(m.gc.getCoin(64));
						m.eco.withdrawPlayer(p, 60000);
						
						m.gi.setItem(inv, 49, Material.BOOK, 1, 0, "��r�����ڻ�: "+m.eco.getBalance(p), null);
					}
				}
			} else if (Click.getType() == Material.DIAMOND_SWORD){
				if (Click.getItemMeta().getDisplayName().equals("��r��4��l�巡�� �����̾� ����")){
					Inventory playeriv = p.getInventory();
					int buy = 0;
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta()){
							if (stack.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾�")){
								buy += 1;
							}
						}
						if (buy == 2) break;
					}
					
					if (buy == 2){
						ItemStack dragonslayer = new ItemStack(Material.DIAMOND_SWORD,1);
						ItemMeta m = dragonslayer.getItemMeta();
						m.setDisplayName("��r��4��l�巡�� �����̾�");
						m.setLore(Arrays.asList(new String[]{"��r��l������ �������� ����� ��, �̸��� óġ�� ��,","��r��l�巡�� �����̾ ���� ���⿡ �����ϸ���."}));
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
								if (lockdragon.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾�")){
									playeriv.setItem(t, null);
									buy -= 1;
								}
							}
							
							if (buy == 0) {
								break;
							}
						}
					}
				} else if (Click.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾� ����")){
					Inventory playeriv = p.getInventory();
					for (int s=0;s<36;s++){
						ItemStack stack = playeriv.getItem(s);
						if (stack != null && stack.hasItemMeta()){
							if (stack.getItemMeta().getDisplayName().equals(m.gc.getEye(1).getItemMeta().getDisplayName()) && stack.getAmount()>= 10){
								ItemStack dragonslayer = new ItemStack(Material.DIAMOND_SWORD,1);
								ItemMeta m = dragonslayer.getItemMeta();
								m.setDisplayName("��r��4���ε� �巡�� �����̾�");
								m.setLore(Arrays.asList(new String[]{"��r��l���� �տ� ���� ��û�� ������ ���� �귯���´�.","��r��l�ѹ��� �ֵѷ��� �μ��� �͸� ����."}));
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
