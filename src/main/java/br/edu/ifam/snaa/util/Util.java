package br.edu.ifam.snaa.util;

import java.sql.Connection;
import java.sql.SQLException;

public final class Util {
	
	private Util() {
		
	}

	public static boolean isNull(String valor) {
		return (valor == null || valor.trim().isEmpty());
	}

	public static boolean isNotNull(String valor) {
		return !isNull(valor);
	}

	public static boolean isNull(Long valor) {
		return (valor == null || valor.intValue() == 0L);
	}

	public static boolean isNotNull(Long valor) {
		return !isNull(valor);
	}

	public static boolean isNull(Integer valor) {
		return (valor == null || valor.intValue() == 0L);
	}

	public static boolean isNotNull(Integer valor) {
		return !isNull(valor);
	}

	public static String formatarHora(String str) {

		return str.replaceAll("(\\d{2})(\\d{2})", "$1:$2");
	}

	public static Long parseLong(String valor) {

		try {
			return Long.valueOf(valor);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void execute(Runnable runnable) {
		Thread th = new Thread(runnable);
		
		th.start();
		
	}
}

