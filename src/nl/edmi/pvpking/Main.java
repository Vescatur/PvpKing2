package nl.edmi.pvpking;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import nl.edmi.pvpking.Commands.comList;
import nl.edmi.pvpking.Commands.comStartpvp;
import nl.edmi.pvpking.Commands.comStoppvp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import nl.edmi.pvpking.Commands.com;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    public com[] Commands;

    public static Game game = null;
    public static GameListener gameListener = null;
    public static Timer timer = null;

    @Override
    public void onEnable() {
        Bukkit.broadcastMessage("PvpKing is starting up.");
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (!setupPermissions() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Commands = new com[3];
        Commands[0] = new comStartpvp(this);
        Commands[1] = new comList(this);
        Commands[2] = new comStoppvp(this);
        game = new Game(this);
        gameListener = new GameListener(this);
        timer = new Timer(this);

        Bukkit.getPluginManager().registerEvents(gameListener, this);
        Bukkit.broadcastMessage("PvpKing has succesfully started.");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("startpvp")) {
            Commands[0].Execute(sender,cmd,label,args);
        }else if (cmd.getName().equalsIgnoreCase("listalive")) {
            Commands[1].Execute(sender,cmd,label,args);
        }else if (cmd.getName().equalsIgnoreCase("stoppvp")) {
            Commands[2].Execute(sender, cmd, label, args);
        }
        return  true;
    }
}
