INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (1, 1, 'lux', 'free', 100.00, '/images/lux_1.jpg');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (2, 2, 'lux', 'free', 150.00, '/images/lux_2.jpg');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (3, 2, 'standard', 'free', 70.00, '/images/standard_2.jpg');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (4, 3, 'standard', 'free', 110.00, '/images/standard_3.jpg');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (5, 1, 'economy', 'free', 50.00, '/images/default_room.png');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (6, 1, 'lux', 'pending payment', 110.00, '/images/default_room.png');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (7, 2, 'economy', 'pending payment', 60.00, '/images/default_room.png');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (8, 3, 'standard', 'free', 120.00, '/images/room4.jpg');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (9, 4, 'lux', 'free', 250.00, '/images/room3.jpg');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (10, 2, 'standard', 'free', 80.00, '/images/room2.jpg');
INSERT INTO hoteldb.rooms (apartment_number, sleeping_places, room_class, status, price, img_path)
    VALUES (11, 2, 'economy', 'free', 70.00, '/images/room.jpg');


INSERT INTO hoteldb.users (user_id, login, email, password, role)
    VALUES (1, 'kos', 'kos@gmail.com', 'kospass', 'client');
INSERT INTO hoteldb.users (user_id, login, email, password, role)
    VALUES (2, 'admin', 'admin@gmail.com', 'password', 'manager');