package br.edu.ifam.snaa.util;


public class ReflectionUtil {
	
	private static String PREFIXO_GET = "get";
	private static String PREFIXO_SET = "set";
	private static String PREFIXO_IS = "is";
	
	
	public static <T> T getValueField(Object obj,String getter,Class<T> tipoRetorno) {
		
		try {
			 return tipoRetorno
					 .cast(obj.getClass()
					 .getMethod(PREFIXO_GET+capitalize(getter)).invoke(obj));
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	private static String capitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}
