package org.itschoolhillel.dnepropetrovsk.datasource.sql;

import com.jolbox.bonecp.BoneCP;
import com.sun.glass.ui.EventLoop;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.QueryContext;
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

    public SQLStatement(BoneCP connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void executeUpdate(StatementContext context) {
        AbstractRequest.CUDRequest request = new AbstractRequest.CUDRequest();
        request.doRequest(connectionPool, context);
    }

    public void executeQuery(QueryContext<?> context) {
        AbstractRequest.SelectRequest request = new AbstractRequest.SelectRequest();
        request.doRequest(connectionPool, context);
    }

    public void executeQueryV2(QueryContext<?> context){
        new AbstractRequest<QueryContext.QueryStrategy>() {
            @Override
            protected void applyIternalStrategy(QueryContext.QueryStrategy strategy) throws SQLException {
                resultSet = statement.executeQuery();
                strategy.extractResult(resultSet);
            }
        }.doRequest(connectionPool, context);
    }


}