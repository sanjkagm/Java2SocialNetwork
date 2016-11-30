package lv.javaguru.java2.database.jdbc;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.junit.Before;

public abstract class DBUnitTestCase {

    protected DatabaseUtil databaseUtil = new DatabaseUtil();


    @Before
    public void init() throws Exception {
        databaseUtil.setupDatabaseFromFile(getDatabaseFile());
    }

    protected abstract String getDatabaseFile();



}
