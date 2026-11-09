package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainGrafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtAA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGrafico frame = new MainGrafico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGrafico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Peliculas");
		lblNewLabel.setBounds(29, 10, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(39, 33, 52, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(195, 33, 96, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("AA: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(402, 33, 61, 27);
		contentPane.add(lblNewLabel_3);
		
		txtId = new JTextField();
		txtId.setBounds(72, 33, 103, 27);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(289, 33, 103, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtAA = new JTextField();
		txtAA.setBounds(441, 33, 96, 26);
		contentPane.add(txtAA);
		txtAA.setColumns(10);
		
		JTextPane ContenidoTabla = new JTextPane();
		ContenidoTabla.setEditable(false);
		ContenidoTabla.setBounds(29, 70, 598, 233);
		contentPane.add(ContenidoTabla);
		
		JButton btnPrimero = new JButton("<<");
		btnPrimero.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrimero.setBounds(161, 313, 84, 64);
		contentPane.add(btnPrimero);
		
		JButton btnRetroceder = new JButton("<");
		btnRetroceder.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnRetroceder.setBounds(255, 313, 82, 64);
		contentPane.add(btnRetroceder);
		
		JButton btnAvanzar = new JButton(">");
		btnAvanzar.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnAvanzar.setBounds(347, 313, 84, 64);
		contentPane.add(btnAvanzar);
		
		JButton btnUltimo = new JButton(">>");
		btnUltimo.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnUltimo.setBounds(441, 313, 84, 64);
		contentPane.add(btnUltimo);
		
		JButton btnNewButton_4 = new JButton("Nuevo");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_4.setBounds(161, 387, 174, 64);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Guardar");
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_5.setBounds(347, 386, 178, 65);
		contentPane.add(btnNewButton_5);

	}
}
