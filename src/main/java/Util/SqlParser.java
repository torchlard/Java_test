package Util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

public class SqlParser {

  public static List<String> getSelectColumns(String sql) throws Exception {
    if (sql == null) {
      throw new Exception("SQL is null");
    }
    CCJSqlParserManager ccjSqlParserManager = new CCJSqlParserManager();
    List<String> columns = new ArrayList<String>();

    try {
      Statement statement = ccjSqlParserManager.parse(new StringReader(sql));
      if (statement instanceof Select) {
        SelectBody selectBody = ((Select) statement).getSelectBody();
        List<SelectItem> selectItems = ((PlainSelect) selectBody).getSelectItems();

        if (selectItems != null) {
          for (SelectItem item : selectItems) {

            // System.out.println("item: "+item);
            // TODO: need further parsing of *
            if (item instanceof AllColumns) {
              String column = item.toString();
              columns.add(column);
            }
            else if (item instanceof AllTableColumns) {
              columns.add(item.toString());
            }
            else if (item instanceof SelectExpressionItem) {
              Alias alias = ((SelectExpressionItem) item).getAlias();
              Expression expression = ((SelectExpressionItem) item).getExpression();
              if (alias != null) {
                String column = alias.getName();
                columns.add(column);
              } else if (expression != null) {
                columns.add(expression.toString());
              }
            }
          }
        }
      }
    } catch (JSQLParserException e) {
      throw new JSQLParserException(e.getMessage());
    }
    return columns;
  }
}