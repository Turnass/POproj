package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import xxl.core.SearchValue;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

  DoShowValues(Spreadsheet receiver) {
    super(Label.SEARCH_VALUES, receiver);
    addStringField("value", Message.searchValue());
  }
  
  @Override
  protected final void execute() {
    //_display.addAll(_receiver.searchValue(stringField("value")));
    _display.addAll(_receiver.search(new SearchValue(stringField("value"))));
    _display.display();
  }
}
