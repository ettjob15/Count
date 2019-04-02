package producer_consumer_count;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author lb
 */
public class MyQueue<String> implements Serializable{
    
    LinkedList<String> data = new LinkedList<>();
    private int maxSize = 3;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }
    
    public void put(String newElement) throws FullException{
        if(data.size() < this.maxSize)
            data.add(newElement);
        else
            throw new FullException();
    }
    
    public String get() throws EmptyException{
        if( data.isEmpty() )
            throw new EmptyException();
        else
            return data.poll();
    }
}
