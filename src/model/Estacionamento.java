package model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "estacionamento")
public class Estacionamento {

	@Id
	@Column(name = "os")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String os;
	
	
	@Column(name = "placa", length = 7)
	@NotNull
	private String placa;
	
	@Column(name = "modelo" , length = 20)
	@NotNull
	private String modelo;
	
	@Column(name = "cor", length = 10)
	@NotNull
	private String cor;
	
	@Column(name = "data")
	@NotNull
	private LocalDate data;
	
	@Column(name = "hr_entrada", length = 2)
	@NotNull
	private String hrEntrada;
	
	@Column(name = "min_entrada", length = 2)
	@NotNull
	private String minEntrada;

	@Column(name = "hr_saida", length = 2)
	private String hrSaida;
	
	@Column(name = "min_saida", length = 2)
	private String minSaida;
	
	
	@Column(name = "pagamento", length = 10)
	private String pagamento;

	
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getHrEntrada() {
		return hrEntrada;
	}

	public void setHrEntrada(String hrEntrada) {
		this.hrEntrada = hrEntrada;
	}

	public String getMinEntrada() {
		return minEntrada;
	}

	public void setMinEntrada(String minEntrada) {
		this.minEntrada = minEntrada;
	}

	public String getHrSaida() {
		return hrSaida;
	}

	public void setHrSaida(String hrSaida) {
		this.hrSaida = hrSaida;
	}

	public String getMinSaida() {
		return minSaida;
	}

	public void setMinSaida(String minSaida) {
		this.minSaida = minSaida;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	
}
