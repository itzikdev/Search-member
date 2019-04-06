package itzik.com.membersimulator.represent_search_member;

public class UtilsGenerator {
	
	 public static void generatorRandomMemberMatrix(int [][] matrix){
		    System.out.println("l i ? "+matrix.length);
		    System.out.println("l j? "+matrix[0].length);
	        for(int i = 0 ; i<matrix.length ; i++){
	            for(int j = 0 ; j<matrix[0].length; j++){
	            	 matrix[i][j] =  (int) Math.round(Math.random());
	            }
	        }
	    }

}
