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
		if (a==0){ //���ε� �巡�� �����̾�
			getm.setDisplayName("��r��4���ε� �巡�� �����̾�");
			getm.setLore(Arrays.asList(new String[]{"��r��l���� �տ� ���� ��û�� ������ ���� �귯���´�.","��r��l�ѹ��� �ǵ� �μ��� �͸� ����."}));
			getm.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
			getm.addEnchant(Enchantment.KNOCKBACK, 3, true);
			getm.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
			getm.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
			getm.addEnchant(Enchantment.SWEEPING_EDGE, 3, true);
			get.setDurability((short)1561);
		} else if (a == 1){ //�巡�� �����̾�
			getm.setDisplayName("��r��4��l�巡�� �����̾�");
			getm.setLore(Arrays.asList(new String[]{"��r��l������ �������� ����� ��, �̸��� óġ�� ��,","��r��l�巡�� �����̾ ���� ���⿡ �����ϸ���."}));
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
			getm.setDisplayName("��rĿ�� ��r��2���ھ������� Į");
		} else if (a==1){
			getm.setDisplayName("��e��Ŀ�� ��r��2���ھ������� Į");
		} else if (a==2){
			getm.setDisplayName("��6�̸� ��r��2���ھ������� Į");
		}
		get.setItemMeta(getm);
		return get;
	}
	
	public ItemStack UpgradeStone(int amount){
		ItemStack get = new ItemStack(Material.IRON_NUGGET,amount);
		ItemMeta m = get.getItemMeta();
		m.setDisplayName("��e�ʿ���");
		m.setLore(Arrays.asList(new String[]{"��r�ǹ��� ���� ��� ��."}));
		get.setItemMeta(m);
		return get;
	}
	
	public ItemStack MendingBook(int amount){
		ItemStack Book = new ItemStack(Material.ENCHANTED_BOOK,amount);
		ItemMeta bookmeta = Book.getItemMeta();
		bookmeta.setDisplayName("��r��e����å");
		bookmeta.addEnchant(Enchantment.MENDING, 1, true);
		Book.setItemMeta(bookmeta);
		return Book;
	}
}
