package nl.edmi.pvpking.Commands;

import nl.edmi.pvpking.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ivan on 12-9-2016.
 */
public class comStoppvp extends com {

    public comStoppvp(Main Tmain) {
        super(Tmain);
    }

    @Override
    public void Execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(Main.perms.playerHas((Player)sender,"pvpking.admin")) {
            Main.game.End(null);
        }
    }
}