package model;

import java.util.HashMap;
import java.util.Map;

public class Material {
    private String title;
    private String content;
    private Map<String,Boolean> completionStatus;

    // Constructor
    public Material(String title, String content) {
        this.title = title;
        this.content = content;
        completionStatus = new HashMap<>();
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

    public void setCompleted(User user) {
        completionStatus.put(user.getId(), true);
    }

    public boolean isCompleted(User user) {
        return completionStatus.getOrDefault(user.getId(), false);
    }



    @Override
    public String toString() {
        return "Material [title=" + title + ", content=" + content + ", completionStatus=" + completionStatus + "]";
    }
    
}

