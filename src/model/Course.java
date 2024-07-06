package model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Course {
    private String courseName;
    private String courseDescription;
    private double courseRating;
    private String tag;
    private List<Quiz> quizzes;
    private List<Material> materials;
    private String courseId;
    private String courseImagePath;
    private String instructorId;
    private List<String> registeredUserIds;

    private transient Image image;

    private static final String IMAGE_DIR = "src/Resources/courses_Images";
    static private int countId = 0;



    public Course(String courseName, String courseDescription, String tag,
        String courseImagePath, Instructor instructor) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseRating = 5.0;
        this.tag = tag;
        this.quizzes = new ArrayList<>();
        this.materials = new ArrayList<>();
        this.registeredUserIds = new ArrayList<>();
        this.instructorId = instructor.getId();
        countId += 1;
        intialCourseId();
        this.courseImagePath = courseImagePath;
        setImagePath(courseImagePath);
    }


    private void setImagePath(String imagePath) {
    
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                // Remove "file:/" prefix from the URL
                String correctedPath = imagePath.replace("file:/", "");
                Path sourcePath = Paths.get(correctedPath);
                Path destinationDir = Paths.get(IMAGE_DIR);
                if (!Files.exists(destinationDir)) {
                    Files.createDirectories(destinationDir);
                }
                Path destinationPath = destinationDir.resolve(sourcePath.getFileName());
                Files.copy(sourcePath, destinationPath);
                this.courseImagePath = destinationDir.relativize(destinationPath).toString();
                this.image = new Image(destinationPath.toUri().toString());
            } catch (IOException e) {
                System.err.println("An error occurred while copying the image: " + e.getMessage());
            }
        }
    
    }
    

    private void intialCourseId() {
        String idstr = courseName.substring(0, 2) + "@" + countId;
        this.courseId = idstr;
    }

    // Getters and Setters

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public List<Material> getMaterials() {
        return materials;
    }


    // Methods
    public void registerUser(User user) {
        if (!registeredUserIds.contains(user.getId())) {
            registeredUserIds.add(user.getId());
        }
    }

    public void unregisterUser(User user) {
        if (registeredUserIds.contains(user.getId())) {
            registeredUserIds.remove(user.getId());
        }
    }
    

    public double countUserProgress(User user) {
        // Implement logic to count user's progress based on completed quizzes and
        // materials
        return 0.0; // Placeholder return value
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }



    public String getCourseId() {
        return courseId;
    }

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        Course.countId = countId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }




    public Image getImage() {
        if (image == null && courseImagePath != null) {
            Path imagePath = Paths.get(IMAGE_DIR).resolve(this.courseImagePath).toAbsolutePath();
            image = new Image(imagePath.toUri().toString());
        }
        return image;
    }

    public String getImagePath() {
        return courseImagePath;
    }


    public String getInstructorId() {
        return instructorId;
    }


    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }


    public List<String> getRegisteredUserIds() {
        return registeredUserIds;
    }


    public void setRegisteredUserIds(List<String> registeredUserIds) {
        this.registeredUserIds = registeredUserIds;
    }


    public void setImage(Image image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Course{" + 
        "courseId='" + courseId + '\'' +
        ", courseName='" + courseName + '\'' +
        ", courseImagePath='" + courseImagePath + '\'' +
        ", instructorId='" + instructorId + '\'' +
        ", registeredUserIds=" + registeredUserIds +
        "} " ;

    }
}
