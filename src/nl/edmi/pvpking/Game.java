package nl.edmi.pvpking;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Ivan on 1-9-2016.
 */
public class Game {
    public List<Player> PlayersAlive;
    public Main main;
    public Game(Main instance){
        main = instance;
    }

    public boolean pvp = false;
    public boolean battle = false;


    public void Begin() {
        if (battle) return;

        //Get All Players
        PlayersAlive = (List<Player>) Bukkit.getOnlinePlayers();
        World world = Bukkit.getWorlds().get(5);

        for(Player player: PlayersAlive) {
            //Teleport Players
            Location loc = new Location(world,0,120,0);
            player.teleport(loc);

            //Heal and Saturate Players
            player.setHealth(player.getMaxHealth());
            player.setSaturation(20f);
        }

        //Beweeg worldborder
        WorldBorder border = world.getWorldBorder();
        border.setSize(400);
        border.setSize(20,600);

        //keep inventory
        //Give Players absorption op basis van ranks
        //StartTimer
        //pvp aan over 10 seconden
        main.timer.Begin();
        battle = true;


    }

    public void TurnPvpOn(){
        pvp = true;
    }

    public boolean ChangeHealthBeam() {
        return true;
    }

    public void End() {
        pvp = false;
        battle = false;

        PlayersAlive = (List<Player>) Bukkit.getOnlinePlayers();

        //teleport iedereen naar spawn
        World world = Bukkit.getWorlds().get(0);
        for(Player player: PlayersAlive) {
            Location loc = new Location(world,161.5,63,259.5);
            player.teleport(loc);
            player.setHealth(player.getMaxHealth());
            player.setSaturation(20);
        }


        //Display winnaar
        //permisions winnaar
        //Spawn verbouwen met een bordje van de winnaar
    }

}
