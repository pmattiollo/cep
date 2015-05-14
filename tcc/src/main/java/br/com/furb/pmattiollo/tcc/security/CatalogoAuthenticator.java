package br.com.furb.pmattiollo.tcc.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.constant.MeansEnum;
import br.com.furb.pmattiollo.tcc.constant.OperationsEnum;
import br.com.furb.pmattiollo.tcc.constant.UserEnum;
import br.com.furb.pmattiollo.tcc.domain.UserEntity;
import br.com.furb.pmattiollo.tcc.persistence.UserDAO;
import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.security.User;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class CatalogoAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
    private SecurityContext securityContext;

    @Inject
    private Identity identity;

    @Inject
    private UserDAO userDAO;

    @Inject
    private ResourceBundle rb;

    @Override
    public void authenticate() throws Exception {
    	UserEntity user = null;
    	
    	try {
        	user = this.userDAO.findByLogin(identity.getLoginUser());
        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("login.user.not.exists"), ex);
        }

    	if (user == null) {
    		throw new AuthenticationException(rb.getString("login.user.not.exists"));
    	} else {
    		if (!user.getPassword().equals(identity.getPassword())) {
    			throw new AuthenticationException(rb.getString("login.password.not.match"));
    		}
    	}
    	
    	this.identity.setAttribute("id", user.getId());
    	this.identity.setAttribute("login", user.getLogin());
    	this.identity.setAttribute("name", user.getName());
    	this.identity.setAttribute("administrator", user.getPaper().equals(UserEnum.ADM));
    	this.identity.setAttribute("resources", defineResources(user.getPaper()));
    	this.identity.setAttribute("isLogged", true);
    }

    private Map<MeansEnum, List<OperationsEnum>> defineResources(UserEnum userEnum) {
    	Map<MeansEnum, List<OperationsEnum>> mapMeansOperations = new HashMap<MeansEnum,  List<OperationsEnum>>();
    	switch (userEnum) {
		case ADM:
			for (MeansEnum mean : MeansEnum.values()) {
				mapMeansOperations.put(mean, Arrays.asList(OperationsEnum.values()));
			}
			break;
		case OPERATOR:
			for (MeansEnum mean : MeansEnum.values()) {
				switch (mean) {
				case PROCESS:
					mapMeansOperations.put(mean, Arrays.asList(OperationsEnum.LIST));
					break;
				case COLLECT:
					mapMeansOperations.put(mean, Arrays.asList(OperationsEnum.values()));
					break;
				case ITEM:					
					mapMeansOperations.put(mean, Arrays.asList(OperationsEnum.LIST, OperationsEnum.CLASSIFICATION));
					break;
				case SOFTWARE:
					mapMeansOperations.put(mean, Arrays.asList(OperationsEnum.LIST));
					break;
				default:
					break;
				}				
			}
			break;
		default:
			break;
		}
    	
    	return mapMeansOperations;
	}

	@Override
    public User getUser() {
        if (identity.getId() == null || identity.getId().isEmpty())
            return null;
        else
            return identity;
    }

    @Override
    public void unauthenticate() throws Exception {
        this.identity.setAttribute("id", null);
    }

    public boolean loggedIn() {
        return securityContext.isLoggedIn();
    }

    public void login() {
        securityContext.login();
    }

    public void logout() {
        securityContext.logout();
    }

}
