package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stephenvolf on 11/01/17.
 */
public interface StatementContext {

    String sql();

    <S extends ExecutionStrategy> S strategy();

    interface ExecutionStrategy {
        void setParameters(PreparedStatement statement) throws SQLException;
    }

}
