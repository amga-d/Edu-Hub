package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class CourseModel {
    private static final String XML_FILE = ("courses.xml");
    private XStream xStream;

    public CourseModel() {
        xStream = new XStream(new StaxDriver());
        xStream.allowTypes(new Class[] { ArrayList.class,
                model.Course.class,
                model.Material.class, model.Quiz.class });
    }

    public void saveCourses(List<Course> courses) {
        String xml = xStream.toXML(courses);
        FileOutputStream outputStream;

        try {
            byte[] data = xml.getBytes("UTF-8");
            outputStream = new FileOutputStream(XML_FILE);
            outputStream.write(data);
            outputStream.close();

        } catch (Exception e) {
            System.out.println("An error occur while saving Courses:" + e.getMessage());
        }
    }

    public List<Course> loadCourses() {
        List<Course> courses = new ArrayList<Course>();
        File file = new File(XML_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            int content;
            char c;
            String s = "";
            while ((content = inputStream.read()) != -1) {
                c = (char) content;
                s += c;
            }
            courses = (List<Course>) xStream.fromXML(s);
            inputStream.close();
        } catch (Exception e) {
            System.err.println("An error occur while loading Courses" + e.getMessage());
        }
        return courses;
    }
}
