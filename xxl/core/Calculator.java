package xxl.core;

import java.io.*;
import java.util.ArrayList;

import xxl.core.exception.*;


/**
 * Class representing a spreadsheet application.
 */
public class Calculator implements Serializable {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = null;
    private User _activeUser = new User("root");
    private Repository _repo = new Repository(_activeUser);
    private String _filename;

    /**
     *
      * @param name name of user to create
     */
    public void newUser(String name){
        _activeUser = new User(name);
        _spreadsheet = null;
    }

    public ArrayList<String> removeSpreadsheets(int numUsers){
        ArrayList<Spreadsheet> sheets = _repo.getAllSheets();
        ArrayList<String> res = new ArrayList<>();
        int numRemoved = 0;
        for (Spreadsheet sheet : sheets){
          if (sheet.getNumUsers() < numUsers)
          res.add(sheet.getNumLines() + sheet.getNumColumns());
          numRemoved++;
        }
        res.add("O numero de sheets apagadas foi" + numRemoved);
        
        return res; 
    }

    /**
     *
     * @param numLines
     * @param numColumns
     * @return if the operation was successful or not
     */
    public boolean createSpreadsheet(int numLines, int numColumns){
        if (numLines <= 0 || numColumns <= 0)
            return false;
        _spreadsheet = new Spreadsheet(numLines,numColumns);
        return true;
    }

    /**
     * Return the current spreadsheet.
     *
     * @returns the current spreadsheet of this application. This reference can be null.
     */
    public final Spreadsheet getSpreadsheet(){
        return _spreadsheet;
    }

    /**
     * Saves the repository of this session
     */
    public void saveRepo(){
        _repo.addUser(_activeUser);
        _repo.addSpreadsheet(_spreadsheet);
        _spreadsheet.addUser(_activeUser);
        _activeUser.addSpreadsheet(_spreadsheet);
    }
    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        saveAs(_filename);
    }

    /**
     * Saves the serialized application's state into the specified file. The current network is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {

        try(FileOutputStream fileOut = new FileOutputStream(filename)) {
            try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                saveRepo();
                _spreadsheet.setSaved(true);
                out.writeObject(_repo);
                setFilename(filename);
            }
        }
    }


    /**
     * Sets the filename name to the file received
     * @param filename
     */
    private void setFilename(String filename) {_filename = filename;}

    /**
     *
     * @return filename name of the file containing the serialized application's state
     */
    public String getFilename() {return _filename;}

    /**
     * @param _filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String _filename) throws UnavailableFileException, FileNotFoundException, ClassNotFoundException, ImportFileException {

            try(FileInputStream fileIn = new FileInputStream(_filename)) {
                try (ObjectInputStream in = new ObjectInputStream(fileIn)) {
                    _repo = (Repository) in.readObject();
                    _activeUser = _repo.getUser();
                    _spreadsheet = _repo.getSpreadsheet();
                    setFilename(_filename);

                } catch (IOException e) {
                    throw new ImportFileException(_filename, e);
                }
            } catch (IOException e) {
                throw new UnavailableFileException(_filename);
            }
    }

    /**
     * Read text input file and create domain entities.
     *
     * @param _filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String _filename) throws ImportFileException {
        try {
            Parser parser = new Parser(_spreadsheet);
            _spreadsheet = parser.parseFile(_filename);
            _spreadsheet.setSaved(false);

        } catch (IOException | UnrecognizedEntryException | InvalidGammaException | UnknownFunctionException/* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(_filename, e);
        }
    }

}
