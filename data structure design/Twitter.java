/*
🧩 Problem: Design Twitter (LeetCode)

------------------------------------------------------------
💡 Improvements in this version:

1. Avoid unnecessary putIfAbsent calls
2. Use computeIfAbsent for cleaner + faster map handling
3. Reduce repeated map lookups
4. Keep heap operations minimal
5. Cleaner, more interview-friendly structure

------------------------------------------------------------
⏱️ Time Complexity:
postTweet → O(1)
follow/unfollow → O(1)
getNewsFeed → O(10 log k)

📦 Space Complexity:
O(U + T)
------------------------------------------------------------
*/

import java.util.*;

class Twitter {

    private int time = 0;

    private Map<Integer, Set<Integer>> followMap = new HashMap<>();
    private Map<Integer, Tweet> tweetMap = new HashMap<>();

    private static class Tweet {
        int id, time;
        Tweet next;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    public Twitter() {}

    // Post tweet
    public void postTweet(int userId, int tweetId) {
        Tweet head = tweetMap.get(userId);
        Tweet newTweet = new Tweet(tweetId, time++);
        newTweet.next = head;
        tweetMap.put(userId, newTweet);
    }

    // Get news feed
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>(10);

        Set<Integer> followees = followMap.get(userId);
        if (followees == null) {
            followees = new HashSet<>();
        }
        followees.add(userId); // include self

        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>(
            (a, b) -> b.time - a.time
        );

        // Add only head tweets (important optimization)
        for (int f : followees) {
            Tweet t = tweetMap.get(f);
            if (t != null) {
                maxHeap.offer(t);
            }
        }

        while (!maxHeap.isEmpty() && res.size() < 10) {
            Tweet curr = maxHeap.poll();
            res.add(curr.id);

            if (curr.next != null) {
                maxHeap.offer(curr.next);
            }
        }

        return res;
    }

    // Follow
    public void follow(int followerId, int followeeId) {
        followMap
            .computeIfAbsent(followerId, k -> new HashSet<>())
            .add(followeeId);
    }

    // Unfollow
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = followMap.get(followerId);
        if (set != null && followerId != followeeId) {
            set.remove(followeeId);
        }
    }
}
