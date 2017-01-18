package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stephenvolf on 15/01/17.
 */
public interface QueryContext<V> extends StatementContext {

    V result();

    interface QueryStrategy<T> extends ExecutionStrategy {

        void extractResult(ResultSet resultSet) throws SQLException;

        T result();
    }
}
