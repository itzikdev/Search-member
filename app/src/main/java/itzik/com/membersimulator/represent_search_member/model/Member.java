package itzik.com.membersimulator.represent_search_member.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable {
	
	
	private int indexI,indexJ;
	
	public Member(int i , int j) {
		this.indexI = i;
		this.indexJ = j;
	}

	protected Member(Parcel in) {
		indexI = in.readInt();
		indexJ = in.readInt();
	}



	public int getIndexI() {
		return this.indexI;
	}
	
	public int getIndexJ() {
		return this.indexJ;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
         dest.writeInt(indexI);
         dest.writeInt(indexJ);
	}

	public static final Creator<Member> CREATOR = new Creator<Member>() {
		@Override
		public Member createFromParcel(Parcel in) {
			return new Member(in);
		}

		@Override
		public Member[] newArray(int size) {
			return new Member[size];
		}
	};
}
