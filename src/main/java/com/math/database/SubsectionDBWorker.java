package com.math.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;





import com.math.logic.Subsection;
import com.math.logic.Section;


public class SubsectionDBWorker {
	
	public Integer addSubsection(Subsection subsection, Section section) {
		Integer subsectionId = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "INSERT INTO subsections (title, section_id) VALUES (?, ?)";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, subsection.getTitle());
			statement.setInt(2, subsection.getSectionId());
			statement.setInt(3, section.getId());
			statement.executeUpdate();
			
			ResultSet gk = statement.getGeneratedKeys();
			if(gk.next()) {
				subsectionId = gk.getInt(1);// 1 - generated id
			}
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		
		return subsectionId;
	}
	
	public void updateSubsectionById(Subsection subsection, Integer id) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "UPDATE subsections SET title=? WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, subsection.getTitle());
			statement.setInt(2, id);
			statement.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();			
		}
	}
	
	
	public List<Subsection> getAll(Section section) {
		List<Subsection> subsections = new ArrayList<Subsection>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from subsections where section_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, section.getId());
			
			ResultSet result = statement.executeQuery();
			
			subsections = this.getSubsectionsList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return subsections;
	}
	
	public void deleteSubsectionById(Integer id) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "DELETE FROM subsections WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Subsection> getSubsectionsList(ResultSet result) {
		List<Subsection> subsections = new ArrayList<Subsection>();
		try {
			while (result.next()) {
				Integer id = result.getInt("id");
				String title = result.getString("title");
				Integer section_id = result.getInt("section_id");
				
				Subsection subsection = new Subsection(id, title, section_id);
				subsections.add(subsection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subsections;
	}
}
