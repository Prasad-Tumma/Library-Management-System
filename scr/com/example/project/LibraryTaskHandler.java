package com.example.project;

public class LibraryTaskHandler extends Thread {
    private Runnable task;
    public LibraryTaskHandler(Runnable task) {
        this.task = task;
    }
    public void run() {
        task.run();
    }
}
