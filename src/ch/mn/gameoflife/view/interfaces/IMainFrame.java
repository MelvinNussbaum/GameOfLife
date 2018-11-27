package ch.mn.gameoflife.view.interfaces;

import java.util.Locale;
import java.util.ResourceBundle;

public interface IMainFrame {

    void buildGUI();

    void updateGUI();

    default ResourceBundle internationalize() {

        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();

        Locale currentLocale = new Locale(language, country);

        ResourceBundle rBundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);

        return rBundle;
    }
}
