package br.com.furb.pmattiollo.tcc.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.constant.MeansEnum;
import br.com.furb.pmattiollo.tcc.constant.OperationsEnum;
import br.com.furb.pmattiollo.tcc.constant.UserEnum;
import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class CatalogoAuthorizer implements Authorizer {
	 
	private static final long serialVersionUID = 1L;

	@Inject
    private Identity identity;

    @Inject
    private ResourceBundle rb;

    @Override
    public boolean hasRole(String role) throws Exception {
        try {
            return UserEnum.valueOf(role).equals(identity.getAttribute("role"));
        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("control.acess.has.not.paper"), ex);
        }
    }

	@Override
	@SuppressWarnings("unchecked")
    public boolean hasPermission(String resource, String operation) throws Exception {
        try {
        	Map<MeansEnum, OperationsEnum> meanOperations = (Map<MeansEnum, OperationsEnum>) identity.getAttribute("recursos_operacoes");

            List<String> operacoes = new ArrayList<String>();
            operacoes.add(operation);

            MeansEnum meanEnum = MeansEnum.valueOf(resource);
            OperationsEnum operationEnum = OperationsEnum.valueOf(operation);

            for (Map.Entry<MeansEnum, OperationsEnum> entry : meanOperations.entrySet()) {
                if (meanEnum.equals(entry.getKey()) && operationEnum.equals(entry.getValue())) {
                    return true;
                }
            }

            return false;
        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("control.acess.has.not.permission"), ex);
        }
    }

}
