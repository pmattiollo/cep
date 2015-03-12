package br.com.furb.pmattiollo.tcc.security;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.security.User;

@Named
@SessionScoped
public class Identity implements User {

	private static final long serialVersionUID = 1L;
	private Map<Object, Object> attribute;

    public Identity() {
        attribute = new HashMap<Object, Object>();
    }

    public String getLoginUser() {
        return (String) (attribute.get("userLogin") == null ? "" : attribute.get("userLogin"));
    }

    public void setLoginUser(String usuario) {
        attribute.put("userLogin", usuario);
    }

    public String getPassword() {
         return (String) (attribute.get("password") == null ? "" : attribute.get("password"));
    }

    public void setPassword(String senha) {
         attribute.put("password", senha);
    }

    @Override
    public String getId() {
        return (String) (attribute.get("id") == null ? "" : attribute.get("id").toString());
    }

    @Override
    public Object getAttribute(Object key) {
        return attribute.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        attribute.put(key, value);
    }
}
