package org.ruqinhu.liang.food.service;

import org.ruqinhu.liang.food.model.User;
import org.ruqinhu.liang.food.util.RandomUUIDUtil;

public class LoginService {

    /**
     * 省略与数据库交互操作
     * @param account
     * @return
     */
    public User getUserByAccount(String account) {
        User user = new User();
        user.setAccount(RandomUUIDUtil.get32RandomStr());
        user.setStoreId(RandomUUIDUtil.get32RandomStr());
        return user;
    }

}
