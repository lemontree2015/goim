package com.company.task;

import com.company.net.Skynet;

public class ReadThread extends Thread {
    Skynet skynet;

    public ReadThread(Skynet s) {
        this.skynet = s;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                this.skynet.Read();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
