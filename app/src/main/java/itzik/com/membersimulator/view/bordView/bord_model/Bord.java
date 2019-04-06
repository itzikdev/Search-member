package itzik.com.membersimulator.view.bordView.bord_model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import itzik.com.membersimulator.represent_search_member.model.SpaceMember;

public class Bord {

    public static final String TAG = Bord.class.getSimpleName();


    private int row,col;
    private int [][] matrix;
    private ArrayList<SpaceMember> spaceMembers;


    public Bord(ArrayList<SpaceMember> spaceMembers,int [][] matrix){
        this.row = matrix.length;
        this.col = matrix[0].length;
        this.spaceMembers = spaceMembers;
        this.matrix = matrix;
    }


    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public ArrayList<SpaceMember> getSpaceMembers() {
        return spaceMembers;
    }

    public int [][] getMatrix(){
        return matrix;
    }
}
