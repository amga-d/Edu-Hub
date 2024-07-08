package model;

public class Material {
    private String title;
    private String content;

    // Constructor
    public Material(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public boolean isCompletedByUser(User user) {
        // Check if the user has completed this quiz
        // Return true if completed, false otherwise
        return false; 
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

