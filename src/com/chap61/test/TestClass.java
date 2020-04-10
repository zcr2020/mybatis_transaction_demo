package com.chap61.test;

import java.util.List;
import java.util.Scanner;

import javax.naming.directory.SearchControls;

import org.apache.ibatis.annotations.Update;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chap61.dao.UserDAO;
import com.chap61.domain.Users;

public class TestClass {
	Scanner input = new Scanner(System.in);

	@Test
	public void test() {
		// �õ�Spring�ĺ�������
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		UserDAO userDAO = (UserDAO) context.getBean("userDao");
		System.out.println(userDAO.findUserById(2));
		System.out.println("��ѡ��1.�û�ע�� 2.�û���¼3.ģ����ѯ4.�޸��û���Ϣ  0.�˳�����");
		int choice = input.nextInt();
		while (choice != 0) {
			switch (choice) {
			case 1:
				register(userDAO);
				break;
			case 2:
				login(userDAO);
				break;
			case 3:
				search(userDAO);
				break;
			case 4:
				update(userDAO);
			}
			System.out.println("��ѡ��1.�û�ע�� 2.�û���¼3.�˳�����");
			choice = input.nextInt();
		}

	}
	public void update(UserDAO  userDAO){
		System.out.println("�������޸��û���LoginID");
		String loginId=input.next();
		System.out.println("�������޸��û����ֶΣ�");
		String field=input.next();
		System.out.println("�������µ�ֵ��");
		String value=input.next();
		if(userDAO.UdpateUser(loginId, field, value)){
			System.out.println("�û���Ϣ���³ɹ�");
		}
		else{
			System.out.println("�û���Ϣ����ʧ�ܣ�");
		}
	}
	public void search(UserDAO userDAO){
		System.out.println("������Ҫ���ҵ��ֶ�");
		String name=input.next();
		System.out.println("������������ֵ��");
		String value=input.next();
		List<Users>  users=userDAO.listUsersByCondition(name,value);
		for(Users u:users){
			System.out.println(u);
		}
	}
	public void login(UserDAO userDAO) {
		System.out.println("�������û�����");
		String nameString = input.next();
		System.out.println("���������룺");
		String pwdString = input.next();
		if (userDAO.doLogin(nameString, pwdString)) {
			System.out.println("�û���¼�ɹ���");
		} else {
			System.out.println("�û���¼ʧ�ܣ�");
		}
	}

	public void register(UserDAO userDAO) {
		System.out.println("�������û�����");
		String loginId = input.next();
		System.out.println("���������룺");
		String loginPwd = input.next();
		System.out.println("��������ʵ����");
		String name = input.next();
		System.out.println("�������ַ");
		String address = input.next();
		System.out.println("������绰");
		String phone = input.next();
		System.out.println("���������䣺");
		String mail = input.next();
		Users users = new Users();
		users.setLoginId(loginId);
		users.setLoginPwd(loginPwd);
		users.setName(name);
		users.setAddress(address);
		users.setPhone(phone);
		users.setMail(mail);
		if (userDAO.addUser(users)) {
			System.out.println("�û�ע��ɹ�");
		} else {
			System.out.println("�û�ע��ʧ�ܣ�");
		}
	}

}
