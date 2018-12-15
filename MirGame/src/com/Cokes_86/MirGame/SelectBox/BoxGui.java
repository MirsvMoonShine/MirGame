package com.Cokes_86.MirGame.SelectBox;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.Cokes_86.MirGame.MirGame;

public class BoxGui implements Listener{
	final MirGame m;
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	
	public BoxGui(MirGame m){
		this.m = m;
	}
	
	public void BoxOpenReady(Player p, ItemStack[] stacks, String title){
		Inventory i = Bukkit.createInventory(null, 9, "��l���� ����: ��r"+title);
		
		for (int a=0;a<stacks.length;a++){
			i.setItem(a, stacks[a]);
		}
		
		p.openInventory(i);
	}
	
	public void BoxOpenConfirm(Player p, ItemStack stack, String title){
		Inventory i = Bukkit.createInventory(null, 36, "��l���� ����: ��r"+title+" - Ȯ��");
		
		i.setItem(13, stack);
		
		ItemStack ok = new ItemStack(Material.WOOL,1,(short)13);
		ItemMeta ok2 = ok.getItemMeta();
		ok2.setDisplayName("��aȮ��");
		ok.setItemMeta(ok2);
		i.setItem(30, ok);
		
		ItemStack no = new ItemStack(Material.WOOL,1,(short)14);
		ItemMeta no2 = no.getItemMeta();
		no2.setDisplayName("��4���");
		no.setItemMeta(no2);
		i.setItem(32, no);
		
		p.openInventory(i);
	}
	
	public void BoxRandom(Player p, ItemStack[] list, String title){
		Inventory i = Bukkit.createInventory(null, 36, "��l������...: ��r"+title);
		RandomBox(i,list,0);
		loading(p,i,list,0,title);
		p.openInventory(i);
	}
	
	public void RandomBox(Inventory i, ItemStack[] list,int a){
		i.setItem(13, list[a]);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (a == list.length-1) RandomBox(i,list,0);
				else RandomBox(i,list,a+1);
			}
        }, (long)1.5);
	}
	
	public void RandomOpenComplete(Player p, ItemStack is, String title){
		Inventory i = Bukkit.createInventory(null, 9, "��l������ϴ�!: ��r"+title);
		
		i.setItem(4, is);
		
		p.openInventory(i);
	}
	
	public void loading(Player p, Inventory i, ItemStack[] list,int a,String title){
		ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)13);
		ItemMeta itemmeta = pane.getItemMeta();
		itemmeta.setDisplayName(" ");
		pane.setItemMeta(itemmeta);
		i.setItem(27+a, pane);
		
		scheduler.scheduleSyncDelayedTask(m, new Runnable(){
			@Override
			public void run() {
				if (a==8) {
					Random r = new Random();
			    	int c = r.nextInt(list.length);
			    	ItemStack get = list[c];
			    	p.getInventory().addItem(get);
			    	if (get.hasItemMeta() && get.getItemMeta().getDisplayName() != null) p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���� ���ڿ��� "+get.getItemMeta().getDisplayName()+" "+get.getAmount()+"����r�� ȹ���Ͽ����ϴ�.");
					else p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���� ���ڿ��� "+get.getType().toString().replace("_", " ").toLowerCase()+" "+get.getAmount()+"����r�� ȹ���Ͽ����ϴ�.");
			    	ItemStack Hand = p.getInventory().getItemInMainHand();
					if (Hand.getAmount() == 1){
						p.getInventory().setItemInMainHand(null);
					} else {
						ItemStack Hand2 = Hand;
						Hand2.setAmount(Hand.getAmount()-1);
						p.getInventory().setItemInMainHand(Hand2);
					}
					
					RandomOpenComplete(p,get,title);
				}
				else {
					loading(p,i,list,a+1,title);
				}
			}
        }, 5);
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		World w = p.getWorld();
		if (inv == p.getInventory()) {e.setCancelled(false);}
		else if (inv == null) { return; }
		else if (inv.getName().contains("��l���� ����: ")){
			e.setCancelled(true);
			if (inv.getName().contains("- Ȯ��")){
				if (Click.getType() == Material.WOOL){
					if (Click.getItemMeta().getDisplayName().equals("��aȮ��")){
						ItemStack get = inv.getItem(13);
						p.getInventory().addItem(get);
						p.closeInventory();
						w.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 0);
						if (get.hasItemMeta() && get.getItemMeta().getDisplayName() != null) p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���� ���ڿ��� "+get.getItemMeta().getDisplayName()+" "+get.getAmount()+"����r�� ȹ���Ͽ����ϴ�.");
						else p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���� ���ڿ��� "+get.getType().toString().replace("_", " ").toLowerCase()+" "+get.getAmount()+"����r�� ȹ���Ͽ����ϴ�.");
						ItemStack Hand = p.getInventory().getItemInMainHand();
						if (Hand.getAmount() == 1){
							p.getInventory().setItemInMainHand(null);
						} else {
							ItemStack Hand2 = Hand;
							Hand2.setAmount(Hand.getAmount()-1);
							p.getInventory().setItemInMainHand(Hand2);
						}
					} else if (Click.getItemMeta().getDisplayName().equals("��4���")){
						p.closeInventory();
					}
				}
			} else {
				if (Click == null || Click.getType() == Material.AIR){
					e.setCancelled(true);
				} else {
					String title = inv.getName().substring(9);
					BoxOpenConfirm(p,Click,title);
				}
			}
		} else if (inv.getTitle().contains("��l������")){
			e.setCancelled(true);
		} else if (inv.getTitle().contains("��l������ϴ�!")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK ){
			if (p.getInventory().getItemInMainHand().getType() == Material.CHEST){
				ItemStack stack = p.getInventory().getItemInMainHand();
				ItemMeta me = stack.getItemMeta();
				if (me.hasLore()){
					if (me.getLore().get(0).equals("��a��l�̸����� ���� ����")){
						e.setCancelled(true);
					    String boxname = me.getDisplayName().substring(2);
					    Box box = null;
					    for (Box b : m.boxs){
						    if (b.getBoxName().equals(boxname)){
							    box = b;
						    }
					    }
					    if (box!=null && box.Box == 1) BoxOpenReady(p,box.list,boxname);
					    else p.sendMessage("��6[��9�̸� ���ӡ�6]��r �� ���ڴ� ���̻� �� �� �����ϴ�.");
					} else if (me.getLore().get(0).equals("��a��l�̸����� ���� ����")){
						e.setCancelled(true);
					    String boxname = me.getDisplayName().substring(2);
					    Box box = null;
					    for (Box b : m.boxs){
						    if (b.getBoxName().equals(boxname)){
							    box = b;
						    }
					    }
					    if (box!=null && box.Box == 0) BoxRandom(p,box.list,box.getBoxName());
					    else p.sendMessage("��6[��9�̸� ���ӡ�6]��r �� ���ڴ� ���̻� �� �� �����ϴ�.");
					} else if (me.getLore().get(0).equals("��a��l�̸����� ����")){
						e.setCancelled(true);
					    String boxname = me.getDisplayName().substring(2);
					    Box box = null;
					    for (Box b : m.boxs){
						    if (b.getBoxName().equals(boxname)){
							    box = b;
						    }
					    }
					    if (box!=null && box.Box == 2) {
					    	for (ItemStack s : box.list){
					    		p.getInventory().addItem(s);
					    		if (s.hasItemMeta() && s.getItemMeta().getDisplayName() != null) p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���ڿ��� "+s.getItemMeta().getDisplayName()+" "+s.getAmount()+"����r�� ȹ���Ͽ����ϴ�.");
								else p.sendMessage("��6[��9�̸� ���ӡ�6]��r ���ڿ��� "+s.getType().toString().replace("_", " ").toLowerCase()+" "+s.getAmount()+"����r�� ȹ���Ͽ����ϴ�.");
					    	}
					    	
					    	ItemStack Hand = p.getInventory().getItemInMainHand();
							if (Hand.getAmount() == 1){
								p.getInventory().setItemInMainHand(null);
							} else {
								ItemStack Hand2 = Hand;
								Hand2.setAmount(Hand.getAmount()-1);
								p.getInventory().setItemInMainHand(Hand2);
							}
					    }
					    else p.sendMessage("��6[��9�̸� ���ӡ�6]��r �� ���ڴ� ���̻� �� �� �����ϴ�.");
					}
				}
			}
		}
	}
}
