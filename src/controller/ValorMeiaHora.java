package controller;

import model.TempoEPreco;

public class ValorMeiaHora implements IValorCoR{

	@Override
	public double calculaValor(TempoEPreco e) {
		double valor = 3.00;
		if (e.getTempoPermanencia() <= 30) {
			e.setValor(valor);
		}
		return e.getValor();
	}

	@Override
	public void proximaCondicao(TempoEPreco e) {
		e.setValor(calculaValor(e));
		ValorUmaHora valorHora = new ValorUmaHora();
		valorHora.proximaCondicao(e);
		
	}

	
	
}
