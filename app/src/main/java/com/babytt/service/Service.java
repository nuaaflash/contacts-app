package com.babytt.service;

import java.util.List;

import com.babytt.dataaccess.DBOperation;
import com.babytt.model.Account;

import android.content.Context;

public class Service {
	
	private DBOperation dao=null;
	
	//���캯��
	public Service(Context context){
		dao = new DBOperation(context);
	}
	
	//������ϵ�˵ķ���
	public boolean save(Account account){
		boolean flag = dao.save(account);
		return flag;
	}
	
	//��ѯ��ϵ�˷���
	public List getByName(String queryName){
		List list = dao.getByName(queryName);
		return list;
	}
	
	//ID��ѯ
	public Account getById(int id){
		Account account = dao.getById(id);
		return account;
	}
	
	//�޸���ϵ�˷���
	public boolean update(Account account){
		boolean flag = dao.update(account);
		return flag;
	}
	
	//ɾ����ϵ�˷���
	public void delete(int id){
		dao.delete(id);
	}
}
