package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stephenvolf on 11/01/17.
 */
public interface StatementContext<V> {

    String sql();

    ExecutionStrategy strategy();

    V result();

    interface ExecutionStrategy<T> {
        void setParameters(PreparedStatement statement) throws SQLException;

        void extractResult(ResultSet resultSet) throws SQLException;

        T result();
    }

}
