/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_count;

import java.io.Serializable;
import java.util.HashMap;

public class Book implements Serializable{

    HashMap<String, Integer> booking = new HashMap<>();

    public void count(String k) {
        if(booking.isEmpty())
        {
           booking.put("", 1); 
           booking.put(k.toString(), 1); 
        }
        for (String k2 : booking.keySet()) {
            if (booking.containsKey(k)) {
                if (k2.equals(k)) {
                    int i = booking.get(k2);
                    i++;
                    booking.remove(k2);
                    booking.put(k, i);
                    break;
                }
            } else {
                booking.put(k.toString(), 1);
                break;
            }
        }
    }
}
