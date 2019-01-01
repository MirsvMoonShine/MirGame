package com.Cokes_86.MirGame.System.Box;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
	
	public ItemStack getBox(){
		if (Box==0){
			ItemStack result = new ItemStack(Material.CHEST,1);
			ItemMeta m = result.getItemMeta();
			m.setDisplayName("��r"+BoxName);
			ArrayList<String> lore = new ArrayList<>();
			lore.add("��a��l�̸����� ���� ����");
			lore.add("");
			lore.add("��r������ǰ �� �ϳ� ȹ�桷");
			for (ItemStack list : this.list){
				if (list.hasItemMeta() && list.getItemMeta() != null) lore.add("��r- "+list.getItemMeta().getDisplayName()+ " "+ list.getAmount()+"��");
				else lore.add("��r- "+list.getType().toString().replace("_", " ").toLowerCase()+ " "+ list.getAmount()+"��");
			}
			m.setLore(lore);
			result.setItemMeta(m);
			return result;
		} else if (Box == 1){
			ItemStack result = new ItemStack(Material.CHEST,1);
			ItemMeta m = result.getItemMeta();
			m.setDisplayName("��r"+BoxName);
			ArrayList<String> lore = new ArrayList<>();
			lore.add("��a��l�̸����� ���� ����");
			lore.add("");
			lore.add("��r������ǰ �� �ϳ� ���� ȹ�桷");
			for (ItemStack list : this.list){
				if (list.hasItemMeta() && list.getItemMeta() != null) lore.add("��r- "+list.getItemMeta().getDisplayName()+ " "+ list.getAmount()+"��");
				else lore.add("��r- "+list.getType().toString().replace("_", " ").toLowerCase()+ " "+ list.getAmount()+"��");
			}
			m.setLore(lore);
			result.setItemMeta(m);
			return result;
		} else if (Box == 2){
			ItemStack result = new ItemStack(Material.CHEST,1);
			ItemMeta m = result.getItemMeta();
			m.setDisplayName("��r"+BoxName);
			ArrayList<String> lore = new ArrayList<>();
			lore.add("��a��l�̸����� ����");
			lore.add("");
			lore.add("��r������ǰ ���� ȹ�桷");
			for (ItemStack list : this.list){
				if (list.hasItemMeta() && list.getItemMeta() != null) lore.add("��r- "+list.getItemMeta().getDisplayName()+ " "+ list.getAmount()+"��");
				else lore.add("��r- "+list.getType().toString().replace("_", " ").toLowerCase()+ " "+ list.getAmount()+"��");
			}
			m.setLore(lore);
			result.setItemMeta(m);
			return result;
		} else {return null;}
	}
}