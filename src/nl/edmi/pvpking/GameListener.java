package nl.edmi.pvpking;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

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
    public void onBlockBreak(BlockBreakEvent e) {
        if(!Main.game.battle) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlace(BlockPlaceEvent e) {
        if(!Main.game.battle) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onIgniteBreak(BlockIgniteEvent e) {
        if(!Main.game.battle) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        if(!Main.game.battle) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBucketFill(PlayerBucketFillEvent e) {
        if(!Main.game.battle) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void PlayerSpawnMob(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getMaterial() == Material.MONSTER_EGG) {
            if(!Main.game.battle) return;
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if(!Main.game.battle) return;
        Player player = e.getPlayer();
        PlayerStat playerStat =  Main.game.GetStatOfPlayer(player);
        //playerStat.PlayerRespawn();
        Main.timer.RespawnPlayer(playerStat);
        player.sendMessage("You have " + playerStat.Lives + " Lives left.");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(PlayerDeathEvent e){
        if(!Main.game.battle) return;
        Player player = (Player) e.getEntity();
        PlayerStat playerStat =  Main.game.GetStatOfPlayer(player);

        Player killer = player.getKiller();
        if (killer != null) {
            Bukkit.broadcastMessage(killer.getDisplayName());
            PlayerStat playerStat2 =  Main.game.GetStatOfPlayer(killer);
            playerStat2.PlayerKill(playerStat.Score);
        }else {
            Bukkit.broadcastMessage("no killer");
        }

        playerStat.PlayerDie();
        e.setKeepInventory(true);

    }

}
