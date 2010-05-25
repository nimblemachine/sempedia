package web;

import java.sql.SQLException;

import dao.PredicateDao;

public class Random {
	
	public static void main(String[] args) throws SQLException{
		
		PredicateDao pdao = new PredicateDao();
		String pre = "nationality";
		boolean predExists = pdao.doesPredExist(pre);
		
		int preId=-99;
		if(!predExists){
			pdao.addPredicate(pre);
			System.out.println("lallala");
		}
		preId=pdao.getPredId(pre);
		
		System.out.println(preId);
	}
}
