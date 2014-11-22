package br.edu.ifam.snaa.util;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.edu.ifam.snaa.constant.Constantes;

public final class JasperUtil {

	public static void emitirRelatorio(String caminho, Connection con,
			Map<String, Object> parametros) {
		try {
			parametros.put("REPORT_LOCALE", Constantes.PT_BR);

			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext contexto = fc.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) contexto
					.getResponse();
			

			JasperPrint relatorioPronto = JasperFillManager.fillReport(caminho,
					parametros, con);

			contexto.responseReset();
			contexto.setResponseContentType("application/pdf");
			
			response.setContentType("application/pdf");

			OutputStream saida = response.getOutputStream();

			JasperExportManager.exportReportToPdfStream(relatorioPronto, saida);

			fc.responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
