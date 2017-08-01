package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MedioDePago.class)
public abstract class MedioDePago_ {

	public static volatile SingularAttribute<MedioDePago, Integer> id;
	public static volatile ListAttribute<MedioDePago, Pedido> pedidos;
	public static volatile SingularAttribute<MedioDePago, String> nombre;

}

