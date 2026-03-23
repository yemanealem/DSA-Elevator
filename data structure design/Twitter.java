/*
🧩 Problem: Design Twitter (LeetCode)

Design a simplified Twitter system with the following methods:
1. postTweet(userId, tweetId)
2. getNewsFeed(userId) → return 10 most recent tweets
3. follow(followerId, followeeId)
4. unfollow(followerId, followeeId)

------------------------------------------------------------

💡 Optimized Approach (Best Practice):

We use:

1. HashMap<Integer, Set<Integer>> → followMap
   follower → followees

2. HashMap<Integer, Tweet> → tweetMap
   user → head of linked list of tweets

3. Tweet class (linked list)
   each tweet points to next tweet (older one)

4. PriorityQueue (Max Heap)
   → used to merge k sorted tweet lists (like merge k lists)

------------------------------------------------------------

🔍 Key Idea:

- Each user’s tweets are stored as a linked list (latest first)
- For getNewsFeed:
    1. Add head tweet of each followee into heap
    2. Extract top tweet
    3. Add next tweet from same user
    4. Repeat until 10 tweets collected

👉 This avoids scanning ALL tweets → much faster

------------------------------------------------------------

⏱️ Time Complexity:

postTweet → O(1)
follow/unfollow → O(1)
getNewsFeed → O(10 log k)
(k = number of followees)

📦 Space Complexity:
O(U + T)

------------------------------------------------------------

🎯 Key Insight:
This is a classic "merge k sorted lists" problem using a heap

------------------------------------------------------------
*/

import java.util.*;

class Twitter {

    private static int time = 0;

    // user -> set of followees
    private Map<Integer, Set<Integer>> followMap;

    // user -> head of tweet linked list
    private Map<Integer, Tweet> tweetMap;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    // Tweet linked list node
    private static class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }

    // Post tweet → insert at head
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId, time++);
        newTweet.next = tweetMap.get(userId);
        tweetMap.put(userId, newTweet);
    }

    // Get top 10 recent tweets
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();

        // Max heap by time
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>(
            (a, b) -> b.time - a.time
        );

        // Ensure user follows themselves
        followMap.putIfAbsent(userId, new HashSet<>());
        followMap.get(userId).add(userId);

        // Add head tweets
        for (int followee : followMap.get(userId)) {
            Tweet t = tweetMap.get(followee);
            if (t != null) {
                maxHeap.offer(t);
            }
        }

        // Extract top 10
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
        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    // Unfollow
    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId) && followerId != followeeId) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}
