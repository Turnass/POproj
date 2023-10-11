package xxl.core;

import java.util.ArrayList;
import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable{

    @Serial
    private static final long serialVersionUID = 202310111059L;

    private String _name;
    private ArrayList<Spreadsheet> _spreadsheets;

    public User(String name){
        _spreadsheets = new ArrayList<>();
        _name = name;
    }
    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }
}
