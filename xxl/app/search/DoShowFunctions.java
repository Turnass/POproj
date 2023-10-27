package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import xxl.core.SearchFunctions;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("function", Message.searchFunction());
  }

  @Override
  protected final void execute() {
    //_display.addAll(_receiver.searchFunction(stringField("function")));
    _display.addAll(_receiver.search(new SearchFunctions(stringField("function"))));
    _display.display();
  }
}
