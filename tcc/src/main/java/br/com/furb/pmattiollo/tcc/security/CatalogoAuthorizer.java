package br.com.furb.pmattiollo.tcc.security;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.constant.MeansEnum;
import br.com.furb.pmattiollo.tcc.constant.OperationsEnum;
import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class CatalogoAuthorizer implements Authorizer {
	 
	private static final long serialVersionUID = 1L;

	@Inject
    private Identity identity;

    @Inject
    private ResourceBundle rb;

    @SuppressWarnings("unchecked")
	@Override
    public boolean hasRole(String resource) throws Exception {
        try {
        	MeansEnum mean = MeansEnum.getMeanByDescription(resource);
        	
        	return ((Map<MeansEnum, List<OperationsEnum>>) identity.getAttribute("resources")).containsKey(mean);
        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("control.acess.has.not.paper"), ex);
        }
    }

	@Override
	@SuppressWarnings("unchecked")
    public boolean hasPermission(String resource, String operation) throws Exception {
        try {
        	MeansEnum mean = MeansEnum.getMeanByDescription(resource);
        	Map<MeansEnum, List<OperationsEnum>> meansOperations = (Map<MeansEnum, List<OperationsEnum>>) identity.getAttribute("resources");

            List<OperationsEnum> operations = meansOperations.get(mean);

            for (OperationsEnum operationEnum : operations) {
                if (operationEnum.getDescription().equals(operation)) {
                    return true;
                }
            }

            return false;
        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("control.acess.has.not.permission"), ex);
        }
    }

}
