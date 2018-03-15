package com.zjl.iwechatmoments.http.loader;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zjl on 18-3-15.
 */

public class ThreadManager {

    private static final ThreadManager instance = new ThreadManager();
    private LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private ThreadPoolExecutor poolExecutor;


    public static ThreadManager getInstance(){
        return instance;
    }

    private ThreadManager() {
        //拒绝策略
        RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    taskQueue.put(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        poolExecutor = new ThreadPoolExecutor(4,10,10, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(4), rejectedHandler);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Runnable task = null;
                    try {
                        task = taskQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (task != null) {
                        poolExecutor.execute(task);
                    }
                }
            }
        };
        poolExecutor.execute(runnable);
    }

    public <T> boolean removeTask(Runnable task)
    {
        boolean result=false;
        /**
         * 阻塞式队列是否含有线程
         */
        if(taskQueue.contains(task))
        {
            taskQueue.remove(task);
        }else
        {
            result=poolExecutor.remove(task);
        }
        return  result;
    }

    public synchronized void execute(Runnable task) throws InterruptedException {
        taskQueue.put(task);
    }
}
