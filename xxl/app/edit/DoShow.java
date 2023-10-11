package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("gamma", Message.address());
    // FIXME add fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
   _display.addAll(_receiver.createGamma(stringField("gamma")).getGamma());
   _display.display();
  }
}
