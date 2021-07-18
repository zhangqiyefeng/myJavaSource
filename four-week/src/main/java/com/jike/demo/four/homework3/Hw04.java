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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 功能描述: <br>
 * 〈作业3-4〉  4:代表第几种实现方式
 *
 * @author yida
 * @date 2021/7/17
 */
public class Hw04 {

    public static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        System.out.println("start....");

        // 在这里创建一个线程或线程池，
        // 异步->同步执行 下面方法
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> future = executor.submit(() -> {
            try {
                int result = sum();
                System.out.println("异步计算结果为：" + result);
                Thread t2 = Thread.currentThread();
                System.out.println("当前线程:" + t2.getName());
                return result;
            }
            catch (Exception e) {
                throw new IllegalStateException("task interrupted", e);
            }
        });
        try {
            Integer result = future.get();
            System.out.println("future done: " + future.isDone());
            executor.shutdownNow();
            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }


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
