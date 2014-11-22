package br.edu.ifam.snaa.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.ProfissionalSaudeBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.notificacao.oficial.ProfissionalSaude;
import br.edu.ifam.snaa.security.UsuarioSessao;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("/pages/unidadesaude/profissionalForm.jsf")
@PreviousView("/pages/unidadesaude/profissionalList.jsf")
@RequiredRole(Constantes.PERFIL_REPRESENTANTE_UNIDADE_DE_SAUDE)
public class ProfissionalSaudeListBean extends
		AbstractListPageBean<ProfissionalSaude, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfissionalSaudeBC profissionalSaudeBC;

	private List<ProfissionalSaude> profissionais;

	@Inject
	private UsuarioSessao usuarioSessao;

	@Inject
	private ProfissionalSaude profissional;

	private ProfissionalSaude profissionalAtivacao;

	@Inject
	private MessageContext messageContext;

	@PostConstruct
	public void init() {
		pesquisar();
	}

	@Override
	protected List<ProfissionalSaude> handleResultList() {
		return profissionais;
	}

	public void pesquisar() {

		this.clear();
		this.profissional.setUnidadeSaude(usuarioSessao.getUnidadeSaude());

		this.profissionais = this.profissionalSaudeBC.pesquisar(profissional);

	}

	@Transactional
	public String reativar() {
		
		this.profissionalSaudeBC.reativar(profissionalAtivacao);

		messageContext.add("Registro reativado com sucesso", SeverityType.INFO);

		return getPreviousView();

	}

	@Transactional
	public String inativar() {
		this.profissionalSaudeBC.inativar(profissionalAtivacao);
		messageContext
				.add("Registro inativado com sucesso", SeverityType.INFO);

		return getPreviousView();

	}

	public ProfissionalSaude getProfissionalSaude() {
		return profissional;
	}

	public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
		this.profissional = profissionalSaude;
	}

	public ProfissionalSaude getProfissional() {
		return profissional;
	}

	public void setProfissional(ProfissionalSaude profisional) {
		this.profissional = profisional;
	}

	public ProfissionalSaude getProfissionalAtivacao() {
		return profissionalAtivacao;
	}

	public void setProfissionalAtivacao(ProfissionalSaude profissionalAtivacao) {
		this.profissionalAtivacao = profissionalAtivacao;
	}
	
	
	

}