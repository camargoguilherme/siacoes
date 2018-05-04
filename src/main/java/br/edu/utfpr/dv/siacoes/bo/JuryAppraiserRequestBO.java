package br.edu.utfpr.dv.siacoes.bo;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.utfpr.dv.siacoes.dao.JuryAppraiserRequestDAO;
import br.edu.utfpr.dv.siacoes.model.JuryAppraiserRequest;

public class JuryAppraiserRequestBO {

	public JuryAppraiserRequest findById(int id) throws Exception{
		try {
			JuryAppraiserRequestDAO dao = new JuryAppraiserRequestDAO();
			
			return dao.findById(id);
		} catch (SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			
			throw new Exception(e.getMessage());
		}
	}
	
	public JuryAppraiserRequest findByAppraiser(int idJury, int idUser) throws Exception{
		try {
			JuryAppraiserRequestDAO dao = new JuryAppraiserRequestDAO();
			
			return dao.findByAppraiser(idJury, idUser);
		} catch (SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			
			throw new Exception(e.getMessage());
		}
	}
	
	public List<JuryAppraiserRequest> listAppraisers(int idJuryRequest) throws Exception{
		try {
			JuryAppraiserRequestDAO dao = new JuryAppraiserRequestDAO();
			
			return dao.listAppraisers(idJuryRequest);
		} catch (SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			
			throw new Exception(e.getMessage());
		}
	}
	
	public int save(JuryAppraiserRequest appraiser) throws Exception{
		try {
			JuryAppraiserRequestDAO dao = new JuryAppraiserRequestDAO();
			
			return dao.save(appraiser);
		} catch (SQLException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean appraiserHasJuryRequest(int idJuryRequest, int idUser, Date date) throws Exception{
		Date startDate, endDate;
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -59);
		startDate = cal.getTime();
		
		cal.setTime(date);
		cal.add(Calendar.MINUTE, 59);
		endDate = cal.getTime();
		
		try{
			JuryAppraiserRequestDAO dao = new JuryAppraiserRequestDAO();
			
			return dao.appraiserHasJuryRequest(idJuryRequest, idUser, startDate, endDate);
		}catch(Exception e){
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			
			throw new Exception(e.getMessage());
		}
	}
	
}
