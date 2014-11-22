package br.edu.ifam.snaa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifam.snaa.business.ImagemController;
import br.edu.ifam.snaa.factory.ImagemControllerFactory;
import br.edu.ifam.snaa.util.Util;

/**
 * 
 * 
 * @author Ronei
 * 
 *         Classe responsável por carregar as imagens do banco de dados e
 *         renderizá-las nas views
 * 
 */

public class ImagemServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String strId = req.getParameter("id");
		if (Util.isNotNull(strId)) {
			Long id = Long.valueOf(strId);
			String context = req.getParameter("context");

			ImagemController imagemController = new ImagemControllerFactory()
					.create(context);

			if (imagemController.hasPermission()) {
				byte[] bytes = imagemController.loadImage(id);

				resp.getOutputStream().write(bytes);
			}

		}

	}

}
