Booking Table
CREATE TABLE `booking` (
`id` int NOT NULL,
`room_price_id` int NOT NULL,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`e_mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`book_note` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`book_check_in` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`book_check_out` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`price` int NOT NULL,
`guest` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `booking` (`id`, `room_price_id`, `name`, `phone`, `e_mail`, `book_note`, `book_check_in`, `book_check_out`, `price`, `guest`) VALUES
(11, 9, 'hasan', '53435', 'ayahada', 'defdsd', '15/02/2023', '17/02/2023', 18000, 2),
(12, 8, 'muhammed kurt', '5397665434', 'muhammed@gmail.com', 'oda temiz olsun', '02/08/2023', '03/08/2023', 3600, 2),
(13, 8, 'Yakup Çelik', '05396777356', 'yakupcelik@gmail.com', 'temiz havlu olsun.', '21/09/2023', '25/09/2023', 22400, 3);



Hostel_type table
CREATE TABLE `hostel_type` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`name` enum('Ultra Her Şey Dahil','Her şey Dahil','Oda Kahvaltı','Tam Pansiyon','Yarım Pansiyon','Sadece Yatak','Alkol Hariç Full Credit') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `hostel_type` (`id`, `hotel_id`, `name`) VALUES
(31, 14, 'Ultra Her Şey Dahil'),
(32, 14, 'Her şey Dahil'),
(33, 15, 'Tam Pansiyon'),
(34, 15, 'Sadece Yatak'),
(35, 16, 'Ultra Her Şey Dahil'),
(36, 16, 'Oda Kahvaltı');


Hotel Table
REATE TABLE `hotel` (
CREATE TABLE `hotel` (
`id` int NOT NULL,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`country` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`e_mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`phone_num` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`star` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`hotel_feat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `hotel` (`id`, `name`, `address`, `city`, `country`, `e_mail`, `phone_num`, `star`, `hotel_feat`) VALUES
(14, 'Elit', 'Kiraz mahallesi Altan Caddesi\nNo.25', 'İstanbul', 'Türkiye', 'elitotel@elit.com', '02126514323', '5 Yıldız', 'Ücretsiz Otopark,Yüzme Havuzu,Hotel Concierge,Fitness Center,SPA,Ücretsiz Wifi,7/24 Oda Servisi'),
(15, 'Gür Otel', 'Amasya mahallesi erkal caddesi', 'Antalya', 'Türkiye', 'gürotel@gür.com', '5364631245', '4 Yıldız', 'Ücretsiz Otopark,Yüzme Havuzu,Fitness Center,Ücretsiz Wifi,7/24 Oda Servisi'),
(16, 'Lara Bulut Otel', 'Terkosan mahallesi kirazlıbend\nsokak bodrum', 'muğla', 'türkiye', 'larabulut@gmail.com', '05367563432', '5 Yıldız', 'Ücretsiz Otopark,Ücretsiz Wifi,Hotel Concierge,Yüzme Havuzu,Fitness Center,SPA,7/24 Oda Servisi');

Room Table
CREATE TABLE `room` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`hostel_type_id` int NOT NULL,
`season_id` int NOT NULL,
`name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`bed_number` int NOT NULL,
`room_feat` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `room` (`id`, `hotel_id`, `hostel_type_id`, `season_id`, `name`, `bed_number`, `room_feat`) VALUES
(10, 14, 31, 11, 'Suit', 4, '[Televizyon, Minibar, Oyun konsolu, Projeksiyon, Kasa]'),
(11, 14, 32, 12, 'Single', 1, '[Televizyon, Oyun konsolu, Kasa]'),
(12, 15, 34, 13, 'Dörtlü oda', 4, '[Televizyon]'),
(13, 16, 35, 15, 'Suit', 2, '[Televizyon, Minibar, Projeksiyon, Kasa, Oyun konsolu]'),
(14, 16, 36, 16, 'Tesla', 3, '[Televizyon, Minibar, Projeksiyon, Oyun konsolu]'),
(15, 16, 36, 16, 'yakup', 2, '[Televizyon, Kasa, Minibar, Projeksiyon, Oyun konsolu]');


RoomPrice Table

CREATE TABLE `room_price` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`hostel_type_id` int NOT NULL,
`season_id` int NOT NULL,
`room_id` int NOT NULL,
`stock` int NOT NULL,
`adult_price` int NOT NULL,
`child_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `room_price` (`id`, `hotel_id`, `hostel_type_id`, `season_id`, `room_id`, `stock`, `adult_price`, `child_price`) VALUES
(5, 14, 31, 11, 10, 2, 2000, 1500),
(6, 14, 32, 11, 11, 5, 1500, 1250),
(7, 15, 33, 13, 12, 5, 1000, 750),
(8, 16, 35, 16, 14, 3, 2000, 1600),
(9, 16, 35, 15, 13, 4, 5000, 4000),
(10, 14, 31, 12, 10, 10, 1500, 1000);


Season Table
CREATE TABLE `season` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`season_start_date` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`season_end_date` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `season` (`id`, `hotel_id`, `season_start_date`, `season_end_date`) VALUES
(11, 14, '01/01/2023', '31/05/2023'),
(12, 14, '01/06/2023', '31/12/2023'),
(13, 15, '01/02/2023', '31/07/2023'),
(15, 16, '01/01/2023', '31/06/2023'),
(16, 16, '01/07/2023', '31/11/2023');



User Table

CREATE TABLE `user` (
`id` int NOT NULL,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `user` (`id`, `name`, `uname`, `password`, `type`) VALUES
(1, 'yakup', 'y', '1', 'admin'),
(2, 'ahmet', 'a', '1', 'AgencyPerson'),
(4, 'yakup', 'yak', '1', 'AgencyPerson'),
(6, 'yusuf', 'yu', '1', 'AgencyPerson'),
(10, 'yakup', 'ya', '1', 'AgencyPerson');


ALTER TABLE `booking`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `hostel_type`
--
ALTER TABLE `hostel_type`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room_price`
--
ALTER TABLE `room_price`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `season`
--
ALTER TABLE `season`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
ADD PRIMARY KEY (`id`);

--
-- Tablo için AUTO_INCREMENT değeri `booking`
--
ALTER TABLE `booking`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `hostel_type`
--
ALTER TABLE `hostel_type`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `room_price`
--
ALTER TABLE `room_price`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `season`
--
ALTER TABLE `season`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;