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
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!Main.game.battle) return;
        Player player = e.getPlayer();
        PlayerStat playerStat =  Main.game.GetStatOfPlayer(player);
        playerStat.online = true;
        playerStat.PlayerRespawn();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent e) {
        if(!Main.game.battle) return;
        Player player = (Player) e.getPlayer();
        PlayerStat playerStat =  Main.game.GetStatOfPlayer(player);
        playerStat.PlayerDie();
        playerStat.online = false;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if(!Main.game.battle) return;
        Player player = e.getPlayer();
        PlayerStat playerStat =  Main.game.GetStatOfPlayer(player);
        playerStat.PlayerRespawn();

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(PlayerDeathEvent e){
        if(!Main.game.battle) return;
        Player player = (Player) e.getEntity();
        PlayerStat playerStat =  Main.game.GetStatOfPlayer(player);

        Player killer = player.getKiller();
        if (killer != null) {
            PlayerStat playerStat2 =  Main.game.GetStatOfPlayer(player);
            playerStat2.PlayerKill(playerStat.Score);
        }

        playerStat.PlayerDie();
        e.setKeepInventory(true);

    }

}
