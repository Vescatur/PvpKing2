package nl.edmi.pvpking.Commands;

import nl.edmi.pvpking.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ivan on 25-8-2016.
 */
public class comStartpvp extends com {

    public comStartpvp(Main Tmain) {
        super(Tmain);
    }

    @Override
    public void Execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(Main.perms.playerHas((Player)sender,"pvpking.admin")) {
            Main.game.Begin();
        }
    }
}