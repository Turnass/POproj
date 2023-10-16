package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidGammaException;
import xxl.core.exception.UnknownFunctionException;
import xxl.core.exception.UnrecognizedEntryException;
// FIXME import classes

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    addStringField("gamma", Message.address());
    addStringField("content", Message.contents());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.insertGammaContent(stringField("gamma"), stringField("content"));
    }catch (UnknownFunctionException e){
      throw new xxl.app.exception.UnknownFunctionException(e.getFunctionName());
    }catch (InvalidGammaException e) {
      throw new InvalidCellRangeException(stringField("gamma"));
    }catch (UnrecognizedEntryException e){

    }
  }
}
