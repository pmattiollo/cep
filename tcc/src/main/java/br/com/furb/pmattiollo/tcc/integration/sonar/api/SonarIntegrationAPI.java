package br.com.furb.pmattiollo.tcc.integration.sonar.api;

import java.util.List;

import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;

public interface SonarIntegrationAPI {
	
	public List<SoftwareEntity> getAllSoftwares();
	
	public List<ItemEntity> getItemsByProjectId(Integer projectId);
	
}
