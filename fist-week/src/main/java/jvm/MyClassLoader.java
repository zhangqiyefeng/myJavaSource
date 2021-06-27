/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2021 www.iwhalecloud.com
 * FileName: MyClassLoader
 * Author:   yida
 * Date:     2021/6/27 14:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package jvm;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 功能描述: <br>
 * 〈〉
 *
 * @author yida
 * @date 2021/6/27
 */
public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new MyClassLoader();
        // 加载相应的类
        Class<?> clazz = classLoader.loadClass("Hello");
        Method method = clazz.getMethod("hello");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        final String xlassPath = "old/Hello.xlass";

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xlassPath);
        try {
            // 读取数据
            int length = inputStream.available();
            byte[] bt = new byte[length];
            inputStream.read(bt);
            // 255-
            byte[] newArray = new byte[bt.length];
            for (int i = 0; i < bt.length; i++) {
                newArray[i] = (byte) (255 - bt[i]);
            }
            // 通知底层定义这个类
            return defineClass(name, newArray, 0, newArray.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
