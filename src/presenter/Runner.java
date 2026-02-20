package presenter;

import interfaces.ModelInterface;
import interfaces.PresenterInterface;
import interfaces.ViewInterface;
import model.Model;
import view.ConsoleView;

public class Runner {

    PresenterInterface presenter;
    ModelInterface model;
    ViewInterface view;

    public void makeMVP(String path){
        presenter = new Presenter();
        model = new Model();
        view = new ConsoleView();

        model.setDirectory(path);

        presenter.setModel(model);
        presenter.setView(view);
        view.setPresenter(presenter);
    }

    public void start(String path){
        makeMVP(path);
        presenter.menu();
    }
}
