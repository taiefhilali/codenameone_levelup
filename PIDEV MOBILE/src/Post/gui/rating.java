/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Post.gui;

import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.gui.BaseForm;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.mycompany.myapp.entities.Facture;
/**
 * 
 *
 * @author msi
 */
public class rating extends BaseForm{
      


/**
 *
 * @author solta
 */


    public static final String ACCOUNT_SID = "AC92321fb722a833528acbcb8828493ab4";
    public static final String AUTH_TOKEN = "9f86775ea7d77c13e0f51bec8bcecad1";
    public static void sendSMSreservation(String s) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new com.twilio.type.PhoneNumber("+21697585788"),//to
                new com.twilio.type.PhoneNumber("+12053040135"),//from 
                "Bonjour monsieur vous avez reservé l'événement " + s).create();
    }

}

