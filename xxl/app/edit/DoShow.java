package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.app.exception.UnknownFunctionException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidGammaException;
import xxl.core.exception.UnrecognizedEntryException;
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
      try {
          _display.addAll(_receiver.createGamma(stringField("gamma")).printGamma());
          _display.display();
      }catch (InvalidGammaException ex){
          throw new InvalidCellRangeException(stringField("gamma"));
      } catch (UnrecognizedEntryException e) {

      }
  }
}
