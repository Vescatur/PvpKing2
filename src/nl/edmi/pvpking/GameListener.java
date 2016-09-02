package nl.edmi.pvpking;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by Ivan on 1-9-2016.
 */
public class GameListener implements Listener {

    //Geen blocken slopen
    //Gedoden mensen uit de lijst halen -> teleporteren
    //Leaven uit de lijst
    //Joinen moet geteleporteert worden

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
        if(Main.game.battle) {
            Player player = e.getPlayer();
            World world = Bukkit.getWorlds().get(5);
            Location loc = new Location(world,0,140,0);
            player.teleport(loc);
        }
    }

    public void onPlayerQuit(PlayerQuitEvent e) {
        if(Main.game.battle) {
            if(Main.game.PlayersAlive.contains(e.getPlayer())) {
                Main.game.PlayersAlive.remove(e.getPlayer());
            }
        }
    }

    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if(Main.game.battle) {
            Player player = e.getPlayer();
            if(Main.game.PlayersAlive.contains(player)) {
                Main.game.PlayersAlive.remove(player);
            }
            World world = Bukkit.getWorlds().get(5);
            Location loc = new Location(world,0,140,0);
            player.teleport(loc);
        }
    }

}
