package nl.edmi.pvpking;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Ivan on 1-9-2016.
 */
public class GameListener implements Listener {

    public Main main;

    public GameListener(Main instance){
        main = instance;
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(PlayerDeathEvent e){
        if (e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();

        }
    }

    public void onPlayerJoin(PlayerJoinEvent e){
        e.getPlayer().sendMessage(ChatColor.DARK_BLUE+"Welcome to our server!");
    }


}
