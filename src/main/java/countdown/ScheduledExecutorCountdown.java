package countdown;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用ScheduledExecutor实现
 * <p>
 * 注意，这玩意不稳定，可能倒数至 -1 秒，慎用好吧
 */
public class ScheduledExecutorCountdown {

    private volatile int limitSec;
    private int currentSec;

    public ScheduledExecutorCountdown(int limitSec) {
        this.limitSec = limitSec;
        this.currentSec = limitSec;
    }

    //启动1个线程池，1秒执行一次，并在倒计时结束时停止
    private void countdown() throws InterruptedException {
        System.out.println("倒计时：" + limitSec + "秒开始");
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(limitSec);
        service.shutdownNow(); //停止线程池
        System.out.println("午时已到");
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("时间剩余" + --currentSec + "秒");
        }
    }

    public static void main(String[] args) {
        try {
            new ScheduledExecutorCountdown(3).countdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
