CREATE SCHEMA IF NOT EXISTS `hotelDB` DEFAULT CHARACTER SET utf8;

CREATE TABLE users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       login VARCHAR(30) NOT NULL UNIQUE,
                       email VARCHAR(30) NOT NULL UNIQUE,
                       password VARCHAR(30) NOT NULL,
                       role VARCHAR(10) NOT NULL
);
CREATE TABLE rooms (
                       apartment_number INT PRIMARY KEY NOT NULL,
                       sleeping_places INT,
                       room_class VARCHAR(10) NOT NULL,
                       price DECIMAL(9, 2) NOT NULL,
                       status VARCHAR(16) NOT NULL,
                       img_path VARCHAR(30) DEFAULT '/images/default_room.png'
);
CREATE TABLE orders (
                        order_id INT PRIMARY KEY AUTO_INCREMENT,
                        apartment_number INT,
                        user_id INT NOT NULL,
                        days_number INT NOT NULL,
                        peoples_count INT NOT NULL,
                        room_class VARCHAR(10) NOT NULL,
                        total_sum DECIMAL(9, 2),
                        status VARCHAR(16) DEFAULT 'new',
                        FOREIGN KEY (user_id) REFERENCES users (user_id),
                        FOREIGN KEY (apartment_number) REFERENCES rooms (apartment_number)
);
CREATE TABLE invoice (
                         invoice_id INT PRIMARY KEY AUTO_INCREMENT,
                         apartment_number INT NOT NULL,
                         user_id INT NOT NULL,
                         order_id INT,
                         date BIGINT NOT NULL,
                         sum DECIMAL(9, 2),
                         status VARCHAR(16) NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users (user_id),
                         FOREIGN KEY (apartment_number) REFERENCES rooms (apartment_number),
                         FOREIGN KEY (order_id) REFERENCES orders (order_id)
);