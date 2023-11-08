Booking Table
CREATE TABLE `booking` (
  `id` int NOT NULL,
  `room_price_id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `e_mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `book_note` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `book_check_in` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `book_check_out` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `booking` (`id`, `room_price_id`, `name`, `phone`, `e_mail`, `book_note`, `book_check_in`, `book_check_out`) VALUES
(9, 5, 'yakup çelik', '5396778545', 'yakup@gmail.com', 'temiz havlu', '15/02/2023', '17/02/2023');


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
(14, 16, 36, 16, 'Tesla', 3, '[Televizyon, Minibar, Projeksiyon, Oyun konsolu]');


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
(5, 14, 31, 11, 10, 1, 2000, 1500),
(6, 14, 32, 11, 11, 5, 1500, 1250),
(7, 15, 33, 13, 12, 5, 1000, 750),
(8, 16, 35, 16, 14, 5, 2000, 1600),
(9, 16, 35, 15, 13, 5, 5000, 4000);


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
(2, 'ali', 'a', '1', 'AgencyPerson'),
(4, 'yakup', 'yak', '1', 'AgencyPerson'),
(6, 'yusuf', 'yu', '1', 'AgencyPerson'),
(10, 'yakup', 'ya', '1', 'AgencyPerson');


`booking`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
--  `hostel_type`
--
ALTER TABLE `hostel_type`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
--  `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
--  `room_price`
--
ALTER TABLE `room_price`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
--  `season`
--
ALTER TABLE `season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

