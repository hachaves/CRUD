package boundary;

import controller.SuporteController;
import dao.DAOException;
import entity.Carrinho;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Classe que gera a tela com as intera��es do suporte
 */
public class SuporteBoundary implements EventHandler<ActionEvent>, GerarTela {

	private TextField txtEmail = new TextField();

	private BorderPane panePrincipal = new BorderPane();

	private Button btnAtender = new Button("Atender Chamados");
	private Button btnPesquisar = new Button("Pesquisar Pedido");

	private SuporteController sc = new SuporteController();
	
	private TableView<Carrinho> tabela = new TableView<>();

	@Override
	/**
	 * Fun��o retorna a tela de suporte via pane
	 */
	public Pane gerarTela() {
		
		vincularCampos();

		GridPane paneCampos = new GridPane();

		paneCampos.add(new Label("Email"), 0, 1);
		paneCampos.add(txtEmail, 1, 1);
		
		paneCampos.add(btnAtender, 0, 5);
		paneCampos.add(btnPesquisar, 1, 5);

		btnAtender.setOnAction(this);
		btnPesquisar.setOnAction(this);

		panePrincipal.setTop(paneCampos);
		panePrincipal.setCenter(tabela);
		
		try {
			sc.pesquisarTodosProdutos();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return panePrincipal;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Fun��o que vincula os campos com o controller
	 */
	private void vincularCampos() {
		Bindings.bindBidirectional(txtEmail.textProperty(), sc.getEmailProperty());

		TableColumn<Carrinho, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Carrinho, Integer>("idProduto"));

		TableColumn<Carrinho, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Carrinho, String>("clienteEmail"));

		tabela.getColumns().addAll(colId, colEmail);
		tabela.setItems(sc.getLista());
	}

	@Override
	/**
	 * Fun��o que controla a intera��o com o controller
	 */
	public void handle(ActionEvent e) {
		if(btnPesquisar == e.getTarget()) {
			try {
				sc.pesquisarProduto();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}else if(btnAtender == e.getTarget()) {
			BorderPane paneNovo = (BorderPane) panePrincipal.getParent();
			paneNovo.setCenter(new SuporteChamadoBoundary().gerarTela());
		}
	}

}
