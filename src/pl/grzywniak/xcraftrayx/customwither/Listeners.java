package pl.grzywniak.xcraftrayx.customwither;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener
{
    @EventHandler
    void onEntitySpawnEvent(EntitySpawnEvent e)
    {
    	if(e.getEntityType() == EntityType.WITHER)
    	{
    		Wither w = (Wither)e.getEntity();
    		AttributeInstance attribute = w.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    		attribute.setBaseValue(Config.baseHP);
    		w.setHealth(Config.baseHP);
    		CustomWither.withers.add(w);
    	}
    }
    
    @EventHandler
    void onEntityDeath(EntityDeathEvent e)
    {
    	if(e.getEntityType() == EntityType.WITHER)
    	{
    		Wither w = (Wither)e.getEntity();
    		if(CustomWither.withers.contains(w))
    		{
    			CustomWither.withers.remove(w);
    		}
    		
    		//DROP
    		e.getDrops().clear();
    		
    		e.getDrops().add(new ItemStack(Material.NETHER_STAR));
    		
    		//GOLD APPLE
    		if(new Random().nextInt(100) == 5)
    		{
    			ItemStack elytra = new ItemStack(Material.ELYTRA);
    			elytra.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
    			e.getDrops().add(elytra);
    		}
    		else if(new Random().nextInt(20) == 5)
    		{
    			ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
    			pickaxe.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
    			pickaxe.addEnchantment(Enchantment.LUCK, 5);
    			pickaxe.addEnchantment(Enchantment.DIG_SPEED, 8);
    			e.getDrops().add(pickaxe);
    		}
    		else if(new Random().nextInt(20) == 5)
    		{
    			e.getDrops().add(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
    		}
    		else if(new Random().nextInt(10) == 5)
    		{
    			e.getDrops().add(new ItemStack(Material.NETHERITE_INGOT));
    		}
    	}
    }
}
