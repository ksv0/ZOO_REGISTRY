package ksv;

import ksv.model.Registry;
import ksv.presenter.Presenter;
import ksv.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Registry registry = new Registry();
        Presenter presenter = new Presenter(view, registry);

        // Запускаем презентер
        presenter.start();
    }
}