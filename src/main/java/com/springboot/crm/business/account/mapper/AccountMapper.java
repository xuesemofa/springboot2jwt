package com.springboot.crm.business.account.mapper;

import com.github.pagehelper.Page;
import com.springboot.crm.business.account.mapper.sql.AccountSql;
import com.springboot.crm.business.account.model.AccountModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface AccountMapper {

    String table = " account_table ";

    @Insert({
            "insert into " + table + " (uuid,account,password,salt,lx,parents,bmid,is_login,systimes,creator_acc_id,name,phone) " +
                    " values (#{model.uuid},#{model.account},#{model.password},#{model.salt},#{model.lx},#{model.parents}," +
                    "#{model.isLogin},#{model.systimes},#{model.creatorAccId},#{model.name},#{model.phone})"
    })
    int add(@Param("model") AccountModel model);

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    int deleteById(@Param("uuid") String uuid);

    @UpdateProvider(type = AccountSql.class, method = "updateByIdSql")
    int updateById(@Param("model") AccountModel model);

    @Update({
            "update " + table + " set password = #{password} where account = #{account}"
    })
    int updateByAccount(@Param("account") String account, @Param("password") String password);

    @SelectProvider(type = AccountSql.class, method = "findAllSql")
    @Results(id = "accountMap", value = {
            @Result(property = "isLogin", column = "is_login"),
            @Result(property = "creatorAccId", column = "creator_acc_id"),
    })
    Page<AccountModel> findAll(@Param("model") AccountModel model);

    @Select({
            "select * from " + table + " where uuid = #{uuid}"
    })
    @ResultMap("accountMap")
    AccountModel getByUuid(@Param("uuid") String uuid);

    @Select({
            "select * from " + table + " where account = #{account}"
    })
    @ResultMap("accountMap")
    AccountModel getByAccount(@Param("account") String account);

    @Select({
            "select * from " + table + " where name = #{name}"
    })
    @ResultMap("accountMap")
    AccountModel getByName(@Param("name") String name);

    //    property 实体类名称
//    column 注解名称
    @Select({
            "select * from " + table + " where parents = #{parents}"
    })
    @ResultMap("accountMap")
    List<AccountModel> findByParents(@Param("parents") String parents);

    @Select({
            "select * from " + table + " where parents != '-1'"
    })
    @ResultMap("accountMap")
    List<AccountModel> findByParents3();

    @Select({
            "SELECT a.* FROM account_table a WHERE a.parents IN ( SELECT b.uuid FROM bmgl_table b " +
                    "WHERE b.parents = ( SELECT a1.parents FROM account_table a1 WHERE a1.uuid = #{uuid} ) )"
    })
    @ResultMap("accountMap")
    List<AccountModel> findByParents2(@Param("uuid") String uuid);
}
