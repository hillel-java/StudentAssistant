package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

/**
 * Created by svolkovskyi on 03.02.17.
 */
public interface InsertContext extends StatementContext {
    int generatedKey();

    interface InsertStrategy extends ExecutionStrategy {
        void onGeneratedKey(int generatedKey);
    }
}
