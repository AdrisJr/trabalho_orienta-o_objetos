package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import banco.Gerenciador;
import model.Filme;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

public class TelaFilme {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFilme window = new TelaFilme();
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
	public TelaFilme() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Gerenciador g = new Gerenciador();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCadastro = new JButton("Cadastrar Filme");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cadastro tela = new Cadastro();
				Rectangle bounds = frame.getBounds();
				tela.show(bounds.x, bounds.y);
				frame.dispose();
			}
		});
		btnCadastro.setBounds(10, 11, 164, 23);
		frame.getContentPane().add(btnCadastro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 664, 184);
		frame.getContentPane().add(scrollPane);
		
		table = createTable(g);
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 67, 414, 183);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Filmes já Cadastrados");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(298, 43, 113, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCadastrarCategoria = new JButton("Cadastrar Categoria");
		btnCadastrarCategoria.setBounds(510, 11, 164, 23);
		btnCadastrarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCategoria tela = new TelaCategoria();
				Rectangle bounds = frame.getBounds();
				tela.show(bounds.x, bounds.y);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnCadastrarCategoria);
		
		
	}

	public void show(int x, int y) {
		Rectangle bounds = frame.getBounds();
		frame.setBounds(x,y,bounds.width,bounds.height);
		frame.setVisible(true);
	}
	
	private JTable createTable(Gerenciador g)
	{
		JTable table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int linha = table.getSelectedRow();
				int id = (int)table.getModel().getValueAt(linha, 0);
				
				TelaAlterar tela = new TelaAlterar();
				Rectangle bounds = frame.getBounds();
				tela.show(bounds.x,bounds.y);
				tela.setRegistro(id);
				frame.dispose();
			
			}
		});
		
		DefaultTableModel m = (DefaultTableModel)table.getModel();
		
		m.addColumn("ID");
		m.addColumn("Título");
		m.addColumn("Autor");
		m.addColumn("Sinopse");
		m.addColumn("Gênero");
		
		for(Filme a : g.getFilmes())
		{
			m.addRow(a.toObject());
		}
		
		return table;
		
	}
	
	
}
