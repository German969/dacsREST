package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, MedioDePago> mediodepago;
	public static volatile SingularAttribute<Pedido, Date> fecha;
	public static volatile SingularAttribute<Pedido, String> estado;
	public static volatile SingularAttribute<Pedido, Usuario> usuario;
	public static volatile SingularAttribute<Pedido, Integer> id;
	public static volatile ListAttribute<Pedido, Producto> productos;
	public static volatile SingularAttribute<Pedido, Date> fechaPago;

}

