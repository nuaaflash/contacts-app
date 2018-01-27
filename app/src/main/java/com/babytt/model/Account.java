package com.babytt.model;

public class Account {
	private int id;
	private String number=null;       //���
	private String name=null;         //����
	private String money=null;        //�ֻ���
	private String remark=null;       //��ע
	//���캯��
	public Account(){
		id=0;
		number="";
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
	//��ȡ��������ϵ�˱��
	public void setNumber(String number){
		this.number = number;
	}
	public String getNumber(){
		return number;
	}
	//��ȡ��������ϵ������
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
