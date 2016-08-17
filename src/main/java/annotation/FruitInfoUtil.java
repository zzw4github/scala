package annotation;

/**
 * Created by infosea on 2016-08-17.
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***********注解处理器***************/

public class FruitInfoUtil {
    public static String getSetMethodName(Field field) {
        String fieldName = field.getName();
       return "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }
    public  static void invokeMethod(Class clazz, Class<?>... paramTypes){

    }
    public static void getFruitInfo(Class<?> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Apple apple = (Apple)clazz.newInstance();
        String strFruitName=" ";
        String strFruitColor="";
        String strFruitProvider="";

        Field[] fields = clazz.getDeclaredFields();

        for(Field field :fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                strFruitName=fruitName.value();

                Method method = clazz.getDeclaredMethod(getSetMethodName(field), String.class); //获得 set方法
                method.invoke(apple, strFruitName); //调用 set方法

            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=fruitColor.fruitColor().toString();

                Method method = clazz.getDeclaredMethod(getSetMethodName(field),String.class); //获得 set方法
                method.invoke(apple, strFruitColor); //调用 set方法
            }
            else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvider=" id: "+fruitProvider.id()+" name: " +fruitProvider.name()+" addr: "+fruitProvider.address();

                Method method = clazz.getDeclaredMethod(getSetMethodName(field),String.class); //获得 set方法
                method.invoke(apple, strFruitProvider); //调用 set方法


            }else if(field.isAnnotationPresent(Import.class)){
                System.out.println(field.getName());
                Import anImport= (Import) field.getAnnotation(Import.class);
                String className =anImport.value();

                Class<?> cc = Class.forName(className); // Printer class
                Object obj = cc.newInstance(); // Printer object
                //通过此方法将新建的实例对象赋值给 static Peron person
                //如果是非static，那么set的第一个参数必须指定实例对象，也就是哪个Injection对象
                field.set(apple, obj);
                Method method = clazz.getDeclaredMethod("displayName");
                try {
                    method.invoke(apple);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
