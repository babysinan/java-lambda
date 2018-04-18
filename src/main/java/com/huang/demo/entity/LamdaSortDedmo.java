package com.huang.demo.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 用lambda表达式排序
 * @author timkobe
 */
public class LamdaSortDedmo {

    public static void main(String[] args) {
        test1();
    }

    /**
     * lambda 多条件排序 正序
     */
    public static void test1(){
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity(3,1));
        list.add(new UserEntity(4,3));
        list.add(new UserEntity(1,3));
        list.add(new UserEntity(2,1));
        list.add(new UserEntity(5,4));
        list.add(new UserEntity(5,2));
//        thenComparing 可以继续追加
        list.sort(Comparator.comparing(UserEntity::getStatus).thenComparing(UserEntity::getId));
        System.out.println(list);
    }

    /**
     * 单条件排序
     */
    public static void test2(){
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity(3,1));
        list.add(new UserEntity(4,3));
        list.add(new UserEntity(1,3));
        list.add(new UserEntity(2,1));
        list.add(new UserEntity(5,4));
        list.add(new UserEntity(5,2));
        list.sort(Comparator.comparing(UserEntity::getStatus));
        System.out.println(list);
    }

    /**
     * 倒序
     */
    public static void test3(){
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity(3,1));
        list.add(new UserEntity(4,3));
        list.add(new UserEntity(1,3));
        list.add(new UserEntity(2,1));
        list.add(new UserEntity(5,4));
        list.add(new UserEntity(5,2));
        list.sort(Comparator.comparing(UserEntity::getStatus).reversed());//反转
        System.out.println(list);
    }

    /**
     * 使用lambda 表达式排序
     */
    @Test
    public void test4(){
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity(3,1));
        list.add(new UserEntity(4,3));
        list.add(new UserEntity(1,3));
        list.add(new UserEntity(6,2));
        list.add(new UserEntity(2,1));
        list.add(new UserEntity(5,4));
        list.sort((UserEntity x,UserEntity y)->x.getId().compareTo(y.getId()));
        System.out.println();
    }
}
