package org.itschoolhillel.dnepropetrovsk.datasource.sql;

import com.jolbox.bonecp.BoneCP;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.InsertContext;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.QueryContext;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.StatementContext;

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
        AbstractRequest.UDRequest request = new AbstractRequest.UDRequest();
        request.doRequest(connectionPool, context);
    }

    public void executeQuery(QueryContext<?> context) {
        AbstractRequest.SelectRequest request = new AbstractRequest.SelectRequest();
        request.doRequest(connectionPool, context);
    }

    public void executeInsert(InsertContext context){
        AbstractRequest.InsertRequest request = new AbstractRequest.InsertRequest();
        request.doRequest(connectionPool, context);
    }
}