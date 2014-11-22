package br.edu.ifam.snaa.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.SintomaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.Sintoma;
import br.edu.ifam.snaa.domain.notificacao.oficial.TipoSintomaEnum;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@RequiredRole(Constantes.PERFIL_PROFISSIONAL_SAUDE)
public class SintomaListBean extends AbstractListPageBean<Sintoma, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SintomaBC sintomaBC;

	@Override
	protected List<Sintoma> handleResultList() {
		return this.sintomaBC.findAll();
	}

	public List<Sintoma> buscarSintomaSistemico(String nome) {

		return sintomaBC.buscarPor(nome, TipoSintomaEnum.SISTEMICO);

	}

	public List<Sintoma> buscarSintomaLocal(String nome) {

		return sintomaBC.buscarPor(nome, TipoSintomaEnum.LOCAL);

	}

}