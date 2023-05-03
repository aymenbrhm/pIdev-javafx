/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class StripeController {
    
            @FXML
private TextField cardNumberField;
    private Canvas canvas;

@FXML
private TextField nameField;

@FXML
private TextField expMonthField;

@FXML
private TextField expYearField;

@FXML
private TextField cvcField;

@FXML
public void processPayment() throws StripeException {

    // Configurez votre clé API Stripe
    Stripe.apiKey = "sk_test_51MiGlsDOK8juJEL0QN54Vxc2PBwUQG795FLsok4GoeNgITsxfbSacQrwflSk5OqeLE5CEH9niVsm5uuZ805mmk1d001mwmecAd";

    // Collectez les informations de paiement entrées par l'utilisateur
    String cardNumber = cardNumberField.getText();
    String name = nameField.getText();
    int expMonth = Integer.parseInt(expMonthField.getText());
    int expYear = Integer.parseInt(expYearField.getText());
    String cvc = cvcField.getText();

    // Créez un jeton de carte en utilisant les informations de paiement
    Map<String, Object> tokenParams = new HashMap<String, Object>();
    Map<String, Object> cardParams = new HashMap<String, Object>();

    cardParams.put("number", cardNumber);
    cardParams.put("exp_month", expMonth);
    cardParams.put("exp_year", expYear);
    cardParams.put("cvc", cvc);

    tokenParams.put("card", cardParams);

    Token token = Token.create(tokenParams);

    // Créez un paiement en utilisant le jeton de carte
    Map<String, Object> chargeParams = new HashMap<String, Object>();
    chargeParams.put("amount", 1000); // Montant en centimes
    chargeParams.put("currency", "eur");
    chargeParams.put("source", token.getId()); // Token de carte créé à partir des informations de paiement
    chargeParams.put("description", "Paiement pour contrat");

    try {
        Charge charge = Charge.create(chargeParams);
        System.out.println("Paiement réussi : " );
    } catch (CardException e) {
        // Traitement des erreurs liées à la carte        // Traitement des erreurs liées aux limites d'appel API
}   
     Stage primaryStage=new Stage();
     canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        canvas.setOnMousePressed(e -> {
            gc.beginPath();
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(e -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Signature App");
        primaryStage.show();
}
}
    


