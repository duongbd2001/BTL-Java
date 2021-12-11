package EnglishCenter.Website.common;

import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DaoCommonImp implements IDaoCommon{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HttpServletRequest req;


    @Override
    public SessionFactory getSessionFactory() {
        return entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
    }


    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public String getTableName(Class dataModel) {
        Class<?> modelClass = dataModel;
        Table table = modelClass.getAnnotation(Table.class);
        String tableName = table.name();
        return tableName;
    }

    @Override
    public void saveOrUpdate(Object dataObject) {
        getSession().saveOrUpdate(dataObject);
    }

    @Override
    public void save(Object dataObject) {
        getSession().save(dataObject);
    }

    @Override
    public void saveWithId(Object dataObject) {
        getSession().replicate(dataObject, ReplicationMode.EXCEPTION);
    }

    @Override
    public void delete(Object entity) {
        getSession().delete(entity);
    }

    @Override
    public Query createQuery(String hql) {
        return getSession().createQuery(hql);
    }

    @Override
    public SQLQuery createSQLQuery(String sql) {
        return getSession().createSQLQuery(sql);
    }


    @Override
    public void setResultTransformer(SQLQuery query, Class obj) {
        Field[] fileds = obj.getDeclaredFields();
        Map<String, String> mapFileds = new HashMap<>();
        for (Field filed : fileds) {
            mapFileds.put(filed.getName(), filed.getGenericType().toString());
        }
        List<String> aliasColumns = getReturnAliasColumns(query);
        for (String aliasColumn : aliasColumns) {
            String dataType = mapFileds.get(aliasColumn);
            if (dataType != null) {
                Type hbmType = null;
                if ("class java.lang.Long".equals(dataType)) {
                    hbmType = LongType.INSTANCE;
                } else if ("class java.lang.Integer".equals(dataType)) {
                    hbmType = IntegerType.INSTANCE;
                } else if ("class java.lang.Double".equals(dataType)) {
                    hbmType = DoubleType.INSTANCE;
                } else if ("class java.lang.String".equals(dataType)) {
                    hbmType = StringType.INSTANCE;
                } else if ("class java.lang.Boolean".equals(dataType)) {
                    hbmType = BooleanType.INSTANCE;
                } else if ("class java.util.Date".equals(dataType)) {
                    hbmType = TimestampType.INSTANCE;
                } else if ("class java.math.BigDecimal".equals(dataType)) {
                    hbmType = new BigDecimalType();
                }

                if (hbmType != null) {
                    query.addScalar(aliasColumn, hbmType);
                }
            }
        }
        query.setResultTransformer(Transformers.aliasToBean(obj));

    }

    @Override
    public <T> List<T> list(String nativeQuery, Map<String, Object> mapParams, Class obj) {
        SQLQuery query = createSQLQuery(nativeQuery);
        setResultTransformer(query, obj);

        if (mapParams != null && !mapParams.isEmpty()) {
            query.setProperties(mapParams);
        }
        return query.list();
    }



    @Override
    public List<String> getReturnAliasColumns(SQLQuery query) {
        List<String> aliasColumns = new ArrayList<>();
        String sqlQuery = query.getQueryString();
        sqlQuery = sqlQuery.replace("\n", " ");
        sqlQuery = sqlQuery.replace("\t", " ");
        int numOfRightPythis = 0;
        int startPythis = -1;
        int endPythis = 0;
        boolean hasRightPythis = true;
        while (hasRightPythis) {
            char[] arrStr = sqlQuery.toCharArray();
            hasRightPythis = false;
            int idx = 0;
            for (char c : arrStr) {
                if (idx > startPythis) {
                    if ("(".equalsIgnoreCase(String.valueOf(c))) {
                        if (numOfRightPythis == 0) {
                            startPythis = idx;
                        }
                        numOfRightPythis++;
                    } else if (")".equalsIgnoreCase(String.valueOf(c))) {
                        if (numOfRightPythis > 0) {
                            numOfRightPythis--;
                            if (numOfRightPythis == 0) {
                                endPythis = idx;
                                break;
                            }
                        }
                    }
                }
                idx++;
            }
            if (endPythis > 0) {
                sqlQuery = sqlQuery.substring(0, startPythis) + " # " + sqlQuery.substring(endPythis + 1);
                hasRightPythis = true;
                endPythis = 0;
            }
        }
        String arrStr[] = sqlQuery.substring(0, sqlQuery.toUpperCase().indexOf(" FROM ")).split(",");
        for (String str : arrStr) {
            String[] temp = str.trim().split(" ");
            String alias = temp[temp.length - 1].trim();
            if (alias.contains(".")) {
                alias = alias.substring(alias.lastIndexOf(".") + 1).trim();
            }
            if (alias.contains(",")) {
                alias = alias.substring(alias.lastIndexOf(",") + 1).trim();
            }
            if (alias.contains("`")) {
                alias = alias.replace("`", "");
            }
            if (!aliasColumns.contains(alias)) {
                aliasColumns.add(alias);
            }
        }
        return aliasColumns;
    }

}
