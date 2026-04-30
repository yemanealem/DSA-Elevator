import java.util.ArrayDeque;
import java.util.Deque;

class FrontMiddleBackQueue {

    Deque<Integer> left;
    Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    // push at front
    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    // push at back
    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    // push in middle
    public void pushMiddle(int val) {
        if (left.size() > right.size()) {
            right.addFirst(left.removeLast());
        }
        left.addLast(val);
        balance();
    }

    // pop front
    public int popFront() {
        if (left.isEmpty() && right.isEmpty()) return -1;

        int val;
        if (!left.isEmpty()) {
            val = left.removeFirst();
        } else {
            val = right.removeFirst();
        }

        balance();
        return val;
    }

    // pop back
    public int popBack() {
        if (left.isEmpty() && right.isEmpty()) return -1;

        int val;
        if (!right.isEmpty()) {
            val = right.removeLast();
        } else {
            val = left.removeLast();
        }

        balance();
        return val;
    }

    // pop middle
    public int popMiddle() {
        if (left.isEmpty() && right.isEmpty()) return -1;

        int val = left.removeLast();
        balance();
        return val;
    }

    // maintain balance between two deques
    private void balance() {
        if (left.size() > right.size() + 1) {
            right.addFirst(left.removeLast());
        } else if (left.size() < right.size()) {
            left.addLast(right.removeFirst());
        }
    }

    // demo main
    public static void main(String[] args) {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();

        q.pushFront(1);
        q.pushBack(2);
        q.pushMiddle(3);
        q.pushMiddle(4);

        System.out.println(q.popFront());  // 1
        System.out.println(q.popMiddle()); // 3
        System.out.println(q.popMiddle()); // 4
        System.out.println(q.popBack());   // 2
        System.out.println(q.popFront());  // -1
    }
}
