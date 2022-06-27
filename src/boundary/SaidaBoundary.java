package boundary;

import java.sql.SQLException;

import controller.SaidaController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SaidaBoundary extends Application{
	private Stage stage;
	private String imagem = 		"Imagem1.png";
	SaidaController control = new SaidaController();
	
	Estilos estilos = new Estilos();
	
	TextField os = new TextField();
	private Text placa = new Text();
	private Text modelo = new Text();
	private Text cor = new Text();
	private Text data = new Text();
	private Text hrEntrada = new Text();
	private Text minEntrada = new Text();
	TextField hrSaida = new TextField();
	TextField minSaida = new TextField();
//	private Text valor = new Text();
	
	ComboBox<String> comboPag = new ComboBox<String>(FXCollections.observableArrayList("Dinheiro", "Pix", "Cartão"));
	
	Button btnSaida = new Button("Registrar");
	Button btnPesq = new Button("Consultar");
	Button btnFechar = new Button ("Fechar");
	
	Label lblOs = new Label("Num. OS");
	Label lblPlaca = new Label("Placa");
	Label lblModelo = new Label("Modelo");
	Label lblCor = new Label("Cor");
	Label lblData = new Label("Data");
	Label lblEntrada = new Label("Entrada");
	Label lblSaida = new Label("Saida");
	Label lblPgto = new Label("Forma de pagamento");
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Bindings.bindBidirectional(control.osProperty(), os.textProperty());
		Bindings.bindBidirectional(control.placaProperty(), placa.textProperty());
		Bindings.bindBidirectional(control.modeloProperty(), modelo.textProperty());
		Bindings.bindBidirectional(control.corProperty(), cor.textProperty());
		Bindings.bindBidirectional(control.dataProperty(), data.textProperty());
		Bindings.bindBidirectional(control.hrEntradaProperty(), hrEntrada.textProperty());
		Bindings.bindBidirectional(control.minEntradaProperty(),minEntrada.textProperty());
		Bindings.bindBidirectional(control.hrSaidaProperty(), hrSaida.textProperty());
		Bindings.bindBidirectional(control.minSaidaProperty(),minSaida.textProperty());
		Bindings.bindBidirectional(comboPag.valueProperty(),control.pagamentoProperty());
//		Bindings.bindBidirectional(control.valorProperty(),valor.textProperty());
		
		
		Background bkg = estilos.setarPlanoDeFundo(imagem);
		GridPane panegd = new GridPane();
		GridPane paneEntrada = new GridPane();
		GridPane paneSaida = new GridPane();
		
		
		paneEntrada.add(hrEntrada, 0, 0);
		paneEntrada.add(new Label(":"), 1, 0);
		paneEntrada.add(minEntrada, 2, 0);
		
		
		paneSaida.add(hrSaida, 0, 0);
		paneSaida.add(new Label(":"), 1, 0);
		paneSaida.add(minSaida, 2, 0);
		hrSaida.setPrefSize(50, 50);
		minSaida.setPrefSize(50, 50);
		
		panegd.setStyle(estilos.GetEstiloSub());
		panegd.setBackground(bkg);
		panegd.setHgap(20);
		panegd.setVgap(40);
		
		panegd.add(lblOs, 0, 0);
		panegd.add(os, 1, 0);
		panegd.add(btnPesq, 2, 0);
		btnPesq.setStyle(estilos.getEstiloBotao2());
		
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
		
		panegd.add(lblSaida, 0, 6);
		panegd.add(paneSaida, 1, 6);
		
		panegd.add(lblPgto, 0, 7);
		panegd.add(comboPag, 1, 7);
		panegd.add(btnSaida, 2, 7);
		btnSaida.setStyle(estilos.getEstiloBotao2());
		
		panegd.add(btnFechar, 0, 9);
		btnFechar.setStyle(estilos.getEstiloBotao2());
		
		btnPesq.setOnAction(e ->{
			try {
				control.pesquisar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		
		btnSaida.setOnAction(e->{
			try {
				control.atualizar();
				
				stage.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		
		btnFechar.setOnAction(e->{
			stage.close();
		});
		
		Scene sc = new Scene(panegd, 600,800 );
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

//	public void setValor(Text valor) {
//		this.valor = valor;
//	}
	
}
