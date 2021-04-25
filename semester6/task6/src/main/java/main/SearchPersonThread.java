package main;


import rwlock.ReadWriteLock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SearchPersonThread extends Thread {
    protected String path;
    protected String person;
    protected String phone;
    private final ReadWriteLock lock;

    public SearchPersonThread(ReadWriteLock lock, String path, String phone) {
        this.lock = lock;
        this.path = path;
        this.phone = phone;
    }

    @Override
    public void run() {
        try {
            lock.readLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Search person thread lock.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String data[] = line.split(" - ", 2);
                if (data[1].equals(phone)) {
                    person = data[0];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (person != null)
            System.out.println("Phone number " + phone + " belongs to " + person);
        else
            System.out.println("Phone number " + phone + " not found");
        System.out.println("Search person thread unlock.");
        lock.readUnLock();
    }
}