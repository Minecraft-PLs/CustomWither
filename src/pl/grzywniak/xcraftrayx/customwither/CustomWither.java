package pl.grzywniak.xcraftrayx.customwither;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomWither extends JavaPlugin
{
	public void onEnable()
	{
		Config.createDefaultFile();
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		loadWithers();
		runSchedulerForTasks();
		System.out.println("CustomWither załadowany!");
	}
	
	public void onDisable()
	{
		System.out.println("CustomWither wyłączony!");
	}
	
	static List<Wither> withers = new ArrayList<Wither>();
	
	private void runSchedulerForTasks()
	{
		Bukkit.getScheduler().runTaskTimer(this, new Runnable()
		{
			public void run()
			{
				for(Wither w : withers)
				{
					attackThornsNearbyPlayers(w);
					spawnMobsNearbyWither(w);
					replaceNameWithHP(w);
				}
			}
		},0, 60);
	}
	
	private void attackThornsNearbyPlayers(Wither w)
	{
		if(w.getHealth() >= Config.thunderAtMinHP && w.getHealth() <= Config.thunderAtMaxHP)
		{
			Location witherLoc = w.getLocation();
			for(Player p : Bukkit.getOnlinePlayers())
			{
				Location playerLoc = p.getLocation();
				
				if(witherLoc.distance(playerLoc) <= 10)
				{
					if(new Random().nextBoolean())
					{
						p.getWorld().strikeLightning(p.getLocation());
					}
				}
			}
		}
	}
	
	private void spawnMobsNearbyWither(Wither w)
	{
		if(w.getHealth() >= Config.startRespMobsAtMinHP && w.getHealth() <= Config.startRespMobsAtMaxHP)
		{
			Location witherLoc = w.getLocation();
			
			if(new Random().nextBoolean())
			{
				for(int i = 0; i < new Random().nextInt(4); i++)
				{
					w.getWorld().spawnEntity(witherLoc, EntityType.ZOMBIE);
				}
			}
			else
			{
				for(int i = 0; i < new Random().nextInt(4); i++)
				{
					w.getWorld().spawnEntity(witherLoc, EntityType.SKELETON);
				}
			}
			
		}
	}
	
	private void replaceNameWithHP(Wither w)
	{
		w.setCustomName("Wither: "+ (int)w.getHealth() + " HP");
	}
	
	private void loadWithers()
	{
		for(World w : Bukkit.getWorlds())
		{
			for(Entity e : w.getEntities())
			{
				if(e.getType() == EntityType.WITHER)
				{
					withers.add((Wither)e);
				}
			}
		}
	}
}