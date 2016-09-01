package nl.edmi.pvpking.Commands;

import nl.edmi.pvpking.Main;
import org.bukkit.command.CommandSender;

/**
 * Created by Ivan on 25-8-2016.
 */
public abstract class com {

    Main main;

    public com(Main Tmain) {
        main = Tmain;
    }

    public void Execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {

    }

}
