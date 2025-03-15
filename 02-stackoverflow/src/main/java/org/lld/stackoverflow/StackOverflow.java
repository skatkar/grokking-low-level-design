package org.lld.stackoverflow;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StackOverflow {
    private Map<Integer, User> users;
    private Map<Integer, Question> questions;
    private Map<Integer, Tag> tagMap;
    private Map<Integer, Answer> answers;

    public StackOverflow() {
        this.users = new ConcurrentHashMap<>();
        this.questions = new ConcurrentHashMap<>();
        this.tagMap = new ConcurrentHashMap<>();
        this.answers = new ConcurrentHashMap<>();
    }

    public User createUser(String email, String userName) {
        int id = users.size() + 1;
        User user = new User(id, email, userName);
        users.put(id, user);
        return user;
    }

    public Question askQuestion(User user, String title, String content, List<String> tags) {
        Question question = user.askQuestion(title, content, tags);
        questions.put(question.getId(), question);

        // Now, keep track of the tags
        for(Tag tag : question.getTags()) {
            tagMap.putIfAbsent(tag.getId(), tag);
        }

        return question;
    }

    public Answer answerQuestion(User user, Question question, String content) {
        Answer answer = user.answerQuestion(question, content);
        answers.put(answer.getId(), answer);
        return answer;
    }

    public Comment addComment(User user, Commentable commentable, String content) {
        return user.addComment(commentable, content);
    }

    public void acceptAnswer(Answer answer) throws IllegalAccessException {
        answer.markAsAccepted();
    }

    public List<Question> searchQuestions(String query) {
        return questions.values().stream()
                .filter(question -> question.getTitle().toLowerCase().contains(query.toLowerCase()))
                .filter(question -> question.getContent().toLowerCase().contains(query.toLowerCase()))
                .filter(question -> question.getTags().stream().anyMatch(t -> t.getName().equalsIgnoreCase(query)))
                .collect(Collectors.toList());
    }

    public void voteQuestion(User user, Question question, int value) {
        question.vote(user, value);
    }

    public void voteAnswer(User user, Answer answer, int value) {
        answer.vote(user, value);
    }

    public List<Question> questionsByUser(User user) {
        return user.getQuestions();
    }
}
