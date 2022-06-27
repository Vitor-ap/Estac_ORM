package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Estacionamento;
import model.TempoEPreco;
import persistence.EstacionamentoPersistence;
import persistence.IEstacionamentoPersistence;
import util.HibernateUtil;

public class SaidaController {

	
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	private IEstacionamentoPersistence<Estacionamento> dao = new EstacionamentoPersistence(sf);
	private Estacionamento resultEstac;
	
	
	private StringProperty os = new SimpleStringProperty();
	private StringProperty placa = new SimpleStringProperty();
	private StringProperty modelo = new SimpleStringProperty();
	private StringProperty cor = new SimpleStringProperty();
	private StringProperty data = new SimpleStringProperty();
	private StringProperty hrEntrada = new SimpleStringProperty();
	private StringProperty minEntrada = new SimpleStringProperty();
	private StringProperty hrSaida = new SimpleStringProperty();
	private StringProperty minSaida = new SimpleStringProperty();
	private StringProperty pagamento = new SimpleStringProperty();
	private double valor;
	
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
	public StringProperty hrSaidaProperty() {
		return hrSaida;
	}
	public StringProperty minSaidaProperty() {
		return minSaida;
	}
	public StringProperty pagamentoProperty() {
		return pagamento;
	}
	
	public double valorProperty() {
		return valor;
	}
	

	public void pesquisar() throws SQLException {
		Estacionamento e = new Estacionamento();
		Estacionamento e2 = new Estacionamento();
			e.setOs(os.get());
			e2 = dao.selectOne(e);
			placa.set(e2.getPlaca());
			modelo.set(e2.getModelo());
			cor.set(e2.getCor());
			data.set(String.valueOf(e2.getData()));
			hrEntrada.set(e2.getHrEntrada());
			minEntrada.set(e2.getMinEntrada());
			resultEstac = e2;
	}
		
		public void atualizar() throws SQLException {
			TempoEPreco tmp = new TempoEPreco();
			int confirm = JOptionPane.showConfirmDialog(null, "Confirma o Registro?");
			if (confirm == JOptionPane.YES_OPTION) {
			resultEstac.setHrSaida(hrSaida.get());
			resultEstac.setMinSaida(minSaida.get());
			resultEstac.setPagamento(pagamento.get());
			dao.update(resultEstac);
			int he = Integer.parseInt(hrEntrada.get());
			int me = Integer.parseInt(minEntrada.get());
			int hs = Integer.parseInt(hrSaida.get());
			int ms = Integer.parseInt(minSaida.get());
			
			if (he > hs ) {
				hs = hs + 24;
			}
			if ( me > ms ) {
				ms = ms + 60;
				hs--;
			}
			
			int tempoH = hs- he;
			int minH = ms - me;
			
			int horaToMin = tempoH * 60;
			int tempoTotal = horaToMin + minH;
			tmp.setTempoPermanencia(tempoTotal);
			
			ValorMeiaHora valorM = new ValorMeiaHora();
			valorM.proximaCondicao(tmp);
			
			JOptionPane.showMessageDialog(null, "valor a ser Pago  " + tmp.getValor());
			limpaSaida();
			}
		}
		
		public void calculaValor() {
			
		}
		
		public void limpaSaida() {
			os.set("");
			placa.set("");
			modelo.set("");
			cor.set("");
			hrEntrada.set("");
			hrSaida.set("");
			minEntrada.set("");
			minSaida.set("");
			pagamento.set("");
			data.set("");
		}
		
		public void calcTempoPermanencia() {
		}
		
	}

