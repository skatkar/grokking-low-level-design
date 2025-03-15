package org.lld.stackoverflow;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String email;
    private String userName;
    private int reputation;

    private List<Question> questions;
    private List<Answer> answers;
    private List<Comment> comments;

    public User(int id, String email, String userName) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.reputation = 0;
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public Question askQuestion(String title, String content, List<String> tags) {
        Question question = new Question(this, title, content, tags);
        questions.add(question);
        addReputation(AppConstant.QUESTION_REPUTATION);
        return question;
    }

    public Answer answerQuestion(Question question, String content) {
        Answer answer = new Answer(this, question, content);
        question.addAnswer(answer);
        addReputation(AppConstant.ANSWER_REPUTATION);
        return answer;
    }

    public Comment addComment(Commentable commentable, String content) {
        Comment comment = new Comment(this, content);
        comments.add(comment);
        commentable.addComment(comment);
        addReputation(AppConstant.COMMENT_REPUTATION);
        return comment;
    }

    public synchronized void addReputation(int value) {
        this.reputation += value;

        // This will support downvoting.
        // If the user gets downvote, we'll reset it to 0
        if(this.reputation < 0)
            this.reputation = 0;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public int getReputation() {
        return reputation;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
