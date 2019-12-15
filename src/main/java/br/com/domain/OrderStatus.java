package br.com.domain;

public enum OrderStatus {
    OPEN,
    PROCESSING,
    CLOSED,
    CANCELED;

    public static String getName (OrderStatus status){
        for (OrderStatus type : OrderStatus.values()) {
            if (type.equals(status)){
                return type.name();
            }
        }
        return null;
    }

    public static OrderStatus getEnum (String status){
        for (OrderStatus type : OrderStatus.values()) {
            if (status.equals(type.name())){
                return type;
            }
        }
        return null;
    }

}
