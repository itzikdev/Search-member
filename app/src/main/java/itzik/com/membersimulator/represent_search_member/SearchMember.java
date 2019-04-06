package itzik.com.membersimulator.represent_search_member;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import itzik.com.membersimulator.represent_search_member.model.Member;
import itzik.com.membersimulator.represent_search_member.model.SpaceMember;

public class SearchMember {

	public static final String TAG = SearchMember.class.getSimpleName();

	public static ArrayList<SpaceMember> startSearchMatrixMembers(int [][] matrix){
		
		ArrayList<SpaceMember> list = new ArrayList<SpaceMember>();
		Stack<ItemStack> stackMembers = new Stack<ItemStack>();
		int count = 2;
		for(int i = 0 ; i<matrix.length ; i++){
            for(int j = 0 ; j<matrix[0].length; j++){
            	 if(matrix[i][j] == 1) {
            		  SpaceMember space = new SpaceMember(count);
                      LinkedList<Member> memberList = new LinkedList<Member>();
                      memberList.addFirst(new Member(i,j));
                      stackMembers.add(new ItemStack(i, j));
                      matrix[i][j] = count;
                      relaxMember(matrix, stackMembers, memberList, count);
                      space.setSizeMember(memberList.size());
                      list.add(space);
                      count++;
            	 }
            }
		}
		Log.d(TAG,"size space list? "+list.size());
		return list;
		
	}
	
	
	private static void relaxMember(int [][] matrix,Stack<ItemStack> stackMembers,LinkedList<Member> memberList,int countColor) {
		while(!stackMembers.empty()) {
			final ItemStack itemStack = stackMembers.pop();
			int indexI = itemStack.indexI,indexJ = itemStack.indexJ;
			if((indexI-1)>=0 && matrix[indexI - 1][indexJ] == 1) {
				// member up
				memberList.add(new Member(indexI-1, indexJ));
				stackMembers.add(new ItemStack(indexI-1, indexJ));
				matrix[indexI-1][indexJ] = countColor;
				
			}if((indexI+1)<matrix.length && matrix[indexI + 1][indexJ] == 1) {
				// member down
				memberList.add(new Member(indexI+1, indexJ));
				stackMembers.add(new ItemStack(indexI+1, indexJ));
				matrix[indexI+1][indexJ] = countColor;
				
			}if((indexJ-1)>=0 && matrix[indexI][indexJ-1] == 1) {
				// member left
				memberList.add(new Member(indexI, indexJ-1));
				stackMembers.add(new ItemStack(indexI, indexJ-1));
				matrix[indexI][indexJ-1] = countColor;
				
			}if((indexJ+1)<matrix[0].length && matrix[indexI][indexJ+1] == 1) {
				// member right
				memberList.add(new Member(indexI, indexJ+1));
				stackMembers.add(new ItemStack(indexI, indexJ+1));
				matrix[indexI][indexJ+1] = countColor;
				
			}if((indexI-1)>=0 && (indexJ-1)>=0 && matrix[indexI-1][indexJ -1] == 1) {
				memberList.add(new Member(indexI-1, indexJ-1));
				stackMembers.add(new ItemStack(indexI-1, indexJ-1));
				matrix[indexI-1][indexJ-1] = countColor;
				
			}if((indexI+1)<matrix.length && (indexJ+1)<matrix[0].length && matrix[indexI+1][indexJ+1] == 1) {
				memberList.add(new Member(indexI+1, indexJ+1));
				stackMembers.add(new ItemStack(indexI+1, indexJ+1));
				matrix[indexI+1][indexJ+1] = countColor;
				
			}if((indexI-1)>=0 && (indexJ+1)<matrix[0].length && matrix[indexI-1][indexJ+1] == 1) {
				memberList.add(new Member(indexI-1, indexJ+1));
				stackMembers.add(new ItemStack(indexI-1, indexJ+1));
				matrix[indexI-1][indexJ+1] = countColor;
				
			}if((indexI+1)<matrix.length && (indexJ-1)>=0 && matrix[indexI+1][indexJ-1] == 1) {
				memberList.add(new Member(indexI+1, indexJ-1));
				stackMembers.add(new ItemStack(indexI+1, indexJ-1));
				matrix[indexI+1][indexJ-1] = countColor;
				
			}
		}
	}
	
	
	
    static class ItemStack{
		
		   int indexI,indexJ;
		
		   public ItemStack(int i , int j) {
			  this.indexI = i;
			  this.indexJ = j;
		   }
	}

}
