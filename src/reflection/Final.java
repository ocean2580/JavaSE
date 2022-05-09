package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static java.util.Arrays.sort;

public class Final {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        final Integer i = 10;

        Field field = Integer.class.getDeclaredField("value");

        Field modifiersField = Field.class.getDeclaredField("modifiers");  //这里要获取Field类的modifiers字段进行修改
        modifiersField.setAccessible(true);
        modifiersField.setInt(field,field.getModifiers()&~Modifier.FINAL);  //去除final标记

        field.setAccessible(true);
        field.set(i, 100);   //强行设置值

        System.out.println(i);

    }
}
