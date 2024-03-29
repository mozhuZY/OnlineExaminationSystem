package com.zy.oes.common.util;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zy.oes.common.token.Token;
import com.zy.oes.module.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: OnlineExaminationSystem
 * @className: TokenUtil
 * @author: MoZhu
 * @date: 2023/3/10 1:42
 * @description: <p> Token工具类 </p>
 */
@SuppressWarnings("all")
@Component
@Slf4j
public class TokenUtil {

    /**
     * redisTemplate
     */
    private final StringRedisTemplate template;

    /**
     * redis-hash
     */
    private final ValueOperations<String, String> redis;

    @Autowired
    public TokenUtil(StringRedisTemplate template) {
        this.template = template;
        this.redis = template.opsForValue();
    }

    /**
     * @param user 用户对象
     * @return {@link Token}
     * @title getToken
     * @description <p> 通过用户对象获取token </p>
     * @date 2023/3/11 1:28
     * @author MoZhu
     */
    public Token setToken(User user) {
        // 生成token
        Token token = generalToken(user);
        redis.append(token.getToken(), JSON.toJSONString(user));
        // 设置过期时间
        template.expire(token.getToken(), Token.ALIVE, TimeUnit.SECONDS);
        return token;
    }

    /**
     * @param token token对象
     * @return {@link User}
     * @title getUser
     * @description <p> 通过token获取用户信息 </p>
     * @date 2023/3/11 1:39
     * @author MoZhu
     */
    public User getUser(Token token) {
        return (User) JSON.parseObject(redis.get(token.getToken()), User.class);
    }

    /**
     * @param user 用户对象
     * @return {@link Token}
     * @title generalToken
     * @description <p> 生成token </p>
     * @date 2023/3/11 1:27
     * @author MoZhu
     */
    private Token generalToken(User user) {
        String tokenStr = DigestUtil.md5Hex(Long.toString(new Date().getTime()) + user.getId());
        return new Token(tokenStr);
    }

    /**
     * @title getCurrentToken
     * @description <p> 获取当前请求的token </p>
     * @date 2023/3/15 3:49
     * @author MoZhu
     * @param
     * @return {@link Token}
     */
    public Token getCurrentToken() {
        return new Token(HttpUtil.getCurrentRequest().getHeader(Token.HEADER));
    }

    /**
     * @title getCurrentUser
     * @description <p> 获取当前请求用户 </p>
     * @date 2023/3/15 3:50
     * @author MoZhu
     * @param
     * @return {@link User}
     */
    public User getCurrentUser() {
        return getUser(getCurrentToken());
    }

    /**
     * @title destroyToken
     * @description <p> 销毁指定token </p>
     * @date 2023/4/25 1:37
     * @author MoZhu
     * @param token 指定的token
     * @return {@link boolean}
     */
    public boolean destroyToken(Token token) {
        return template.delete(token.getToken());
    }

    /**
     * @title destroyCurrentToken
     * @description <p> 销毁当前token </p>
     * @date 2023/4/25 1:40
     * @author MoZhu
     * @param
     * @return {@link boolean}
     */
    public boolean destroyCurrentToken() {
        return template.delete(getCurrentToken().getToken());
    }
}
