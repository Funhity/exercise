package com.huaxia.rocketmq.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private static volatile boolean running = true;
    private static ClassPathXmlApplicationContext context;

    public static void main(String[] args) {
        startServer();
//    	System.out.println("Press any key to Quit!");
//    	System.in.read();
        synchronized (Consumer.class) {
            while (running) {
                try {
                    Consumer.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void startServer() {
        context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                stopServer();
            }
        });
        logger.info("demo-consumer started!");
    }

    private static void stopServer() {
        context.close();
        synchronized (Consumer.class) {
            running = false;
            Consumer.class.notify();
        }
        logger.info("demo-consumer stopped!");
    }
}
