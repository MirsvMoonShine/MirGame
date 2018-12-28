package com.Cokes_86.MirGame.StartSystem;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.Cokes_86.MirGame.MirGame;

public class CraftListener implements Listener{
	final MirGame m;
	
	public CraftListener(MirGame m){
		this.m = m;
	}
	
	@EventHandler
	public void dontcraft(PrepareItemCraftEvent e) {
		for (int a = 0 ; a<9;a++){
			if (e.getInventory().getItem(a) != null){
				ItemStack s = e.getInventory().getItem(a);
				if (s.hasItemMeta() && s.getItemMeta().hasDisplayName()){
					if (s.getType() == Material.GOLD_NUGGET && s.getItemMeta().getDisplayName().equals("��e��ȭ")){
						e.getInventory().setResult(null);
						break;
					} else if (s.getType() == Material.IRON_NUGGET && s.getItemMeta().getDisplayName().equals("��e�ʿ���")){
						e.getInventory().setResult(null);
						break;
					} else if (s.getType() == Material.DIAMOND_SWORD && s.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾�")){
						e.getInventory().setResult(null);
						break;
					} else if (s.getType() == Material.DIAMOND_SWORD && s.getItemMeta().getDisplayName().equals("��r��4��l�巡�� �����̾�")){
						e.getInventory().setResult(null);
						break;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void dontanvil(PrepareAnvilEvent e) {
		ItemStack[] s = e.getInventory().getContents();
		ItemStack s1 = s[0], s2 = s[1];
		if (s1 != null && s1.hasItemMeta() && s1.getItemMeta().hasDisplayName()){
			if (s1.getType() == Material.DIAMOND_SWORD && s1.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾�")){
				e.setResult(null);
			} else if (s1.getType() == Material.DIAMOND_SWORD && s1.getItemMeta().getDisplayName().equals("��r��4��l�巡�� �����̾�")){
				e.setResult(null);
			}
		} else if (s2 != null && s2.hasItemMeta() && s2.getItemMeta().hasDisplayName()){
			if (s2.getType() == Material.DIAMOND_SWORD && s2.getItemMeta().getDisplayName().equals("��r��4���ε� �巡�� �����̾�")){
				e.setResult(null);
			} else if (s2.getType() == Material.DIAMOND_SWORD && s2.getItemMeta().getDisplayName().equals("��r��4��l�巡�� �����̾�")){
				e.setResult(null);
			}
		}
	}
}
