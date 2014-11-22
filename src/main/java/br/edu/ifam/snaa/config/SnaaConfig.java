package br.edu.ifam.snaa.config;

import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource="snaa")
public class SnaaConfig {

		private String pais;
		private String estado;
		
		
		public String getPais() {
			return pais;
		}
		public void setPais(String pais) {
			this.pais = pais;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
		
		
}


