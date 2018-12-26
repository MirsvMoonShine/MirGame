package com.Cokes_86.MirGame;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameRewards {
	
	public ItemStack DragonSlayer(int a){
		ItemStack get = new ItemStack(Material.DIAMOND_SWORD,1);
		ItemMeta getm = get.getItemMeta();
		if (a==0){ //봉인된 드래곤 슬레이어
			getm.setDisplayName("§r§4봉인된 드래곤 슬레이어");
			getm.setLore(Arrays.asList(new String[]{"§r§l검을 손에 쥐자 엄청난 미지의 힘이 흘러나온다.","§r§l한번만 건들어도 부서질 것만 같다."}));
			getm.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
			getm.addEnchant(Enchantment.KNOCKBACK, 3, true);
			getm.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
			getm.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
			getm.addEnchant(Enchantment.SWEEPING_EDGE, 3, true);
			get.setDurability((short)1561);
		} else if (a == 1){ //드래곤 슬레이어
			getm.setDisplayName("§r§4§l드래곤 슬레이어");
			getm.setLore(Arrays.asList(new String[]{"§r§l전설로 전해지는 고대의 용, 미르를 처치한 검,","§r§l드래곤 슬레이어가 지금 여기에 강림하리라."}));
			getm.addEnchant(Enchantment.DAMAGE_ALL, 15, true);
			getm.addEnchant(Enchantment.KNOCKBACK, 5, true);
			getm.addEnchant(Enchantment.DURABILITY, 10, true);
			getm.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
			getm.addEnchant(Enchantment.LOOT_BONUS_MOBS, 5, true);
			getm.addEnchant(Enchantment.SWEEPING_EDGE, 5, true);	
		}
		get.setItemMeta(getm);
		return get;
	}
	
	public ItemStack WatermelonSword(int a){
		ItemStack get = new ItemStack(Material.DIAMOND_SWORD,1);
		ItemMeta getm = get.getItemMeta();
		if (a==0){
			getm.setDisplayName("§r커먼 §r§2수박아저씨의 칼");
		} else if (a==1){
			getm.setDisplayName("§e언커먼 §r§2수박아저씨의 칼");
		} else if (a==2){
			getm.setDisplayName("§6미르 §r§2수박아저씨의 칼");
		}
		get.setItemMeta(getm);
		return get;
	}
	
	public ItemStack UpgradeStone(int amount){
		ItemStack get = new ItemStack(Material.IRON_NUGGET,amount);
		ItemMeta m = get.getItemMeta();
		m.setDisplayName("§e초월석");
		m.setLore(Arrays.asList(new String[]{"§r의문의 힘이 담긴 돌."}));
		get.setItemMeta(m);
		return get;
	}
	
	public ItemStack MendingBook(int amount){
		ItemStack Book = new ItemStack(Material.ENCHANTED_BOOK,amount);
		ItemMeta bookmeta = Book.getItemMeta();
		bookmeta.setDisplayName("§r§e수선책");
		bookmeta.addEnchant(Enchantment.MENDING, 1, true);
		Book.setItemMeta(bookmeta);
		return Book;
	}
}
