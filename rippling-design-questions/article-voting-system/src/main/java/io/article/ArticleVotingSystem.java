package io.article;

import java.util.*;

public class ArticleVotingSystem {
    private int nextArticleId = 1;

    // article id -> article name
    private Map<Integer, String> articles = new HashMap<>();

    // user id -> [article id, vote]
    // vote - 1 (up vote), vote - 0 (down vote)
    private Map<Integer, Map<Integer, Integer>> userVotes = new HashMap<>();

    // user id -> list of article ids where flip occurred
    private Map<Integer, List<Integer>> userFlips = new HashMap<>();

    // article -> score mapping
    private Map<Integer, Integer> articleScore = new HashMap<>();

    /**
     * Add a new article to the system.
     *         Returns a unique article_id.
     * @param articleName
     * @return
     */
    public int addArticle(String articleName) {
        int articleId = nextArticleId++;
        articles.put(articleId, articleName);
        articleScore.put(articleId, 0);

        return articleId;
    }

    /**
     * User upvotes an article (+1 to score).
     * - If user already upvoted this article, this is a no-op.
     * - If user previously downvoted, this counts as a "flip" (change of mind).
     * @param articleId
     * @param userId
     */
    public void upVoteArticle(int articleId, int userId) {
        vote(articleId, userId, 1);
    }

    /**
     * User downvotes an article (-1 to score).
     * - If user already downvoted this article, this is a no-op.
     * - If user previously upvoted, this counts as a "flip" (change of mind).
     * @param articleId
     * @param userId
     */
    public void downVoteArticle(int articleId, int userId) {
        vote(articleId, userId, -1);
    }

    /**
     * Return the k most recent article IDs where this user "flipped" their vote.
     * A flip is when a user changes from upvote → downvote OR downvote → upvote.
     * ⚠️ CRITICAL: This must run in O(k) time, not O(total_flips)!
     * @param userId
     * @param k
     * @return
     */
    public List<Integer> getMostRecentKFlips(int userId, int k) {
        List<Integer> flips = userFlips.getOrDefault(userId, Collections.emptyList());

        List<Integer> result = new ArrayList<>();
        for(int i= flips.size() - 1; i >= 0 && result.size() < k; i--){
            result.add(flips.get(i));
        }

        return result;
    }

    /**
     * Return the top k article IDs by score (upvotes - downvotes).
     * Return in descending order of score.
     * @param k
     * @return
     */
    public List<Integer> getTopK(int k) {
        // 0 - score, 1 - article id
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a,b) -> {
                    if(a[0] != b[0])
                        return Integer.compare(a[0], b[0]);
                    return Integer.compare(a[1], b[1]);
                }
        );

        for (int articleId : articles.keySet()) {
            int score = articleScore.getOrDefault(articleId, 0);

            minHeap.offer(new int[]{score, articleId});

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!minHeap.isEmpty()){
            result.add(minHeap.poll()[1]);
        }

        Collections.reverse(result);
        return result;
    }

    private void vote(int articleId, int userId, int newVote) {

        // Article doesn't exist
        if (!articles.containsKey(articleId)) {
            return;
        }

        // Get or create user's vote map
        Map<Integer, Integer> votes =
                userVotes.computeIfAbsent(userId, k -> new HashMap<>());

        Integer currentVote = votes.get(articleId);

        // User already voted the same way
        if (currentVote != null && currentVote == newVote) {
            return;
        }

        // If user already voted, this is a flip
        if (currentVote != null) {
            userFlips
                    .computeIfAbsent(userId, k -> new ArrayList<>())
                    .add(articleId);

            // Remove old vote and add new vote
            int newArticleScore =
                    articleScore.get(articleId) - currentVote + newVote;

            articleScore.put(articleId, newArticleScore);

        } else {
            // First vote for this article by this user
            int newArticleScore =
                    articleScore.get(articleId) + newVote;

            articleScore.put(articleId, newArticleScore);
        }

        // Store user's new vote
        votes.put(articleId, newVote);
    }
}
