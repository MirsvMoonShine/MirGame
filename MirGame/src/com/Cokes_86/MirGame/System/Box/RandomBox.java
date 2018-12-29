package com.Cokes_86.MirGame.System.Box;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RandomBox extends Box{
	final String BoxName;
	final ItemStack[] list;
	
	public RandomBox(String name, ItemStack[] list){
		super(name,list, 0);
		this.BoxName = name;
		this.list = list;
	}
	
	public String getBoxName(){
		return BoxName;
	}
	
	public ItemStack[] getList(){
		return list;
	}
	
	public ItemStack getBox(){
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
	}
	
	public ItemStack getResult(){
		Random r = new Random();
		int a = r.nextInt(list.length);
		return list[a];
	}
}
