package com.Cokes_86.MirGame.SelectBox;

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

import com.Cokes_86.MirGame.MirGame;

public class BoxGui implements Listener{
	final MirGame m;
	
	public BoxGui(MirGame m){
		this.m = m;
	}
	
	public void BoxOpenReady(Player p, ItemStack[] stacks, String title){
		Inventory i = Bukkit.createInventory(null, 9, "§l선택 상자: "+title);
		
		for (int k=0;k<9;k++){
			ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE,1);
			ItemMeta m = glass.getItemMeta();
			m.setDisplayName(" ");
			glass.setItemMeta(m);
			i.setItem(k, glass);
		}
		for (int a=0;a<stacks.length;a++){
			i.setItem(a, stacks[a]);
		}
		
		p.openInventory(i);
	}
	
	public void BoxOpenConfirm(Player p, ItemStack stack, String title){
		Inventory i = Bukkit.createInventory(null, 36, "§l선택 상자: "+title+" - 확인");
		
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
		if (inv == p.getInventory()) {e.setCancelled(false);}
		else if (inv == null) { return; }
		else if (inv.getName().contains("§l선택 상자: ")){
			e.setCancelled(true);
			if (inv.getName().contains("- 확인")){
				if (Click.getType() == Material.WOOL){
					if (Click.getItemMeta().getDisplayName().equals("§a확인")){
						ItemStack get = inv.getItem(13);
						p.getInventory().addItem(get);
						p.closeInventory();
						w.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 0);
						
					} else if (Click.getItemMeta().getDisplayName().equals("§4취소")){
						p.closeInventory();
					}
				}
			} else {
				if (Click.getType() == Material.STAINED_GLASS_PANE && Click.getItemMeta().getDisplayName().equals(" ")){
					e.setCancelled(true);
				} else {
					String title = inv.getName().substring(9);
					BoxOpenConfirm(p,Click,title);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR){
			if (p.getInventory().getItemInMainHand().getType() == Material.CHEST){
				ItemStack stack = p.getInventory().getItemInMainHand();
				ItemMeta me = stack.getItemMeta();
				if (me.getLore().get(0).contains("선택 상자")){
					int a = me.getLore().get(0).indexOf("선택");
					String boxname = me.getLore().get(0).substring(2, a-1);
					SelectBox box = null;
					for (SelectBox b : m.boxs){
						if (b.getBoxName().equals(boxname)){
							box = b;
						}
					}
					BoxOpenReady(p,box.list,boxname);
				}
			}
		}
	}
}
