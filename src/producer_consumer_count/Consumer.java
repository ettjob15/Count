/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_count;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jörg
 */
public class Consumer extends Thread implements Serializable {

    private final MyQueue<String> pwdQueue;
    private final Book b;

    public Consumer(MyQueue<String> pwdQueue, Book b) {
        this.pwdQueue = pwdQueue;
        this.b = b;
    }
    int counter = 0;

    @Override
    public void run() {
        while (true) {
            // ONLY INCLUDE CODE TO ACCESS QUEUE IN 
            // THE SYNCHRONIZED BLOCK
            synchronized (pwdQueue) {

                try {
                    counter = 0;
                    String a = pwdQueue.get();
                    b.count(a);
                    pwdQueue.notifyAll();
                } catch (EmptyException ex) {
                    counter++;
                    try {
                        pwdQueue.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    // jump to next .get() call
                    // to avoid null pointer exception
                    if (counter == 4) {
                        String s;
                        try {
                            FileOutputStream fs = new FileOutputStream(new File("exit.txt"));
                            ObjectOutputStream oas = new ObjectOutputStream(fs);
                            for (String wr : b.booking.keySet()) {
                                oas.writeChars(wr + " " + String.valueOf(b.booking.get(wr)));

                            }
                        } catch (FileNotFoundException ex1) {
                            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (IOException ex1) {
                            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                    continue;
                }
            }
        }
    }

}
