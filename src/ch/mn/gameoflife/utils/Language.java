/******************************************************************************
 *
 * [ Language.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class Language extends ResourceBundle {

    static Locale currentLocale = Locale.getDefault();

    public Language() {
        super();
    }

    public static ResourceBundle switchLanguage(Locale newLocale) {

        currentLocale = newLocale;
        ResourceBundle rBundle = ResourceBundle.getBundle("ch.mn.gameoflife.properties.MessagesBundle", currentLocale);
        if (!(currentLocale.getLanguage().equals(rBundle.getLocale().getLanguage()))) {
            currentLocale = new Locale("en");
            rBundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        }
        return rBundle;
    }

    public static ResourceBundle getResourceBundle() {

        ResourceBundle rBundle = ResourceBundle.getBundle("ch.mn.gameoflife.properties.MessagesBundle", currentLocale);
        return rBundle;
    }
}
