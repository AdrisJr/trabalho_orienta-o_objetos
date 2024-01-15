package tela;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import banco.Gerenciador;

import model.Filme;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class Cadastro {
	Filme novoFilme;
	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtSinopse;
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private JTextField txtAutor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro window = new Cadastro();
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
	public Cadastro() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JComboBox<String> comboBox = new JComboBox<String>();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNomeFilme = new JLabel("Nome Filme:");
		lblNomeFilme.setBounds(10, 11, 67, 14);
		frame.getContentPane().add(lblNomeFilme);
		
		JLabel lblSinopse = new JLabel("Sinopse:");
		lblSinopse.setBounds(10, 67, 56, 14);
		frame.getContentPane().add(lblSinopse);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(20, 36, 202, 20);
		frame.getContentPane().add(txtNome);
		
		txtSinopse = new JTextField();
		txtSinopse.setColumns(10);
		txtSinopse.setBounds(20, 92, 393, 75);
		frame.getContentPane().add(txtSinopse);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Obrigatório Nome!");
				}
				else if(txtSinopse.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Obrigatório Sinopse!");}
				else if(txtAutor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Obrigatório Autor!");
				}
				
				else {
					Random random = new Random();
					int i = random.nextInt(100);
					novoFilme = new Filme(i,					
							txtNome.getText(),
							txtAutor.getText(),
							txtSinopse.getText(), comboBox.getSelectedItem().toString());
					
					Gerenciador g = new Gerenciador();
					
					if(!g.inserirFilme(novoFilme)) {
						JOptionPane.showMessageDialog(null,"Cadastro não efetuado");
					}
					
					TelaFilme tela = new TelaFilme();
					Rectangle bounds = frame.getBounds();
					tela.show(bounds.x, bounds.y);
					frame.dispose();
					
					
				}
			}
		});
		btnCadastrar.setBounds(20, 227, 89, 23);
		frame.getContentPane().add(btnCadastrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaFilme tela = new TelaFilme();
				Rectangle bounds = frame.getBounds();
				tela.show(bounds.x, bounds.y);
				frame.dispose();
			}
		});
		btnCancelar.setBounds(324, 227, 89, 23);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(250, 11, 67, 14);
		frame.getContentPane().add(lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		txtAutor.setBounds(260, 36, 153, 20);
		frame.getContentPane().add(txtAutor);
		
		JLabel lblNewLabel = new JLabel("Categoria:");
		lblNewLabel.setBounds(10, 193, 79, 14);
		frame.getContentPane().add(lblNewLabel);
		Gerenciador g = new Gerenciador();
		
		
		
		for(int i = 0; i < g.getCategoria().size(); i++)
			   comboBox.addItem(g.getCategoria().get(i).toString());
		
		comboBox.setBounds(70, 194, 143, 22);
		frame.getContentPane().add(comboBox);
	}
	
	public void show(int x, int y) {
		Rectangle bounds = frame.getBounds();
		frame.setBounds(x,y,bounds.width,bounds.height);
		frame.setVisible(true);
	}
}
