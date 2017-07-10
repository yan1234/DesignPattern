package com.yanling.java.designpattern.singleton;

/**
 * 单例模式
 * 整个运行环境中只有一个实例对象存在
 * @author yanling 
 * @date 2017-07-10
 */
public class SingleInstance {


    //定义静态对象用于保存该单例对象
    private static SingleInstance instance1;
    private volatile static SingleInstance instance;

    /**
     * 定义静态方法返回该单例对象（简单的线程安全，效率较低）
     * @return
     */
    public static SingleInstance getInstance1(){
        /**
         * 使用同步代码块来保证线程安全，但执行效率会比较低,
         * 主要是因为主要的业务逻辑都出于同步代码块中，若执行时间过长会导致其他线程阻塞
         */
        synchronized (SingleInstance.class){
            if (instance1 == null){
                //执行一些初始化操作，可能比较耗时
                //doSomething
                instance1 = new SingleInstance();
            }
        }
        return instance1;
    }

    /**
     * 定义静态方法返回单例对象（双锁机制，效率相对较高）
     * @return
     */
    public static SingleInstance getInstance(){
        //这里由于将instance实例定义为volatile,可以保证各个线程间该实例的可见性
        if (instance == null){
            //执行初始化操作
            //doSomething
            synchronized (SingleInstance.class){
                //实例化时进行再次判断（双锁机制），保证只有一个实例被创建
                if (instance == null){
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }


    private SingleInstance(){

    }
    
}
