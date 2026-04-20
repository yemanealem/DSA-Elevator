PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();

        heap.add(1L);
        seen.add(1L);

        long current = 1;

        for (int i = 0; i < n; i++) {
            current = heap.poll();

            long next2 = current * 2;
            long next3 = current * 3;
            long next5 = current * 5;

            if (seen.add(next2)) heap.add(next2);
            if (seen.add(next3)) heap.add(next3);
            if (seen.add(next5)) heap.add(next5);
        }

        return (int) current;