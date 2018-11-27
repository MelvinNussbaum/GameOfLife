package ch.mn.gameoflife.view.interfaces;

import java.util.Locale;
import java.util.ResourceBundle;

public interface IMainFrame {

    void buildGUI();

    void updateGUI();

    default ResourceBundle switchLanguage(Locale currentLocale) {

        ResourceBundle rBundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        if (!(currentLocale.getLanguage().equals(rBundle.getLocale().getLanguage()))) {
            currentLocale = new Locale("en");
            rBundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        }
        return rBundle;
    }
}
