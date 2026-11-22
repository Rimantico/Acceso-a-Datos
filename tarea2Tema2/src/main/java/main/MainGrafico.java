package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import metodos.MetodosBaseDatos;
import modelos.Pelicula;

public class MainGrafico extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtAA;
    private final DefaultListModel<Pelicula> listaPeliculas = new DefaultListModel<>();
    private final JList<Pelicula> MostrarRegistros = new JList<>(listaPeliculas);
    private JButton btnGuardarPelicula;
    private JButton btnNuevaPelicula;
    private JButton btnCargar;
    private boolean ocupado;
    private boolean nuevo;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainGrafico().setVisible(true));
    }

    public MainGrafico() {
        setTitle("Películas Sakila");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 747, 513);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        etiqueta("Películas", 29, 10, 150, 20, 14);
        etiqueta("ID:", 29, 33, 43, 27, 20);
        etiqueta("Nombre:", 180, 33, 100, 27, 20);
        etiqueta("AA:", 545, 33, 50, 27, 20);
        txtId = campo(72, 33, 95);
        txtId.setEditable(false);
        txtNombre = campo(275, 33, 255);
        txtAA = campo(595, 33, 95);
        txtAA.setToolTipText("Año de estreno; vacío significa NULL");

        JScrollPane scrollPane = new JScrollPane(MostrarRegistros);
        scrollPane.setBounds(29, 69, 661, 234);
        contentPane.add(scrollPane);
        MostrarRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        MostrarRegistros.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) mostrarSeleccion();
        });

        boton("<<", 161, 313, 84, 64).addActionListener(e -> seleccionar(0));
        boton("<", 255, 313, 82, 64).addActionListener(e ->
                seleccionar(MostrarRegistros.getSelectedIndex() - 1));
        boton(">", 347, 313, 84, 64).addActionListener(e ->
                seleccionar(MostrarRegistros.getSelectedIndex() + 1));
        boton(">>", 441, 313, 84, 64).addActionListener(e ->
                seleccionar(listaPeliculas.size() - 1));
        btnNuevaPelicula = boton("Nuevo", 161, 387, 174, 64);
        btnNuevaPelicula.addActionListener(e -> nuevaPelicula());
        btnGuardarPelicula = boton("Guardar", 347, 387, 178, 64);
        btnGuardarPelicula.addActionListener(e -> guardarPelicula());
        btnCargar = boton("Recargar", 540, 320, 150, 45);
        btnCargar.addActionListener(e -> cargarPeliculas());
        actualizarControles();
        cargarPeliculas();
    }

    private void etiqueta(String texto, int x, int y, int ancho, int alto, int tamano) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Tahoma", Font.BOLD, tamano));
        label.setBounds(x, y, ancho, alto);
        contentPane.add(label);
    }

    private JTextField campo(int x, int y, int ancho) {
        JTextField campo = new JTextField();
        campo.setBounds(x, y, ancho, 27);
        contentPane.add(campo);
        return campo;
    }

    private JButton boton(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Tahoma", Font.BOLD, 22));
        boton.setBounds(x, y, ancho, alto);
        contentPane.add(boton);
        return boton;
    }

    private void seleccionar(int posicion) {
        if (ocupado || posicion < 0 || posicion >= listaPeliculas.size()) return;
        MostrarRegistros.setSelectedIndex(posicion);
        MostrarRegistros.ensureIndexIsVisible(posicion);
    }

    private void mostrarSeleccion() {
        Pelicula pelicula = MostrarRegistros.getSelectedValue();
        nuevo = false;
        txtId.setText(pelicula == null ? "" : String.valueOf(pelicula.getId()));
        txtNombre.setText(pelicula == null ? "" : pelicula.getNombre());
        txtAA.setText(pelicula == null || pelicula.getAA() == null ? ""
                : String.format(java.util.Locale.ROOT, "%04d", pelicula.getAA()));
        actualizarControles();
    }

    private void nuevaPelicula() {
        MostrarRegistros.clearSelection();
        txtId.setText("");
        txtNombre.setText("");
        txtAA.setText("");
        nuevo = true;
        actualizarControles();
        txtNombre.requestFocusInWindow();
    }

    private void actualizarControles() {
        MostrarRegistros.setEnabled(!ocupado);
        txtNombre.setEditable(nuevo && !ocupado);
        txtAA.setEditable(nuevo && !ocupado);
        btnGuardarPelicula.setEnabled(nuevo && !ocupado);
        btnNuevaPelicula.setEnabled(!ocupado);
        btnCargar.setEnabled(!ocupado);
    }

    private void cargarPeliculas() {
        ocupado = true;
        actualizarControles();
        new SwingWorker<List<Pelicula>, Void>() {
            protected List<Pelicula> doInBackground() throws Exception {
                return MetodosBaseDatos.listaEntera();
            }
            protected void done() {
                try {
                    List<Pelicula> peliculas = get();
                    listaPeliculas.clear();
                    for (Pelicula pelicula : peliculas) listaPeliculas.addElement(pelicula);
                    if (!listaPeliculas.isEmpty()) MostrarRegistros.setSelectedIndex(0);
                    else mostrarSeleccion();
                } catch (Exception ex) {
                    mostrarError(ex);
                } finally {
                    ocupado = false;
                    actualizarControles();
                }
            }
        }.execute();
    }

    private void guardarPelicula() {
        if (!nuevo || ocupado) return;
        final String nombre = txtNombre.getText().trim();
        final Integer aa;
        try {
            if (nombre.isEmpty() || nombre.length() > 255) {
                throw new IllegalArgumentException("El nombre debe tener entre 1 y 255 caracteres.");
            }
            aa = MetodosBaseDatos.validarAA(txtAA.getText());
        } catch (IllegalArgumentException ex) {
            mostrarError(ex);
            return;
        }
        ocupado = true;
        actualizarControles();
        new SwingWorker<Pelicula, Void>() {
            protected Pelicula doInBackground() throws Exception {
                return MetodosBaseDatos.guardarPelicula(nombre, aa);
            }
            protected void done() {
                try {
                    Pelicula guardada = get();
                    listaPeliculas.addElement(guardada);
                    MostrarRegistros.setSelectedIndex(listaPeliculas.size() - 1);
                    MostrarRegistros.ensureIndexIsVisible(listaPeliculas.size() - 1);
                    JOptionPane.showMessageDialog(MainGrafico.this, "Película guardada.");
                } catch (Exception ex) {
                    mostrarError(ex);
                } finally {
                    ocupado = false;
                    actualizarControles();
                }
            }
        }.execute();
    }

    private void mostrarError(Exception ex) {
        Throwable causa = ex instanceof ExecutionException ? ex.getCause() : ex;
        JOptionPane.showMessageDialog(this, causa.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
