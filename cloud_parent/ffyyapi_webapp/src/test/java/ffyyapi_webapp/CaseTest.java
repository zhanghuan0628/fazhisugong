package ffyyapi_webapp;

import com.ffxl.platform.exception.BusinessException;

public class CaseTest {

    public static void main(String[] args) {
//       int key=1;
//       switch (key) {
//    case 1:
//        if("null".equals(null)){
//            System.out.println("这是1");
//        }else{
//            System.out.println("这不是1");
//        }
//        break;
//
//    default:
//        System.out.println("错误");
//        break;
//    }

        try{
        String s = "15.7sp";
        Float f = Float.parseFloat(s);
        System.out.println(f.intValue());
        }catch(Exception e){
            System.out.println("音频长度类型错误");
        }
    }

}
