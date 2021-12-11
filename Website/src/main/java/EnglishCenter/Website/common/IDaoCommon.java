package EnglishCenter.Website.common;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public interface IDaoCommon {
    public SessionFactory getSessionFactory();
    public Session getSession();
    public String getTableName(Class dataModel);
    public void saveOrUpdate(Object dataObject);
    public void save(Object dataObject);
    public void saveWithId(Object dataObject);
    public void delete(Object entity);
    public <T> List<T> list(String nativeQuery, Map<String, Object> mapParams, Class obj);
    public Query createQuery(String hql);
    public SQLQuery createSQLQuery(String sql);
    public void setResultTransformer(SQLQuery query, Class obj);
    public List<String> getReturnAliasColumns(SQLQuery query);
}
