/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_count;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jörg
 */
public class Producer extends Thread implements Serializable{

    private final MyQueue <String> pwdQueue;
    private final Book b;
    public Producer(MyQueue <String> pwdQueue,Book b) {
        this.pwdQueue = pwdQueue;
        this.b=b;
    }

    @Override
    public void run() {

        while (true) {
            // only lock access to queue.
            // DO NOT INCLUDE OTHER CODE
            synchronized (pwdQueue) {
                File fr = new File("C:\\Users\\Jörg\\Documents\\NetBeansProjects\\Producer_Consumer_Count\\files");
                for (File f : fr.listFiles()) {
                    try {
                        BufferedReader bf = new BufferedReader(new FileReader(f));
                        String line = bf.readLine();
                        while (line != null) {
                            String[] s = line.split(" ");
                            for (int i = 0; i < s.length; i++) {
                                try {
                                    pwdQueue.put(s[i]);
                                    pwdQueue.notifyAll();
                                } catch (FullException fe) {
                                    try {
                                        pwdQueue.wait();
                                        pwdQueue.notifyAll();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Datei Fertig");
                }
            }
        }
    }
}
