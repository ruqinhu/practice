package org.ruqinhu.liang.food.controller;

import org.apache.commons.lang3.StringUtils;
import org.ruqinhu.liang.food.model.User;
import org.ruqinhu.liang.food.service.LoginService;
import org.ruqinhu.liang.food.util.KeyValueUtil;
import org.ruqinhu.liang.food.util.RandomUUIDUtil;
import org.ruqinhu.liang.food.util.SecurityUtil;

public class LoginController {

    //可以使用 spring 容器来注入
    private LoginService loginService = new LoginService();

    /**
     * 获取加密的 salt
     * salt 可以不固定，可以加上时间维度
     * @return 加密的 salt
     */
    public String getSalt() {
        //生成加密的key
        return "hello world";
    }

    /**
     * 获取身份标识
     * @param account 用户名
     * @param password 密码
     * @param salt 盐
     * @return
     */
    public String login(String account, String password, String salt) {
        //如果有全局异常处理，这里可以直接抛出指定的异常
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password) || StringUtils.isEmpty(salt)) {
            return "parameter err";
        }

        //加密后与数据库中的密文比对
        if (SecurityUtil.sha1(account + password + salt).equals(password)) {
            return "login err";
        }

        //查询数据库获取指定用户信息
        User user = loginService.getUserByAccount(account);

        //身份信息缓存到 key-value 型数据库
        String key = RandomUUIDUtil.get32RandomStr();
        if (KeyValueUtil.set(key, user, 7200)) {
            return key;
        }

        return "err";
    }

}
