package cn.dzj.dao;

import cn.dzj.domain.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class IUserInfoDAOImpl implements IUserInfoDAO{

    private SqlSessionFactory sessionFactory;

    @Override
    public void add(UserInfo info) {
        SqlSession sqlSession = sessionFactory.openSession();
        sqlSession.insert("cn.dzj.dao.IUserInfoDAO.add",info);
        sqlSession.commit();
        sqlSession.close();
    }

    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
