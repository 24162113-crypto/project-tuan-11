USE ltweb_de03_24162113;

-- Xem các đơn hiện có
SELECT OrderId, Username, Total, Status FROM Orders ORDER BY OrderId;

-- Đổi trạng thái 1 đơn (thay 1 bằng OrderId cần đổi), rồi F5 trang /orders để quan sát
UPDATE Orders SET Status = 'PENDING'    WHERE OrderId = 1;  -- Đơn hàng mới
UPDATE Orders SET Status = 'CONFIRMED'  WHERE OrderId = 1;  -- Đã xác nhận
UPDATE Orders SET Status = 'PREPARING'  WHERE OrderId = 1;  -- Chuẩn bị hàng
UPDATE Orders SET Status = 'SHIPPING'   WHERE OrderId = 1;  -- Vận chuyển
UPDATE Orders SET Status = 'DELIVERING' WHERE OrderId = 1;  -- Giao hàng
UPDATE Orders SET Status = 'DELIVERED'  WHERE OrderId = 1;  -- Đã giao
UPDATE Orders SET Status = 'CANCELLED'  WHERE OrderId = 1;  -- Đơn hàng hủy
UPDATE Orders SET Status = 'RETURNED'   WHERE OrderId = 1;  -- Đơn hàng hoàn

-- Muốn thử nhanh nhiều tab: rải trạng thái cho các đơn đã có (theo thứ tự OrderId)
-- UPDATE Orders SET Status = ELT(1 + (OrderId - 1) MOD 8,
--   'PENDING','CONFIRMED','PREPARING','SHIPPING','DELIVERING','DELIVERED','CANCELLED','RETURNED');
