package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Quiz {
    private String title;
    private String question;
    private String[] choices;
    private int rightAnswer;
    private Map <String, Boolean> completionStatus;

    // Constructor
    public Quiz(String title, String question,String[] choices, int rightAnswer) {
        this.title = title;
        this.question = question;
        this.choices =choices;
        this.rightAnswer = rightAnswer;
        this.completionStatus = new HashMap<>();
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }



    public void setCompleted(User user) {
        completionStatus.put(user.getId(), true);
    }

    public boolean isCompleted(User user) {
        return completionStatus.getOrDefault(user.getId(), false);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getChoices() {
        return choices;
    }


    public boolean isRightAnswer(int answer) {
        return rightAnswer == answer;
    }

    @Override
    public String toString() {
        return "Quiz [title=" + title + ", question=" + question + ", choices=" + Arrays.toString(choices)
                + ", rightAnswer=" + rightAnswer + ", completionStatus=" + completionStatus + "]";
    }

    public void removeUserCompletionStatus(String userId) {
        completionStatus.remove(userId);
    }







    
}
