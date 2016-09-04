package nl.edmi.pvpking;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

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
        BukkitTask task2 = new ChangePvp(main).runTaskLater(main,60*20);
        BukkitTask task = new UpdateGame(main).runTaskTimer(main,5,5);
    }

    public void RespawnPlayer(PlayerStat playerStat) {
        BukkitTask task = new RespawnPlayer(main,playerStat).runTaskLater(main, 1);
    }

    class RespawnPlayer extends BukkitRunnable {
        private Main main;
        private PlayerStat playerStat;

        public RespawnPlayer(Main mainT,PlayerStat playerStatT) {
            main = mainT;
            playerStat = playerStatT;
        }

        @Override
        public void run() {
            playerStat.PlayerRespawn();
        }
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

    class UpdateGame extends BukkitRunnable {
        private Main main;

        public UpdateGame (Main mainT) {
            main = mainT;
        }

        @Override
        public void run() {
            if(!Main.game.UpdateGame()) {
                this.cancel();
            }
        }
    }

}
