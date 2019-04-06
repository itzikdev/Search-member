package itzik.com.membersimulator.represent_search_member.model;

import android.os.Parcel;
import android.os.Parcelable;



public class SpaceMember implements Parcelable {
	
	private int color;
	//private LinkedList<Member> list;
	private int sizeMember;
	
	public SpaceMember(int color) {
		this.color = color;
	}

	protected SpaceMember(Parcel in) {
		color = in.readInt();
		sizeMember = in.readInt();
	}


	public void setSizeMember(int sizeMember) {
		this.sizeMember = sizeMember;
	}

	public int getSizeMember(){
		return sizeMember;
	}
	
//	public LinkedList<Member> getMemberList(){
//		return this.list;
//	}

	public int getColorMember(){
		return color;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(color);
		dest.writeInt(sizeMember);
	}

	public static final Creator<SpaceMember> CREATOR = new Creator<SpaceMember>() {
		@Override
		public SpaceMember createFromParcel(Parcel in) {
			return new SpaceMember(in);
		}

		@Override
		public SpaceMember[] newArray(int size) {
			return new SpaceMember[size];
		}
	};
}
