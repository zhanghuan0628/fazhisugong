package admin_webapp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ffxl.cloud.model.AAnswers;
import com.ffxl.cloud.model.SysUser;

public class Test {

     
        public static void main(String[] args) throws Exception {
//          AAnswers user = new AAnswers();
//          //获取公共属性名  
//            Field privateField = getDeclaredField(user, "privateField");
//            System.out.println(privateField.getName());
            getFieldDemo();

        }
     
        /*
         * 获取字节码文件中的字段
         * */
        public static void getFieldDemo() throws Exception{
            Class clazz=Class.forName("com.ffxl.cloud.model.SysUser");
            List<Field> fieldList = new ArrayList<>();
            while (clazz != null){
              fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
              clazz = clazz.getSuperclass();
            }
            Field[] fields = new Field[fieldList.size()];
            fields = fieldList.toArray(fields);

        }
        
        public static Field getDeclaredField(Object object, String fieldName){  
            Field field = null ;  
              
            Class<?> clazz = object.getClass() ;  
              
            for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
                try {  
                    field = clazz.getDeclaredField(fieldName) ;  
                    return field ;  
                } catch (Exception e) {  
                    //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
                    //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了  
                      
                }   
            }  
          
            return null;  
        }     
    }
