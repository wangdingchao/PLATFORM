package com.example.platform.utils;


import com.example.platform.pojo.constants.ComFinalParams;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by reshine on 2017/11/12.
 */
public class ListUtil {
    public static <T> T find(List<T> source, Predicate<? super T> find) {
//        for (T item : source) {
//            if (find.test(item)) {
//                return item;
//            }
//        }
//
//        return null;
        return source.stream().filter(find).findFirst().orElse(null);
    }

    public static <T> List<T> filter(List<T> source, Predicate<? super T> filter) {
//        List<T> results = new ArrayList<>();
//        for (T item : source) {
//            if (filter.test(item)) {
//                results.add(item);
//            }
//        }
//
//        return results;
        return source.stream().filter(filter).collect(Collectors.toList());
    }

    public static <T, K> List<K> map(List<T> source, Function<T, K> mapper) {
//        List<K> results = new ArrayList<>();
//        for (T item : source) {
//            results.add(mapper.apply(item));
//        }
//
//        return results;
        return source.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 泛型嵌套List转换为二维数组
     *
     * @param src  List<List<T>> 原嵌套list （子list的长度必须相等）
     * @param type 泛型
     * @return T[][] 转换后的二维数组
     */
    public static <T> T[][] listsToArrays(List<List<T>> src, Class<T> type) {
        if (src == null || src.isEmpty()) {
            return null;
        }
        // 初始化泛型二维数组
        T[][] dest = dest = (T[][]) Array.newInstance(type, src.size(), src.get(0).size());
        for (int i = 0; i < src.size(); i++) {
            for (int j = 0; j < src.get(i).size(); j++) {
                dest[i][j] = src.get(i).get(j);
            }
        }
        return dest;
    }

    public static Map<String, List<String>> sameAndDiff(List<String> before, List<String> now) {
        Map<String, List<String>> map = new HashMap<>();
        map.put(ComFinalParams.SAME, null);
        map.put(ComFinalParams.ADD, null);
        map.put(ComFinalParams.REMOVE, null);
        if ((before == null) && (now != null)) {
            map.put(ComFinalParams.ADD, now);
        }
        if ((before != null) && (now == null)) {
            map.put(ComFinalParams.REMOVE, before);
        }
        if ((before != null) && (now != null)) {
            List<String> add = filter(now, (i) -> !before.contains(i));
            List<String> remove = filter(before, (i) -> !now.contains(i));
            List<String> same = filter(before, (i) -> !now.contains(i));
            map.put(ComFinalParams.ADD, add);
            map.put(ComFinalParams.REMOVE, remove);
            map.put(ComFinalParams.SAME, same);
        }
        return map;
    }

    public static <T> List<T> depCopy(List<T> srcList) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            List<T> destList = (List<T>) inStream.readObject();
            return destList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

