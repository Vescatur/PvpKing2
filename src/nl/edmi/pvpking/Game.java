package nl.edmi.pvpking;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 1-9-2016.
 */
public class Game {
    public List<PlayerStat> PlayersAlive;
    public Main main;
    public Game(Main instance){
        main = instance;
    }

    public boolean pvp = false;
    public boolean battle = false;

    ScoreboardManager manager;
    Scoreboard board;
    Objective objective;

    public void Begin() {
        if (battle) return;
        battle = true;
        Bukkit.broadcastMessage("First person with a lvl of 30 is the KING.");
        PlayersAlive = new ArrayList<PlayerStat>();
        //Get All Players
        for(Player player:(List<Player>) Bukkit.getOnlinePlayers()) {
            PlayersAlive.add(new PlayerStat(3,0,player));
        }

        World world = Bukkit.getWorlds().get(5);

        for(PlayerStat playerStat: PlayersAlive) {
            //Teleport Players
            Player player = playerStat.player;
            playerStat.PlayerRespawn();

            //Heal and Saturate Players
            player.setHealth(player.getMaxHealth());
            player.setSaturation(20f);
        }

        //Beweeg worldborder
        WorldBorder border = world.getWorldBorder();
        border.setSize(50);
        border.setWarningDistance(0);
        border.setDamageAmount(100);
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        objective = board.registerNewObjective("score","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.BLUE + "Leaderboard");

        //keep inventory
        //Give Players absorption op basis van ranks
        //StartTimer
        //pvp aan over 10 seconden
        Main.timer.Begin();



    }

    public void TurnPvpOn(){
        pvp = true;
    }

    public boolean UpdateGame() {
        for(PlayerStat playerStat: PlayersAlive) {
            if (!playerStat.player.isDead()) {
                if (playerStat.Lives >=0) {
                    Player player = playerStat.player;
                    Location loc = player.getLocation();
                    loc.setY(loc.getY()-1);
                    Block block = loc.getBlock();
                    if(block.getType() == Material.IRON_BLOCK) {
                        playerStat.Score+=1;
                    }else if(block.getType() == Material.BEACON){
                        playerStat.Score+=3;
                    }
                }
            }
            if (playerStat.Score >= 200) {
                End(playerStat);
            }
        }

        if (PlayersAlive.size()>=1) {
            for(String line:board.getEntries()) {
                board.resetScores(line);
            }

            for(PlayerStat playerStat: PlayersAlive) {
                if (playerStat.player != null ) {
                    if (playerStat.Score != 0) {
                        Score line = objective.getScore(playerStat.player.getName());
                        line.setScore((int)playerStat.Score);
                    }
                }
            }
            if (Bukkit.getOnlinePlayers().size()>=1) {
                for (Player person : Bukkit.getOnlinePlayers()) {
                    person.setScoreboard(board);
                }
            }
        }

        return battle;
    }

    public void End(PlayerStat winner) {
        pvp = false;
        battle = false;
        board.clearSlot(DisplaySlot.SIDEBAR);
        World world = Bukkit.getWorlds().get(0);

        for(Player player: (List<Player>) Bukkit.getOnlinePlayers()) {
            Location loc = new Location(world,161.5,63,259.5);
            player.teleport(loc);
            player.setHealth(player.getMaxHealth());
            player.setSaturation(20f);
        }


        Bukkit.broadcastMessage(winner.player.getName() + " is the Winner");
    }

    public PlayerStat GetStatOfPlayer(Player player) {
        for(PlayerStat playerStat: PlayersAlive) {
            if(playerStat.player == player) {
                return playerStat;
            }
        }
        PlayerStat playerStat = new PlayerStat(-1,0,player);
        PlayersAlive.add(playerStat);
        return playerStat;
    }
}
