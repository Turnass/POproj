package xxl.core;

import java.util.ArrayList;

public class User {

    private String _name;
    private ArrayList<Spreadsheet> _spreadsheets;

    public User(String name){
        _name = name;
    }
    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }
}
