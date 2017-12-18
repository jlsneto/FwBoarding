package view;

import java.io.IOException;
import java.util.Optional;

import controller.CadastroNavioController;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.vo.NavioVO;

public class FwBoarding extends Application {

	public static Stage stage;
	public static BorderPane rootLayout;

	@Override
	public void start(Stage stage) throws IOException {
		FwBoarding.stage = stage;
		carregarTelaLogin();

	}

	public static void carregarRootLayout() {
		try {

			// Carrega Tela Principal
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FwBoarding.class.getResource("/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.setTitle("FwBoarding");
			stage.setMaximized(true);
			stage.getIcons()
					.add(new Image(FwBoarding.class.getResource("/view/images/Icons/IconNavio.png").toString()));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void carregarConfiguracaoDatabase() throws IOException {

		// Carrega o arquivo fxml e cria um novo stage para a janela popup.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ConfiguracaoDatabase.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		// Cria o palco dialogStage.
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Configuração do Banco de Dados");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);

		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		// Mostra a janela e espera até o usuário fechar.
		dialogStage.showAndWait();

	}

	public static void carregarTelaLogin() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FwBoarding.class.getResource("/view/TelaLogin.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("FwBoarding Login");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		// dialogStage.initStyle(StageStyle.UNDECORATED);
		// dialogStage.initStyle(StageStyle.UTILITY);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);
		dialogStage.getIcons()
				.add(new Image(FwBoarding.class.getResource("/view/images/Icons/IconNavio.png").toString()));

		LoginController controller = loader.getController();
		controller.setDialogStage(dialogStage);

		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
	}

	public static void carregarTelaNavioConsulta() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FwBoarding.class.getResource("/view/TelaConsultas.fxml"));
			AnchorPane navio;
			navio = (AnchorPane) loader.load();
			// Define a TelaConsultas no centro do root layout.
			FwBoarding.rootLayout.setCenter(navio);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ConstruirDialog alert = new ConstruirDialog();
			alert.dialogAlert("Erro Janela", "Não Foi possível Iniciar Tela Consulta de Navio", e.getMessage());
		}

	}

	public static void carregarTelaCadastroNavio(NavioVO... args) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FwBoarding.class.getResource("/view/CadastroNavio.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastro de Navios");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initStyle(StageStyle.UTILITY);
			dialogStage.initOwner(FwBoarding.stage);
			dialogStage.setResizable(false);

			// Define Palco deste Dialog
			CadastroNavioController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			if (CadastroNavioController.isAlterarNavio) {
				dialogStage.setTitle("Alteração de Navio");
				controller.setNavioAlterar(args);
			}

			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
		} catch (Exception e) {
			ConstruirDialog alert = new ConstruirDialog();
			alert.dialogAlert("Erro Janela", "Não Foi possível Iniciar Tela Cad Navio", e.getMessage());
		}

	}

	public static boolean confirmouCancelamentoOuFehamento() {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Sair do Sistema",
				"Atenção, deseja realmente sair do sistema?", "Pressione OK para confirmar!");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	public static void carregarTelaCadastroGrupoUsuario() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FwBoarding.class.getResource("/view/TelaCadastroGrupo.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro Grupo de Usuario");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);
		
		
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
