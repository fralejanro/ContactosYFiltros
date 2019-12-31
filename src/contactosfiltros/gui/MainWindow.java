/**
 * MainWindow.java
 */
package contactosfiltros.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import contactosfiltros.logic.Logic;
import static javax.swing.JOptionPane.ERROR_MESSAGE;


/**
 * <b>Descripción:<b> Clase que determina la interfaz principal de la aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 
 */
public class MainWindow extends JFrame {
	
	/**
	 * Atributo que representa el número de serialización
	 */
	private static final long serialVersionUID = 6793456738426989526L;
	
	/**
	 * Atributo que determina si un archivo fue cargado
	 */
	private boolean archivoCargado;
	/**
	 * Atributo que representa una instancia de la clase Logic que contiene
	 * todas las funcionalidades de la aplicación
	 */
	private Logic logic;
	
	 /**
     * Atributo que representa el botón Cargar Archivo
     */
    private JButton jBTCargar;
    
    /**
     * Atributo que representa el area donde se muestra el filtro
     */
    private JTextArea jTAFiltro;
    
    /**
     * Atributo que representa el scroll donde se muestra el filtro
     */
    private JScrollPane jSPFiltro;
    
    /**
     * Atributo que representa el botón Generar Filtro
     */
    private JButton jBTGenerarFiltro;
	
    /**
     * Atributo que representa el mensaje que indica donde ingresar la etiqueta
     */
    private JLabel jLBMensajeGrupo;
    
    /**
     * Atributo que representa el campo donde se ingresa la etiqueta 
     */
    private JTextField jTFEtiquetaGrupo;
    
    /**
     * Atributo que representa el botón GenerarCSV
     */
    private JButton jBTGenerarCSV;
	
    /**
     * 
     * Constructor de la clase.
     */
	public MainWindow() {
		logic = new Logic();
		archivoCargado = false;
		initComponents();
	}
	
	/**
	 * 
	 * Metodo encargado de iniciar los componentes de la interfaz
	 * @author Francisco Alejandro Hoyos Rojas
	 *
	 */
	private void initComponents() {
		setLayout(null);
		setSize(400,360);
		setResizable(false);
		setTitle("Generador");
		
		jBTCargar = new JButton("Cargar Archivo");
        jBTCargar.setBounds(100, 20, 200, 30);
        jBTCargar.addActionListener((ActionEvent evt) -> {
            jBTCargarActionPerformed(evt);
        });
        add(jBTCargar);
        
        jBTGenerarFiltro = new JButton("Generar Filtro");
        jBTGenerarFiltro.setBounds(100, 70, 200, 30);
        jBTGenerarFiltro.addActionListener((ActionEvent evt) -> {
            jBTGenerarFiltroActionPerformed(evt);
        });
        add(jBTGenerarFiltro);
        
        jTAFiltro = new JTextArea();
        jTAFiltro.setEditable(false);
        jTAFiltro.setColumns(20);
        jSPFiltro = new JScrollPane(jTAFiltro);
        jSPFiltro.setBounds(100, 110, 200, 70);
        add(jSPFiltro);
		
		jLBMensajeGrupo = new JLabel("Ingrese el nombre de la etiqueta del grupo");
		jLBMensajeGrupo.setBounds(80, 200, 250, 30);
		add(jLBMensajeGrupo);
		
		jTFEtiquetaGrupo = new JTextField();
		jTFEtiquetaGrupo.setBounds(100, 230,200,30);
		add(jTFEtiquetaGrupo);
		
		jBTGenerarCSV = new JButton("Generar CSV");
		jBTGenerarCSV.setBounds(100,270,200,30);
		jBTGenerarCSV.addActionListener((ActionEvent evt) -> {
			jBTGenerarCSVActionPerformed(evt);
		});
		add(jBTGenerarCSV);
	}
	
	/**
	 * 
	 * Metodo encargado de manejar el evento cuando se oprime el botón
	 * Generar CSV

	 * @author Francisco Alejandro Hoyos Rojas
	 * 
	 * @param evt evento generado por el usuario
	 */
	private void jBTGenerarCSVActionPerformed(ActionEvent evt) {
		if(archivoCargado) {
			String csv = logic.generateCSV(logic.getDatosEstudiantes(), jTFEtiquetaGrupo.getText());
			if(logic.saveAs(csv)) {
				JOptionPane.showMessageDialog(null, "El archivo csv fue creado con éxito");
			}
		}else {
	    	  JOptionPane.showMessageDialog(null, "No se ha cargado ningún archivo","Error archivo", ERROR_MESSAGE);
	      }
	}
	
	/**
	 * 
	 * Metodo encargado de manejar el evento cuando se oprime el botón
	 * Generar Filtro
	 * @author Francisco Alejandro Hoyos Rojas
	 * 
	 * @param evt evento generado por el usuario
	 */
	private void jBTGenerarFiltroActionPerformed(ActionEvent evt) {
	      if(archivoCargado) {
	    	  String filtro = logic.filter(logic.getDatosEstudiantes());
	    	  jTAFiltro.setText(filtro);
	    	  StringSelection selection = new StringSelection(filtro);
	    	  Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    	  clipboard.setContents(selection, selection);
	    	  JOptionPane.showMessageDialog(null, "El filtro fue creado con éxito");
	      }else {
	    	  JOptionPane.showMessageDialog(null, "No se ha cargado ningún archivo","Error archivo", ERROR_MESSAGE);
	      }
	    }
	
	/**
	 * 
	 * Metodo encargado de manejar el evento cuando se oprime el botón
	 * Cargar Archivo
	 
	 * @author Francisco Alejandro Hoyos Rojas
	 * 
	 * @param evt evento generado por el usuario
	 */
	 private void jBTCargarActionPerformed(ActionEvent evt) {
	      archivoCargado = logic.openFile();
	      if(archivoCargado) {
	    	  JOptionPane.showMessageDialog(null, "Archivo cargado con éxito");
	      }
	    }
}
