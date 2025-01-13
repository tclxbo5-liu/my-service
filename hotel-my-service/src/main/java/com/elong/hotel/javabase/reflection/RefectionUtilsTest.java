package com.elong.hotel.javabase.reflection;

import com.elong.hotel.javabase.scheduler.ScheduleTest;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;

/**
 * @author bobo
 * @date 2024/12/26 18:51
 **/
public class RefectionUtilsTest {

    private String privateField = "private";
    private String publicField= "public";



    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//        Class<ShowMethod> clazz = ShowMethod.class;
//        for (Method method : clazz.getMethods()) {
//            System.out.println(method);
//        }
//        Method method = clazz.getDeclaredMethod("add", int.class, int.class);
//        ShowMethod showMethod = new ShowMethod();
//        method.setAccessible(true);
//        System.out.println(method.invoke(showMethod, 1, 2));
//        testFieldRefectionMethod();
//        copyStatic();
        Class<?> aClass = Class.forName("com.elong.hotel.javabase.reflection.ShowMethod");
        Method method = aClass.getDeclaredMethod("show", String.class);
        method.setAccessible(true);
        ShowMethod o =(ShowMethod) aClass.newInstance();
        String invoke = (String)method.invoke(o, "hello");
        System.out.println(invoke);
        LinkedList<String> linkedList = new LinkedList<>();
    }

    public static  void testFieldReflectionUtils() {
        ShowMethod showMethod = new ShowMethod();

        Field field = ReflectionUtils.findField(ShowMethod.class, "name");
        ReflectionUtils.makeAccessible(field);

        System.out.println(ReflectionUtils.getField(field, showMethod));
        ReflectionUtils.setField(field,showMethod,"world");

        System.out.println(ReflectionUtils.getField(field, showMethod));



        System.out.println(field.getName());
        System.out.println(field.getType());
    }

    public static  void testFieldRefectionMethod() throws InvocationTargetException, IllegalAccessException {
        ShowMethod showMethod = new ShowMethod();
        Method add = ReflectionUtils.findMethod(ShowMethod.class, "add", int.class, int.class);
        int modifiers = add.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            System.out.println("public");
        }

        System.out.println(modifiers);
        ReflectionUtils.makeAccessible(add);
        System.out.println(add.invoke(showMethod, 1, 2));
        ReflectionUtils.doWithFields(showMethod.getClass(), field -> {
            System.out.println(field.getName());
        });

        ReflectionUtils.doWithMethods(Integer.class,method -> {
            System.out.println(method.getName());
        });


    }

    public static void copyStatic(){
        ShowMethod showMethod = new ShowMethod();
        ScheduleTest scheduleTest = new ScheduleTest();
        showMethod.setHome("home");
        showMethod.setAge(18);
        showMethod.setShceduleTest(scheduleTest);
        ShowMethod showMethod1 = new ShowMethod();
        ReflectionUtils.shallowCopyFieldState(showMethod,showMethod1);
        System.out.println(showMethod1.getAge());
        System.out.println(showMethod1.getShceduleTest().equals(showMethod.getShceduleTest()));
    }
}
