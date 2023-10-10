package xxl.core;

import java.io.*;
import java.util.ArrayList;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

// FIXME import classes

/**
 * Class representing a spreadsheet application.
 */
public class Calculator implements Serializable {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = null;
    private User _activeUser = new User("root");
    private Repository _repo = new Repository(_activeUser);
    private ArrayList<User> _users = new ArrayList<>();

    private String _filename;
    // FIXME add more fields if needed



    public void newUser(String name){
        _activeUser = new User(name);
        _repo.addUser(_activeUser);
        _users.add(_activeUser);
        _spreadsheet = null;
    }
    public void newSpreadsheet(int numLines, int numColumns){
        _spreadsheet = new Spreadsheet(numLines,numColumns,_activeUser);
        _activeUser.addSpreadsheet(_spreadsheet);
        _repo.addSpreadsheet(_spreadsheet);
    }

    /**
     * Return the current spreadsheet.
     *
     * @returns the current spreadsheet of this application. This reference can be null.
     */
    public final Spreadsheet getSpreadsheet(){
        return _spreadsheet;
    }
    public void setSpreadsheet(){

    }

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        // FIXME implement serialization method
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
        // FIXME implement serialization method
        try(FileOutputStream fileOut = new FileOutputStream(filename)) {
            try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(_users);
                setFilename(filename);
            }
        }
    }

    private void setFilename(String filename) {
        _filename = filename;
    }

    public String getFilename() {
        return _filename;
    }
    /**
     * @param _filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String _filename) throws UnavailableFileException, FileNotFoundException, ClassNotFoundException, ImportFileException {
        // FIXME implement serialization method

            try(FileInputStream fileIn = new FileInputStream(_filename)) {
                try (ObjectInputStream in = new ObjectInputStream(fileIn)) {
                    _users = (ArrayList<User>) in.readObject();
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
            // FIXME open import file and feed entries to new spreadsheet (in a cycle)
            //       each entry is inserted with:
            Parser parser = new Parser(_spreadsheet);
            parser.parseFile(_filename);
            _spreadsheet.addUser(_activeUser);


        } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(_filename, e);
        }
    }

}
