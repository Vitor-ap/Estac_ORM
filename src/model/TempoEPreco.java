package model;

public class TempoEPreco {


	private double valor;
	private int tempoPermanencia;
	

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
