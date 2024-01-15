package tela;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


import banco.Gerenciador;
import model.Filme;




public class TelaAlterar {

	private JFrame frame;
	private int registro;
	private JTextField txtNome;
	private JTextField txtSinopse;
	private JTextField txtAutor;
	private int FilmeID;
	JComboBox<String> comboBox = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAlterar window = new TelaAlterar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public int getRegistro() {
		return registro;
	}

	public void setRegistro(int registro) {
		FilmeID = registro;
		
		if(registro!=0) {
			Gerenciador g= new Gerenciador();
			Filme filme = g.getFilmebyID(registro);
			txtNome.setText(filme.getNomeFilme());
			txtSinopse.setText(filme.getSinopse());
			txtAutor.setText(filme.getAutor());
			
		}
	}

	/**
	 * Create the application.
	 */
	public TelaAlterar() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome Filme:");
		lblNewLabel.setBounds(10, 11, 123, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(20, 36, 202, 20);
		frame.getContentPane().add(txtNome);
		
		txtSinopse = new JTextField();
		txtSinopse.setColumns(10);
		txtSinopse.setBounds(20, 92, 393, 75);
		frame.getContentPane().add(txtSinopse);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		txtAutor.setBounds(260, 36, 153, 20);
		frame.getContentPane().add(txtAutor);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(250, 11, 67, 14);
		frame.getContentPane().add(lblAutor);
		
		//JComboBox<String> comboBox = new JComboBox<String>();
		Gerenciador g = new Gerenciador();
		
		
		
		for(int i = 0; i < g.getCategoria().size(); i++)
			   comboBox.addItem(g.getCategoria().get(i).toString());
		
		comboBox.setBounds(70, 194, 143, 22);
		frame.getContentPane().add(comboBox);
		
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(20, 227, 89, 23);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = FilmeID;
				Filme novoFilme = new Filme(id, txtNome.getText(), txtAutor.getText(),
						txtSinopse.getText(),comboBox.getSelectedItem().toString());
				
				g.remove(id);
				g.inserirFilme(novoFilme);
				
				TelaFilme tela = new TelaFilme();
				Rectangle bounds = frame.getBounds();
				tela.show(bounds.x, bounds.y);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnAtualizar);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria:");
		lblNewLabel_1.setBounds(10, 193, 79, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(324, 227, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaFilme tela = new TelaFilme();
				Rectangle bounds = frame.getBounds();
				tela.show(bounds.x, bounds.y);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnCancelar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(171, 227, 89, 23);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = FilmeID;
				g.remove(id);
				
				TelaFilme tela = new TelaFilme();
				Rectangle bounds = frame.getBounds();
				tela.show(bounds.x, bounds.y);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnExcluir);
	}
	
	public void show(int x, int y)
	{
		Rectangle bounds = frame.getBounds();
		frame.setBounds(x,y,bounds.width,bounds.height);
		frame.setVisible(true);
	}
}
