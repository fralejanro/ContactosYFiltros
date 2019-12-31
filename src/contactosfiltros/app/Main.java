/**
 * Main.java
 */
package contactosfiltros.app;

import contactosfiltros.gui.MainWindow;

/**
 * <b>Descripción:<b> Clase que determina la clase principal
 * @author Francisco Alejandro Hoyos Rojas
 * @version 
 */
public class Main {

	/**
	 * Metodo encargado de inicializar la aplicación

	 * @author Francisco Alejandro Hoyos Rojas
	 * 
	 * @param args valores que recibe la aplicación
	 */
	public static void main(String[] args) {
		MainWindow app = new MainWindow();
		app.setVisible(true);
	}

}
