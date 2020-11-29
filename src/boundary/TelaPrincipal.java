package boundary;

import controller.ProcessarComandos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TelaPrincipal extends Application implements EventHandler<ActionEvent>, ProcessarComandos {

	private MenuBar mnuBar = new MenuBar();

	private Menu mnuSistema = new Menu("Sistema");
	private MenuItem mnuSair = new MenuItem("Sair");
	private MenuItem mnuLogar = new MenuItem("Logar");
	
	private Menu mnuAjuda = new Menu("Ajuda");
	private MenuItem mnuCreditos = new MenuItem("Cr�ditos");

	private BorderPane panePrincipal = new BorderPane();

	public static void main(String[] args) {
		// Chamar a tela do javaFX
		Application.launch(TelaPrincipal.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ImageView imagens = new ImageView();
		// Colocar background em branco
		panePrincipal.setStyle("-fx-background-color: #FFFFFF;");
		imagens.setImage(new Image("file:images/bannerComLogo.png", 420, 320, false, false));
		panePrincipal.setCenter(imagens);
		
		stage.getIcons().add(new Image("file:images/icon.png"));

		mnuSistema.getItems().addAll(mnuLogar, mnuSair);
		mnuAjuda.getItems().addAll(mnuCreditos);
		
		// Deixar os bot�es funcionando
		mnuLogar.setOnAction(this);
		mnuSair.setOnAction(this);
		mnuCreditos.setOnAction(this);

		mnuBar.getMenus().addAll(mnuSistema, mnuAjuda);
		panePrincipal.setTop(mnuBar);
		Scene cena = new Scene(panePrincipal, 640, 480);

		// Mostrar tela
		stage.setScene(cena);
		stage.setTitle("Loja EletricBuy");
		stage.show();
	}

	@Override
	public void executarComando(String comando) {
		if ("sair".equalsIgnoreCase(comando)) {
			System.exit(0);
		} else if ("creditos".equalsIgnoreCase(comando)) {
			panePrincipal.setCenter(new CreditosBoundary().gerarTela());
		} else if ("logar".equalsIgnoreCase(comando)) {
			panePrincipal.setCenter(new LoginBoundary().gerarTela());
		}
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == mnuSair) {
			executarComando("sair");
		} else if (e.getTarget() == mnuCreditos) {
			executarComando("creditos");
		} else if (e.getTarget() == mnuLogar) {
			executarComando("logar");
		}
	}

}
