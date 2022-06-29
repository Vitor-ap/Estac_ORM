package controller;

import javax.swing.JOptionPane;

public class PagamentoCartão implements IPagamentoStrategy{

	@Override
	public void recebePagamento(double valor) {
	int op = JOptionPane.showConfirmDialog(null, "JÁ PASSOU O CARTÃO NA MÁQUINA? ");
	if (op == JOptionPane.YES_OPTION) {
		JOptionPane.showMessageDialog(null, "Registrado Com Sucesso!!!");
	}
			
		
	}

}
