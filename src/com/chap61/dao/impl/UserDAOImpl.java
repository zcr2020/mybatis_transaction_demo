package com.chap61.dao.impl;

import java.util.List;
import java.util.Properties;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.chap61.dao.UserDAO;
import com.chap61.domain.Users;
import com.chap61.util.MyBatisUtils;
/**
 * UserDAOImpl类实现UserDAO接口，用MyBatis框架实现用户登录验证 
 * @author Administrator
 *
 */
@Repository("userDao")
public class UserDAOImpl implements UserDAO {
	@Override
	/**
	 * 用MyBatis实现用户登录验证
	 */
	public boolean doLogin(String loginId, String loginPwd) {
		/**
		 * 得到SqlSession
		 */
		SqlSession  sqlSession=MyBatisUtils.getSqlSession();
		Users  user=new Users();
		user.setLoginId(loginId);
		user.setLoginPwd(loginPwd);
		int row=sqlSession.selectOne("com.chap61.mapper.UsersMapper.doLogin",user);
		sqlSession.close();
		return row>0?true:false;
	}

	@Override
	/**
	 * 用户注册，将user信息写入数据库的Users表中
	 * @param user
	 * @return  true 注册成功  false 注册失败
	 */
	public boolean addUser(Users user) {
		/**
		 * 得到SqlSession
		 */
		SqlSession  sqlSession=MyBatisUtils.getSqlSession();
		// TODO Auto-generated method stub
		int row=sqlSession.insert("com.chap61.mapper.UsersMapper.addUser", user);
		sqlSession.commit();
		sqlSession.close();
		return row>0?true:false;
	}
	@Override
	/**
	 * 修改指定loginId的用户的field字段的值 为value
	 * @param field User表的字段名
	 * @param value  修改的值
	 * @return  true  修改成功   false  修改失败
	 */
	public boolean UdpateUser(String loginId,String field, String value) {
		// TODO Auto-generated method stub
		/**
		 * 得到SqlSession
		 */
		SqlSession  sqlSession=MyBatisUtils.getSqlSession();
		Properties  properties=new Properties();
		properties.put("loginId", loginId);
		properties.put("field", field);
		properties.put("value", value);
		int row=sqlSession.update("com.chap61.mapper.UsersMapper.updateUser", properties);
		sqlSession.commit();
		sqlSession.close();
		return row>0?true:false;
	}
	@Override
	/**
	 * 按照指定字段模糊查询用户信息
	 * @param name:查找的字段
	 * @param  value:查找的值
	 * @return  返回users表中name字段含有value的所有用户信息
	 */
	public List<Users> listUsersByCondition(String name,String value) {
		/**
		 * 得到SqlSession
		 */
		SqlSession  sqlSession=MyBatisUtils.getSqlSession();
		Properties  properties=new Properties();
		properties.setProperty("name", name);
		properties.setProperty("value", value);
		List<Users>  userList=sqlSession.selectList("com.chap61.mapper.UsersMapper.listUsersByCondition",properties);
		sqlSession.close();
		return userList;
	}
	@Override
	public Users findUserById(int id) {
		// TODO Auto-generated method stub
		SqlSession  sqlSession=MyBatisUtils.getSqlSession();
		Users user=sqlSession.selectOne("com.chap61.mapper.UsersMapper.findUser",id);
		sqlSession.close();
		return user;
	}
}
