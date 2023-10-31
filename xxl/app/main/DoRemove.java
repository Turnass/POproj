package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;

import java.io.IOException;
// FIXME import classes

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoRemove extends Command<Calculator> {

  DoRemove(Calculator receiver) {
    super("Remove Folhas", receiver);
  }
  
  @Override
  protected final void execute() throws FileOpenFailedException {
    try {
        int numUsers = Form.requestInteger();
        _display.addAll(_receiver.removeSpreadsheets(numUsers));
        _display.display();

    } catch (IOException | MissingFileAssociationException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
