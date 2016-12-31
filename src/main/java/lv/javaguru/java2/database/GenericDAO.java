package lv.javaguru.java2.database;

/**
 * Created by Pavel on 28.12.2016..
 */
import org.hibernate.JDBCException;
import java.util.List;

public interface GenericDAO<T> {

    void create(T obj) throws JDBCException;
    void update(T obj) throws JDBCException;
    List<T> getAll() throws JDBCException;
    T getById(long id) throws JDBCException;
    void delete(long id) throws JDBCException;

}
