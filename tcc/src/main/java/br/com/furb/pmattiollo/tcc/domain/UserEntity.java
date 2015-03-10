package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name="UserEntity.findById", query="SELECT obj FROM UserEntity obj WHERE obj.id = :id"),				
})
public class UserEntity extends User {

	private static final long serialVersionUID = 1L;
	
}
