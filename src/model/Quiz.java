package model;

public class Quiz {
    private String title;
    private String description;
    private int maxScore;

    // Constructor
    public Quiz(String title, String description, int maxScore) {
        this.title = title;
        this.description = description;
        this.maxScore = maxScore;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public boolean isCompletedByUser(User user) {
        // Check if the user has completed this quiz
        // Return true if completed, false otherwise
        return false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
