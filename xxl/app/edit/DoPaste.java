package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidGammaException;
// FIXME import classes

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

  DoPaste(Spreadsheet receiver) {
    super(Label.PASTE, receiver);
    addStringField("gamma", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.paste(stringField("gamma"));
    } catch (InvalidGammaException e) {
      throw new InvalidCellRangeException(stringField("gamma"));
    }
  }
}
