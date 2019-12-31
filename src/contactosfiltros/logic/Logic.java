
package contactosfiltros.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
/**
 * <b>Descripción:<b> Clase que contiene toda la lógica de la aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class Logic {
	
	  /**
     * Atributo que representa el seleccionador de archivos
     */
    private JFileChooser fileChooser;

    /**
     * Atributo que representa los datos de los estudiantes
     */
    private ArrayList<String[]> datosEstudiantes;

	/**
	 * 
	 * Constructor de la clase.
	 */
	public Logic() {
		
	}
	
	 /**
     * Método utilizado para abrir un fichero externo
     *
     * @return true si se abre correctamente el archivo, false si no se abre
     */
    public boolean openFile() {
        boolean state = false;
        styleChooser();
        String extension = ".csv";
        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.CANCEL_OPTION && result != JFileChooser.ERROR_OPTION) {
            if (fileChooser != null) {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                if (!path.endsWith(extension)) {
                    JOptionPane.showMessageDialog(null, "El archivo seleccionado no tiene extensión .csv","Error extensión", ERROR_MESSAGE);
                } else {
                    readFile(path);
                    return true;
                }
            }
        }
        return state;
    }
    
    /**
     * Método utilizado para leer un fichero externo
     *
     * @param path ruta donde se encuentra el fichero
     */
    public void readFile(String path) {
        String line;
         datosEstudiantes = new ArrayList<>();
        try {
            BufferedReader read = new BufferedReader(new FileReader(path));
            line = read.readLine();
            while (line != null) {
            	System.out.println(line.split(";")[0]);
                datosEstudiantes.add(line.split(";"));
                line = read.readLine();
            }
           
          
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }
    
    /**
     * Método utilizado para guardar en una ruta especificada por el usuario
     *
     * @param inputText código a ser guardado
     * @return true si se guardo correctamente, false si no se guardo
     */
    public boolean saveAs(String inputText) {
        boolean state = false;
        styleChooser();
        String extension = ".csv";
        int result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.CANCEL_OPTION && result != JFileChooser.ERROR_OPTION) {
            // Se obtiene la ruta donde se va a guardar el archivo
            if (fileChooser != null) {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                if (path.endsWith(extension)) {
                    createFile(path, inputText);
                } else {
                    path = path + extension;
                    createFile(path, inputText);
                }
                return true;
            }

        }
        return state;
    }
    
    /**
     * Método utilizado para crear el archivo
     *
     * @param path ruta donde se va a crear el archivo
     * @param textInput 
     */
    public void createFile(String path, String textInput) {

        try (FileWriter file = new FileWriter(path, false)) {
            String[] text = textInput.split("\n");
            for (String x : text) {
                file.write(x + "\n");
            }
        } catch (IOException ex) {
        	JOptionPane.showMessageDialog(null, "Error al crear el archivo","Error archivo", ERROR_MESSAGE);
        }

    }

    /**
     * 
     * Metodo encargado de generar el texto para el archivo csv
     * @author Francisco Alejandro Hoyos Rojas
     * 
     * @param datosEstudiantes datos con los que se va a crear el texto para el archivo csv
     * @param etiqueta etiqueta en la cual se van a agrupar los estudiantes
     * @return texto para escribir el archivo csv
     */
    public String generateCSV(ArrayList<String[]> datosEstudiantes, String etiqueta) {
    	String csv = "Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi,Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix,Initials,Nickname,Short Name,Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation,Hobby,Sensitivity,Priority,Subject,Notes,Language,Photo,Group Membership,E-mail 1 - Type,E-mail 1 - Value\r\n" + 
    			"";
    	for(String datosEstudiante [] : datosEstudiantes) {
    		csv = csv + datosEstudiante[0] + ",,,,,,,,,,,,,,,,,,,,,,,,,,,," + etiqueta + " ::: * myContacts,* "+ etiqueta + "," + datosEstudiante[1] + "\n";
    	}
    	return csv;
    }
    
    /**
     * 
     * Metodo encargado de crear el filtro
     * @author Francisco Alejandro Hoyos Rojas
     * 
     * @param datosEstudiantes datos con los que se va a crear el filtro
     * @return filtro creado
     */
    public String filter(ArrayList<String[]> datosEstudiantes) {
    	String filtro = "";
    	for(String datosEstudiante [] : datosEstudiantes) {
    		filtro = filtro + "from:" + datosEstudiante[1] + " OR ";
    	}
    	return filtro.substring(0,filtro.length()-3);
    }
    
    /**
     * Método utilizado para que el estilo del seleccionador de archivo sea
     * igual al del sistema operativo utilizado por el usuario y no se utilice
     * el defecto por java
     */
    public void styleChooser() {
        fileChooser = null;
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            fileChooser = new JFileChooser();
            UIManager.setLookAndFeel(previousLF);
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {
        }

    }

	/**
	 * Metodo encargado de retornar el valor del atributo datosEstudiantes
	 * @return El datosEstudiantes asociado a la clase
	 */
	public ArrayList<String[]> getDatosEstudiantes() {
		return datosEstudiantes;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo datosEstudiantes
	 * @param datosEstudiantes El nuevo datosEstudiantes a modificar.
	 */
	public void setDatosEstudiantes(ArrayList<String[]> datosEstudiantes) {
		this.datosEstudiantes = datosEstudiantes;
	}
    
    
}
