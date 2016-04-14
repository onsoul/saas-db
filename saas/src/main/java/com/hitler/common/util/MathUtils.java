package com.hitler.common.util;


public class MathUtils {

	public static int combination(int m, int n) {
		if (n == 0 || m == 0) {
			return 1;
		}
		if (n > m) {
			return 0;
		}
		if (n > m / 2) {
			n = m - n;
		}
		double a = 0;
		for (int i = m; i >= (m - n + 1); i--) {
			a += Math.log(i);
		}
		for (int i = n; i >= 1; i--) {
			a -= Math.log(i);
		}
		a = Math.exp(a);
		return (int) Math.round(a);
	}
	
	public static int getCombinCount(int a, int d) {
	    if (d > a) {
	        return 0;
	    }
	    if (a == d || d == 0) {
	        return 1;
	    }
	    if (d == 1) {
	        return a;
	    }
	    int b = 1;
	    int e = 1;
	    for (int c = 0; c < d; c++) {
	        b *= a - c;
	        e *= d - c;
	    }
	    return b / e;
	}
	
}
