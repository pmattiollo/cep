package br.com.furb.pmattiollo.tcc.integration.sonar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.com.furb.pmattiollo.tcc.integration.connector.ConnectorFactory;
import br.com.furb.pmattiollo.tcc.integration.sonar.api.SonarIntegrationAPI;

public class SonarIntegration implements SonarIntegrationAPI {

	private Connection conn;
	
	private void loadConnection()  {
		try {
			conn = ConnectorFactory.getInstance().getConexao();
		} catch (SQLException e) { }
	}
	
	@Override
	public List<SoftwareEntity> getAllSoftwares() {
		loadConnection();
		List<SoftwareEntity> softwareList = new ArrayList<SoftwareEntity>();
		
		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = conn.prepareStatement("select * from projects where root_id is null");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Integer projectId = rs.getInt("id");
				
				SoftwareEntity software = new SoftwareEntity();
				software.setDescription(rs.getString("name"));
				software.setItems(getItemsByProjectId(projectId));
				softwareList.add(software);
			}
			
		} catch (SQLException e) { }
		return softwareList;
	}
	
	@Override
	public List<ItemEntity> getItemsByProjectId(Integer projectId) {
		loadConnection();
		List<ItemEntity> itemList = new ArrayList<ItemEntity>();

		PreparedStatement stmt;
		ResultSet rs;
		
		try {
			stmt = conn.prepareStatement("select * from metrics");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				ItemEntity item = new ItemEntity();
				item.setDescription(rs.getString("name"));
				itemList.add(item);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return itemList;
	}

}
