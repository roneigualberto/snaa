package br.edu.ifam.snaa.business;

import javax.persistence.NoResultException;

import br.edu.ifam.snaa.domain.notificacao.oficial.RepresentanteUnidadeSaude;
import br.edu.ifam.snaa.domain.seguranca.Perfil;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.persistence.RepresentanteUnidadeSaudeDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class RepresentanteUnidadeSaudeBC
		extends
		DelegateCrud<RepresentanteUnidadeSaude, Long, RepresentanteUnidadeSaudeDAO> {

	private static final long serialVersionUID = 1L;

	public RepresentanteUnidadeSaude buscarAtivo(Usuario usuario) {
		try {
			return getDelegate().buscarAtivo(usuario);

		} catch (NoResultException e) {
			
			return null;
		}

	}
	
	@Override
	public RepresentanteUnidadeSaude insert(RepresentanteUnidadeSaude representante) {
		
		representante.getUsuario().addPerfil(Perfil.REPRESENTANTE_UNIDADE_SAUDE);
		return super.insert(representante);
	}
	
	public RepresentanteUnidadeSaude buscarPorCPF(String cpf) {
		
		return getDelegate().buscaPorCpf(cpf);
	}

}
