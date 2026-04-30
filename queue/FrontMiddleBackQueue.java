import java.util.ArrayDeque;
import java.util.Deque;

class FrontMiddleBackQueue {

    private final Deque<Integer> left;
    private final Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    public void pushMiddle(int val) {
        if (left.size() > right.size()) {
            right.addFirst(left.removeLast());
        }
        left.addLast(val);
        balance();
    }

    public int popFront() {
        if (isEmpty()) return -1;

        int val = !left.isEmpty() ? left.removeFirst() : right.removeFirst();
        balance();
        return val;
    }

    public int popBack() {
        if (isEmpty()) return -1;

        int val = !right.isEmpty() ? right.removeLast() : left.removeLast();
        balance();
        return val;
    }

    public int popMiddle() {
        if (isEmpty()) return -1;

        int val = left.removeLast();
        balance();
        return val;
    }

    private void balance() {
        if (left.size() > right.size() + 1) {
            right.addFirst(left.removeLast());
        } else if (left.size() < right.size()) {
            left.addLast(right.removeFirst());
        }
    }

    private boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();

        q.pushFront(1);
        q.pushBack(2);
        q.pushMiddle(3);
        q.pushMiddle(4);

        System.out.println(q.popFront());
        System.out.println(q.popMiddle());
        System.out.println(q.popMiddle());
        System.out.println(q.popBack());
        System.out.println(q.popFront());
    }
}
