/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2021 www.iwhalecloud.com
 * FileName: Hw02
 * Author:   yida
 * Date:     2021/7/17 22:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.jike.demo.four.homework3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述: <br>
 * 〈作业3-3〉  3:代表第几种实现方式
 *
 * @author yida
 * @date 2021/7/17
 */
public class Hw03 {

    final static Lock lock = new ReentrantLock();
    final static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("start....");
                condition.await();
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        });
        t1.start();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Runnable task = () -> {
            lock.lock();
            try {
                // 这是得到的返回值
                int result = sum();
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为：" + result);
                Thread t2 = Thread.currentThread();
                System.out.println("当前线程:" + t2.getName());
                synchronized (lock) {
                    condition.signalAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Thread thread = new Thread(task);
        thread.setName("第二个线程");
        thread.start();

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
