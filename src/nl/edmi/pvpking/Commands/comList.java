package nl.edmi.pvpking.Commands;

import nl.edmi.pvpking.Main;
import nl.edmi.pvpking.PlayerStat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ivan on 2-9-2016.
 */
public class comList extends com {

    public comList(Main Tmain) {
        super(Tmain);
    }

    @Override
    public void Execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(Main.perms.playerHas((Player)sender,"pvpking.admin"))
        {
            Player player = (Player) sender;
            player.sendMessage("List of players Alive");
            for (PlayerStat p : Main.game.PlayersAlive) {
                player.sendMessage(p.player.getDisplayName());
            }
        }
    }
}