package controller;

import model.TempoEPreco;

public class ValorUmaHora implements IValorCoR {

	@Override
	public double calculaValor(TempoEPreco e) {
		double valor = 5.00;
		if (e.getTempoPermanencia() > 30 && e.getTempoPermanencia() <= 60) {
			e.setValor(valor);
		}
		return e.getValor();
	}

	@Override
	public void proximaCondicao(TempoEPreco e) {
		e.setValor(calculaValor(e));
		ValorDuasHoras valorDuasHoras = new ValorDuasHoras();
		valorDuasHoras.proximaCondicao(e);
		
	}

}
