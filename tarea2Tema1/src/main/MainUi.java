
package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.MetodosCSV;

public class MainUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private File archivoSeleccionado;
	private JEditorPane editorPane;
	private JEditorPane editorPane_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainUi frame = new MainUi();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainUi() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 431);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TITULO
		JLabel lblNewLabel = new JLabel("Pasado de Ficheros de CSV a Binario y viceversa");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 10, 529, 29);
		contentPane.add(lblNewLabel);

		// PANEL IZQUIERDO
		editorPane = new JEditorPane();
		editorPane.setEditable(false);

		JScrollPane scroll1 = new JScrollPane(editorPane);
		scroll1.setBounds(10, 86, 216, 198);
		contentPane.add(scroll1);

		// PANEL DERECHO
		editorPane_1 = new JEditorPane();
		editorPane_1.setEditable(false);

		JScrollPane scroll2 = new JScrollPane(editorPane_1);
		scroll2.setBounds(417, 86, 232, 198);
		contentPane.add(scroll2);

		// ELEGIR ARCHIVO
		JButton btnNewButton = new JButton("Elegir Archivo:");
		btnNewButton.setBounds(20, 47, 169, 29);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(e -> {

			JFileChooser selector = new JFileChooser(new File("."));

			if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

				archivoSeleccionado = selector.getSelectedFile();
				

				try {
					if (archivoSeleccionado.getName().toLowerCase().endsWith(".csv")) {
						editorPane.setText(Files.readString(archivoSeleccionado.toPath()));
					} else {
						editorPane.setText("Binario seleccionado:\n" + archivoSeleccionado.getName());
					}

					editorPane_1.setText("");

				} catch (Exception ex) {
					mostrarError(ex);
				}
			}
		});

		// CSV A BINARIO
		JButton btnNewButton_1 = new JButton("CSV a Binario");
		btnNewButton_1.setBounds(236, 119, 171, 20);
		contentPane.add(btnNewButton_1);

		btnNewButton_1.addActionListener(e -> {

			if (!comprobarArchivo(".csv"))
				return;

			String nombre = obtenerNombreBase();

			MetodosCSV.fichero_CSV_To_Binario(nombre);

			editorPane_1.setText("Archivo generado:\n" + nombre + ".dat");
		});

		// CSV ORDENADO
		JButton btnNewButton_1_1 = new JButton("CSV ordenado");
		btnNewButton_1_1.setBounds(236, 149, 171, 20);
		contentPane.add(btnNewButton_1_1);

		btnNewButton_1_1.addActionListener(e -> {

			if (!comprobarArchivo(".csv"))
				return;

			MetodosCSV.ordenar_Archivo_CSV(archivoSeleccionado.getAbsolutePath());

			mostrarResultado(obtenerNombreBase() + "_ord.csv");
		});

		// BINARIO A CSV
		JButton btnNewButton_1_2 = new JButton("Binario a CSV");
		btnNewButton_1_2.setBounds(236, 179, 171, 20);
		contentPane.add(btnNewButton_1_2);

		btnNewButton_1_2.addActionListener(e -> {

			if (!comprobarArchivo(".dat"))
				return;

			String nombre = obtenerNombreBase();

			MetodosCSV.fichero_Binario_To_CSV(nombre);

			mostrarResultado(nombre + ".csv");
		});

		// BINARIO A CSV ORDENADO
		JButton btnNewButton_1_3 = new JButton("Binario a CSV Ordenado");
		btnNewButton_1_3.setBounds(236, 209, 171, 20);
		contentPane.add(btnNewButton_1_3);

		btnNewButton_1_3.addActionListener(e -> {

			if (!comprobarArchivo(".dat"))
				return;

			String nombre = obtenerNombreBase();

			MetodosCSV.fichero_Binario_To_CSV_Ordenado(nombre);

			mostrarResultado(nombre + "_ord.csv");
		});
	}

	// OBTENER RUTA SIN EXTENSION
	private String obtenerNombreBase() {

		String ruta = archivoSeleccionado.getAbsolutePath();

		return ruta.substring(0, ruta.lastIndexOf("."));
	}

	// COMPROBAR ARCHIVO
	private boolean comprobarArchivo(String extension) {

		if (archivoSeleccionado == null) {
			JOptionPane.showMessageDialog(this, "Selecciona primero un archivo");
			return false;
		}

		if (!archivoSeleccionado.getName().toLowerCase().endsWith(extension)) {

			JOptionPane.showMessageDialog(this, "Debes seleccionar un archivo " + extension);
			return false;
		}

		return true;
	}

	// MOSTRAR RESULTADO
	private void mostrarResultado(String ruta) {

		try {
			editorPane_1.setText(Files.readString(Path.of(ruta)));

		} catch (Exception ex) {
			mostrarError(ex);
		}
	}

	private void mostrarError(Exception ex) {
		JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
	}
}
