package br.edu.ifam.snaa.business;

public interface ImagemController {
	
	byte[] loadImage(Long id);
	
	boolean hasPermission();

}
