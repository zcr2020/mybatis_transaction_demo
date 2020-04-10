package com.chap61.dao;

import java.util.List;

import com.chap61.domain.Users;

/**
 * UserDAO�ӿڣ�����ʵ���û���¼��֤
 * @author Administrator
 *
 */
public interface UserDAO {
	/**
	 * �û���¼��֤
	 * @param loginId  ���û���
	 * @param loginPwd  ������
	 * @return  true����¼�ɹ���false ��¼ʧ��
	 */
	public boolean doLogin(String loginId,String loginPwd);
	/**
	 * �û�ע�ᣬ��user��Ϣд�����ݿ��Users����
	 * @param user
	 * @return  true ע��ɹ�  false ע��ʧ��
	 */
	public boolean addUser(Users  user);
	/**
	 * �޸��û���field�ֶ�
	 * @param field  
	 * @param value
	 * @return
	 */
	public boolean UdpateUser(String loginId,String field,String value);
	/**
	 * ����ָ���ֶ�ģ����ѯ�û���Ϣ
	 * @param name:���ҵ��ֶ�
	 * @param  value:���ҵ�ֵ
	 * @return  ����users����name�ֶκ���value�������û���Ϣ
	 */
	public List<Users>  listUsersByCondition(String name,String value);
	public Users findUserById(int id);
}
