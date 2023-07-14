package trail;

public class Location {
   private String nameLocation;
    private  int orderNum=1;



    public Location() {
     }


    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }




    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }


    @Override
    public String toString() {
        return "Location" +
                "nameLocation='" + nameLocation + "\n" +
                ", orderNum=" + orderNum;
    }
}
