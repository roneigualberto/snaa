package br.edu.ifam.snaa.business;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifam.snaa.domain.notificacao.SituacaoAcidenteEnum;
import br.edu.ifam.snaa.domain.notificacao.oficial.Acidente;
import br.edu.ifam.snaa.domain.notificacao.oficial.ProfissionalSaude;
import br.edu.ifam.snaa.persistence.AcidenteDAO;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class AcidenteBC extends DelegateCrud<Acidente, Long, AcidenteDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private VitimaBC vitimaBC;

	public List<Acidente> listPendentesPorProfissional(
			ProfissionalSaude profissionalSaude) {
		return getDelegate().buscarPor(SituacaoAcidenteEnum.PENDENTE,
				profissionalSaude);

	}

	@Override
	public Acidente insert(Acidente acidente) {
		acidente.reativar(acidente.getProfissionalSaude());
		return super.insert(acidente);
	}

	public void concluir(Acidente acidente) {
		try {
			Acidente acidenteConclusao = this.load(acidente.getId());

			acidenteConclusao.setQuadroSaida(acidente.getQuadroSaida());
			acidenteConclusao.getUltimoAtendimento().setQuadroSaida(
					acidente.getQuadroSaida());
			acidenteConclusao.getUltimoAtendimento().setComentario(
					acidente.getUltimoAtendimento().getComentario());
			acidenteConclusao.concluir();

			setarVitima(acidente);
			update(acidenteConclusao);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private void setarVitima(Acidente acidente) {
		Long idVitima = acidente.getVitima().getId();

		if (Util.isNotNull(idVitima)) {
			acidente.setVitima(this.vitimaBC.load(idVitima));
		}
	}

	public void reativar(Acidente acidente,ProfissionalSaude profissionalSaude) {
		Acidente newAcidente = this.load(acidente.getId());
		newAcidente.reativar(profissionalSaude);
		this.update(newAcidente);

	}
	
	public Acidente loadAcidente(Long id) {
		return getDelegate().loadAcidente(id);
	}

}
