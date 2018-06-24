package cn.dzj.service;

import cn.dzj.dao.IUserInfoDAO;
import cn.dzj.domain.UserInfo;

public class UserInfoServiceImpl implements IUserInfoService{
    private IUserInfoDAO dao;

    public void add(UserInfo info) {
        dao.add(info);
    }
    public IUserInfoDAO getDao() {
        return dao;
    }
    public void setDao(IUserInfoDAO dao) {
        this.dao = dao;
    }
}
