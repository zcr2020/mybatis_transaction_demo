package com.chap61.dao.impl;

import java.util.List;
import java.util.Properties;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.chap61.dao.UserDAO;
import com.chap61.domain.Users;
import com.chap61.util.MyBatisUtils;
/**
 * UserDAOImpl��ʵ��UserDAO�ӿڣ���MyBatis���ʵ���û���¼��֤ 
 * @author Administrator
 *
 */
@Repository("userDao")
public class UserDAOImpl implements UserDAO {
	@Override
	/**
	 * ��MyBatisʵ���û���¼��֤
	 */
	public boolean doLogin(String loginId, String loginPwd) {
		/**
		 * �õ�SqlSession
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
	 * �û�ע�ᣬ��user��Ϣд�����ݿ��Users����
	 * @param user
	 * @return  true ע��ɹ�  false ע��ʧ��
	 */
	public boolean addUser(Users user) {
		/**
		 * �õ�SqlSession
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
	 * �޸�ָ��loginId���û���field�ֶε�ֵ Ϊvalue
	 * @param field User����ֶ���
	 * @param value  �޸ĵ�ֵ
	 * @return  true  �޸ĳɹ�   false  �޸�ʧ��
	 */
	public boolean UdpateUser(String loginId,String field, String value) {
		// TODO Auto-generated method stub
		/**
		 * �õ�SqlSession
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
	 * ����ָ���ֶ�ģ����ѯ�û���Ϣ
	 * @param name:���ҵ��ֶ�
	 * @param  value:���ҵ�ֵ
	 * @return  ����users����name�ֶκ���value�������û���Ϣ
	 */
	public List<Users> listUsersByCondition(String name,String value) {
		/**
		 * �õ�SqlSession
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
