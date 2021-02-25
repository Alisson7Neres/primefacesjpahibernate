package dao;

import java.util.List;

import javax.persistence.Query;

import model.UsuarioPessoa;

public class DaoUsuario<E> extends DaoGeneric<UsuarioPessoa>{

	public void removerUsuario(UsuarioPessoa pessoa) throws Exception{
		getEntityManager().getTransaction().begin();
	
		getEntityManager().remove(pessoa);
		
		getEntityManager().getTransaction().commit();
		
	}

	public List<UsuarioPessoa> pesquisar(String pesquisarCampo) {
		Query query = super.getEntityManager().createQuery
				("from UsuarioPessoa where nome like '%"+ pesquisarCampo+"%' ");
		System.out.println(pesquisarCampo);
		return query.getResultList();
	}
}
