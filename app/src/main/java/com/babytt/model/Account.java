package com.babytt.model;

public class Account {
	private int id;
	private String name=null;         //����
	private String money=null;        //�ֻ���
	private String remark=null;       //��ע
	//���캯��
	public Account(){
		id=0;
		name="";
		money="";
		remark="";
	}
	//��ȡ��������ϵ��id
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	//��ȡ��������ϵ���ֻ���
	public void setMoney(String money){
		this.money = money;
	}
	public String getMoney(){
		return money;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return remark;
	}
}
