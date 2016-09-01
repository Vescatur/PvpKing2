package nl.edmi.pvpking;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Ivan on 1-9-2016.
 */
public class Game {
    List<Player> PlayersAlive;
    public Main main;

    public Game(Main instance){
        main = instance;
    }

    public void Begin() {
        //Get All Players
        PlayersAlive = (List<Player>) Bukkit.getOnlinePlayers();

        //Teleport Players
        for(Player player: PlayersAlive) {
            Location loc = new Location(Bukkit.getWorlds().get(5),0,120,0);
            player.teleport(loc);
        }
        //Heal and Saturate Players
        //keep inventory
        //Give Players absorption op basis van ranks
        //StartTimer
    }

    public void DuringBattle() {



    }

    public void End() {

    }

}
