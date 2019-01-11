package org.sysu.activitiservice.component;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

//用于计算
@Component
public class Counter {
    //for windows
    public static String prefix = "E:\\workspace\\temp\\scheduler\\";
    //for linux
//    public static String prefix = "/home/stack/hqk/scheduler/";

    //先计算总的替换次数
    private LongAdder removeEldestEntryTotal;

    //用于计算每秒发生替换的次数
    String fileForremoveEldestEntry = prefix + "removeEldestEntry";
    private LongAdder removeEldestEntry;
    FileWriter writerForRemoveEldestEntry;

    @PostConstruct
    public void init() {
        this.removeEldestEntry = new LongAdder();
        this.removeEldestEntryTotal = new LongAdder();
//        try {
//            writerForRemoveEldestEntry = new FileWriter(fileForremoveEldestEntry);
//        } catch (IOException e) {
//        }
    }

    public void increaseRemoveEldestEntry() {
        this.removeEldestEntry.increment();
    }

    public void increaseRemoveEldestEntryTotal() {
        this.removeEldestEntryTotal.increment();
    }

    private class Task implements Runnable {
       FileWriter writerForRemoveEldestEntry = Counter.this.writerForRemoveEldestEntry;
       LongAdder removeEldestEntry = Counter.this.removeEldestEntry;

       @Override
        public void run() {

       }
    }

}
