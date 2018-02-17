import java.util.List;
import java.util.LinkedList;
import java.lang.IllegalArgumentException;


/*
@author amalladi3

This class implements the backing queue data structure needed to implement the queueing model simulation

I am using the built in LinkedList class from java.util, but the queue implementation is from scratch
 */

public class Queue {
    private int numWaiting;
    List<User> userList = new LinkedList<User>();

    public Queue() {
        this.numWaiting = 0;
    }

    public Queue(User[] users) {
        numWaiting = users.length;

        for (int i = 0; i < users.length; i++) {
            enqueue(users[i]);
        }
    }

    public int getNumWaiting() {
        return numWaiting;
    }

    public void enqueue(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        userList.add(numWaiting, user);
        numWaiting++;
    }

    public User dequeue() {
        if (numWaiting > 0) {
            numWaiting -= 1;
            return userList.remove(0);
        }
        return null;
    }
}
