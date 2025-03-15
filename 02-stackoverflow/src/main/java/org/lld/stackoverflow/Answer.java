package org.lld.stackoverflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer implements Commentable, Votable{
    private int id;
    private User author;
    private Question question;
    private String content;
    private Date creationDate;
    private boolean isAccepted;
    private List<Vote> votes;
    private List<Comment> comments;

    public Answer(User user, Question question, String content) {
        this.id = generateId();
        this.author = user;
        this.question = question;
        this.content = content;
        this.votes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() * Integer.MAX_VALUE);
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

    }

    public void markAsAccepted() throws IllegalAccessException {
        if(isAccepted)
            throw new IllegalAccessException("This answer is already accepted");
        isAccepted = true;
        author.addReputation(AppConstant.ACCEPTED_ANSWER_REPUTATION);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    public User getAuthor() {
        return author;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getContent() {
        return content;
    }

    public Question getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }
}
