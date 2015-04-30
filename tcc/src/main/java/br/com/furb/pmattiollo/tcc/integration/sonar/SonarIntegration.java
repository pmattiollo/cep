package br.com.furb.pmattiollo.tcc.integration.sonar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.CollectBC;
import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.business.ProcessBC;
import br.com.furb.pmattiollo.tcc.business.SoftwareBC;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.ProcessEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.com.furb.pmattiollo.tcc.integration.connector.ConnectorFactory;
import br.com.furb.pmattiollo.tcc.integration.sonar.api.SonarIntegrationAPI;
import br.com.furb.pmattiollo.tcc.integration.sonar.beans.SonarSoftware;
import br.com.furb.pmattiollo.tcc.persistence.ProcessDAO;

public class SonarIntegration implements SonarIntegrationAPI {
	
	@Inject
	private ProcessBC processBc;
	
	@Inject
	private SoftwareBC softwareBc;
	
	@Inject
	private ItemBC itemBc;
	
	@Inject
	private CollectBC collectBc;
	
	private List<SonarSoftware> sonarSoftwares;

	private Connection conn;
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void insertProcessPhases() {
		ProcessDAO dao = new ProcessDAO();
		
		if(!dao.existsProcess(PROCESS_TEST)) {
			ProcessEntity process = new ProcessEntity();
			process.setDescription(PROCESS_TEST);
			processBc.insert(process);
		}
		
		if(!dao.existsProcess(PROCESS_DEVELOPMENT)) {
			ProcessEntity process = new ProcessEntity();
			process.setDescription(PROCESS_DEVELOPMENT);
			processBc.insert(process);
		}
		
		if(!dao.existsProcess(PROCESS_DOCUMENTATION)) {
			ProcessEntity process = new ProcessEntity();
			process.setDescription(PROCESS_DOCUMENTATION);
			processBc.insert(process);
		}
	}
	
	@Override
	public void insertSoftwares() {
		SoftwareEntity software = new SoftwareEntity();
	}
	
	@Override
	public void insertItems() {
		ItemEntity item = new ItemEntity();
		
	}
	
	@Override
	public void insertCollects() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void loadSoftwares() {
		loadConnection();
		
		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = conn.prepareStatement("SELECT id, name FROM projects WHERE root_id IS NULL AND qualifier = ?'");
			stmt.setString(1, QUALIFIER);
			
			rs = stmt.executeQuery();
			while (rs.next()) {				
				SonarSoftware software = new SonarSoftware();
				software.setId(rs.getInt("id"));
				software.setDescription(rs.getString("name"));

				sonarSoftwares.add(software);
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadItemsBySoftware(Integer projectId) {
		loadConnection();
		List<ItemEntity> itemList = new ArrayList<ItemEntity>();

		PreparedStatement stmt;
		ResultSet rs;
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT pm.value, m.name FROM project_measures pm ");
			sb.append("JOIN snapshots s ON s.id = pm.snapshot_id AND s.project_id = ? ");
			sb.append("JOIN metrics m ON m.id = pm.metric_id AND m.domain IN(?, ?, ?) AND m.description IS NOT NULL ");
			sb.append("WHERE pm.value IS NOT NULL");
			
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, projectId);
			stmt.setString(2, DOMAIN_TEST);
			stmt.setString(3, DOMAIN_SIZE);
			stmt.setString(4, DOMAIN_DOCUMENTATION);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				ItemEntity item = new ItemEntity();
				item.setDescription(rs.getString("name"));
				itemList.add(item);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadConnection()  {
		try {
			conn = ConnectorFactory.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
