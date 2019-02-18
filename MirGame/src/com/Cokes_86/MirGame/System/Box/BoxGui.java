package com.Cokes_86.MirGame.System.Box;

import java.util.Arrays;
import java.util.HashMap;
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
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.Cokes_86.MirGame.MirGame;
import com.meowj.langutils.lang.LanguageHelper;

public class BoxGui implements Listener{
	final MirGame m;
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	public HashMap<Player, Boolean> open = new HashMap<>();
	
	public BoxGui(MirGame m){
		this.m = m;
	}
	
	public void BoxOpenReady(Player p, ItemStack[] stacks, String title){
		Inventory i = Bukkit.createInventory(null, 9, "§l선택 상자: §r"+title);
		
		for (int a=0;a<stacks.length;a++){
			i.setItem(a, m.u.getFakeItem(stacks[a]));
		}
		
		p.openInventory(i);
	}
	
	public void BoxOpenConfirm(Player p, ItemStack stack, String title){
		Inventory i = Bukkit.createInventory(null, 36, "§l선택 상자: §r"+title+" - 확인");
		
		i.setItem(13, stack);
		
		ItemStack ok = new ItemStack(Material.WOOL,1,(short)13);
		ItemMeta ok2 = ok.getItemMeta();
		ok2.setDisplayName("§a확인");
		ok.setItemMeta(ok2);
		i.setItem(30, ok);
		
		ItemStack no = new ItemStack(Material.WOOL,1,(short)14);
		ItemMeta no2 = no.getItemMeta();
		no2.setDisplayName("§4취소");
		no.setItemMeta(no2);
		i.setItem(32, no);
		
		p.openInventory(i);
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		ItemStack Click = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		World w = p.getWorld();
		if (inv == null) { return; }
		else if (inv.getName().contains("§l선택 상자: ")){
			e.setCancelled(true);
			if (inv.getName().contains("- 확인")){
				if (Click.getType() == Material.WOOL){
					if (Click.getItemMeta().getDisplayName().equals("§a확인")){
						ItemStack get = inv.getItem(13);
						if (get.hasItemMeta() && get.getItemMeta().getDisplayName() != null) p.sendMessage("§6[§9미르 게임§6]§r 선택 상자에서 "+get.getItemMeta().getDisplayName()+" "+get.getAmount()+"개§r를 획득하였습니다.");
						else p.sendMessage("§6[§9미르 게임§6]§r 선택 상자에서 "+LanguageHelper.getItemDisplayName(get, p)+" "+get.getAmount()+"개§r를 획득하였습니다.");
						p.getInventory().addItem(m.u.getRealItem(get));
						p.closeInventory();
						w.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 0);
						ItemStack Hand = p.getInventory().getItemInMainHand();
						if (Hand.getAmount() == 1){
							p.getInventory().setItemInMainHand(null);
						} else {
							ItemStack Hand2 = Hand;
							Hand2.setAmount(Hand.getAmount()-1);
							p.getInventory().setItemInMainHand(Hand2);
						}
						m.box();
					} else if (Click.getItemMeta().getDisplayName().equals("§4취소")){
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
		} else if (inv.getTitle().contains("§l여는중")){
			e.setCancelled(true);
		} else if (inv.getTitle().contains("§l얻었습니다!")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void closeInventory(InventoryCloseEvent e){
		Inventory i = e.getInventory();
		if (i.getTitle().contains("§l여는중") && open.getOrDefault(e.getPlayer(), false)){
			scheduler.scheduleSyncDelayedTask(m, new Runnable(){
				@Override
				public void run() {
					e.getPlayer().openInventory(e.getInventory());
				}
			},1);
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
					if (me.getLore().get(0).equals("§a§l미르게임 선택 상자")){
						m.box();
						e.setCancelled(true);
					    String boxname = me.getDisplayName().substring(2);
					    Box box = null;
					    for (Box b : m.boxs){
						    if (b.getBoxName().equals(boxname)){
							    box = b;
						    }
					    }
					    if (box!=null && box.Box == 1) BoxOpenReady(p,box.list,boxname);
					    else p.sendMessage("§6[§9미르 게임§6]§r 이 상자는 더이상 열 수 없습니다.");
					} else if (me.getLore().get(0).equals("§a§l미르게임 랜덤 상자")){
						m.box();
						e.setCancelled(true);
					    String boxname = me.getDisplayName().substring(2);
					    Box box = null;
					    for (Box b : m.boxs){
						    if (b.getBoxName().equals(boxname)){
							    box = b;
						    }
					    }
					    if (box!=null && box.Box == 0) {
					    	Random r = new Random();
					    	int random = r.nextInt(box.getList().length);
					    	ItemStack s = Arrays.asList(box.getList()).get(random);
					    	if (s.hasItemMeta() && s.getItemMeta().getDisplayName() != null) p.sendMessage("§6[§9미르 게임§6]§r 랜덤 상자에서 "+s.getItemMeta().getDisplayName()+" "+s.getAmount()+"개§r를 획득하였습니다.");
							else p.sendMessage("§6[§9미르 게임§6]§r 랜덤 상자에서 "+LanguageHelper.getItemDisplayName(s, p)+" "+s.getAmount()+"개§r를 획득하였습니다.");
					    	p.getInventory().addItem(s);
					    	
					    	ItemStack Hand = p.getInventory().getItemInMainHand();
							if (Hand.getAmount() == 1){
								p.getInventory().setItemInMainHand(null);
							} else {
								ItemStack Hand2 = Hand;
								Hand2.setAmount(Hand.getAmount()-1);
								p.getInventory().setItemInMainHand(Hand2);
							}
							
							m.box();
					    }
					    else p.sendMessage("§6[§9미르 게임§6]§r 이 상자는 더이상 열 수 없습니다.");
					} else if (me.getLore().get(0).equals("§a§l미르게임 상자")){
						m.box();
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
					    		if (s.hasItemMeta() && s.getItemMeta().getDisplayName() != null) p.sendMessage("§6[§9미르 게임§6]§r 상자에서 "+s.getItemMeta().getDisplayName()+" "+s.getAmount()+"개§r를 획득하였습니다.");
								else p.sendMessage("§6[§9미르 게임§6]§r 상자에서 "+LanguageHelper.getItemDisplayName(s, p)+" "+s.getAmount()+"개§r를 획득하였습니다.");
					    		p.getInventory().addItem(s);
					    	}
					    	
					    	ItemStack Hand = p.getInventory().getItemInMainHand();
							if (Hand.getAmount() == 1){
								p.getInventory().setItemInMainHand(null);
							} else {
								ItemStack Hand2 = Hand;
								Hand2.setAmount(Hand.getAmount()-1);
								p.getInventory().setItemInMainHand(Hand2);
							}
							
							m.box();
					    }
					    else p.sendMessage("§6[§9미르 게임§6]§r 이 상자는 더이상 열 수 없습니다.");
					}
				}
			}
		}
	}
}
