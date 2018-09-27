package com.hitler.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayUtils extends org.apache.commons.lang.ArrayUtils {

	/**
	 * 统计arr数组中包含e元素的个数
	 * 
	 * @param arr
	 * @param e
	 * @return
	 */
	public static int countElements(Object[] arr, Object e) {
		int count = 0;
		for (Object o : arr) {
			if (o.equals(e)) {
				count++;
			}
		}
		return count;
	}

	public static Object[] sort(Object[] arr) {
		Arrays.sort(arr);
		return arr;
	}

	/**
	 * 检查one数组是否包含two数组所有元素
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static boolean containsAll(Object[] one, Object[] two) {
		for (Object b : two) {
			if (!ArrayUtils.contains(one, b)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查数组中是否有相同元素
	 * 
	 * @param array
	 * @return
	 */
	public static boolean duplicate(Object[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i].equals(array[j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean duplicate(Object[] one, Object[] two) {
		for (Object a : one) {
			for (Object b : two) {
				if (a.equals(b)) {
					return true;
				}
			}
		}
		return false;
	}

	public static Object[] intersect(Object[] one, Object[] two) {
		Set<Object> a = new HashSet(Arrays.asList(one));
		Set<Object> b = new HashSet(Arrays.asList(two));
		a.retainAll(b);
		return a.toArray();
	}

	public static String join(Object[] array, String str) {
		StringBuffer sb = new StringBuffer();
		for (Object element : array) {
			sb.append(element).append(str);
		}
		sb.delete(sb.length() - str.length(), sb.length());
		return sb.toString();
	}

	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		int listSize = list.size(); 						// list的大小
		int page = (listSize + (pageSize - 1)) / pageSize;	// 页数
		List<List<T>> listArray = new ArrayList<List<T>>();	// 创建list数组,用来保存分割后的list
		for (int i = 0; i < page; i++) { 					// 按照数组大小遍历
			List<T> subList = new ArrayList<T>(); 			// 数组每一位放入一个分割后的list
			for (int j = 0; j < listSize; j++) {			// 遍历待分割的list
				int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;// 当前记录的页码(第几页)
				if (pageIndex == (i + 1)) {					// 当前记录的页码等于要放入的页码时
					subList.add(list.get(j)); 				// 放入list中的元素到分割后的list(subList)
				}
				if ((j + 1) == ((j + 1) * pageSize)) {		// 当放满一页时退出当前循环
					break;
				}
			}
			listArray.add(subList);							// 将分割后的list放入对应的数组的位中
		}
		return listArray;
	}

}
