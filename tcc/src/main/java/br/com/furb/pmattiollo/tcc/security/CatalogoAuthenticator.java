package br.com.furb.pmattiollo.tcc.security;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.constant.MeansEnum;
import br.com.furb.pmattiollo.tcc.constant.OperationsEnum;
import br.com.furb.pmattiollo.tcc.constant.UserEnum;
import br.com.furb.pmattiollo.tcc.domain.UserEntity;
import br.com.furb.pmattiollo.tcc.persistence.UserDAO;
import br.com.furb.pmattiollo.tcc.util.CriptografiaUtil;
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
        try {
        	UserEntity user = this.userDAO.findByLogin(identity.getLoginUser());

	        if (user == null) {
	            throw new AuthenticationException(rb.getString("login.falhou"));
	        } else {
	            if (!user.getPassword().equals(CriptografiaUtil.getCodigoMd5(identity.getPassword()))) {
	                throw new AuthenticationException(rb.getString("login.falhou"));
	            }
	        }
	
	        this.identity.setAttribute("id", user.getId());
	        this.identity.setAttribute("login", user.getLogin());
	        this.identity.setAttribute("name", user.getName());
	        this.identity.setAttribute("active", user.getActive());
	        this.identity.setAttribute("resources", defineResources(user.getPaper()));
	        this.identity.setAttribute("isLogged", true);
        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("login.usuario.nao.existe"), ex);
        }

    }

    private Map<MeansEnum, OperationsEnum> defineResources(UserEnum userEnum) {
    	Map<MeansEnum, OperationsEnum> mapRecursosOperacoes = new HashMap<MeansEnum, OperationsEnum>();
    	switch (userEnum) {
		case ADM:
			for (MeansEnum recurso : MeansEnum.values()) {
				for (OperationsEnum operacao : OperationsEnum.values()) {
					mapRecursosOperacoes.put(recurso, operacao);
				}
			}
			break;
		case AVALIADOR:
			for (OperationsEnum operacao : OperationsEnum.values()) {
				mapRecursosOperacoes.put(MeansEnum.ATTACH, operacao);
				mapRecursosOperacoes.put(MeansEnum.SOFTWARE, operacao);
			}
			break;
		default:
			break;
		}
    	
    	return mapRecursosOperacoes;
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
