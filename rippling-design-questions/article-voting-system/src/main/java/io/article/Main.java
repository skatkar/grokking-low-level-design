package io.article;

public class Main {
    public static void main(String[] args) {

        ArticleVotingSystem system = new ArticleVotingSystem();

        // Add articles
        int article1 = system.addArticle("Java Concurrency");
        int article2 = system.addArticle("System Design");
        int article3 = system.addArticle("Kafka Internals");
        int article4 = system.addArticle("Spring Boot");
        int article5 = system.addArticle("Distributed Systems");

        // User 1 votes
        system.upVoteArticle(article1, 101);
        system.upVoteArticle(article2, 101);
        system.downVoteArticle(article3, 101);

        // User 2 votes
        system.upVoteArticle(article1, 102);
        system.upVoteArticle(article3, 102);
        system.upVoteArticle(article4, 102);

        // User 3 votes
        system.upVoteArticle(article1, 103);
        system.downVoteArticle(article2, 103);
        system.upVoteArticle(article5, 103);

        /*
         * User 101 changes:
         *
         * Article 1: UP -> DOWN
         * Article 2: UP -> DOWN
         * Article 3: DOWN -> UP
         *
         * These are flips and should be recorded.
         */
        system.downVoteArticle(article1, 101);
        system.downVoteArticle(article2, 101);
        system.upVoteArticle(article3, 101);

        // Get most recent flips for user 101
        System.out.println(
                "Recent flips: "
                        + system.getMostRecentKFlips(101, 2)
        );

        // Get top 3 articles by score
        System.out.println(
                "Top 3 articles: "
                        + system.getTopK(3)
        );
    }
}