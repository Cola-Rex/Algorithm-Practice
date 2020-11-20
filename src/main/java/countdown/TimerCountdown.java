package countdown;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerCountdown {

    private int limitSec;
    private int currentSec;

    public TimerCountdown(int limitSec) {
        this.limitSec = limitSec;
        this.currentSec = limitSec;
    }

    private void countdown() throws InterruptedException {
        System.out.println("倒计时：" + limitSec + "秒开始");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("剩余：" + currentSec-- + "秒");
            }
        }, 0L, 1000L);
        TimeUnit.SECONDS.sleep(limitSec);
        timer.cancel();
        System.out.println("午时已到");
    }

    public static void main(String[] args) {
        try {
            new TimerCountdown(5).countdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
