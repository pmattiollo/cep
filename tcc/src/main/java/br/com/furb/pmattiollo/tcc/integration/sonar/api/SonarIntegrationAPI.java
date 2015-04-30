package br.com.furb.pmattiollo.tcc.integration.sonar.api;


public interface SonarIntegrationAPI {
	
	public static final String QUALIFIER = "TRK";
	
	public static final String DOMAIN_TEST = "Tests";
	
	public static final String DOMAIN_SIZE = "Size";
	
	public static final String DOMAIN_DOCUMENTATION = "Documentation";
	
	public static final String PROCESS_TEST = "Tests";
	
	public static final String PROCESS_DEVELOPMENT = "Development";
	
	public static final String PROCESS_DOCUMENTATION = "Documentation";
	
	public void execute();
	
	public void insertProcessPhases();
	
	public void insertSoftwares();
	
	public void insertItems();
	
	public void insertCollects();
	
	public void loadSoftwares();
	
	public void loadItemsBySoftware(Integer projectId);
	
}
