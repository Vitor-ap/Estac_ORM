package boundary;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import controller.EstacionamentoController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.HibernateUtil;

public class EstacionamentoBoundary extends Application {
	EstacionamentoController control = new EstacionamentoController();
	Estilos estilos = new Estilos();
	
	
	private Stage stage;
	private String imagem = "fundopreto2.jpg";

	Label lblOs = new Label("Num.Ordem");
	Label lblPlaca = new Label("Placa");
	Label lblModelo = new Label("Modelo");
	Label lblCor = new Label("Cor");
	Label lbldata = new Label("Data");
	Label lblHoraE = new Label("Entrada");
	Label lblHoraS = new Label("Saida");

	TextField txtOs= new TextField();
	TextField txtPlaca = new TextField();
	TextField txtModelo = new TextField();
	TextField txtCor = new TextField();
	TextField txtHoraEntrada = new TextField();
	TextField txtMinutoEntrada = new TextField();
	TextField txtHoraSaida = new TextField();
	TextField txtMinutoSaida = new TextField();


	DatePicker txtData = new DatePicker(LocalDate.now());
	
	
	Button btnRg = new Button("Registrar Entrada");
	Button btnRgs = new Button ("Saída");
	Button btnPesqPlaca = new Button("Consultar");
	Button btnVoltar = new Button("Fechar");
	Button btnDelete = new Button("Deletar");

	@Override
	public void start(Stage stage) throws Exception {

		Background bGround = estilos.setarPlanoDeFundo(imagem);
		
		GridPane formularioEstac = new GridPane();
		GridPane formularioEntrada = new GridPane();
		BorderPane painelPrincipal = new BorderPane();

		Bindings.bindBidirectional(control.osProperty(), txtOs.textProperty());
		Bindings.bindBidirectional(control.placaProperty(), txtPlaca.textProperty());
		Bindings.bindBidirectional(control.modeloProperty(), txtModelo.textProperty());
		Bindings.bindBidirectional(control.corProperty(), txtCor.textProperty());
		Bindings.bindBidirectional(txtData.valueProperty(), control.dataProperty());
		Bindings.bindBidirectional(control.hrEntradaProperty(), txtHoraEntrada.textProperty());
		Bindings.bindBidirectional(control.minEntradaProperty(),txtMinutoEntrada.textProperty());

		
		formularioEntrada.add(txtHoraEntrada, 0, 0);
		formularioEntrada.add(txtMinutoEntrada, 2, 0);
		formularioEntrada.add(new Label(" : "), 1, 0);
		txtHoraEntrada.setPrefSize(80, 40);
		txtMinutoEntrada.setPrefSize(80,40);
		
		

		formularioEstac.setStyle(estilos.GetEstiloSub());
		formularioEstac.setHgap(50);
		formularioEstac.setVgap(10);
		
		formularioEstac.add(lblPlaca, 0, 0);
		formularioEstac.add(txtPlaca, 1, 0);
		
		formularioEstac.add(lblModelo, 2, 0);
		formularioEstac.add(txtModelo, 3, 0);
		
		formularioEstac.add(lblCor, 0, 1);
		formularioEstac.add(txtCor, 1, 1);
		
		formularioEstac.add(lbldata, 2, 1);
		formularioEstac.add(txtData, 3, 1);
		
		formularioEstac.add(lblHoraE, 0, 2);
		formularioEstac.add(formularioEntrada, 1, 2);
		
		formularioEstac.add(lblOs, 2, 2);
		formularioEstac.add(txtOs, 3, 2);
		
		formularioEstac.add(btnRg, 4, 0);
		btnRg.setStyle(estilos.getEstiloBotao1());
		
		formularioEstac.add(btnRgs, 4, 1);
		btnRgs.setStyle(estilos.getEstiloBotao1());
		
		formularioEstac.add(btnPesqPlaca, 4, 2);
		btnPesqPlaca.setStyle(estilos.getEstiloBotao1());
		
		formularioEstac.add(btnVoltar, 5, 2);
		btnVoltar.setStyle(estilos.getEstiloBotao1());
		
		formularioEstac.add(btnDelete, 5, 0);
		btnDelete.setStyle(estilos.getEstiloBotao1());
		

		painelPrincipal.setTop(formularioEstac);
		painelPrincipal.setCenter(control.getTable());
		formularioEstac.setBackground(bGround);
		
		btnRg.setOnAction(e -> {
			try {
				control.adicionar();
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERRO! NÃO FOI POSSIVEL ADICIONAR");
			}
		});
		
		btnPesqPlaca.setOnAction(e -> {
			control.pesquisar();
		});
		
		btnRgs.setOnAction(e -> {
			control.acessaSaida();
		});
		
		btnVoltar.setOnAction(e ->{
			getStage().close();
		});
		
		btnDelete.setOnAction(e->{
			control.acessaDelete();
		});
		
		
		Scene scn = new Scene(painelPrincipal, 1400, 800);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setMaximized(true);
		stage.setScene(scn);
		stage.show();
		setStage(stage);
	}

	public Stage getStage() {
		return stage;

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		Application.launch(EstacionamentoBoundary.class, args);	}
		}
		

