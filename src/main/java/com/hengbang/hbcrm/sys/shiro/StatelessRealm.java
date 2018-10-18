package com.hengbang.hbcrm.sys.shiro;

import com.auth0.jwt.interfaces.Claim;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.account.service.AccountService;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import com.hengbang.hbcrm.hb.jurisdiction.service.JurBmService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class StatelessRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;
    @Autowired
    private JurBmService jurBmService;

    //    Only StatelessToken type Token is supported.
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        ResponseResult<AccountModel> result1 = accountService.getByUuid(null);
        String uuid = result1.getData().getBmid();
        ResponseResult<List<JurisdictionModel>> result2 = jurBmService.findByBmId2(uuid);
        if (result2.isSuccess()) {
            result2.getData().forEach(k -> {
                if (k != null) {
                    info.addRole(k.getBs().split(":")[0]);
                    info.addStringPermission(k.getBs());
                }
            });
        }
        ResponseResult<AccountModel> result = accountService.getByUuid(null);
        if (result.isSuccess())
            info.addRole(result.getData().getLx() == -1 ? "admin" : "user");
        return info;
    }

    /**
     * 登陆验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        String token = (String) authcToken.getPrincipal();
        if (token == null)
            throw new UnknownAccountException("令牌丢失!");
        Map<String, Claim> map = JWTUtils.verifToken(token);
        if (map == null)
            throw new UnknownAccountException("令牌过期，请从新登陆!");
        String iss = map.get("iss").asString();
        if (!iss.equals("ldtoken"))
            throw new UnknownAccountException("令牌签名不正确!");
        Date nowDate = new Date();
        Date iat = map.get("iat").asDate();
        if (nowDate.before(iat))
            throw new UnknownAccountException("令牌过期，请从新登陆!");
        Date exp = map.get("exp").asDate();
        if (exp.before(nowDate))
            throw new UnknownAccountException("令牌过期，请从新登陆!");

        String account = map.get("sub").asString();
        ResponseResult<AccountModel> result = accountService.getByUuid(account);
        if (!result.isSuccess())
            throw new UnknownAccountException("当前账户已不存在!");
        if (result.getData().getIsLogin().equals("N"))
            throw new UnknownAccountException("当前账户已禁止登陆!");
        return new SimpleAuthenticationInfo(token, token, getName());
    }

}
