package model;

public class TempoEPreco {

	private String hrEntrada;
	private String minEntrada;
	private String hrSaida;
	private String minSaida;
	private String pagamento;
	private double valor;
	private int tempoPermanencia;
	
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getTempoPermanencia() {
		return tempoPermanencia;
	}
	public void setTempoPermanencia(int tempoPermanencia) {
		this.tempoPermanencia = tempoPermanencia;
	}


	
	
	@Override
	public String toString() {
		return "" + valor + "";
	}
	
	
	
	
}
