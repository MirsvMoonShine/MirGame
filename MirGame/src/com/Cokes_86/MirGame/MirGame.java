package com.Cokes_86.MirGame;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.Cokes_86.MirGame.Box.Box;
import com.Cokes_86.MirGame.Box.BoxGui;
import com.Cokes_86.MirGame.Box.CollectBox;
import com.Cokes_86.MirGame.Box.RandomBox;
import com.Cokes_86.MirGame.Box.SelectBox;
import com.Cokes_86.MirGame.StartSystem.ClickListener;
import com.Cokes_86.MirGame.StartSystem.CraftListener;
import com.Cokes_86.MirGame.StartSystem.FusionUpgradeListener;
import com.Cokes_86.MirGame.StartSystem.StartGame;
import com.Cokes_86.MirGame.StartSystem.SystemListener;
import com.Cokes_86.MirGame.UI.FusionUpgradeGui;
import com.Cokes_86.MirGame.UI.GameInventory;
import com.Cokes_86.MirGame.UI.Sliding;

import net.milkbowl.vault.economy.Economy;

public class MirGame extends JavaPlugin{
	
	public GameInventory gi = new GameInventory(this);
	public SystemListener sys = new SystemListener(this);
	public ClickListener ci = new ClickListener(this);
	public GameCoins gc = new GameCoins();
	public GameRewards gr = new GameRewards();
	public StartGame sg = new StartGame(this);
	public boolean hottime = false;
	public Sliding sl = new Sliding(this);
	public CraftListener cl = new CraftListener(this);
	public WrittenBook wr = new WrittenBook();
	public FusionUpgradeGui fug = new FusionUpgradeGui(this);
	public FusionUpgradeListener ful = new FusionUpgradeListener(this);
	
	public BoxGui bg = new BoxGui(this);
	
	public ArrayList<Box> boxs = new ArrayList<>();
	
	public Economy eco = null;
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(ci,this);
		getServer().getPluginManager().registerEvents(bg,this);
		getServer().getPluginManager().registerEvents(sys,this);
		getServer().getPluginManager().registerEvents(cl,this);
		getServer().getPluginManager().registerEvents(ful,this);
		setupEconomy();
		
		boxs.add(new CollectBox("��l(��)���� ����", new ItemStack[]{gc.get�ϵ���(15),gc.getEye(1)})); //(��)���� ����
		//���� ����
		boxs.add(new CollectBox("��l���� ���� <��>", new ItemStack[]{gc.get�ϵ���(1)}));
		boxs.add(new RandomBox("��l���� ���� <����>",new ItemStack[]{gc.getCoin(30), gc.get�ϵ���(5)}));
		boxs.add(new RandomBox("��l���� ���� <��>",new ItemStack[]{gc.getCoin(30), gc.get�ϵ���(5), gc.getEye(1)}));
		boxs.add(new SelectBox("��l���� ���� <ö>",new ItemStack[]{gc.getCoin(30), gc.get�ϵ���(5), gc.getEye(1)}));
		boxs.add(new CollectBox("��l���� ���� <��>",new ItemStack[]{gc.get�ϵ���(15), gc.getEye(1), new ItemStack(Material.DIAMOND,5)}));
		boxs.add(new RandomBox("��l���� ���� <���̾Ƹ��>",new ItemStack[]{gc.get�ϵ���(20), gc.getEye(2), new ItemStack(Material.TOTEM,1)}));
		boxs.add(new RandomBox("��l���� ���� <�ɽõ��>",new ItemStack[]{gc.get�ϵ���(30), gc.getEye(5), new ItemStack(Material.TOTEM,3)}));
		boxs.add(new SelectBox("��l���� ���� <���޶���>",new ItemStack[]{gc.get�ϵ���(30), gc.getEye(5), new ItemStack(Material.TOTEM,5)}));
		boxs.add(new SelectBox("��l���� ���� <����>",new ItemStack[]{gc.getEye(5), gc.get�ϵ���(30), new ItemStack(Material.BEACON,1),new ItemStack(Material.TOTEM,5)}));
		boxs.add(new CollectBox("��l���� ���� <�״��� ��>",new ItemStack[]{gc.getEye(7), new ItemStack(Material.BEACON,1), new ItemStack(Material.TOTEM,5)}));
	}
	
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            eco = economyProvider.getProvider();
        }

        return (eco != null);
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (sender instanceof Player){
			Player p = (Player) sender;
			if (label.equals("�̸�����") && args.length == 0){
				gi.openMain(p);
			} else if (label.equals("�̸�����") && args.length > 0){
				if (args[0].equals("�ռ�") && args.length == 1){
					fug.openReadyFusion(p);
				} else if (args[0].equals("�ʿ�") && args.length == 1){
					fug.openReadyUpgrade(p);
				} else if (p.isOp()){
					if (args[0].equals("��Ÿ��") && args.length == 1){
						if (!hottime){
							Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��Ÿ���� ���۵Ǿ����ϴ�!");
							hottime = true;
						} else {
							Bukkit.broadcastMessage("��6[��9�̸� ���ӡ�6]��r ��Ÿ���� �������ϴ�!");
							hottime = false;
						}
					}
					else if (args[0].equals("�ڽ�") && args.length == 1){
						for (Box b : boxs){
							p.getInventory().addItem(b.getBox());
						}
						p.sendMessage("��6[��9�̸� ���ӡ�6]��r ��� ���� �ڽ��� ���޵Ǿ����ϴ�.");
					} else if (args[0].equals("�׽�Ʈ")){
						if (args[1].equals("�ʿ���") && args.length==2){
							p.getInventory().addItem(gr.UpgradeStone(1));
						} else if (args[1].equals("����") && args.length==2){
							p.getInventory().addItem(gr.WatermelonSword(0));
							p.getInventory().addItem(gr.WatermelonSword(1));
							p.getInventory().addItem(gr.WatermelonSword(2));
						} else if (args[1].equals("���������") && args.length==2){
							p.getInventory().addItem(gr.CrokersBow());
						}
					}
				}
			}
		} else {
			System.out.println("[�̸�����] �ַܼδ� ������ �� �����ϴ�.");
		}
		return false;
	}
	
	public void firework(Player p){
		Firework fw = (Firework) p.getWorld().spawn(p.getLocation(),Firework.class);
		FireworkMeta fm = fw.getFireworkMeta();
		FireworkEffect fe = FireworkEffect.builder().flicker(true).with(Type.BALL).withColor(Color.GREEN).build();
		fm.addEffect(fe);
		fw.setFireworkMeta(fm);
	}
	
	public Box getBox(String Title){
		Box box = null;
	    for (Box b : boxs){
		    if (b.getBoxName().equals(Title)){
			    box = b;
		    }
	    }
	    return box;
	}
}
