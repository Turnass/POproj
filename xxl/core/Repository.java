package xxl.core;

import java.util.ArrayList;

public class Repository {
    private ArrayList<User> _users;
    private ArrayList<Spreadsheet> _spreadsheets;


    public Repository(User user){
        _users.add(user);
    }
    public void addUser(User user){
        _users.add(user);
    }
    public void addSpreadsheet(Spreadsheet spreadsheet){
        _spreadsheets.add(spreadsheet);
    }
}
