package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {
	@Test
	public void testeHibernate() {
		HibernateUtil.getEntityManager();
	}

	@Test
	public void salvar() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
		UsuarioPessoa usuarioPessoa2 = new UsuarioPessoa();

		usuarioPessoa.setNome("Álisson");
		usuarioPessoa.setSobrenome("Neres");
		usuarioPessoa.setIdade(27);
		usuarioPessoa.setLogin("Alisson7Neres");
		usuarioPessoa.setSenha("15975641");

		usuarioPessoa2.setNome("Álisson");
		usuarioPessoa2.setSobrenome("Neres");
		usuarioPessoa2.setIdade(26);
		usuarioPessoa2.setLogin("Alisson7Neres");
		usuarioPessoa2.setSenha("15975641");

		try {
			daoGeneric.salvar(usuarioPessoa);
			daoGeneric.salvar(usuarioPessoa2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(4L, UsuarioPessoa.class);

		System.out.println(pessoa);
	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(4L, UsuarioPessoa.class);

		pessoa.setNome("Alisson");
		// pessoa.setIdade(26);

		try {
			pessoa = daoGeneric.updateMerge(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(pessoa);
	}

	@Test
	public void testeDeletar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		UsuarioPessoa pessoa2 = daoGeneric.pesquisar(3L, UsuarioPessoa.class);

		try {
			daoGeneric.deletarPorId(pessoa);
			daoGeneric.deletarPorId(pessoa2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(pessoa);
	}

	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		try {

			for (UsuarioPessoa usuarioPessoa : list) {

				System.out.println(usuarioPessoa);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeQuetyList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("From UsuarioPessoa").setMaxResults(1)
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);

		}
	}

	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome = :nome")
				.setParameter("nome", "Álisson").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@Test
	public void testeQUerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u")
				.getSingleResult();
		System.out.println(somaIdade);
	}

	@Test
	public void testeNameQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@Test
	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "Álisson").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeGravaTelefone() {
		@SuppressWarnings("rawtypes")
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(4L, UsuarioPessoa.class);
		TelefoneUser telefoneUser = new TelefoneUser();
		
		telefoneUser.setTipo("Celula");
		telefoneUser.setNumero("91709633");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeConsultaTelefone() {
		@SuppressWarnings("rawtypes")
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(4L, UsuarioPessoa.class);
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("-----------------------------");
		}
	}
}
