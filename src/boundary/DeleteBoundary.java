package boundary;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.DeleteController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DeleteBoundary extends Application{
	private Stage stage;
	private String imagem = 		"waptrash.jpg";
	DeleteController control = new DeleteController();
	
	Estilos estilos = new Estilos();
	
	TextField os = new TextField();
	private Text placa = new Text();
	private Text modelo = new Text();
	private Text cor = new Text();
	private Text data = new Text();
	private Text hrEntrada = new Text();
	private Text minEntrada = new Text();
	
	Button btnApagar = new Button("Deletar");
	Button btnPesq = new Button("Consultar");
	Button btnFechar = new Button ("Fechar");
	Button btnLimpar = new Button("Limpar");
	
	Label lblOs = new Label("Num. OS");
	Label lblPlaca = new Label("Placa");
	Label lblModelo = new Label("Modelo");
	Label lblCor = new Label("Cor");
	Label lblData = new Label("Data");
	Label lblEntrada = new Label("Entrada");
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Bindings.bindBidirectional(control.osProperty(), os.textProperty());
		Bindings.bindBidirectional(control.placaProperty(), placa.textProperty());
		Bindings.bindBidirectional(control.modeloProperty(), modelo.textProperty());
		Bindings.bindBidirectional(control.corProperty(), cor.textProperty());
		Bindings.bindBidirectional(control.dataProperty(), data.textProperty());
		Bindings.bindBidirectional(control.hrEntradaProperty(), hrEntrada.textProperty());
		Bindings.bindBidirectional(control.minEntradaProperty(),minEntrada.textProperty());
		
		
		Background bkg = estilos.setarPlanoDeFundo(imagem);
		GridPane panegd = new GridPane();
		GridPane paneEntrada = new GridPane();
		
		
		paneEntrada.add(hrEntrada, 0, 0);
		paneEntrada.add(new Label(":"), 1, 0);
		paneEntrada.add(minEntrada, 2, 0);
		
		
		panegd.setStyle(estilos.GetEstiloSub());
		panegd.setBackground(bkg);
		panegd.setHgap(20);
		panegd.setVgap(40);
		
		panegd.add(lblOs, 0, 0);
		panegd.add(os, 1, 0);
		panegd.add(btnPesq, 2, 0);
		panegd.add(btnApagar, 3, 0);
		btnPesq.setStyle(estilos.getEstiloBotao1());
		btnApagar.setStyle(estilos.getEstiloBotao1());
		
		panegd.add(lblPlaca,0,1);
		panegd.add(placa, 1, 1);
		
		panegd.add(lblModelo, 0, 2);
		panegd.add(modelo, 1, 2);
		
		panegd.add(lblCor, 0, 3);
		panegd.add(cor, 1, 3);
		
		panegd.add(lblData, 0, 4);
		panegd.add(data, 1, 4);
		
		panegd.add(lblEntrada, 0, 5);
		panegd.add(paneEntrada, 1, 5);
		
		panegd.add(btnFechar, 0, 9);
		btnFechar.setStyle(estilos.getEstiloBotao1());

		panegd.add(btnLimpar, 1, 9);
		btnLimpar.setStyle(estilos.getEstiloBotao1());
		
		
		btnPesq.setOnAction(e ->{
			try {
				control.pesquisar();
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERRO! NÃO FOI POSSIVEL LOCALIZAR");
			}
		});
		
		
		btnApagar.setOnAction(e->{
			try {
				control.deletar();
				stage.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERRO! NÃO FOI POSSIVEL DELETAR");
			}
		});
		
		
		btnFechar.setOnAction(e->{
			stage.close();
		});
		
		btnLimpar.setOnAction(e->{
			control.limpa();
		});

		
		Scene sc = new Scene(panegd, 800,800 );
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setAlwaysOnTop(false);
		stage.setScene(sc);
		stage.show();

		
		setStage(stage);
		
	}

	

	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}



	public void setPlaca(Text placa) {
		this.placa = placa;
	}



	public void setModelo(Text modelo) {
		this.modelo = modelo;
	}



	public void setCor(Text cor) {
		this.cor = cor;
	}



	public void setData(Text data) {
		this.data = data;
	}



	public void setHrEntrada(Text hrEntrada) {
		this.hrEntrada = hrEntrada;
	}



	public void setMinEntrada(Text minEntrada) {
		this.minEntrada = minEntrada;
	}

}

