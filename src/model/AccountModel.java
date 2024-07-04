package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class AccountModel {
    private static final String XML_FILE = ("accounts.xml");
    private XStream xStream;
     
    public AccountModel(){
        xStream = new XStream(new StaxDriver());
        xStream.allowTypes(new Class[]{ArrayList.class, model.User.class,model.Course.class});
    }
    
    // public void saveAccounts(List<Account> accounts){
    //     String xml = xStream.toXML(accounts);
    //     FileOutputStream outputStream;

    //     try{
    //         byte[] data = xml.getBytes("UTF-8");
    //         outputStream = new FileOutputStream(XML_FILE);
    //         outputStream.write(data);  
    //         outputStream.close();
    //     }catch(Exception e){
    //         System.err.println("An error occur : " + e.getMessage());
    //     }
    // }

    // public List<Account> loadAccounts(){
    //     List<Account> accounts = new ArrayList<>();
    //     File file = new File(XML_FILE);
    //     if (!file.exists()) {
    //         return new ArrayList<>();
    //     }

    //     FileInputStream inputStream;
    //     try{
    //         inputStream = new FileInputStream(XML_FILE);
    //         int content;
    //         char c;
    //         String s ="";
    //         while((content = inputStream.read()) != -1){
    //             c = (char) content;
    //             s += c;
    //         }
    //         accounts = ((List<Account>) xStream.fromXML(s));
    //         inputStream.close();

    //     }catch(Exception e ){
    //             System.err.println("An error occur : " + e.getMessage());
    //     }

    //     return accounts;
    // }
    public void saveAccounts(List<Account> accounts){
        String xml = xStream.toXML(accounts);
        FileOutputStream outputStream;

        try{
            byte[] data = xml.getBytes("UTF-8");
            outputStream = new FileOutputStream(XML_FILE);
            outputStream.write(data);  
            outputStream.close();
        }catch(Exception e){
            System.err.println("An error occur while saving Accounts: " + e.getMessage());
        }
    }

    public List<Account> loadAccounts(){
        List<Account> accounts = new ArrayList<>();
        File file = new File(XML_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream inputStream;
        try{
            inputStream = new FileInputStream(XML_FILE);
            int content;
            char c;
            String s ="";
            while((content = inputStream.read())!= -1){
                c = (char) content;
                s += c;
            }
            accounts = ((List<Account>) xStream.fromXML(s));
            inputStream.close();

        }catch(Exception e ){
                System.err.println("An error occur : " + e.getMessage());
        }

        return accounts;
    }

    public void cleanAndSaveAccounts(List<Account> accounts) {
        // Clear the existing data in the XML file
        try (FileOutputStream outputStream = new FileOutputStream(XML_FILE)) {
            outputStream.write("".getBytes("UTF-8"));
            outputStream.close();
        } catch (Exception e) {
            System.err.println("An error occur : " + e.getMessage());
        }

        // Save the new list of accounts
        saveAccounts(accounts);
    }
}
