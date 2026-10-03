package vn.hcmute.entity;

/**
 * Các trạng thái của đơn hàng. Cột Orders.Status trong database lưu đúng giá trị {@link #getCode()}.
 * Luồng bình thường: PENDING -> CONFIRMED -> PREPARING -> SHIPPING -> DELIVERING -> DELIVERED
 * Nhánh kết thúc khác: CANCELLED (hủy), RETURNED (hoàn).
 */
public enum OrderStatus_24162113 {
    PENDING("PENDING", "Đơn hàng mới", "label-info", 1),
    CONFIRMED("CONFIRMED", "Đã xác nhận", "label-primary", 2),
    PREPARING("PREPARING", "Chuẩn bị hàng", "label-warning", 3),
    SHIPPING("SHIPPING", "Vận chuyển", "label-warning", 4),
    DELIVERING("DELIVERING", "Giao hàng", "label-warning", 5),
    DELIVERED("DELIVERED", "Đã giao", "label-success", 6),
    CANCELLED("CANCELLED", "Đơn hàng hủy", "label-danger", 0),
    RETURNED("RETURNED", "Đơn hàng hoàn", "label-default", 0);

    private final String code;
    private final String text;
    private final String labelClass;
    private final int step;

    OrderStatus_24162113(String code, String text, String labelClass, int step) {
        this.code = code;
        this.text = text;
        this.labelClass = labelClass;
        this.step = step;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getLabelClass() {
        return labelClass;
    }

    /** Vị trí trong luồng giao hàng (1..6); 0 nếu là hủy/hoàn. */
    public int getStep() {
        return step;
    }

    /** Tìm trạng thái theo mã (không phân biệt hoa thường, bỏ khoảng trắng). Không hợp lệ -> null. */
    public static OrderStatus_24162113 fromCode(String value) {
        if (value == null) {
            return null;
        }
        String v = value.trim();
        for (OrderStatus_24162113 s : values()) {
            if (s.code.equalsIgnoreCase(v)) {
                return s;
            }
        }
        return null;
    }
}
