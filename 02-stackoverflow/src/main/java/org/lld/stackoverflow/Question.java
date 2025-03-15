package org.lld.stackoverflow;

import java.util.ArrayList;
import java.util.List;

public class Question implements Commentable, Votable{
    private int id;
    private User author;
    private String title;
    private String content;
    private List<Tag> tags;
    private List<Answer> answers;
    private List<Comment> comments;
    private List<Vote> votes;

    public Question(User user, String title, String content, List<String> tags) {
        this.id = generateId();
        this.author = user;
        this.title = title;
        this.content = content;

        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.tags = new ArrayList<>();
        for(String tag : tags) {
            this.tags.add(new Tag(tag));
        }
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() * Integer.MAX_VALUE);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void vote(User user, int value) {
        // If this user has already voted, remove it and a new vote
        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, value));

        // Someone upvoted on this question, so it should update the reputation of the author
        author.addReputation(value * AppConstant.UPVOTE_REPUTATION);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Vote> getVotes() {
        return votes;
    }
}
