package mvvm.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mvvm.view.ViewController;
import mvvm.view.Views;
import mvvm.viewModel.ShareAccount;
import services.ViewHandler;
import services.ViewModelFlyweight;

public class ShareAccountController extends ViewController {
    private ShareAccount shareAccount;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField sharedWith;



    @Override
    public void init(ViewModelFlyweight viewModelFlyweight, ViewHandler viewHandler) {
        this.shareAccount = viewModelFlyweight.getShareAccount();
        this.viewHandler = viewHandler;
        name.textProperty().bindBidirectional(shareAccount.nameProperty());
        errorLabel.textProperty().bindBidirectional(shareAccount.errorProperty());
        sharedWith.textProperty().bindBidirectional(shareAccount.shareWithProperty());
        errorLabel.textProperty().setValue("Type email of person you want to share this account with");
    }

    public void share(MouseEvent mouseEvent){
        shareAccount.shareAccount();
    }
    public void back(MouseEvent mouseEvent){
        viewHandler.setCenterView(Views.Accounts.name());
    }


}
