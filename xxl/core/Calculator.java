package xxl.core;

import java.io.*;

import xxl.core.exceptions.ImportFileException;
import xxl.core.exceptions.MissingFileAssociationException;
import xxl.core.exceptions.UnavailableFileException;
import xxl.core.exceptions.UnrecognizedEntryException;

// FIXME import classes

/**
 * Class representing a spreadsheet application.
 */
public class Calculator implements Serializable {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = null;
    private User _activeUser = new User("root");
    private Repository _repo = new Repository(_activeUser);
    private String _filename;
    // FIXME add more fields if needed



    public void newUser(String name){
        _activeUser = new User(name);
        _repo.addUser(_activeUser);
        _spreadsheet = null;
    }
    public void newSpreadsheet(int numLines, int numColumns){
        _spreadsheet = new Spreadsheet(numLines,numColumns,_activeUser);
        _activeUser.addSpreadsheet(_spreadsheet);
        _repo.addSpreadsheet(_spreadsheet);
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
                out.writeObject(_spreadsheet);
                setFilename(filename);
            }
        }
    }

    private void setFilename(String filename) {
        _filename = filename;
    }

    /**
     * @param _filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String _filename) throws UnavailableFileException {
        // FIXME implement serialization method
    }

    /**
     * Read text input file and create domain entities..
     *
     * @param _filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String _filename) throws ImportFileException {
        try {
            // FIXME open import file and feed entries to new spreadsheet (in a cycle)
            //       each entry is inserted with:
            int lines = 0//parser.
            int columns = 0//parser.
            _spreadsheet = new Spreadsheet(lines,  columns, _activeUser);
            //while (){
            _spreadsheet.insertContents( /*FIXME produce arguments */);
            // ....}
        } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(/*_filename, e*/);
        }
    }

}
