package org.itschoolhillel.dnepropetrovsk.datasource.sql;

import com.jolbox.bonecp.BoneCP;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.InsertContext;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.QueryContext;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.StatementContext;

import java.sql.*;

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
            statement = connection.prepareStatement(context.sql(), Statement.RETURN_GENERATED_KEYS);
            S strategy = context.strategy();
            strategy.setParameters(statement);
            applyIternalStrategy(strategy);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, resultSet);
        }
    }

    private void close(AutoCloseable... entities){
        for(AutoCloseable entity : entities){
            if (entity != null) {
                try {
                    entity.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    static class InsertRequest extends AbstractRequest<InsertContext.InsertStrategy> {

        @Override
        protected void applyIternalStrategy(InsertContext.InsertStrategy strategy) throws SQLException {
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                int generatedKey = rs.getInt(1);
                strategy.onGeneratedKey(generatedKey);
            }
        }
    }

    static class UDRequest extends AbstractRequest<StatementContext.ExecutionStrategy> {

        @Override
        protected void applyIternalStrategy(StatementContext.ExecutionStrategy strategy) throws SQLException {
            statement.executeUpdate();
        }
    }
}

