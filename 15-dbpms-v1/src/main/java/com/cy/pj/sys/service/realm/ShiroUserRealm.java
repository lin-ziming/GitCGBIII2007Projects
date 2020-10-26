package com.cy.pj.sys.service.realm;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.pojo.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    /**获取并封装授权信息(做授权业务时写此方法)*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }
    /**获取并封装认证信息*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取登录时输入的用户名
        UsernamePasswordToken uToken= (UsernamePasswordToken) authenticationToken;
        String username=uToken.getUsername();
        //2.基于用户名查询数据库中的用户信息
        SysUser user = sysUserDao.findUserByUserName(username);
        //3.校验用户是否已存在
        if(user==null) throw new UnknownAccountException();
        //4.校验用户是否已被禁用
        if(user.getValid()==0) throw new LockedAccountException();
        //5.封装用户信息由底层进行密码校验
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        return new SimpleAuthenticationInfo(user, //principal 身份
                user.getPassword(), //hashedCredentials 已加密的密码
                credentialsSalt,    //credentialsSalt 凭证盐
                this.getName());    //
    }
    /**此方法中返回凭证加密对象，基于此对象对用户输入的密码进行加密操作*/
    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }//对于此get方法而言，也可以重写set方法
}
