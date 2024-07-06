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

    public AccountModel() {
        xStream = new XStream(new StaxDriver());
        xStream.allowTypes(new Class[] { ArrayList.class, model.Account.class, model.User.class, model.Instructor.class });
    }

    public void saveAccounts(List<Account> accounts) {
        String xml = xStream.toXML(accounts);
        try (FileOutputStream outputStream = new FileOutputStream(XML_FILE)) {
            byte[] data = xml.getBytes("UTF-8");
            outputStream.write(data);
        } catch (Exception e) {
            System.out.println("An error occur while saving Accounts:" + e.getMessage());
        }
    }

    public List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        File file = new File(XML_FILE);
        if (!file.exists()) {
            return accounts;
        }

        try (FileInputStream inputStream = new FileInputStream(file)) {
            int content;
            StringBuilder s = new StringBuilder();
            while ((content = inputStream.read()) != -1) {
                s.append((char) content);
            }
            accounts = (List<Account>) xStream.fromXML(s.toString());
        } catch (Exception e) {
            System.err.println("An error occur while loading Accounts" + e.getMessage());
        }
        return accounts;
    }
}
