package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;

import boundary.DeleteBoundary;
import boundary.Estilos;
import boundary.SaidaBoundary;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Estacionamento;
import persistence.EstacionamentoPersistence;
import persistence.IEstacionamentoPersistence;
import util.HibernateUtil;

public class EstacionamentoController {
	Estilos estilos = new Estilos();
	SaidaBoundary saida = new SaidaBoundary();
	DeleteBoundary del = new DeleteBoundary();
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	private IEstacionamentoPersistence<Estacionamento> dao = new EstacionamentoPersistence(sf);
	private TableView<Estacionamento>table = new TableView<>();
	private ObservableList<Estacionamento> estacionamento = FXCollections.observableArrayList();
	
	private StringProperty os = new SimpleStringProperty();
	private StringProperty placa = new SimpleStringProperty();
	private StringProperty modelo = new SimpleStringProperty();
	private StringProperty cor = new SimpleStringProperty();
	private ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();
	private StringProperty hrEntrada = new SimpleStringProperty();
	private StringProperty minEntrada = new SimpleStringProperty();

	
	public StringProperty osProperty() {
		return os;
	}


	public StringProperty placaProperty() {
		return placa;
	}
	
	public StringProperty modeloProperty() {
		return modelo;
	}
	public StringProperty corProperty() {
		return cor;
	}
	
	public ObjectProperty<LocalDate> dataProperty() {
		return data;
	}
	
	public StringProperty hrEntradaProperty() {
		return hrEntrada;
	}
	
	public StringProperty minEntradaProperty() {
		return minEntrada;
	}
	


	
	@SuppressWarnings("unchecked")
	public EstacionamentoController() {
		TableColumn<Estacionamento, String> col1 = new TableColumn<>("O.S");
		col1.setCellValueFactory(new PropertyValueFactory<>("os"));
		
		TableColumn<Estacionamento, String> col2 = new TableColumn<>("PLACA");
		col2.setCellValueFactory(new PropertyValueFactory<>("placa"));
		
		TableColumn<Estacionamento, String> col3 = new TableColumn<>("MODELO");
		col3.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		
		TableColumn<Estacionamento, String> col4 = new TableColumn<>("COR");
		col4.setCellValueFactory(new PropertyValueFactory<>("cor"));
		
		
		TableColumn<Estacionamento, String> col5 = new TableColumn<>("DATA");
		col5.setCellValueFactory((itemData)-> {
            LocalDate dt = itemData.getValue().getData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));
		});
	
		TableColumn<Estacionamento, String> col6 = new TableColumn<>("HR_ENTRADA");
		col6.setCellValueFactory(new PropertyValueFactory<>("hrEntrada"));
		
		TableColumn<Estacionamento, String> col7 = new TableColumn<>("MIN_ENTRADA");
		col7.setCellValueFactory(new PropertyValueFactory<>("minEntrada"));
		
		
		table.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7);
		
		col1.setPrefWidth(150);
		col2.setPrefWidth(150);
		col3.setPrefWidth(400);
		col4.setPrefWidth(200);
		col5.setPrefWidth(200);
		col6.setPrefWidth(200);
		col7.setPrefWidth(200);
	
		table.setStyle(estilos.GetEstiloSub());
		table.setItems(estacionamento);
		table.setEditable(false);
		
	}
	



	
	public void adicionar() throws SQLException {
		Estacionamento e = new Estacionamento();
		if ( os.get() == "" || placa.get() =="" || modelo.get() == "" || 
				data.get() == null || cor.get() =="" || hrEntrada.get() == "" || minEntrada.get()=="") {
			JOptionPane.showMessageDialog(null, "ERRO! VERIFIQUE SE TODOS OS CAMPOS ESTÃO PREENCHIDOS");
		} else {
		e.setOs(os.get());
		e.setPlaca(placa.get());
		e.setModelo(modelo.get());
		e.setCor(cor.get());
		e.setData(data.get());
		e.setHrEntrada(hrEntrada.get());
		e.setMinEntrada(minEntrada.get());
		dao.insert(e);
		estacionamento.add(e);
		JOptionPane.showMessageDialog(null,"Adicionado com sucesso");
		esvaziarEstac();
	}
	}
	public void pesquisar() {
		Estacionamento e = new Estacionamento();
		e.setPlaca(placa.get());
		e.setModelo(modelo.get());
		e.setData(data.get());
		e.setOs(os.get());
		List<Estacionamento> lista = dao.selectAll(e);
	    estacionamento.clear();
	    estacionamento.addAll(lista);

	}
	
	
	
	public void esvaziarEstac() {
	  placa.set("");
	    modelo.set("");
	    cor.set("");
	    data.set(null);
	    hrEntrada.set("");
	    minEntrada.set("");
	    os.set("");
	}
	
	public void acessaSaida() {
		try {
			saida.start(new Stage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public TableView<Estacionamento> getTable() {
		return table;
	}


	public void acessaDelete() {
		try {
			del.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}


