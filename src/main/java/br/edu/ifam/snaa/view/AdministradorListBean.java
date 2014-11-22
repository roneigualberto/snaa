package br.edu.ifam.snaa.view;

import static br.edu.ifam.snaa.constant.Constantes.PERFIL_ADMINISTRADOR;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.AdministradorBC;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.administrativo.Administrador;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("/pages/administrativo/cadastros/administrador/administradorForm.jsf")
@PreviousView("/pages/administrativo/cadastros/administrador/administrador/administradorList.jsf")
@RequiredRole(value = PERFIL_ADMINISTRADOR)
public class AdministradorListBean extends
		AbstractListPageBean<Administrador, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AdministradorBC administradorBC;

	private List<Administrador> administradores;

	@Inject
	private Administrador administrador;

	private Administrador administradorAtivacao;

	@Inject
	private MessageContext messageContext;

	@PostConstruct
	public void init() {
		pesquisar();
	}

	@Override
	protected List<Administrador> handleResultList() {
		return administradores;
	}

	public void pesquisar() {

		this.clear();

		this.administradores = this.administradorBC.pesquisar(administrador);

	}

	@Transactional
	public String reativar() {
		this.administradorBC.reativar(administradorAtivacao);
		messageContext.add("Registro reativado com sucesso", SeverityType.INFO);
		return getPreviousView();
	}

	@Transactional
	public String inativar() {
		
		administradorBC.inativar(administradorAtivacao);
		messageContext.add("Registro inativado com sucesso", SeverityType.INFO);

		return getPreviousView();

	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Administrador getAdministradorAtivacao() {
		return administradorAtivacao;
	}

	public void setAdministradorAtivacao(Administrador administradorAtivacao) {
		this.administradorAtivacao = administradorAtivacao;
	}

}