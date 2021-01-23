package javabasic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
	public static void main(String[] args) {
		MyDP my = new MyDP();
		int[][] matrix = new int[2][2];
		matrix[0][0] = 3;
		matrix[1][0] = 1;
		matrix[0][1] = 2;
		matrix[1][1] = 0;
		System.out.println(my.largestSquareOfMatches(matrix));
	}
}