package com.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.AccountFlow.Account;
import com.BankCard.Card;
import com.annotation.Server;
import com.base.DataBase;
import com.fenye.Fenye;
import com.inter.AccountDAO;
import com.inter.CardDAO;

@Component	//这里加注解是为了方便Control中的变量调用
public class CardService {
	
	public Fenye List(int number,int currentPage) {
		SqlSession session = DataBase.open(true);
		try {
		CardDAO card = session.getMapper(CardDAO.class);
		int totalNumber=card.totalNumber(number);
		Fenye fenye=new Fenye(totalNumber, currentPage);
		 ArrayList<Card>list=card.List(number, fenye.getcurrentNumber(), fenye.move);
		 fenye.setObject(list);//将获取的记录保存
		 return fenye;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		session.rollback();
	}
	session.close();
	return null;
	}
	
	public Fenye list(String username,int currentPage) {
		SqlSession session = DataBase.open(true);
		try {
		CardDAO card = session.getMapper(CardDAO.class);
		int totalNumber=card.total(username);
		System.out.println(totalNumber);
		Fenye fenye=new Fenye(totalNumber, currentPage);
		 ArrayList<Card>list=card.list(username, fenye.getcurrentNumber(), fenye.move);
		 fenye.setObject(list);//将获取的记录保存
		 return fenye;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		session.rollback();
	}
	session.close();
	return null;
	}

}
