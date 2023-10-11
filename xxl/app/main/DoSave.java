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
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
  }
  
  @Override
  protected final void execute() throws FileOpenFailedException {
    // FIXME implement command and create a local Form
    try {
      if (_receiver.getFilename() != null) {
        _receiver.save();
      } else {
        _receiver.saveAs(Form.requestString(Message.newSaveAs()));
      }
    } catch (IOException | MissingFileAssociationException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
