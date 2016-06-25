package test.study.com.mychabaike.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * create in 2016/6/23 9:57 by xinquan(version 1.0)
 * function:
 */
public class Infomation implements Parcelable{
    private String img;//图片
    private String description;//描述
    private int rcount;//评论读数
    private String time;
    private long id;

    public Infomation() {
    }

    public Infomation(String description, long id, String img, int rcount, String time) {
        this.description = description;
        this.id = id;
        this.img = img;
        this.rcount = rcount;
        this.time = time;
    }

    protected Infomation(Parcel in) {
        img = in.readString();
        description = in.readString();
        rcount = in.readInt();
        time = in.readString();
        id = in.readLong();
    }

    public static final Creator<Infomation> CREATOR = new Creator<Infomation>() {
        @Override
        public Infomation createFromParcel(Parcel in) {
            return new Infomation(in);
        }

        @Override
        public Infomation[] newArray(int size) {
            return new Infomation[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img);
        dest.writeString(description);
        dest.writeInt(rcount);
        dest.writeString(time);
        dest.writeLong(id);
    }
}
