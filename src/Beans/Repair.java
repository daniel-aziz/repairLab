package Beans;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

public class Repair implements Comparable<Repair> {
    //fields
    private Date readyOn; //estimated time that the drone will be ready.
    private Date entered; //when the drone waas entered to the lab...
    private String memo; //some data for the drone
    private String sn; //serial number of the drone
    private boolean importent; //VIP customer (like aviv)
    private boolean poped; //we show the message that the drone is ready
    private int id; //id from the DB;

    //C'tor
    public Repair(int id,Date readyOn, String memo, String sn, boolean importent, boolean poped) {
        this.readyOn = readyOn;
        this.memo = memo;
        this.sn = sn;
        this.importent = importent;
        this.poped = poped;
        this.entered = new Date(Calendar.getInstance().getTime().getTime()); //enter the current date time....
        this.id=id;
    }

    public Repair() {
        this.entered = new Date(Calendar.getInstance().getTime().getTime()); //enter the current date time....
    }

    public Date getReadyOn() {
        return readyOn;
    }

    public void setReadyOn(Date readyOn) {
        this.readyOn = readyOn;
    }

    public Date getEntered() {
        return entered;
    }

    public void setEntered(Date entered) {
        this.entered = entered;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public boolean isImportent() {
        return importent;
    }

    public void setImportent(boolean importent) {
        this.importent = importent;
    }

    public boolean isPoped() {
        return poped;
    }

    public void setPoped(boolean poped) {
        this.poped = poped;
    }

    //if we will use the all mighty new shiny ArrayList (List)
    //we can not use the comperator, becuase it's only for set collection
    //we will sort the list by time :)

    @Override
    public int compareTo(Repair o) {
        //lets check if we have the same object
        if (equals(o)) {
            return 0;
        }
        //equals compare if the object date is before, same , after
        //                                       -1      0      1
        return readyOn.compareTo(o.readyOn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return importent == repair.importent && poped == repair.poped && Objects.equals(readyOn, repair.readyOn) && Objects.equals(entered, repair.entered) && Objects.equals(memo, repair.memo) && Objects.equals(sn, repair.sn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readyOn, entered, memo, sn, importent, poped);
    }

    /*
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        // eta(day/month/year hh:mm) - done
        // memo - done
        // s/n importent

        sb.append("eta: [")
                .append(readyOn.get(Calendar.DAY_OF_MONTH-1)+"/")
                .append(readyOn.get(Calendar.MONTH) + "/")
                .append(readyOn.get(Calendar.YEAR) + " ")
                .append(readyOn.get(Calendar.HOUR) + ":")
                .append(readyOn.get(Calendar.MINUTE))
                .append("]\n")
                .append(memo + "\n")
                .append("S/N:" + sn + "  VIP customer (not natan):" + importent);

        return sb.toString();
    }
    */


    @Override
    public String toString() {
        return "Repair{" +
                "readyOn=" + readyOn +
                ", entered=" + entered +
                ", memo='" + memo + '\'' +
                ", sn='" + sn + '\'' +
                ", importent=" + importent +
                ", poped=" + poped +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
