package annotation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by infosea on 2016-08-17.
 */
public class FruitRun {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            FruitInfoUtil.getFruitInfo(Apple.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
