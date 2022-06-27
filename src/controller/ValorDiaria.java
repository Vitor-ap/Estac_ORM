package controller;

import model.TempoEPreco;

public class ValorDiaria  implements IValorCoR{

	@Override
	public double calculaValor(TempoEPreco e) {
		double valor = 10.00;
		if (e.getTempoPermanencia() > 120) {
			e.setValor(valor);
		}
		return e.getValor();
	}

	@Override
	public void proximaCondicao(TempoEPreco e) {
		e.setValor(calculaValor(e));
		
	}

}
