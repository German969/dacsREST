package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Producto.class)
public abstract class Producto_ {

	public static volatile SingularAttribute<Producto, String> descripcion;
	public static volatile SingularAttribute<Producto, String> marca;
	public static volatile SingularAttribute<Producto, Float> precio;
	public static volatile SingularAttribute<Producto, Categoria> categoria;
	public static volatile SingularAttribute<Producto, Float> comision;
	public static volatile SingularAttribute<Producto, Proveedor> proveedor;
	public static volatile SingularAttribute<Producto, Integer> id;
	public static volatile ListAttribute<Producto, Pedido> pedidos;
	public static volatile SingularAttribute<Producto, Integer> stock;
	public static volatile SingularAttribute<Producto, String> nombre;

}

