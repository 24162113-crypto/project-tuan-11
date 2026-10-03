CREATE DATABASE IF NOT EXISTS ltweb_de03_24162113 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ltweb_de03_24162113;

CREATE TABLE IF NOT EXISTS Users (
    Username VARCHAR(50) NOT NULL PRIMARY KEY,
    Password VARCHAR(50),
    Phone VARCHAR(15),
    Fullname VARCHAR(50),
    Email VARCHAR(150),
    Admin BIT(1),
    Active BIT(1),
    Images VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS Category (
    CategoryId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Categoryname VARCHAR(100),
    Categorycode VARCHAR(100),
    Images VARCHAR(500),
    Status BIT(1)
);

CREATE TABLE IF NOT EXISTS Videos (
    VideoId VARCHAR(50) NOT NULL PRIMARY KEY,
    Title VARCHAR(200),
    Poster VARCHAR(50),
    Views INT,
    Description VARCHAR(500),
    Active BIT(1),
    Price BIGINT,
    Stock INT,
    CategoryId INT,
    FOREIGN KEY (CategoryId) REFERENCES Category (CategoryId)
);

CREATE TABLE IF NOT EXISTS Shares (
    ShareId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Emails VARCHAR(50),
    SharedDate DATE,
    Username VARCHAR(50),
    VideoId VARCHAR(50),
    FOREIGN KEY (Username) REFERENCES Users (Username),
    FOREIGN KEY (VideoId) REFERENCES Videos (VideoId)
);

CREATE TABLE IF NOT EXISTS Favorites (
    FavoriteId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    LikedDate DATE,
    VideoId VARCHAR(50),
    Username VARCHAR(50),
    FOREIGN KEY (Username) REFERENCES Users (Username),
    FOREIGN KEY (VideoId) REFERENCES Videos (VideoId)
);

CREATE TABLE IF NOT EXISTS Orders (
    OrderId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50),
    Receiver VARCHAR(100),
    Phone VARCHAR(15),
    Address VARCHAR(255),
    Note VARCHAR(255),
    Total BIGINT,
    PaymentMethod VARCHAR(20),
    Status VARCHAR(20),
    CreatedAt DATETIME(6),
    FOREIGN KEY (Username) REFERENCES Users (Username)
);

CREATE TABLE IF NOT EXISTS OrderItems (
    ItemId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    OrderId BIGINT,
    VideoId VARCHAR(50),
    Title VARCHAR(200),
    Price BIGINT,
    Quantity INT,
    FOREIGN KEY (OrderId) REFERENCES Orders (OrderId)
);

-- ============================================================
-- Trạng thái đơn hàng (cột Orders.Status):
--   PENDING    = Đơn hàng mới
--   CONFIRMED  = Đã xác nhận
--   PREPARING  = Chuẩn bị hàng
--   SHIPPING   = Vận chuyển
--   DELIVERING = Giao hàng
--   DELIVERED  = Đã giao
--   CANCELLED  = Đơn hàng hủy
--   RETURNED   = Đơn hàng hoàn
-- Xem file database/update_order_status.sql để đổi trạng thái thử.
-- ============================================================
