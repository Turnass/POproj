package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;
// FIXME import classes

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
    addStringField("gamma", Message.address());
  }
  
  @Override
  protected final void execute() {
    try {
      _display.addAll(_receiver.showClipboard());
      _display.display();
    } catch (UnrecognizedEntryException e) {

    }
  }
}
