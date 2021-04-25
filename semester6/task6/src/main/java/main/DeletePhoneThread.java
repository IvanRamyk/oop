package main;

import rwlock.ReadWriteLock;

import java.io.*;

public class DeletePhoneThread extends Thread{
    private final String path;
    private final String phone;
    private final ReadWriteLock lock;

    public DeletePhoneThread(ReadWriteLock lock, String path, String phone) {
        this.lock = lock;
        this.path = path;
        this.phone = phone;
    }

    @Override
    public void run() {
        try {
            lock.writeLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Delete record thread lock.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File inputFile = new File(path);
        File tempFile = new File(path + "tmp");

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = null;
        while(true) {
            try {
                line = reader.readLine();
                if (line == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String data[] = line.split(" - ", 2);
            if (data[1].equals(phone)) {
                System.out.printf("Phone %s was deleted\n", phone);
                continue;
            }
            try {
                writer.write(line + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputFile.delete();
        boolean successful = tempFile.renameTo(inputFile);
        System.out.println("Delete record thread unlock.");
        lock.writeUnLock();
    }

}