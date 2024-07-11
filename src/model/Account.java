package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.image.Image;

public abstract class Account {
    private String name;
    private String password;
    private String email;
    private String role;
    private String Id;
    private String imagePath;
    private transient Image image;

    private static int countId = 0;
    private static final String IMAGE_DIR = "src/Resources/account_images";

    public Account(String name,String password, String email, String imagePath,String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.imagePath = imagePath;
        setImagePath(imagePath);
        countId++;
        intialAccountId();
        
    }
    
    
    // Getters and Setters


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return Id;
    }

    private void intialAccountId() {
        String idstr = name.substring(0, 2) + "@"+countId;
        this.Id = idstr;
    }


    public String getName() {
        return name;
    }
    

    public void setName(String name) {
        this.name = name;
    }


    public void setAccountId(String Id) {
        this.Id = Id;
    }


    public void setId(String id) {
        Id = id;
    }


    public static int getCountId() {
        return countId;
    }


    public static void setCountId(int countId) {
        Account.countId = countId;
    }



    
    public void setImagePath(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return;
        }
    
        try {
            if (!imagePath.startsWith("file:/")) {
                throw new IllegalArgumentException("Invalid image path format.");
            }
            String correctedPath = imagePath.replace("file:/", "");
    
            Path sourcePath = Paths.get(correctedPath);
            if (!Files.exists(sourcePath)) {
                throw new FileNotFoundException("Source image file not found.");
            }
    
            Path destinationDir = Paths.get(IMAGE_DIR);
            if (!Files.exists(destinationDir)) {
                Files.createDirectories(destinationDir);
            }
    
            Path destinationPath = destinationDir.resolve(sourcePath.getFileName());
            int counter = 1;
            while (Files.exists(destinationPath)) {
                String fileName = sourcePath.getFileName().toString();
                String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
                String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
                destinationPath = destinationDir.resolve(fileNameWithoutExtension + "_" + counter + "." + fileExtension);
                counter++;
            }
    
            Files.copy(sourcePath, destinationPath);
            this.imagePath = destinationDir.relativize(destinationPath).toString();
            this.image = new Image(destinationPath.toUri().toString());
    
        } catch (IOException e) {
            System.err.println("An error occurred while copying the image: " + e.getMessage());
        }
    }
    
// private void setImagePath(String imagePath) {
    //     if (imagePath != null && !imagePath.isEmpty()) {
    //         try {
    //             // Remove "file:/" prefix from the URL
    //             String correctedPath = imagePath.replace("file:/", "");
    //             Path sourcePath = Paths.get(correctedPath);
    //             Path destinationDir = Paths.get(IMAGE_DIR);
    //             if (!Files.exists(destinationDir)) {
    //                 Files.createDirectories(destinationDir);
    //             }
    //             Path destinationPath = destinationDir.resolve(sourcePath.getFileName());
    //             Files.copy(sourcePath, destinationPath);
    //             this.imagePath = destinationDir.relativize(destinationPath).toString();
    //             this.image = new Image(destinationPath.toUri().toString());
    //         } catch (IOException e) {
    //             System.err.println("An error occurred while copying the image: " + e.getMessage());
    //         }
    //     }
    
    // }


    
    public Image getImage() {
        if (image == null && imagePath != null) {
            Path imagePath = Paths.get(IMAGE_DIR).resolve(this.imagePath).toAbsolutePath();
            image = new Image(imagePath.toUri().toString());
        }
        return image;
    }

    public String getImagePath() {
        return imagePath;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public void setImage(Image image) {
        this.image = image;
    }


    public static String getImageDir() {
        return IMAGE_DIR;
    }

}