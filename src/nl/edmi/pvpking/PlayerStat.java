package nl.edmi.pvpking;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Created by Ivan on 4-9-2016.
 */
public class PlayerStat {
    public int Lives;
    public float Score;
    public Player player;
    public boolean online = true;


    public PlayerStat(int lives, int score, Player play) {
        Lives = lives;
        Score = score;
        player = play;
    }

    public void PlayerRespawn() {
        if (Lives >= 0) {
            World world = Bukkit.getWorlds().get(5);
            Location loc = new Location(world,0,120,0);
            player.teleport(loc);
        }else {
            World world = Bukkit.getWorlds().get(5);
            Location loc = new Location(world,0,140,0);
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
        if (Score >= 1000) {
            Main.game.End(this);
        }
    }

}
