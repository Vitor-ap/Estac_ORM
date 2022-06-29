package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Estacionamento;
import persistence.EstacionamentoPersistence;
import persistence.IEstacionamentoPersistence;
import util.HibernateUtil;



public class DeleteController {
	
	private String senha = "1234";
	private Estacionamento resultEstac;
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	private IEstacionamentoPersistence<Estacionamento> dao = new EstacionamentoPersistence(sf);
	
	
	private StringProperty os = new SimpleStringProperty();
	private StringProperty placa = new SimpleStringProperty();
	private StringProperty modelo = new SimpleStringProperty();
	private StringProperty cor = new SimpleStringProperty();
	private StringProperty data = new SimpleStringProperty();
	private StringProperty hrEntrada = new SimpleStringProperty();
	private StringProperty minEntrada = new SimpleStringProperty();


	
	public StringProperty osProperty() {
		return os;
	}

	public StringProperty placaProperty() {
		return placa;
	}
	
	public StringProperty modeloProperty() {
		return modelo;
	}
	public StringProperty corProperty() {
		return cor;
	}
	
	public StringProperty dataProperty() {
		return data;
	}
	
	public StringProperty hrEntradaProperty() {
		return hrEntrada;
	}
	
	public StringProperty minEntradaProperty() {
		return minEntrada;
	}



	public void pesquisar() throws SQLException {
		Estacionamento e = new Estacionamento();
			e.setOs(os.get()); 
			resultEstac = dao.selectOne(e);
			if (resultEstac ==null) {
				JOptionPane.showMessageDialog(null, "ERRO! NÃO FOI ENCONTRADO REGISTRO");
			}else {
			placa.set(resultEstac.getPlaca());
			modelo.set(resultEstac.getModelo());
			cor.set(resultEstac.getCor());
			data.set(String.valueOf(resultEstac.getData()));
			hrEntrada.set(resultEstac.getHrEntrada());
			minEntrada.set(resultEstac.getMinEntrada());
			}
	}

	public void deletar() throws SQLException {
		String vsenha = JOptionPane.showInputDialog("Digite a senha para poder deletar os cadastros");
		if (vsenha.equals(senha)) {
		Estacionamento e = new Estacionamento();
		e.setOs(os.get());
		dao.delete(e);
		JOptionPane.showMessageDialog(null, "DELETADO COM SUCESSO!");
		limpa();
		
	} else {
		JOptionPane.showMessageDialog(null, "SENHA INVÁLIDA" );
	}
}	
		
		public void limpa() {
			os.set("");
			placa.set("");
			modelo.set("");
			cor.set("");
			hrEntrada.set("");
			minEntrada.set("");
			data.set("");
		}
		
}
