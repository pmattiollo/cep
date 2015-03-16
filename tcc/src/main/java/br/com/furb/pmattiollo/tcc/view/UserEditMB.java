
package br.com.furb.pmattiollo.tcc.view;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.UserBC;
import br.com.furb.pmattiollo.tcc.domain.UserEntity;
import br.com.furb.pmattiollo.tcc.persistence.UserDAO;
import br.com.furb.pmattiollo.tcc.security.CatalogoAuthenticator;
import br.com.furb.pmattiollo.tcc.security.Identity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./login.jsf")
@NextView("./index.jsf")
public class UserEditMB extends AbstractEditPageBean<UserEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserBC userBC;
	
    @Inject
    private CatalogoAuthenticator catalogoAuthenticator;

    @Inject
    private MessageContext messageContext;
    
    @Inject
    private Identity identity;
    
    @Inject
    private UserDAO userDAO;

	
	@Override
	@Transactional
	public String delete() {
		this.userBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.userBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.userBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected UserEntity handleLoad(Long id) {
		return this.userBC.load(id);
	}
	
	public void login() {
        try {
            catalogoAuthenticator.login();
        } catch (Exception e) {
            messageContext.add(e.getMessage(), e.getMessage());
        }
    }

    public void logout() {
        try {
             catalogoAuthenticator.logout();
        } catch (Exception e) {
            messageContext.add(e.getMessage(), e.getMessage());
        }
    }

	public UserEntity getUserEntity() {
		if(this.identity.getId() != null && !"".equals(this.identity.getId())){
			try {
				return this.userDAO.findById(Long.valueOf(this.identity.getId()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new UserEntity();
	}
}