package br.edu.ifam.snaa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DateUtil {

	private static SimpleDateFormat dateFormatYear = new SimpleDateFormat(
			"yyyy");

	

	public String getYear() {

		return dateFormatYear.format(getCurrentDate());
	}

	public Date getCurrentDate() {

		return new Date();
	}
	

}
