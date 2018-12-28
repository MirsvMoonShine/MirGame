package com.Cokes_86.MirGame;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;

public class WrittenBook {
	
	public void openBook(ItemStack book, Player p) {
		int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);

        ByteBuf buf = Unpooled.buffer(256);
        buf.setByte(0, (byte)0);
        buf.writerIndex(1);

        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        p.getInventory().setItem(slot, old);
    }
	
	public static ItemStack book(String title, String author, String... pages) {  // �鿩����� \n
        ItemStack is = new ItemStack(Material.WRITTEN_BOOK, 1);
        net.minecraft.server.v1_12_R1.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
        NBTTagCompound bd = new NBTTagCompound();
        bd.setString("title", title);
        bd.setString("author", author);
        NBTTagList bp = new NBTTagList();
        for(String text : pages) {
            bp.add(new NBTTagString(text));
        }
        bd.set("pages", bp);
        nmsis.setTag(bd);
        is = CraftItemStack.asBukkitCopy(nmsis);
        return is;
    }
	
	public void openShopWiki(Player p){
		p.closeInventory();
		ItemStack book = book("��������","Cokes_86", new String[]{"<���� ����>\n\n���� �������� Ŭ�� �� �ٷ� ������ �˴ϴ�.\n\n��4���ε� �巡�� �����̾��r��0 3���� ��� �ռ��� ���� ��4��l�巡�� �����̾��0�� ���� �� �ֽ��ϴ�."});
		openBook(book,p);
	}
}
