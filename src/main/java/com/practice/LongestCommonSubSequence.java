package com.practice;


import java.math.BigInteger;

/*
*   S T R I N G 1
* E 0 0 0 0 0 0 0
* S 1 0 0 0 0 0 0
* T 0 2 0 0 0 0 0
* R 0 0 3 0 0 0 0
* I 0 0 0 4 0 0 0
* E 0 0 0 0 0 0 0
* N 0 0 0 0 5 0 0
* G 0 0 0 0 0 6 0
* 2 0 0 0 0 0 0 0
* E 0 0 0 0 0 0 0
*
*
* if char matched array[i][j] = 1 + array[i-1][j-1]
* else array[i][j] = 1 + Math.max(array[i][j-1], array[i-1][j-1], array[i-2][j-1] ...)
* */
public class LongestCommonSubSequence {


}

