package controller;

import javax.swing.JOptionPane;

public class PagamentoPix implements IPagamentoStrategy{

	private String chavePix = "CNPJ: 65.098.123/0001-00";
	
	@Override
	public void recebePagamento(double valor) {
		JOptionPane.showMessageDialog(null, "CHAVE PIX: " + chavePix);
	}

}
