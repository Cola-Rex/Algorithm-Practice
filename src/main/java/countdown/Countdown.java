package countdown;

import java.util.concurrent.TimeUnit;

/**
 * 倒计时 单线程 实现
 */
public class Countdown {

    private int limitSec;

    public Countdown(int limitSec) {
        this.limitSec = limitSec;
    }

    private void countdown() throws InterruptedException {
        System.out.println("倒计时： " + limitSec + "秒开始");
        while (limitSec > 0) {
            TimeUnit.SECONDS.sleep(1); //休息1秒
            System.out.println("剩余： " + --limitSec + "秒");
        }
        System.out.println("午时已到");
    }

    public static void main(String[] args) {
        try {
            new Countdown(5).countdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
