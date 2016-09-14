package nl.edmi.pvpking;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Calendar;

/**
 * Created by Ivan on 1-9-2016.
 */
public class Timer {

    public Main main;

    public Timer(Main instance){
        main = instance;
        Calendar cal = Calendar.getInstance();
        long TimeInSeconds = cal.getTimeInMillis()/1000;
        long SecondsFromFirstFriday = TimeInSeconds - 1474056000;//tien uur      //1472839200(acht uur);
        long SecondsFromAFriday = SecondsFromFirstFriday%(60*60*24*7);
        long SecondsTillFriday = (60*60*24*7) - SecondsFromAFriday;
        long TicksTillFriday = SecondsTillFriday*20;
        BukkitTask task = new UpdateGame(main).runTaskLater(main,TicksTillFriday);
    }



    public void BeginBattle() {
        Main.game.pvp = true;
        BukkitTask task2 = new ChangePvp(main).runTaskLater(main,60*20);
        BukkitTask task = new UpdateGame(main).runTaskTimer(main,5,5);
    }

    public void RespawnPlayer(PlayerStat playerStat) {
        BukkitTask task = new RespawnPlayer(main,playerStat).runTaskLater(main, 1);
    }

    class BeginTimer extends BukkitRunnable {
        private Main main;

        public BeginTimer(Main mainT,PlayerStat playerStatT) {
            main = mainT;
        }

        @Override
        public void run() {
            Main.game.Begin();
        }
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
