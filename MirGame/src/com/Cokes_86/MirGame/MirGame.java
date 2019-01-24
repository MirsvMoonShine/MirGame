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

import com.Cokes_86.MirGame.System.Box.Box;
import com.Cokes_86.MirGame.System.Box.BoxGui;
import com.Cokes_86.MirGame.System.Box.CollectBox;
import com.Cokes_86.MirGame.System.Box.RandomBox;
import com.Cokes_86.MirGame.System.Box.SelectBox;
import com.Cokes_86.MirGame.System.Setting.Setting;
import com.Cokes_86.MirGame.System.Sliding.Sliding;
import com.Cokes_86.MirGame.System.SlotShop.ClickListener;
import com.Cokes_86.MirGame.System.SlotShop.GameInventory;
import com.Cokes_86.MirGame.System.SlotShop.StartGame;
import com.Cokes_86.MirRewards.MirRewards;

import net.milkbowl.vault.economy.Economy;

public class MirGame extends JavaPlugin{
	
	public GameInventory gi = new GameInventory(this);
	public ClickListener ci = new ClickListener(this);
	public GameCoins gc = new GameCoins();
	public StartGame sg = new StartGame(this);
	public boolean hottime = false;
	public Sliding sl = new Sliding(this);
	public Setting st = new Setting(this);
	
	public static RegisteredServiceProvider<MirRewards> rsp = Bukkit.getServer().getServicesManager().getRegistration(MirRewards.class);
	public MirRewards mr = rsp.getProvider();
	
	public BoxGui bg = new BoxGui(this);
	
	public ArrayList<Box> boxs = new ArrayList<>();
	
	public Economy eco = null;
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(ci,this);
		getServer().getPluginManager().registerEvents(bg,this);;
		getServer().getPluginManager().registerEvents(sl,this);
		getServer().getPluginManager().registerEvents(st,this);
		setupEconomy();
		
		box();
	}
	
	private void box() {
		boxs.add(new CollectBox("§l(구)슬롯 보상", new ItemStack[]{gc.get완두콩(35),gc.getEye(2), gc.getCloth(5)})); //(구)슬롯 보상
		//슬롯 보상
		boxs.add(new CollectBox("§l슬롯 보상 <흙>", new ItemStack[]{gc.get완두콩(1)}));
		boxs.add(new RandomBox("§l슬롯 보상 <나무>",new ItemStack[]{gc.getCoin(30), gc.get완두콩(5)}));
		boxs.add(new RandomBox("§l슬롯 보상 <돌>",new ItemStack[]{gc.getCoin(30), gc.get완두콩(5), gc.getEye(1)}));
		boxs.add(new SelectBox("§l슬롯 보상 <철>",new ItemStack[]{gc.getCoin(30), gc.get완두콩(5), gc.getEye(1)}));
		boxs.add(new CollectBox("§l슬롯 보상 <금>",new ItemStack[]{gc.get완두콩(15), gc.getEye(1), new ItemStack(Material.DIAMOND,5)}));
		boxs.add(new RandomBox("§l슬롯 보상 <다이아몬드>",new ItemStack[]{gc.get완두콩(20), gc.getEye(2), new ItemStack(Material.TOTEM,1)}));
		boxs.add(new RandomBox("§l슬롯 보상 <옵시디언>",new ItemStack[]{gc.get완두콩(30), gc.getEye(5), new ItemStack(Material.TOTEM,3)}));
		boxs.add(new SelectBox("§l슬롯 보상 <에메랄드>",new ItemStack[]{mr.gr.MendingBook(1), gc.get완두콩(30), gc.getEye(5), new ItemStack(Material.TOTEM,5)}));
		boxs.add(new SelectBox("§l슬롯 보상 <배드락>",new ItemStack[]{mr.gr.MendingBook(1), gc.getEye(7), gc.get완두콩(30), new ItemStack(Material.BEACON,1),new ItemStack(Material.TOTEM,5)}));
		boxs.add(new CollectBox("§l슬롯 보상 <네더의 별>",new ItemStack[]{mr.gr.MendingBook(1), gc.getEye(7), new ItemStack(Material.BEACON,1), new ItemStack(Material.TOTEM,5)}));
		//슬라이딩 보상
		boxs.add(new CollectBox("§r슬라이딩 보상 <1단계>", new ItemStack[]{new ItemStack(Material.COAL, 20)}));
		boxs.add(new CollectBox("§r슬라이딩 보상 <2단계>", new ItemStack[]{new ItemStack(Material.COAL, 20), new ItemStack(Material.COOKED_BEEF, 5)}));
		boxs.add(new CollectBox("§r슬라이딩 보상 <3단계>", new ItemStack[]{new ItemStack(Material.COAL, 20), new ItemStack(Material.COOKED_BEEF, 5)
				, new ItemStack(Material.DIAMOND,1)}));
		boxs.add(new CollectBox("§r슬라이딩 보상 <4단계>", new ItemStack[]{new ItemStack(Material.COAL, 20), new ItemStack(Material.COOKED_BEEF, 5)
				, new ItemStack(Material.DIAMOND,1), gc.getCoin(1)}));
		boxs.add(new CollectBox("§l슬라이딩 보상 <5단계>", new ItemStack[]{new ItemStack(Material.COAL, 20), new ItemStack(Material.COOKED_BEEF, 5)
				, new ItemStack(Material.DIAMOND,1), gc.getCoin(1), gc.get완두콩(5)}));
		boxs.add(new CollectBox("§l슬라이딩 보상 <6단계>", new ItemStack[]{new ItemStack(Material.COAL, 20), new ItemStack(Material.COOKED_BEEF, 5)
				, new ItemStack(Material.DIAMOND,1), gc.getCoin(1), gc.get완두콩(5), gc.getEye(2)}));
		boxs.add(new CollectBox("§l슬라이딩 보상 <7단계>", new ItemStack[]{new ItemStack(Material.COAL, 20), new ItemStack(Material.COOKED_BEEF, 5)
				, new ItemStack(Material.DIAMOND,1), gc.getCoin(1), gc.get완두콩(5), gc.getEye(2), new ItemStack(Material.TOTEM, 1)}));
		boxs.add(new CollectBox("§l슬라이딩 보상 <8단계>", new ItemStack[]{new ItemStack(Material.COAL, 20), new ItemStack(Material.COOKED_BEEF, 5)
				, new ItemStack(Material.DIAMOND,1), gc.getCoin(1), gc.get완두콩(5), gc.getEye(2), new ItemStack(Material.TOTEM,1),
				new ItemStack(Material.EXP_BOTTLE, 10)}));
		boxs.add(new CollectBox("§a§l슬라이딩 보상 <9단계>", new ItemStack[]{new ItemStack(Material.COAL, 20), new ItemStack(Material.COOKED_BEEF, 5)
				, new ItemStack(Material.DIAMOND,1), gc.getCoin(1), gc.get완두콩(5), gc.getEye(2), new ItemStack(Material.TOTEM,1),
				new ItemStack(Material.EXP_BOTTLE, 10), mr.gr.MendingBook(1)}));
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
			if (label.equals("미르게임") && args.length == 0){
				gi.openMain(p);
			} else if (label.equals("미르게임") && args.length > 0){
				if (p.isOp()){
					if (args[0].equals("핫타임") && args.length == 1){
						if (!hottime){
							Bukkit.broadcastMessage("§6[§9미르 게임§6]§r 핫타임이 시작되었습니다!");
							hottime = true;
						} else {
							Bukkit.broadcastMessage("§6[§9미르 게임§6]§r 핫타임이 끝났습니다!");
							hottime = false;
						}
					} else if (args[0].equals("박스")){
						boxs.clear();
						box();
						p.sendMessage("§6[§9미르 게임§6]§r 박스가 재부팅되었습니다.");
					}
				}
			}
		} else {
			System.out.println("[미르게임] 콘솔로는 실행할 수 없습니다.");
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
