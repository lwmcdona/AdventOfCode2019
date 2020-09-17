package day7;

import java.util.LinkedList;

public class Connection {
    private LinkedList<Integer> pipe;

    public Connection() {
        this.pipe = new LinkedList<>();
    }

    public boolean isEmpty() {
        return pipe.isEmpty();
    }

    public int receive() {
        return pipe.pop();
    }

    public void send(Integer value) {
        pipe.add(value);
    }

    public void reset() {
        pipe.clear();
    }
}
