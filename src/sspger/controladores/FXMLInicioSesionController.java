package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sspger.modelos.dao.SesionDAO;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;



public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private Label lbErrorUsuario;
    @FXML
    private Label lbErrorPassword;
    @FXML
    private PasswordField tfPassword;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        lbErrorUsuario.setText("");
        lbErrorPassword.setText("");
        validarCampos();
    }
    
    private void validarCampos(){
        String usuario = tfUsuario.getText();
        String password = tfPassword.getText();
        boolean sonValidos = true;
        if(usuario.isEmpty()){
            sonValidos = false;
            lbErrorUsuario.setText("Usuario Incorrecto");
        }
        if(password.isEmpty()){
            sonValidos = false;
            lbErrorPassword.setText("Contraseña Incorrecta");
        }
        if(sonValidos){
            validarCredencialesUsuario(usuario, password);
        }
    }
    
    private void validarCredencialesUsuario(String usuario, String password){
        Usuario usuarioRespuesta = SesionDAO.verificarUsuarioSesion(usuario, password);
        switch (usuarioRespuesta.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de conexión", 
                                               "Por el momento no hay conexión, por favor intentelo mas tarde.", 
                                                Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la solicitud", 
                                               "Por el momento no se puede procesar la solicitud de verificación.", 
                                                Alert.AlertType.ERROR);
                break;
            case Constantes.OPERACION_EXITOSA:
                if(usuarioRespuesta.getIdUsuario() > 0){
                    irMenuPrincipal();
                }else{
                    Utilidades.mostrarDialogoSimple("Credenciales incorrectas", 
                                               "El usuario y/o contraseña no son correctos, por favor verifica la información.", 
                                                Alert.AlertType.WARNING);
                }
                    
                break;
            default:
                Utilidades.mostrarDialogoSimple("Error de petición", 
                                               "El sistema no esta disponible por el momento.", 
                                                Alert.AlertType.ERROR);
                throw new AssertionError();
        }
        System.out.println("Codigo: " + usuarioRespuesta.getCodigoRespuesta());
    }
    
    private void irMenuPrincipal(){
        Stage escenarioBase = (Stage) tfUsuario.getScene().getWindow();
        escenarioBase.setScene(Utilidades.inicializarEscena("vistas/FXMLMenuPrincipal.fxml"));
        Utilidades.centrarEscenario(escenarioBase);
        escenarioBase.setTitle("Menu Principal");    
        escenarioBase.show();   
    }
    
}
