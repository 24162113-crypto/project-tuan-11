package vn.hcmute.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import vn.hcmute.util.MoneyUtil_24162113;

public class Cart_24162113 implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<String, CartItem_24162113> items = new LinkedHashMap<>();

    public synchronized Collection<CartItem_24162113> getItems() {
        return new ArrayList<>(items.values());
    }

    public synchronized CartItem_24162113 get(String videoId) {
        return items.get(videoId);
    }

    public synchronized void put(CartItem_24162113 item) {
        items.put(item.getVideoId(), item);
    }

    public synchronized void remove(String videoId) {
        items.remove(videoId);
    }

    public synchronized void clear() {
        items.clear();
    }

    public synchronized boolean isEmpty() {
        return items.isEmpty();
    }

    public synchronized int getSize() {
        return items.size();
    }

    public synchronized int getTotalQuantity() {
        int total = 0;
        for (CartItem_24162113 item : items.values()) {
            total += item.getQuantity();
        }
        return total;
    }

    public synchronized long getTotalAmount() {
        long total = 0;
        for (CartItem_24162113 item : items.values()) {
            total += item.getSubtotal();
        }
        return total;
    }

    public String getTotalAmountText() {
        return MoneyUtil_24162113.format(getTotalAmount());
    }
}
