package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by stephenvolf on 15/01/17.
 */
public class AddLecture implements StatementContext, StatementContext.ExecutionStrategy {
    @Override
    public String sql() {
        //какая-то фигня, не знаю
        StatementContext context = new AddLecture();
        ExecutionStrategy strategy = new AddLecture();

        //приведение типов
        StatementContext contextFromStrategy = (StatementContext) strategy;

        ((StatementContext) strategy).strategy();

        getResult(); //эта херня все равно вернет false, но ее надо вызвать иначе не заработает

        return null;

    }

    public boolean getResult(){
        //TODO: implement this
        return false;
    }

    @Override
    public ExecutionStrategy strategy() {
        return this;
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {

    }
}
