package xxl.core;


import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import xxl.core.exception.UnrecognizedEntryException;

class Parser {

    private Spreadsheet _spreadsheet;

    Parser() throws UnrecognizedEntryException {
    }

    Parser(Spreadsheet spreadsheet) throws UnrecognizedEntryException {
        _spreadsheet = spreadsheet;
    }

    Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException /* More Exceptions? */ {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            parseDimensions(reader);

            String line;

            while ((line = reader.readLine()) != null)
                parseLine(line);
        }

        return _spreadsheet;
    }

    private void parseDimensions(BufferedReader reader) throws UnrecognizedEntryException, IOException {
        int rows = -1;
        int columns = -1;

        for (int i = 0; i < 2; i++) {
            String[] dimension = reader.readLine().split("=");
            if (dimension[0].equals("linhas"))
                rows = Integer.parseInt(dimension[1]);
            else if (dimension[0].equals("colunas"))
                columns = Integer.parseInt(dimension[1]);
            else
                throw new UnrecognizedEntryException("Invalid keyword on line "+i);
        }

        if (rows <= 0 || columns <= 0)
            throw new UnrecognizedEntryException("Invalid spreadsheet dimensions");

        _spreadsheet = new Spreadsheet(rows, columns);
    }

    private void parseLine(String line) throws UnrecognizedEntryException /*, more exceptions? */{
        String[] components = line.split("\\|");

        if (components.length == 1) // do nothing
            return;

        if (components.length == 2) {
            String[] address = components[0].split(";");
            Content content = parseContent(components[1]);
            _spreadsheet.insertContent(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
        } else
            throw new UnrecognizedEntryException("Wrong format in line: " + line);
    }

    // parse the beginning of an expression
    Content parseContent(String contentSpecification) throws UnrecognizedEntryException {
        char c = contentSpecification.charAt(0);

        try {
            if (c == '=') {
                return parseContentExpression(contentSpecification.substring(1));
            }else{
                return parseLiteral(contentSpecification);
            }
        } catch (UnrecognizedEntryException e) {
            throw e;
        }
    }

    private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
        if (literalExpression.charAt(0) == '\'')
            return new Text(literalExpression);
        else {
            try {
                int val = Integer.parseInt(literalExpression);
                return new Number(val);
            } catch (NumberFormatException nfe) {
                throw new UnrecognizedEntryException("Invalid number: " + literalExpression);
            }
        }
    }

    // contentSpecification is what comes after '='
    private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException /*more exceptions */ {
        if (contentSpecification.contains("("))
            return parseFunction(contentSpecification);
        // It is a reference
        String[] address = contentSpecification.split(";");
        return new Reference(_spreadsheet.getCell(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1])));
    }

    private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException /*more exceptions */ {
        String[] components = functionSpecification.split("[()]");
        if (components[1].contains(","))
            return parseBinaryFunction(components[0], components[1]);

        return parseIntervalFunction(components[0], components[1]);
    }

    private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException /* , more Exceptions */ {
        String[] arguments = args.split(",");
        Content arg0 = parseArgumentExpression(arguments[0]);
        Content arg1 = parseArgumentExpression(arguments[1]);

        return switch (functionName) {
            case "ADD" -> new Add(arg0, arg1);
            case "SUB" -> new Sub(arg0, arg1);
            case "MUL" -> new Mul(arg0, arg1);
            case "DIV" -> new Div(arg0, arg1);
            default -> throw new UnrecognizedEntryException("Invalid Function Name " + functionName);//dar erro com função inválida: functionName;
        };
    }

    private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
        if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
            String[] address = argExpression.split(";");
            return new Reference(_spreadsheet.getCell(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1])));
            // pode ser diferente do anterior em parseContentExpression
        } else
            return parseLiteral(argExpression);
    }

    private Content parseIntervalFunction(String functionName, String gammaDescription)
            throws UnrecognizedEntryException /* , more exceptions ? */ {
        Gamma gamma = _spreadsheet.createGamma(gammaDescription);
        return switch (functionName) {
            case "CONCAT" -> new Concat(gamma);
            case "COALESCE" -> new Coalesce(gamma);
            case "PRODUCT" -> new Product(gamma);
            case "AVERAGE" -> new Average(gamma) ;
            default -> throw new UnrecognizedEntryException("Invalid Function Name " + functionName);//dar erro com função inválida: functionName;
        };
    }

  /* Na classe Spreadsheet preciso de algo com a seguinte funcionalidade
  Range createRange(String range) throws ? {
    String[] rangeCoordinates;
    int firstRow, firstColumn, lastRow, lastColumn;

    if (range.indexOf(':') != -1) {
      rangeCoordinates = range.split("[:;]");
      firstRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = Integer.parseInt(rangeCoordinates[1]);
      lastRow = Integer.parseInt(rangeCoordinates[2]);
      lastColumn = Integer.parseInt(rangeCoordinates[3]);
    } else {
      rangeCoordinates = range.split(";");
      firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
    }

    // check if coordinates are valid
    // if yes
    return new Range with firstRow, firstColumn, lastRow, lastColumn and spreadsheet;
  }
  */

}
