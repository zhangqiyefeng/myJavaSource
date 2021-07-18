/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2021 www.iwhalecloud.com
 * FileName: Hw05
 * Author:   yida
 * Date:     2021/7/18 14:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.jike.demo.four.homework3;

import java.util.concurrent.CountDownLatch;

/**
 * 功能描述: <br>
 * 〈作业3-5〉  5:代表第几种实现方式
 *
 * @author yida
 * @date 2021/7/18
 */
public class Hw05 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start....");
        long start=System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new readNum(countDownLatch)).start();
        countDownLatch.await();

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    static class readNum  implements Runnable{

        private CountDownLatch latch;

        public readNum(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            int result = sum();
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);
            Thread t2 = Thread.currentThread();
            System.out.println("子线程:" + t2.getName());
            latch.countDown();
        }

        private static int sum() {
            return fibo(36);
        }

        private static int fibo(int a) {
            if ( a < 2)
                return 1;
            return fibo(a-1) + fibo(a-2);
        }

    }

}
