package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import dao.DaoTelefones;
import dao.DaoUsuario;
import model.TelefoneUser;
import model.UsuarioPessoa;

@ManagedBean(name="telefoneManagedBean")
@ViewScoped
public class TelefoneManegedBean {

	private UsuarioPessoa user = new UsuarioPessoa();
	private DaoUsuario<UsuarioPessoa> daoUser = new DaoUsuario<UsuarioPessoa>();
	private DaoTelefones<TelefoneUser> daoTelefone = new DaoTelefones<TelefoneUser>();
	
	private TelefoneUser telefone = new TelefoneUser();
	
	@PostConstruct
	public void init() {
		
		String coduser = FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("codigouser");
		user = daoUser.pesquisar(Long.parseLong(coduser), UsuarioPessoa.class);
	}
	
	public String salvar() {
		try {
		telefone.setUsuarioPessoa(user);
		daoTelefone.salvar(telefone);
		telefone = new TelefoneUser();
		user = daoUser.pesquisar(user.getId(), UsuarioPessoa.class);
		FacesContext.getCurrentInstance().addMessage
		(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Salvo"));
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String atualizar() {
		daoTelefone.updateMerge(telefone);
		return "";
	}

	
	public String removeTelefone() {
		daoTelefone.deletarPorId(telefone);
		user = daoUser.pesquisar(user.getId(), UsuarioPessoa.class);
		telefone = new TelefoneUser();
		return "";
	}
	
	
	public void setUser(UsuarioPessoa user) {
		this.user = user;
	}
	
	public UsuarioPessoa getUser() {
		return user;
	}
	
		
	public TelefoneUser getTelefone() {
		return telefone;
	}
	
	public void setTelefone(TelefoneUser telefone) {
		this.telefone = telefone;
	}
	
	public DaoTelefones<TelefoneUser> getDaoTelefone() {
		return daoTelefone;
	}
	
	public void setDaoTelefone(DaoTelefones<TelefoneUser> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}
	
	
}
