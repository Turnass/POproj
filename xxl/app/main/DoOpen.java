package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.ImportFileException;
import xxl.core.exception.UnavailableFileException;

import java.io.FileNotFoundException;
// FIXME import classes

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {

    super(Label.OPEN, receiver);
    addStringField("filename", Message.openFile());
  }
  
  @Override
  protected final void execute() throws CommandException {

      try {
         _receiver.load(stringField("filename"));
      } catch (UnavailableFileException | FileNotFoundException | ClassNotFoundException | ImportFileException e) {
        throw new FileOpenFailedException(e);
      }

  }
}
