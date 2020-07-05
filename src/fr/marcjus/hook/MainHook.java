package fr.marcjus.hook;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MainHook extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player player = e.getPlayer();
		Action action = e.getAction();
		if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)return;
		if(player.getInventory().getItemInMainHand().getType().equals(Material.FISHING_ROD)){
			player.setVelocity(player.getVelocity().normalize().multiply(1.5));
			return;
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		Entity ent = e.getEntity();
		if(ent instanceof Player){
			Player player = (Player) ent;
			if(e.getCause().equals(DamageCause.FALL)){
				if(player.getInventory().getItemInMainHand().getType().equals(Material.FISHING_ROD)){
					e.setCancelled(true);
				}
			}
		}
	}

}
