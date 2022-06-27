package controller;

import model.TempoEPreco;

public class ValorDuasHoras implements IValorCoR {

	@Override
	public double calculaValor(TempoEPreco e) {
		double valor = 6.00;
		if (e.getTempoPermanencia() > 60 && e.getTempoPermanencia() <= 120) {
			e.setValor(valor);
		}
		return e.getValor();
	}

	@Override
	public void proximaCondicao(TempoEPreco e) {
		e.setValor(calculaValor(e));
		ValorDiaria valorDiaria = new ValorDiaria();
		valorDiaria.proximaCondicao(e);
		
	}

}
