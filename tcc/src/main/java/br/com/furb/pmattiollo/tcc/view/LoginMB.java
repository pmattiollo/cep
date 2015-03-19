
package br.com.furb.pmattiollo.tcc.view;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.security.CatalogoAuthenticator;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;

@ViewController
@PreviousView("./login.jsf")
@NextView("./software_list.jsf")
public class LoginMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private CatalogoAuthenticator catalogoAuthenticator;

    @Inject
    private MessageContext messageContext;
	
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
    
}