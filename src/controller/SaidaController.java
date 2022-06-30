package controller;

import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

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



	public void pesquisar() throws SQLException {
		Estacionamento e = new Estacionamento();
			e.setOs(os.get());
			resultEstac = dao.selectOne(e);
			if (resultEstac == null ) {
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
		
		public void registraSaida() throws SQLException {
			TempoEPreco tmp = new TempoEPreco();
			int confirm = JOptionPane.showConfirmDialog(null, "Confirma o Registro?");
			if (confirm == JOptionPane.YES_OPTION) {
			resultEstac.setHrSaida(hrSaida.get());
			resultEstac.setMinSaida(minSaida.get());
			resultEstac.setPagamento(pagamento.get());

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
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 24)));
			JOptionPane.showMessageDialog(null, "\n\nVALOR A SER PAGO: \n\n\n\n" + tmp.getValor() + " REAIS.\n\n ");
			recebeValor(tmp.getValor());
			
			resultEstac.setValor(tmp.getValor());
			dao.update(resultEstac);
			limpaSaida();
			}
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
		
		
		public void recebeValor(double valor) {
			IPagamentoStrategy pg;
			
			if (pagamento.get().equals("Dinheiro")) {
				pg = new PagamentoDinheiro();
				pg.recebePagamento(valor);
			}
			if (pagamento.get().equals("Pix")) {
				pg = new PagamentoPix();
				pg.recebePagamento(valor);
			} if (pagamento.get().equals("Cartão")){
				pg = new PagamentoCartão();
				pg.recebePagamento(valor);
			}
		}
	}

