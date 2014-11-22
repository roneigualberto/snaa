package br.edu.ifam.snaa.view;

import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import br.edu.ifam.snaa.business.MunicipioBC;
import br.edu.ifam.snaa.business.RelatorioAcidenteBC;
import br.edu.ifam.snaa.domain.relatorio.RelatorioAcidente;
import br.edu.ifam.snaa.util.JasperUtil;
import br.edu.ifam.snaa.util.RelatorioEnum;
import br.edu.ifam.snaa.util.Util;
import br.edu.ifam.snaa.view.dto.AcidenteDTO;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
public class RelatorioAcidenteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private AcidenteDTO acidenteDTO;

	@Inject
	private RelatorioAcidenteBC relatorioAcidenteBC;
	
	@Inject
	private MunicipioBC municipioBC;

	private List<RelatorioAcidente> dados;

	private RelatorioEnum relatorioEnum;

	@Resource(mappedName = "java:jboss/datasources/snaa-ds")
	private DataSource dataSource;

	public void pesquisar() {

		this.dados = this.relatorioAcidenteBC.pesquisar(acidenteDTO);
	}

	public AcidenteDTO getAcidenteDTO() {
		return acidenteDTO;
	}

	public void setAcidenteDTO(AcidenteDTO acidenteDTO) {
		this.acidenteDTO = acidenteDTO;
	}

	public RelatorioAcidenteBC getRelatorioAcidenteBC() {
		return relatorioAcidenteBC;
	}

	public void setRelatorioAcidenteBC(RelatorioAcidenteBC relatorioAcidenteBC) {
		this.relatorioAcidenteBC = relatorioAcidenteBC;
	}

	public List<RelatorioAcidente> getDados() {
		return dados;
	}

	public void setDados(List<RelatorioAcidente> dados) {
		this.dados = dados;
	}

	public void imprimir() {

		Connection con = null;
		try {
			con = dataSource.getConnection();
			Map<String, Object> parametros = montarParametros();
			String caminho = montarCaminho();
			JasperUtil.emitirRelatorio(caminho, con, parametros);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.closeConnection(con);
		}

	}

	private String montarCaminho() {
		String path = relatorioEnum.getPath();

		String caminho = getRealPath("reports/" + path);
		return caminho;
	}

	private Map<String, Object> montarParametros() {
		Long idMunicipio = acidenteDTO.getMunicipio() == null ? null
				: acidenteDTO.getMunicipio().getId();
		String mnMunicipio = null;
		
		if (idMunicipio != null) {
			mnMunicipio = this.municipioBC.load(idMunicipio).getNome();
		}
		
		String tipoNotificacao = acidenteDTO.getTipoNotificacao() == null ? null
				: acidenteDTO.getTipoNotificacao().name();
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("idMunicipio",idMunicipio);
		parametros.put("nmMunicipio",mnMunicipio);
		parametros.put("dtInicio", acidenteDTO.getDataInicio());
		parametros.put("dtFim", acidenteDTO.getDataFim());
		parametros.put("tipoNotificacao", tipoNotificacao);
		return parametros;
	}

	public String getRealPath(String relativePath) {
		URL url = this.getClass().getClassLoader().getResource(relativePath);

		return url.getFile();
	}

	public RelatorioEnum[] getListRelatorio() {

		return RelatorioEnum.values();
	}

	public RelatorioEnum getRelatorioEnum() {
		return relatorioEnum;
	}

	public void setRelatorioEnum(RelatorioEnum relatorioEnum) {
		this.relatorioEnum = relatorioEnum;
	}

}
