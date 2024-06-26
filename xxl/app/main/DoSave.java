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
    try {
      if (_receiver.getFilename() != null) {
        _receiver.save();
      } else {
        String name = Form.requestString(Message.newSaveAs());
        if (name.length() > 10){
           throw new InvalidCellRangeException(name);
        }
        _receiver.saveAs(name);
      }
    } catch (IOException | MissingFileAssociationException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
