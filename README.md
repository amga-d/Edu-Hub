# Edu-Hub - Educational Desktop Application

**Edu-Hub** is an educational desktop application designed to provide both **students** and **instructors** with a comprehensive learning platform. The app aligns with **Sustainable Development Goal 4: Quality Education** by offering a variety of courses, quizzes, and an interface for instructors to manage course content.


## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Links](#links)

## Features

### For Students:
- Browse and enroll in a variety of courses across different subjects.
- Complete quizzes to reinforce learning.
- Track your progress and view your achievements.

### For Instructors:
- **Instructor Dashboard:** Manage and oversee courses via a dedicated dashboard.
- **Add/Modify Courses:** Easily add, update, or delete courses and quizzes.
- **Course Management:** Track student enrollment and course performance.

## Technologies Used
- **Programming Language:** Java
- **Frontend:** JavaFX
- **Backend:** Java (Data handling with XML)
- **Design Tools:** Figma (For UI/UX visualization), Scene Builder
- **Version Control:** Git, GitHub

## Installation

### Prerequisites
- **Java JDK 11 or higher**
- **JavaFX SDK**

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/amga-d/Edu-Hub.git
   cd edu-hub
   ```

2. Install **JavaFX** dependencies if not already installed:
   ```bash
   sudo apt-get install openjfx  # On Linux
   ```

3. Compile and run the project:
   ```bash
   javac --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml src/com/edu_hub/*.java
   java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml com.edu_hub.Main
   ```

4. Enjoy learning with Edu-Hub!

## Usage
Once the application is running:

### For Students:
1. **Browse Courses:** Access a wide selection of educational courses.
2. **Take Quizzes:** Test your knowledge after each course.
3. **Track Progress:** Keep track of your completed courses and quiz results.

### For Instructors:
1. **Login to the Instructor Dashboard:** Authenticate using your instructor credentials.
2. **Add/Modify Courses:** Easily create new courses or update existing ones.
3. **Manage Quizzes:** Add or update quizzes linked to your courses.
4. **Track Student Enrollment:** Monitor students' progress and engagement with your courses.


## Links
- [Figma UI Design](https://www.figma.com/file/gAOd70aNWOGiGCxFiYFSpZ/Edu-Hub)
- [GitHub Repo](https://github.com/amga-d/Edu-Hub.git)
- [University Expo Presentation](https://hitori.informatics-expo.id/en/karya/766)

