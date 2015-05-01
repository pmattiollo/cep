package br.com.furb.pmattiollo.tcc.integration.sonar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.CollectBC;
import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.business.ProcessBC;
import br.com.furb.pmattiollo.tcc.business.SoftwareBC;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.ProcessEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.com.furb.pmattiollo.tcc.integration.connector.ConnectorFactory;
import br.com.furb.pmattiollo.tcc.integration.sonar.api.SonarIntegrationAPI;
import br.com.furb.pmattiollo.tcc.integration.sonar.beans.SonarCollect;
import br.com.furb.pmattiollo.tcc.integration.sonar.beans.SonarItem;
import br.com.furb.pmattiollo.tcc.integration.sonar.beans.SonarSoftware;
import br.com.furb.pmattiollo.tcc.persistence.ItemDAO;
import br.com.furb.pmattiollo.tcc.persistence.ProcessDAO;
import br.com.furb.pmattiollo.tcc.persistence.SoftwareDAO;

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
	private List<SonarItem> sonarItems;
	private List<SonarCollect> sonarCollects;
	
	private List<ItemEntity> itemsInserted;

	private Connection conn;
	
	@Override
	public void execute() {
		insertProcessPhases();
		insertSoftwares();
		
		insertCollects();
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
		SoftwareDAO dao = new SoftwareDAO();
		loadSoftwares();
		for (SonarSoftware sonarSoftware : sonarSoftwares) {
			boolean isNew = false;
			
			SoftwareEntity software = dao.findByDescription(sonarSoftware.getDescription());
			if (software == null) {
				isNew = true;
				software = new SoftwareEntity();
				software.setDescription(sonarSoftware.getDescription());
			}
			
			loadItemsBySoftware(sonarSoftware.getId());
			insertItems();
			software.setItems(itemsInserted);
			
			if (isNew) {
				softwareBc.insert(software);
			} else {
				softwareBc.update(software);
			}
			loadCollects(sonarSoftware.getId());
		}
	}
	
	@Override
	public void insertItems() {
		ItemDAO itemDao = new ItemDAO();
		ProcessDAO processDao = new ProcessDAO();
		for (SonarItem sonarItem : sonarItems) {
			ItemEntity item = itemDao.findByDescription(sonarItem.getName());
			if (item == null) {
				item = new ItemEntity();
				item.setDescription(sonarItem.getName());
				item.setProcess(processDao.findByDescription(sonarItem.getName()));
				itemBc.insert(item);
			}
			itemsInserted.add(item);
		}
		
	}
	
	@Override
	public void insertCollects() {
		ItemDAO itemDao = new ItemDAO();
		SoftwareDAO softwareDao =  new SoftwareDAO();
		
		for (SonarCollect sonarCollect : sonarCollects) {
			CollectEntity collect = new CollectEntity();
			
			collect.setSoftware(softwareDao.findByDescription(sonarCollect.getSoftwareDesc()));
			collect.setItem(itemDao.findByDescription(sonarCollect.getItemDesc()));
			collect.setValue(sonarCollect.getValue());
			if (sonarCollect.getStartDate() != null) {
				collect.setStart_date(sonarCollect.getStartDate());
			} else {
				collect.setStart_date(new Date());
			}
			collectBc.insert(collect);
		}
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

		PreparedStatement stmt;
		ResultSet rs;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT m.name, m.domain FROM project_measures pm ");
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
				SonarItem item = new SonarItem();
				item.setName(rs.getString("name"));
				item.setDomain(rs.getString("domain"));
				sonarItems.add(item);	
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

	@Override
	public void loadCollects(Integer projectId) {
		loadConnection();

		PreparedStatement stmt;
		ResultSet rs;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT p.name as namesoftware, pm.value, m.name as nameitem FROM project_measures pm ");
			sb.append("JOIN snapshots s ON s.id = pm.snapshot_id AND s.project_id = ? ");
			sb.append("JOIN metrics m ON m.id = pm.metric_id AND m.domain IN(?, ?, ?) AND m.description IS NOT NULL ");
			sb.append("JOIN projects p ON s.project_id = p.id ");
			sb.append("WHERE pm.value IS NOT NULL");
			
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, projectId);
			stmt.setString(2, DOMAIN_TEST);
			stmt.setString(3, DOMAIN_SIZE);
			stmt.setString(4, DOMAIN_DOCUMENTATION);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				SonarCollect collect = new SonarCollect();
				collect.setSoftwareDesc(rs.getString("namesoftware"));
				collect.setItemDesc(rs.getString("nameitem"));
				collect.setValue(rs.getBigDecimal("value"));
				sonarCollects.add(collect);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
