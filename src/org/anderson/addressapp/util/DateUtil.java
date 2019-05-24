package org.anderson.addressapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Funções lições 
 * @author anderson
 *
 */
public class DateUtil {
	
	/**
	 * Padrão da tada
	 */
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	
	/**
	 * Formarter
	 */
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	/**
	 * retorna a data formatada
	 * @param date
	 * @return
	 */
	public static String format(LocalDate date) {
		if(date == null) {
			return null;
		}
		return DATE_FORMATTER.format(date);
	}
	
    /**
     * Checa se o String é uma data válida.
     * 
     * @param dateString A data como String
     * @return true se o String é uma data válida
     */
	public static boolean validDate(String dateString) {
		return DateUtil.parse(dateString) != null;
	}

    /**
     * Converte um String no formato definido {@link DateUtil#DATE_PATTERN} 
     * para um objeto {@link LocalDate}.
     * 
     * Retorna null se o String não puder se convertido.
     * 
     * @param dateString a data como String
     * @return o objeto data ou null se não puder ser convertido
     */
	public static LocalDate parse(String dateString) {
		try {
		return DATE_FORMATTER.parse(dateString, LocalDate::from);
		}catch (DateTimeParseException e) {
			return null;
		}
	}
}
