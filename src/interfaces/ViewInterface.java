package interfaces;

public interface ViewInterface {
    void setPresenter(PresenterInterface presenter);
    int showMenu();
    void showMessage(String msg);
    void showError(String msg);
    String askString(String msg);
    int confirmationMessaeg();
}
