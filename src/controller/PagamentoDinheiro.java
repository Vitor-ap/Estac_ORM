package controller;

import javax.swing.JOptionPane;

public class PagamentoDinheiro implements IPagamentoStrategy {

	

	
	@Override
	public void recebePagamento(double valor) {
	
		
		double recebido = Double.parseDouble(JOptionPane.showInputDialog
				("Valor a receber: " +valor+ " reais.\n\nDigite o valor recebido:"));
		double troco = recebido - valor;
		if (troco < 0 ) {
			troco = troco *(-1);
			JOptionPane.showMessageDialog(null, "Está faltando R$ "+troco);
		} else if (troco == 0) {
			JOptionPane.showMessageDialog(null, "Ok tudo certo! Não Há Troco");
		} else {
			JOptionPane.showMessageDialog(null, "Devolver " + troco + " de troco");
		}
		
	}

}
