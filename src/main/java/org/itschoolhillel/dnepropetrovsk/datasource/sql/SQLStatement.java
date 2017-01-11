package org.itschoolhillel.dnepropetrovsk.datasource.sql;

import com.jolbox.bonecp.BoneCP;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.StatementContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stephenvolf on 11/01/17.
 */
public class SQLStatement {
    private final BoneCP connectionPool;
    private final StatementContext context;

    public SQLStatement(BoneCP connectionPool, StatementContext context) {
        this.connectionPool = connectionPool;
        this.context = context;
    }

    public void executeQuery() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(context.sql());
            StatementContext.ExecutionStrategy strategy = context.strategy();
            strategy.setParameters(statement);
            resultSet = statement.executeQuery();
            strategy.extractResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}