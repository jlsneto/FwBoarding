package fwboarding;

import java.io.IOException;

import java.util.Optional;

import com.jfoenix.controls.JFXDecorator;

import grupoUsuario.CadastroGrupoController;
import helpers.Routes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.LoginController;
import model.vo.GrupoUsuarioVO;
import model.vo.NavioVO;
import model.vo.UsuarioVO;
import navio.CadastroNavioController;
import usuario.CadastroSenhaController;
import usuario.CadastroUsuarioController;
import view.ConstruirDialog;

public class FwBoarding extends Application {

	public static Stage stage;
	public static BorderPane rootLayout;

	@Override
    public void start(Stage stage) throws Exception {
		//Carregar tela Login
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Routes.LOGINVIEW));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setStage(stage);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(FwBoarding.class.getResource("../view/styles/styles.css").toExternalForm());
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(FwBoarding.class.getResource("/view/images/Icons/IconNavio.png").toString()));
        stage.setIconified(false);
        stage.show();
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
		//controller.setDialogStage(dialogStage);

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
			//	controller.setNavioAlterar(args);
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

	public static void carregarTelaCadastroGrupoUsuario(GrupoUsuarioVO... args) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FwBoarding.class.getResource("/view/TelaCadastroGrupo.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro Grupo de Usuario");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);

		CadastroGrupoController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		
		if (CadastroGrupoController.isAlterarGrupo) {
			dialogStage.setTitle("Alteração Grupo de Usuario");
			controller.setGrupoUsuarioAlterar(args);
		}
		
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();

	}

	public static void carregarTelaGrupoConsulta() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FwBoarding.class.getResource("/view/TelaConsultaGrupo.fxml"));
			AnchorPane grupoUsuario;
			grupoUsuario = (AnchorPane) loader.load();
			// Define a TelaConsultas no centro do root layout.
			FwBoarding.rootLayout.setCenter(grupoUsuario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ConstruirDialog alert = new ConstruirDialog();
			alert.dialogAlert("Erro Janela", "Não Foi possível Iniciar Tela Consulta  Grupo de Usuario", e.getMessage());
		}

	}
	
	public static void carregarTelaUsuario() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FwBoarding.class.getResource("/view/TelaConsultaUsuario.fxml"));
			AnchorPane consultaUsuario;
			consultaUsuario = (AnchorPane) loader.load();
			// Define a TelaConsultas no centro do root layout.
			FwBoarding.rootLayout.setCenter(consultaUsuario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ConstruirDialog alert = new ConstruirDialog();
			alert.dialogAlert("Erro Janela", "Não Foi possível Iniciar Tela Consulta Usuario", e.getMessage());
		}

	}
	
	public static void carregarTelaCadastroUsuario(UsuarioVO ... args) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FwBoarding.class.getResource("/view/CadastroUsuario.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de Usuarios");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		// dialogStage.initStyle(StageStyle.UNDECORATED);
		// dialogStage.initStyle(StageStyle.UTILITY);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);
		dialogStage.getIcons()
				.add(new Image(FwBoarding.class.getResource("/view/images/Icons/IconNavio.png").toString()));

		CadastroUsuarioController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		
		if (CadastroUsuarioController.isAlterarUsuario) {
			dialogStage.setTitle("Alteração de usuario");
			controller.setUsuarioAlterar(args);
		}

		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
	}
	
	public static String carregarDialogCadastroSenha(Stage stage) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FwBoarding.class.getResource("/view/DialogCadastroSenha.fxml"));
		VBox page = (VBox) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastrar Senha");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);
		dialogStage.getIcons()
				.add(new Image(FwBoarding.class.getResource("/view/images/Icons/IconNavio.png").toString()));

		CadastroSenhaController controller = loader.getController();
		controller.setDialogStage(dialogStage);

		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
		
		return controller.getSenha();
	}
	
	public static void carregarTelaEmbarque() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FwBoarding.class.getResource("/view/TelaEmbarque.fxml"));
			AnchorPane embarque;
			embarque = (AnchorPane) loader.load();
			// Define a TelaConsultas no centro do root layout.
			FwBoarding.rootLayout.setCenter(embarque);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ConstruirDialog alert = new ConstruirDialog();
			alert.dialogAlert("Erro Janela", "Não Foi possível Iniciar Tela Embarque", e.getMessage());
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
