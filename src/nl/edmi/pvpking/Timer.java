package nl.edmi.pvpking;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

/**
 * Created by Ivan on 1-9-2016.
 */
public class Timer {

    public Main main;

    public Timer(Main instance){
        main = instance;
    }

    public void Begin() {
        Main.game.pvp = true;
        BukkitTask task2 = new ChangeHealthBeam(main).runTaskLater(main,60*20);
        BukkitTask task = new ChangeHealthBeam(main).runTaskTimer(main,60*20,30*20);
    }

    class ChangePvp extends BukkitRunnable {
        private Main main;

        public ChangePvp(Main mainT) {
            main = mainT;
        }

        @Override
        public void run() {
            Main.game.TurnPvpOn();
        }
    }

    class ChangeHealthBeam extends BukkitRunnable {
        private Main main;

        public ChangeHealthBeam (Main mainT) {
            main = mainT;
        }

        @Override
        public void run() {
            if(Main.game.ChangeHealthBeam() ) {
                this.cancel();
            }
        }
    }

}
