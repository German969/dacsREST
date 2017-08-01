package abm;

import javax.persistence.metamodel.SingularAttribute;

import entities.Pedido;
import entities.Pedido_;

public class PedidoFilter {

	public PedidoFilter() {

	}

	private String cod;

	public String getCod() {

		return cod;

	}

	public void setCod(String cod) {

		this.cod = cod;

	}

	public SingularAttribute<Pedido, ? extends Object> getAttr() {

		if (cod.equals("1")) {

			return Pedido_.id;

		}

		if (cod.equals("2")) {

			return Pedido_.fecha;

		}

		if (cod.equals("3")) {

			return Pedido_.estado;

		}

		if (cod.equals("4")) {

			return Pedido_.usuario;

		}

		return null;

	}

}
