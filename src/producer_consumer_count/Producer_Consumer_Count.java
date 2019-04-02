/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_count;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jörg
 */
public class Producer_Consumer_Count {

    MyQueue<String> pwdQueue = new MyQueue<>(3);
    Book b=  new Book();
    
    public static void main(String[] args) {
        new Producer_Consumer_Count().starting();
    }

    void starting() {
        Producer pd = new Producer(pwdQueue, b);
        Consumer cons = new Consumer(pwdQueue, b);
        
        pd.start();
        cons.start();
        try {
            pd.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer_Consumer_Count.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cons.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer_Consumer_Count.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
