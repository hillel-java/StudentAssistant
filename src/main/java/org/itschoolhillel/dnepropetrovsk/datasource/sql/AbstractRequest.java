package org.itschoolhillel.dnepropetrovsk.datasource.sql;

import com.jolbox.bonecp.BoneCP;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.QueryContext;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.StatementContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stephenvolf on 15/01/17.
 */
public abstract class AbstractRequest <S extends StatementContext.ExecutionStrategy> {
    protected ResultSet resultSet;
    protected PreparedStatement statement;

    public void doRequest(BoneCP connectionPool, StatementContext context) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(context.sql());
            S strategy = context.strategy();
            strategy.setParameters(statement);
            applyIternalStrategy(strategy);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
    }

    private void close(AutoCloseable entity) {
        if (entity != null) {
            try {
                entity.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void applyIternalStrategy(S strategy) throws SQLException;

    static class SelectRequest extends AbstractRequest<QueryContext.QueryStrategy>{

        @Override
        protected void applyIternalStrategy(QueryContext.QueryStrategy strategy) throws SQLException {
            resultSet = statement.executeQuery();
            strategy.extractResult(resultSet);
        }

    }

    static class CUDRequest extends AbstractRequest<StatementContext.ExecutionStrategy> {

        @Override
        protected void applyIternalStrategy(StatementContext.ExecutionStrategy strategy) throws SQLException {
            statement.executeUpdate();
        }
    }
}

