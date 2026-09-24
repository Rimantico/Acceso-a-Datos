package utils;

public class Biblioteca_dni {
	public static void main(String[] args) {

		System.out.println(crea_dni_sin_letra());

		System.out.println(calcula_letra("12345678"));
		imprime_dni("12345678A", true);
		boolean b = dni_es_correcto("12345678A");
		if (b)
			System.out.println("True");
		else
			System.out.println("False");
	}

	/*
	 * Crea un dni aleatorio sin letra
	 * 
	 * @return un dni sin letra
	 */
	public static String crea_dni_sin_letra() {
		String dni = "";
		String numero_texto;
		int numero;

		for (int i = 0; i < 8; i++) {
			numero = (int) (Math.random() * 10);
			numero_texto = Integer.toString(numero);
			dni = dni + numero_texto;
		}

		return dni;
	}

	/*
	 * Imprime un dni en dos formatos distintos
	 * 
	 * @param dni de entrada
	 * 
	 * @param si es verdadero lo imprime con formato 12.345.678-A. En otro caso
	 * 12345678A
	 */
	public static void imprime_dni(String dni, boolean con_formato) {
		if (con_formato) {
			System.out.println(dni.substring(0, 2) + "." + dni.substring(2, 5) + "." + dni.substring(5, 8) + "-"
					+ dni.substring(8));
		} else {
			System.out.println(dni);
		}
	}

	/*
	 * Calcula la letra de un dni sin letra
	 * 
	 * @param dni de entrada, sin letra
	 * 
	 * @return la letra correcta para el dni de entrada
	 */
	public static String calcula_letra(String dni) {
		String[] letras = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V",
				"H", "L", "C", "K", "E" };

		int dni_numerico = Integer.parseInt(dni);
		int numero = dni_numerico % 23;

		return letras[numero];
	}

	/*
	 * Crea un dni aleatorio <b>con letra<b>
	 * 
	 * @return un dni con letra
	 */
	public static String crea_dni_con_letra() {
		String dni_sin_letra = crea_dni_sin_letra();
		String letra = calcula_letra(dni_sin_letra);

		return dni_sin_letra + letra;
	}

	/*
	 * Indica si un dni con letra es correcto.
	 * 
	 * @return Verdadero si el dni es correcto. Si la letra es correcta.
	 */
	public static boolean dni_es_correcto(String dni) {
		String letra = dni.substring(8);
		String letraOK = calcula_letra(dni.substring(0, 8));

		if (letra.equals(letraOK)) {
			return true;
		} else {
			return false;
		}
	}
}
