package com.babytt.dataaccess;

import java.util.ArrayList;
import java.util.List;

import com.babytt.model.Account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBOperation{
	
	private DBAccount database = null;
	public DBOperation(Context context){
		database = new DBAccount(context);
	}
	
	public boolean save(Account account){
		SQLiteDatabase db = database.getWritableDatabase();
		if(account != null){
			ContentValues value = new ContentValues();
			value.put("number", account.getNumber());
			value.put("name", account.getName());
			value.put("money", account.getMoney());
			value.put("remark", account.getRemark());
			db.insertOrThrow("account", null, value);
			db.close();
			return true;
		}
		else{
			return false;
		}
	}
	
	public List getByName(String queryName){
		if(queryName == null || queryName.equals("")){
			return getAll();
		}	
		List list = null;
		SQLiteDatabase db = database.getReadableDatabase();
		String sql = "select * from account where name like ? or phone like ?";
		String[] params = new String[]{"%"+queryName+"%", "%"+queryName+"%"};
		Cursor cursor = db.rawQuery(sql, params);
		
		list = new ArrayList();
		while(cursor.moveToNext()){
			Account account = new Account();
			account.setId(cursor.getInt(0));
			account.setNumber(cursor.getString(1));
			account.setName(cursor.getString(2));
			account.setMoney(cursor.getString(3));
			account.setRemark(cursor.getString(8));
			list.add(account);
		}
		cursor.close();
		db.close();
		return list;
	}
	
	public List getAll(){
		List list = null;
		SQLiteDatabase db = database.getReadableDatabase();
		String sql = "select * from account";
		Cursor cursor = db.rawQuery(sql, null);
		
		list = new ArrayList();
		while(cursor.moveToNext()){
			Account account = new Account();
			account.setId(cursor.getInt(0));
			account.setNumber(cursor.getString(1));
			account.setName(cursor.getString(2));
			account.setMoney(cursor.getString(3));
			account.setRemark(cursor.getString(8));
			list.add(account);
		}
		cursor.close();
		db.close();
		return list;
	}

	public Account getById(int id){
		Account account = null;
		if(id > 0){
			SQLiteDatabase db = database.getReadableDatabase();
			String sql = "select * from account where _id=?";
			String[] params = new String[] {String.valueOf(id)};
			Cursor cursor = db.rawQuery(sql, params);
			if(cursor.moveToNext()){
				account = new Account();
				account.setId(cursor.getInt(0));
				account.setNumber(cursor.getString(1));
				account.setName(cursor.getString(2));
				account.setMoney(cursor.getString(3));
				account.setRemark(cursor.getString(8));
			}
			cursor.close();
			db.close();
		}
		return account;
	}
	
	//����
	public boolean update(Account account){
		if(account != null){
			SQLiteDatabase db = database.getWritableDatabase();
//			String sql = "update account set number = ?, name = ?, " +
//			             "phone = ?, email = ?, address = ?, gender = ?, " +
//					     "relationship = ?, remark = ? where _id = ?";
//			Object[] params = new Object[]{account.getNumber(),account.getName(),account.getPhone(),
//					                       account.getEmail(),account.getAddress(),account.getGender(),
//					                       account.getRelationship(),account.getRemark(),account.getId()};
//			db.execSQL(sql, params);
			ContentValues value = new ContentValues();
			value.put("number", account.getNumber());
			value.put("name", account.getName());
			value.put("money", account.getMoney());
			value.put("remark", account.getRemark());
			db.update("account", value, "_id=?", new String[]{String.valueOf(account.getId())});
			db.close();
			return true;
		}
		else{
			return false;
		}
	}
	
	//ɾ��
	public void delete(int id){
		if(id > 0){
			SQLiteDatabase db = database.getWritableDatabase();
			String sql = "delete from account where _id = ?";
			Object[] params = new Object[]{String.valueOf(id)};
			db.execSQL(sql, params);
			db.close();
		}
	}
}


