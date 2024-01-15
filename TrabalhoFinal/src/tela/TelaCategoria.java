package tela;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import banco.Gerenciador;
import model.Categoria;


import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

public class TelaCategoria {
	
	Categoria novaCategoria;
	private JFrame frame;
	private JTextField txtCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCategoria window = new TelaCategoria();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCategoria() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 357, 182);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digite a categoria:");
		lblNewLabel.setBounds(10, 11, 204, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(20, 36, 289, 20);
		frame.getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);
		
		JButton btnConfirma = new JButton("Confirma");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCategoria.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Obrigatório Categoria!");
				}
				else {
										
					novaCategoria = new Categoria(				
							txtCategoria.getText().trim().toLowerCase());
					
					Gerenciador g = new Gerenciador();
					
					if(!g.inserirCat(novaCategoria)) {
						JOptionPane.showMessageDialog(null,"Cadastro não efetuado");
					}
					
					TelaFilme tela = new TelaFilme();
					Rectangle bounds = frame.getBounds();
					tela.show(bounds.x, bounds.y);
					frame.dispose();
					
					
				}
			}
		});
		btnConfirma.setBounds(10, 87, 89, 23);
		frame.getContentPane().add(btnConfirma);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(236, 87, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaFilme tela = new TelaFilme();
				Rectangle bounds = frame.getBounds();
				tela.show(bounds.x, bounds.y);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnCancelar);
	}
	
	public void show(int x, int y) {
		Rectangle bounds = frame.getBounds();
		frame.setBounds(x,y,bounds.width,bounds.height);
		frame.setVisible(true);
	}

}
