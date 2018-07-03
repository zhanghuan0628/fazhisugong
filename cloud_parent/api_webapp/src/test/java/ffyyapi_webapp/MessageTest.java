package ffyyapi_webapp;

import java.text.MessageFormat;

import com.ffxl.platform.util.Message;

public class MessageTest {

    public static void main(String[] args) {
        String parm = "appId";
        String message = MessageFormat.format(Message.getMessage(Message.M4006), parm);
        System.out.print(message);
    }

}
