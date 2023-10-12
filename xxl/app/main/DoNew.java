package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addBooleanField("guardar", Message.saveBeforeExit());
    addIntegerField("lines", Message.lines());
    addIntegerField("columns", Message.columns());
  }
  
  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    boolean res = booleanField("guardar");
    if (res){
      _receiver.saveRepo();
    }
    Integer lines = integerField("lines");
    Integer columns = integerField("columns");

    _receiver.createSpreadsheet(lines, columns);
  }
}
