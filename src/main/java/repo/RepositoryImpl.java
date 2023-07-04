package repo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static util.UtilConnection.getConnection;

public class RepositoryImpl implements Repository {

    private final Connection connection = getConnection();

    private final static String SQL_QUERY = "select " +
            "    TABLE_NAME, " +
            "    COLUMN_NAME, " +
            "    COLUMN_TYPE " +
            "from TABLE_COLS " +
            "where lower(COLUMN_NAME) in (select lower(PK) from TABLE_LIST) " +
            "   or lower(COLUMN_NAME) in (select lower( SUBSTRING(PK ,0 , INSTR(PK, ', ') - 1)) from TABLE_LIST) " +
            "   or lower(COLUMN_NAME) in (select lower( SUBSTRING(PK ,INSTR(PK, ', ') + 2)) from TABLE_LIST)";

    @Override
    public String getContent() {
        Statement statement;
        StringBuilder builder = new StringBuilder();
        try {
            statement = connection.createStatement();
            var resultSet = statement.executeQuery(SQL_QUERY);

            while (resultSet.next()) {
                builder.append(resultSet.getString("TABLE_NAME"))
                        .append(", ")
                        .append(resultSet.getString("COLUMN_NAME"))
                        .append(", ")
                        .append(resultSet.getString("COLUMN_TYPE"))
                        .append("\n");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
