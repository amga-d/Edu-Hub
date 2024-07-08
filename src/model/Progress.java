package model;

public class Progress {
    private boolean[] quizCompleted;
    private boolean[] materialCompleted;

    
    public boolean[] getQuizCompleted() {
        return quizCompleted;
    }
    public void setQuizCompleted(boolean[] quizCompleted) {
        this.quizCompleted = quizCompleted;
    }
    public boolean[] getMaterialCompleted() {
        return materialCompleted;
    }
    public void setMaterialCompleted(boolean[] materialCompleted) {
        this.materialCompleted = materialCompleted;
    }
    
}
