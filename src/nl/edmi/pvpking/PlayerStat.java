package nl.edmi.pvpking;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;


/**
 * Created by Ivan on 4-9-2016.
 */
public class PlayerStat {
    public int Lives;
    public float Score;
    public Player player;
    public boolean online = true;
    public UUID id;

    public PlayerStat(int lives, int score, Player play) {
        Lives = lives;
        Score = score;
        player = play;
        id = player.getUniqueId();
    }

    public void PlayerRespawn() {
        player.sendMessage("You have " + Lives + " Lives left.");
        if (Lives >= 0) {
            World world = Bukkit.getWorlds().get(5);
            Location loc = new Location(world,0,63,0);
            player.teleport(loc);
        }else {
            Score = -1f;
            World world = Bukkit.getWorlds().get(5);
            Location loc = new Location(world,0,90,0);
            player.teleport(loc);
        }
    }

    public void PlayerDie() {
        Lives--;
        Score = 0;
    }



    public void PlayerKill(float score) {
        Bukkit.broadcastMessage("Kill");
        Bukkit.broadcastMessage(score+"");
        Score += score/2f;
        Bukkit.broadcastMessage(Score+"");
        if (Score >= Main.game.ScoreToWin) {
            Main.game.End(this);
        }
    }

}
