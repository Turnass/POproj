package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Repository implements Serializable {
    @Serial
    private static final long serialVersionUID = 202309102340L;
    private ArrayList<User> _users;
    private ArrayList<Spreadsheet> _spreadsheets;


    public Repository(User user){
        _users = new ArrayList<>();
        _spreadsheets = new ArrayList<>();
        addUser(user);
    }
    public void addUser(User user){
        _users.add(user);
    }
    public void addSpreadsheet(Spreadsheet spreadsheet){
        _spreadsheets.add(spreadsheet);
    }
}
