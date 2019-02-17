package com.Cokes_86.MirGame.System.Box;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.meowj.langutils.lang.LanguageHelper;

public class Box {
	final String BoxName;
	final ItemStack[] list;
	final int Box;
	
	public Box(String name, ItemStack[] list, int a){
		this.BoxName = name;
		this.list = list;
		this.Box = a; //random = 0 , select = 1
	}
	
	public String getBoxName(){
		return BoxName;
	}
	
	public ItemStack[] getList(){
		return list;
	}
	
	public ItemStack getBox(Player p){
		ItemStack result = new ItemStack(Material.CHEST,1);
		ItemMeta m = result.getItemMeta();
		m.setDisplayName("§r"+BoxName);
		ArrayList<String> lore = new ArrayList<>();
		
		if (Box==0){
			lore.add("§a§l미르게임 랜덤 상자");
			lore.add("");
			lore.add("§r《구성품 중 하나 획득》");
		}
		if (Box == 1){
			lore.add("§a§l미르게임 선택 상자");
			lore.add("");
			lore.add("§r《구성품 중 하나 선택 획득》");
		}
		if (Box == 2){
			lore.add("§a§l미르게임 상자");
			lore.add("");
			lore.add("§r《구성품 전부 획득》");
		} 
		
		for (ItemStack list : this.list){
			if (list.hasItemMeta() && list.getItemMeta() != null) lore.add("§r- "+list.getItemMeta().getDisplayName()+ " "+ list.getAmount()+"개");
			else lore.add("§r- "+LanguageHelper.getItemDisplayName(list, p)+ " "+ list.getAmount()+"개");
		}
		m.setLore(lore);
		result.setItemMeta(m);
		return result;
	}
	
	public ItemStack getFakeBox(Player p){
		ItemStack result = new ItemStack(Material.CHEST,1);
		ItemMeta m = result.getItemMeta();
		m.setDisplayName("§r"+BoxName+"§r");
		ArrayList<String> lore = new ArrayList<>();
		
		if (Box==0){
			lore.add("§a§l미르게임 랜덤 상자");
			lore.add("");
			lore.add("§r《구성품 중 하나 획득》");
		}
		if (Box == 1){
			lore.add("§a§l미르게임 선택 상자");
			lore.add("");
			lore.add("§r《구성품 중 하나 선택 획득》");
		}
		if (Box == 2){
			lore.add("§a§l미르게임 상자");
			lore.add("");
			lore.add("§r《구성품 전부 획득》");
		} 
		
		for (ItemStack list : this.list){
			if (list.hasItemMeta() && list.getItemMeta() != null) lore.add("§r- "+list.getItemMeta().getDisplayName()+ " "+ list.getAmount()+"개");
			else lore.add("§r- "+LanguageHelper.getItemDisplayName(list, p)+ " "+ list.getAmount()+"개");
		}
		m.setLore(lore);
		result.setItemMeta(m);
		return result;
	}
}
