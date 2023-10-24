package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;

import java.io.IOException;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("lines", Message.lines());
    addIntegerField("columns", Message.columns());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getSpreadsheet() == null || _receiver.getSpreadsheet().isSaved()){
      _receiver.createSpreadsheet(integerField("lines"), integerField("columns"));
      return;
    }
    if (Form.confirm(Message.saveBeforeExit())){
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
    _receiver.createSpreadsheet(integerField("lines"), integerField("columns"));
  }
}
