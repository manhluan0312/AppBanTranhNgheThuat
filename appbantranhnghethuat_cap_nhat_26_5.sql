-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 26, 2022 lúc 11:49 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `appbantranhnghethuat`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL COMMENT 'Mã admin',
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên đăng nhập',
  `name_admin` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên admin',
  `Password_admin` varchar(150) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Password admin',
  `poto_admin` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ảnh của admin',
  `email` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Email của admin',
  `Phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Số điện thoại admin',
  `Address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Địa chỉ của admin'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `name_admin`, `Password_admin`, `poto_admin`, `email`, `Phone`, `Address`) VALUES
(1, 'Admin_xuan_0322', 'Nguyễn Văn Xuân', 'xuan0312', 'IMG_anh_admin2140652033.jpg', 'xuan215@gmail.com', '0322456549', '23 Hoang Xa,Ung Hoa ,Ha Noi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id_customer` int(11) NOT NULL COMMENT 'Mã khách hàng',
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên đăng nhập của khách hàng',
  `name_customer` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên đầy đủ của khách hàng',
  `poto_customer` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ảnh của khách hàng',
  `email` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Email của khách hàng',
  `Password_customer` varchar(150) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Mật khẩu của khách hàng ',
  `Phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Số điện thoại của khách hàng',
  `Address_customer` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Địa chỉ của khách hàng'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`id_customer`, `username`, `name_customer`, `poto_customer`, `email`, `Password_customer`, `Phone`, `Address_customer`) VALUES
(1, 'luan0312', 'Nguyễn Thành Luân', 'IMG714703473.jpg', 'manhluan1234@gmail.com', 'luan1234', '0325514581', 'Ứng Hòa ,Hà Nội'),
(3, 'tien0312', 'Nguyen Van Tien', NULL, NULL, 'tien123456', '0312554788', 'Thanh Oai,Ha Noi'),
(4, 'nam_1234', 'Dang Van Nam', NULL, NULL, 'nam1234', '0312547895', 'Thach that ,Ha Noi'),
(5, 'hoang123', 'Nguyen Van Hoang', NULL, NULL, 'hoang12345', '0525555366', 'Thach That,Ha Noi'),
(6, 'maianh0325', 'Nguyen Mai Anh', NULL, NULL, 'anh123', '0322222555', 'Ha Noi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id_order` int(11) NOT NULL COMMENT 'Mã đơn hàng',
  `id_customer` int(11) NOT NULL COMMENT 'Mã khách hàng ',
  `Payments` enum('Trả tiền mặt') COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên hình thức thanh toán',
  `Order_date` datetime NOT NULL COMMENT 'Ngày đặt hàng',
  `Delivery_address` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Địa chỉ giao hàng',
  `Note` text COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ghi chú đơn hàng',
  `Total_money` float NOT NULL COMMENT 'Tổng tiền của đơn hàng',
  `Delivery_status` enum('Đang chờ xử lý','Đang xử lý','Đã xử lý') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Đang chờ xử lý' COMMENT 'Tìng trạng đơn hàng'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id_order`, `id_customer`, `Payments`, `Order_date`, `Delivery_address`, `Note`, `Total_money`, `Delivery_status`) VALUES
(4, 1, 'Trả tiền mặt', '2022-05-04 14:54:16', '24 Phố Trung Kính', NULL, 44000000, 'Đang chờ xử lý'),
(5, 3, 'Trả tiền mặt', '2022-05-04 14:59:28', '24 Phố Nguyễn Thái Học', '', 14000000, 'Đang chờ xử lý'),
(10, 4, 'Trả tiền mặt', '2022-05-04 18:24:23', '24 Phố Trưng Trắc', 'Giao hàng sớm nhé', 8000000, 'Đang chờ xử lý'),
(24, 3, 'Trả tiền mặt', '2022-05-05 10:54:35', '24 Phố Trưng Trắc,Hai Ba trung', 'Giao hàng sớm nhé', 3500000, 'Đang chờ xử lý'),
(25, 4, 'Trả tiền mặt', '2022-05-05 11:12:17', '24 Phố Trưng Trắc,Hai Ba Trung', 'Giao hàng sớm nhé', 2000000, 'Đang chờ xử lý'),
(27, 3, 'Trả tiền mặt', '2022-05-05 11:18:57', '24 Phố Trưng Trắc,Hai Ba Trung', 'Giao hàng sớm nhé', 27000000, 'Đang chờ xử lý'),
(29, 1, 'Trả tiền mặt', '2022-05-05 11:50:04', '24 Phố Trưng Trắc,Hai Ba Trung', 'Giao hàng sớm nhé', 3500000, 'Đang chờ xử lý'),
(33, 5, 'Trả tiền mặt', '2022-05-05 12:00:37', '24 Phố Trưng Trắc,Hai Ba Trung', 'Giao hàng sớm nhé', 11000000, 'Đang chờ xử lý'),
(52, 3, 'Trả tiền mặt', '2022-05-16 09:55:36', 'Hai Bà Trưng,Hà Nội', 'giao hàng sớm nhé', 27000000, 'Đang xử lý'),
(54, 1, 'Trả tiền mặt', '2022-05-25 09:37:14', 'Ứng Hòa,Hà Nội', 'Giao sớm', 27000000, 'Đang chờ xử lý'),
(65, 6, 'Trả tiền mặt', '2022-05-25 19:42:46', 'Hoàn Kiếm,Hà Nội', 'Giao hàng sớm', 6000000, 'Đang chờ xử lý'),
(66, 6, 'Trả tiền mặt', '2022-05-25 19:58:19', 'Tay Ho,Ha Noi', 'giao hang som', 42500000, 'Đang chờ xử lý'),
(79, 1, 'Trả tiền mặt', '2022-05-26 16:45:43', 'Ba Dinh ,Ha Noi', 'Giao hang som nhe', 14000000, 'Đang chờ xử lý');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id_order_detail` int(11) NOT NULL COMMENT 'Mã chi tiết đơn hàng',
  `id_product` int(11) NOT NULL COMMENT 'Mã sản phẩm',
  `id_order` int(11) NOT NULL COMMENT 'Mã đơn hàng',
  `Amount` int(11) NOT NULL COMMENT 'Số lượng sản phẩm trong đơn hàng',
  `Price` float NOT NULL COMMENT 'Giá của từng sản phẩm trong đơn hàng'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id_order_detail`, `id_product`, `id_order`, `Amount`, `Price`) VALUES
(1, 29, 4, 1, 14000000),
(2, 28, 4, 2, 15000000),
(3, 12, 5, 2, 7000000),
(4, 21, 10, 2, 4000000),
(5, 1, 24, 1, 3500000),
(6, 7, 25, 1, 2000000),
(7, 8, 27, 1, 5000000),
(8, 14, 27, 1, 22000000),
(9, 1, 29, 1, 3500000),
(10, 1, 33, 1, 3500000),
(11, 2, 33, 1, 7500000),
(12, 5, 52, 1, 7000000),
(13, 58, 52, 2, 10000000),
(14, 5, 54, 1, 7000000),
(15, 58, 54, 2, 10000000),
(18, 50, 65, 1, 6000000),
(19, 1, 66, 3, 3500000),
(20, 47, 66, 2, 16000000),
(21, 44, 79, 2, 7000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id_product` int(11) NOT NULL COMMENT 'Mã sản phẩm',
  `name_product` varchar(150) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên sản phẩm',
  `poto_product` varchar(150) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Ảnh sản phẩm',
  `price_product` float NOT NULL COMMENT 'Giá sản phẩm ',
  `product_material` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Chất liệu của sản phẩm',
  `product_dimensions` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Kích thước của sản phẩm',
  `year_of_creation` year(4) NOT NULL COMMENT 'Năm sáng tác sản phẩm',
  `product_description` text COLLATE utf8_unicode_ci NOT NULL COMMENT 'Mô tả sản phẩm',
  `note_products` text COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ghi chú của sản phẩm',
  `id_catalog` int(11) NOT NULL COMMENT 'Mã danh mục sản phẩm'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id_product`, `name_product`, `poto_product`, `price_product`, `product_material`, `product_dimensions`, `year_of_creation`, `product_description`, `note_products`, `id_catalog`) VALUES
(1, 'Bình minh trên biển', 'binhminhtrenbien.jpeg', 3500000, 'Acrylic', '1000 x 1200cm', 2019, 'Một bức tranh về đề tài biển của mình, cảm hứng của mình về những hôm thức dậy sớm đón bình minh trên biển.', NULL, 6),
(2, 'Tháng 3 Làng Là', 'thangbalangla.jpg', 7500000, 'Sơn dầu', ' 70x 90 cm', 2020, 'Tranh được vẽ trong chuyến trực họa tháng 3 tại Tuyên Quang', NULL, 8),
(4, 'Vườn quê', 'vuonque.jpg', 12000000, 'Acrylic', '80 x 100cm ', 2022, 'Vẽ về cảnh một góc vườn quê ngoại với hình ảnh chính là các bé gái đang cho đàn gà ăn trong khu vườn quê bình dị và thân thương, có bụi chuối đã trổ buồng, mái tường quê cũ kỉ toác nứt rõ vết gạch loang lỗ. Từng tia nắng sớm mai chiếu rọi hắt qua từng khóm lá, ngọn cỏ khiến cho khu vườn trở nên ấm áp. Tác phẩm nằm trong Series “Kí ức tuổi thơ”.\r\n', NULL, 8),
(5, 'Khúc sông quê', 'khucsongque.jpg', 7000000, 'Acrylic', '60 x 80cm', 2021, 'Tranh diễn tả một khúc sông của làng quê trong ánh nắng ban mai vàng rực rỡ, ấm áp. Dòng sông lóng lánh những tia nắng ôm ấp tiếng cười khúc khích trẻ thơ nô đùa trên sóng nước. Trên bến sông, có con trâu đang tắm mát dưới làn sóng nước cùng chú bé cưỡi mình trên lưng trâu. Phía xa là đàn vịt bơi lội tung tăng dưới dòng nước. Tất cả tạo nên một  bức tranh tuyệt đẹp về khúc sông quê.', NULL, 8),
(6, 'Vị Xưa', 'vixua.jpeg', 5000000, 'Acrylic', '55x 73cm', 2022, 'Tuổi thơ ngày xưa của người nông đân VIỆT NAM,ai cũng sẽ nhớ mãi vị thơm nức mũi của châu chấu nướng , muôm muỗm , nướng! béo ngậy để rồi vang vọng mãi trong tâm trí mùi vị ấy! đồng ruộng nhiều nơi ko còn nữa các nhà máy mọc lên khoảng trời xưa không còn nữa . tuổi thơ theo mẹ trên cánh đồng lộng gió hoà mình vào thiên nhiên cây cỏ ! đuổi bắt châu chấu có khi chật túi rồi ko biết cất đâu lại vẹn vào gấu quần gấu áo mang về, nướng ăn thơm nức mũi mà vị đặc trưng ko sao quên được ! ……… ', NULL, 8),
(7, 'Chiều quê yên bình', 'chieuqueyenbinh.jpeg', 2000000, ' Acrylic', '73x 55cm', 2022, 'Tranh tả một góc quê nghèo thân thương và gần gũi,khi những tia nắng cuối chiều còn xót cũng là lúc mọi người đi làm về , những bước chân trần trên đất mộc mạc,cái nón lá , cái quần sắn gấu cũng như thân quen với người nhà nông từ khi nào ko biết? chú bò to là vậy mà hiền lành , bước những bước chậm dãi ung dung trong trong cảnh thanh bình ấy, con người con vật nơi đây đều là nét đẹp đặc trưng của làng quê việt nam. con người việt nam thân thương dễ mến chân chất hiền lành và rất chịu khó ! phụ nữ VIỆT NAM đảm đang tảo tần . bao công việc đồng áng đều ko quản vất vả! vẫn chu toàn bếp núc nhà cửa. tia nắng cuối chiều khiễn cảnh vật nên thơ hữu tình ! như thường lệ cậu vàng lại ra cổng ngóng chủ về . chú gà trống cũng dõi theo mà nhìn chủ đang ở đằng xa! những chua gà con vẫn hồn nhiên nô đùa bên bãi cỏ lề đường! con đường quê cũng hơi xe xe khô sau cơn mưa vẫn còn lầy lội ! mùi bùn tanh mùi đất nồng như gợi nhắc ta nhớ về quê hương ta , giúp tâm hồn ta thêm bình yên và thoải mái ! cảm ơn mọi người đã xem tranh! TÔN VINH VẺ ĐẸP QUÊ HƯƠNG VIỆT NAM! ', 'Tranh đã có khung gỗ!', 8),
(8, 'Lúa vàng', 'luavang.jpg', 5000000, 'Sơn dầu', ' 60 x 80 cm', 2021, 'Mùa lúa chín vàng có lẽ là mùa hạnh phúc nhất của người nông dân qua một thời gian chăm sóc,thu hoạch xong cũng là thời gian nghỉ ngơi để chuẩn bị cho mùa vụ mới.', NULL, 8),
(9, 'Ngày cuối năm', 'ngaycuoinam.jpeg', 15000000, 'Sơn dầu', '60 x 80 cm', 2021, 'Tranh vẽ khung cảnh bàn tiệc cuối năm', 'Đã bao gồm khung', 12),
(10, 'Góc thân quen', 'gocthanquen.jpeg', 4500000, 'Sơn dầu', '30x40cm', 2021, 'Tranh tĩnh vật mộc mạc từ những đồ rất thân quen trong bếp. Một bình cỏ khô tạo không qian rất đáng yêu.', NULL, 12),
(11, 'Tĩnh vật hoa quả', 'tinhvathoaqua.jpeg', 4000000, 'Sơn dầu', '60×80cm', 2021, 'Tranh mô tả cách trang trí bố cục đĩa hoa quả đơn giản', NULL, 12),
(12, 'Chớm Đông', 'chomdong.jpeg', 7000000, 'Sơn dầu', '40 cm x 40 cm', 2021, 'Tranh mô tả sự vật một chút lãng đãng khi Đông về…', NULL, 12),
(13, 'Chở che yêu thương', 'chechoyeuthuong.jpg', 5000000, 'Acrylic', '80x 60cm', 2022, 'Sư yêu thương đùm bọc che chở là rất cần thiết đối với con người. Thể hiện lòng tương ái thân thương trong gđ và ngoài xã hội. Tp ẩn dụ muốn nói lên lòng vị tha của mỗi người trong cuộc sống.', NULL, 16),
(14, 'Cô gái bên sen', 'cogaibensen.jpg', 22000000, 'Sơn dầu', '125× 125cm', 2020, 'Vẻ đẹp thanh tao của cô gái được hoạ sỹ bộc bạch như 1 cành sen. Nét vẽ dân gian và đương đại đc hội tụ trong tác phẩm.', NULL, 16),
(15, 'Nàng Sen', 'nangsen.jpg', 18000000, 'Sơn mài', '60x 80cm', 2020, 'Bức tranh mô tả một cô gái đẹp đang đứng cạnh những bông hoa sen rực rỡ.', NULL, 16),
(16, 'Đón nắng', 'donnang.jpg', 10000000, 'Acrylic', '80 x100cm', 2022, 'hững đóa sen trắng tinh khiết nở rộ, giọt sương sớm còn đọng lại trên cánh sen, bầy vịt tắm mình dưới dòng nước mát lạnh, trong xanh khiến cho cả đầm sen trở nên lung linh, bừng tỉnh.', NULL, 16),
(17, 'Sắc vàng', 'sacvang.jpg', 2000000, 'Acrylic', '50x 40 cm', 2021, 'Sắc vàng của hoa hồng rực rỡ bức tranh thật đẹp.', NULL, 17),
(18, 'Đóa hồng trắng', 'doahongtrang.jpg', 6000000, 'Sơn dầu', '50x60cm', 2021, 'Tôi vẫn luôn yêu thích màu trắng của các loài hoa, màu trắng đó nhẹ nhàng thanh thoát. Không rực rỡ sắc màu, không kiêu sa hay nổi bật nhưng vẫn tràn đầy hương và sắc.\r\nBức tranh này cũng xuất phát từ cảm xúc với hoa trăng ấy. Tôi muốn đặc biệt nhấn mạnh vào màu trắng, muốn nhấn mạnh về sự thanh thoát, tinh khôi ấy, để hông hoa hồng trắng bản thân tự bừng sáng lên.', NULL, 17),
(19, 'Hồng vàng', 'hongvang.jpg', 10000000, 'Acrylic', '65X81cm', 2020, 'Tác phẩm này với gam màu nâu hồng thể hiện sự ấm áp.Một bức tranh hoa nhưng nao nức trong lòng,nếu như vào những ngày đông nó sẽ là những gì an ủi nhất cho những người xa quê.', NULL, 17),
(20, 'Vườn hồng ngày xuân', 'vuonhongngayxuan.jpeg', 3000000, 'Arcylic', '50x 60cm', 2019, 'Bức tranh mô tả những cành hồng rực rỡ khi xuân về...', NULL, 17),
(21, 'Rừng hoa mẫu đơn', 'rungmaudon.jpg', 4000000, 'Acrylic', '60x100cm', 2020, 'Hoa mẫu đơn dày đặc khiến chúng ta đang tưởng lạc vào rừng hoa ', NULL, 18),
(22, 'Mẫu đơn trắng', 'maudontrang.jpeg', 3800000, 'Acrylic', '50x80cm', 2021, 'Hoa mẫu đơn trắng trong sương vàng, tinh khôi dịu dàng, đằm thắm….', NULL, 18),
(23, 'Ngày rực rỡ', 'ngayrucro.jpeg', 3000000, 'Acrylic', '50x60cm', 2021, 'Bức tranh mô tả những đóa hoa mẫu đơn giúp cho ngày làm việc hiệu quả và rực rỡ.', NULL, 18),
(24, 'Biển Hát', 'bienhat.jpg', 7000000, 'Sơn dầu', '40x 90cm', 2018, 'Trong bức tranh sơn dầu Biển hát họa sĩ như vẽ nên một đại dương rộng lớn với những con sóng trùng trùng điệp điệp xô vào bờ. Những con sóng như nhảy múa dưới khoảng trời mênh mông. Điệu múa của nó như làm chủ chủ thiên hạ bởi giai điệu sôi động, âm thanh thanh gầm rú của mình. Trong khung cảnh khắc nghiệt ấy của thiên nhiên, mà họa sĩ có thể đưa đến người xem một cảm giác thơ mộng. Không có nhiều màu sắc rực rỡ trong bức tranh nhưng hoạ sĩ vẫn thành công trong việc đưa người xem đến với khung cảnh chân thật như hiện ra trước mắt. Ở khung cảnh đó người xem có thể cùng nhảy múa với vũ điệu biển cả.', NULL, 6),
(25, 'Ven phá tam giang', 'venphatamgiang.jpeg', 8000000, 'Acrylic', '1mx1m2', 2020, 'Bức tranh phong cảnh này đã được họa sĩ Lê Hữu Long trực họa tại ven phá Tam Giang của Huế. Phong cảnh trong bức tranh mang một sắc màu tươi sáng, hài hòa, với bầu trời trong xanh, với màu nước biển cũng xanh nhưng là xanh thăm thẳm, với một con thuyền lớn đang được neo lại ở gần bến. Con thuyền có màu sơn còn rất mới, đang sẵn sàng căng buồm đón gió để chuẩn bị ra khơi. Đặc biệt bức tranh con được điểm xuyết bởi hình ảnh sinh hoạt đời thường rất thân thuộc, gần gũi của con người. Đó là người vợ đang đứng tiễn chồng ở bến, chuẩn bị cho người chồng một cuộc hành trình ra khơi sắp tới. Bức tranh vừa miêu tả cảnh sắc hữu tình của quê hương, vừa gợi nên tấm lòng thủy chung, thiết tha, mặn nồng – cái hữu tình giữa người với người ở nơi xứ Huế mộng mơ.', NULL, 6),
(26, 'Bến Vắng', 'benvang.jpeg', 12000000, 'Sơn dầu ', '100 x 80 cm', 2019, 'Bên bến sông vắng có những con thuyền đang neo đậu, chờ những người chủ của chúng quay về..', NULL, 6),
(27, 'Sóng xô', 'songxo.jpg', 12000000, 'Arcylic', '90 x 90 cm', 2019, 'Bức tranh miêu tả về những con sóng xô với góc nhìn từ trên cao nhìn xuống những con sóng trở nên lạ thường hai tông màu trắng và đen hòa quyện với nhau hình ảnh những con sóng như những hiện tượng kì lạ để lại những tò mò những cảm xúc khó quên. À thì ra, sóng cũng có những tính cách của sóng, đặc trưng của nó, không hề chỉ có một màu tẻ nhạt.', NULL, 6),
(28, 'THUYỀN và BẾN\r\n', 'thuyenvaben.jpg', 15000000, 'Sơn dầu', '80 × 100 cm', 2019, 'Vẽ về một làng chài, bà con ngư dân đang chuẩn bị ra khơi!\r\nBức tranh phong cảnh Thuyền Và Biển miêu tả cảnh những con thuyền của bà con ngư dân đang chuẩn bị ra khơi bố cục của bức tranh rất cân xứng màu nền của trời và biển hài hòa với nhau trung tâm là hình ảnh ngọn núi và cuộc sống của người dân trong cái nét thanh bình, trong cái nền trời nhẹ nhàng với những đám mây trắng đang in trên dòng nước thì bên cạnh đó là cuộc sống tấp nập đông vui trong cảnh biển trao đổi chuẩn bị cho chuyến đi mới.', NULL, 6),
(29, 'Ra khơi bám biển', 'rakhoibambien.jpg', 14000000, 'Sơn dầu', '90 cm x 120 cm', 2020, 'Những thuyền đánh cá cứ nhấp nhô nhấp nhô hoà cùng với màu xanh như ngọc của  biển cảc bên trên là mây trời đã cho tôi một ấn tượng khó quên sau một lần đi qua vùng biển ấy và tôi đã sáng tác nên tác phẩm này.', NULL, 6),
(34, 'Ta Đang Nhớ Ai', 'tadangnhoai.jpg', 20000000, 'Acylic', '800×600cm', 2019, 'Mùa thu với nét trầm mặc với chút buồn man mác luôn là đề tài và nguồn cảm hứng sáng tác cho các văn nghệ sĩ. Các thiếu nữ cũng thường ngẩn ngơ mỗi độ Thu về, khi bối cảnh cũng tương tác với nỗi lòng, tán lá bàng úa vàng, cây cơm nguội vàng và chút tường vàng với khung cửa sổ màu xanh… khi đó, nhớ tới người mình thương hay tương tư một ai đó cũng là cảm xúc k có gì xa lạ với tâm hồn tinh tế và nhạy cảm.', NULL, 22),
(35, 'Dòng Thời Gian', 'dongthoigian.jpeg', 10000000, 'Khắc gỗ', '60*60', 2021, 'Tác Phẩm Dòng Thời Gian là sự tiếp nối tình yêu nghề của những người con làng Gốm . Tác Phẩm lưu giữ và tôn lên những giá trị văn hoá về làng nghề Gốm.', NULL, 15),
(36, 'Nhịp Sống', 'nhipsong.jpeg', 9000000, 'Khắc gỗ', '50x 40 cm', 0000, 'Có một “ Nhịp Sống “ ! Rất yên ả ,rất dung dị , rất đời . Đang diễn ra dưới tán bàng già', NULL, 15),
(37, 'Morning', 'morning.jpeg', 4000000, 'Sơn dầu', '60x 80cm', 2020, 'Tranh miêu tả một cách trừu tượng khung cảnh buổi sáng dưới góc nhìn của tác giả.', NULL, 14),
(38, 'Hưng long mạch', 'hunglongmach.jpeg', 4000000, 'Sơn dầu', ' 50x 60cm', 2021, 'Tranh miêu tả một cách trừu tượng khung cảnh những con sóng cuồn cuộn dưới góc nhìn của tác giả.', NULL, 14),
(39, 'Phố', 'pho.jpg', 8000000, '2022', 'Sơn dầu toan', 2022, 'Tranh miêu tả một cách trừu tượng khung cảnh phố phường cuồn cuộn dưới góc nhìn của tác giả.', NULL, 14),
(40, 'Mùa Hoa Dã Quỳ', 'muahoadaquy.jpg', 3000000, 'Acrylic', '50cmx 50cm', 2021, 'Tranh trừu tượng môt tả tượng tượng hoa dã quỳ nở tuyệt đẹp.', NULL, 14),
(41, 'Bình an', 'binhan.jpeg', 10000000, 'Acrylic', '100cm x 120cm', 2022, 'Bức tranh Phật và Sen cầu mong sự bình an cho mọi người một cuộc sống tốt đẹp. ', NULL, 13),
(42, 'Dưới Ánh Bình Minh', 'duoianhbinhminh.jpeg', 26000000, 'Sơn mài', '80cm x 100cm', 2021, 'Bức tranh vẽ với hình tượng và ý niệm về Phật giáo.', NULL, 13),
(43, 'Tâm an', 'taman.jpg', 5000000, ' Acrylic', '80x 60cm', 2021, 'Tâm an là tác phẩm sáng tác về chân chung phật với ánh mắt hiền từ nhân hậu luôn mở rộng lòng từ bi chào đón chúng sinh. Với chất liệu Acrylic thể hiện dộ xốp của lớp màu mang cảm giác như những bức tượng phật. Tâm an muốn gửi gắm đến gia chủ sự bình yên an lành trong cuộc sống', NULL, 13),
(44, 'Muôn Kiếp Nhân Sinh', 'muonkiepnhansinh.jpeg', 7000000, 'Sơn dầu', '100cm x 130cm', 2020, 'Người ơi bao kiếp ta luân hồi\r\nGiờ ở đây ta mới được gặp nhau\r\nCó khi qua trăm nghìn năm tử sinh luân hồi\r\nTrăm nghìn năm trong kiếp côn trùng được kiếp người\r\nCuộc sống có bao lâu sao ta hững hờ\r\nLợi danh hơn thua được mất có nghĩa chi\r\nRồi vô thường đến, rồi vô thường đi\r\nRồi ta còn chi xuôi tay khép mi\r\nHãy cứ cho nhau niềm vui sống trọn kiếp người.\r\nĐể ta lại thấy, để ta lại nghe\r\nNiềm vui từ những yêu thương đã trao\r\nHãy cứ cho đi niềm vui sống trọn kiếp người.\r\nĐó là những gì bức tranh muốn thể hiện qua các câu thơ trên.', NULL, 13),
(45, 'Phố đường tàu một buổi chiều', 'phoduongtaumotbuoichieu.jpg', 9000000, 'Sơn dầu', '70x 90cm', 2020, 'Bức tranh mô tả một buổi chiều muộn của phố đường tàu đang có đoàn tàu chạy qua.', NULL, 9),
(46, 'Ký ức Hà nội', 'kiuchanoi.jpg', 8000000, 'Acrylic', '45x 90cm', 2020, 'Bức tranh làm ta nhớ lại tiếng leng kenh tầu điện xưa trên phố của Hà Nội những năm 80,90 của thế kỷ trước.', 'Tranh có khung (nếu cần)', 9),
(47, 'Nắng ký ức', 'nangkiuc.jpg', 16000000, 'Sơn dầu', '70cm x 90cm', 2020, 'Bức tranh mô tả một ngõ nhỏ của làng quê Việt Nam trong một buổi sáng đầy nắng.', NULL, 9),
(48, 'Phố nhộn nhịp', 'phonhonnhip.jpeg', 25000000, 'Acrylic', '90x180cm', 2020, 'Bức tranh là cái nhìn của hoạ sỹ về một góc nhỏ Hà Nội sầm uất lung linh và tấp nập vè đêm, tất cả khơi dạy vẻ đẹp vừa bình dị vội vã nhưng cũng rất tinh tế và nhẹ nhàng.', NULL, 9),
(49, 'Ruộng bậc thang', 'ruongbacthang.jpeg', 2500000, 'Sơn dầu', '50x 60cm', 2021, 'Tranh mô tả phong cảnh vùng cao tuyệt đẹp của ruộng bậ thang -một nét đặc trưng vùng quê Tây Bắc.', NULL, 7),
(50, 'Đi chợ sớm', 'dichosom.jpg', 6000000, 'Acrylic', '70x 100 cm', 2020, 'Tranh mô tả những người dân ở vùng núi đu chợ mua đồ từ sáng sớm tinh mơ.', NULL, 7),
(51, 'Đông về qua núi', 'dongvequanui.jpg', 17500000, 'Sơn dầu', '100cm x 120cm', 2020, 'Mùa đông về trên vùng núi cao làm con người luôn sao xuyến. Cái se se lạnh làm người ta luôn nhớ nhung ra riết. Với cách thể hiện trừu tượng biểu hiện cảm xúc, sẻ đưa người xem đến gần hơn cái lạnh của trời đất, cái ấm áp của tình người mộc mạc vùng cao. Những thửa ruộng bậc thang vào mùa xả nước, càng làm tăng thêm cái lạnh giá của những ngày đông và sự trong lành của thiên nhiên nơi đây.', NULL, 7),
(52, 'Đồi Móng Ngựa', 'doimongngua.jpg', 7500000, 'Arcylic', '45cm x 65 cm ', 2020, 'Phong cảnh phương Bắc là một trong những niềm cảm hứng lớn nhất của tôi. Vì càng sống lâu trong lòng đô thị, khi đứng giữa trùng điệp mênh mang tôi càng thêm trân quý sự trong xanh của núi rừng bên khung cảnh bình dị đến nao lòng của người dân nơi đây. Hít thật sâu một làn sương sớm, dỏng tai nghe tiếng lao xao con suối chảy bên đường, và mặt trời trước khi biến mất, vẫn kịp phủ lên vạn vật một sắc hồng sâu thẳm… Tất cả những khoảnh khắc đó, tôi muốn lưu giữ lại trong tranh, như một kí ức đẹp cho bản thân, và cũng mong có thể lan tỏa đến mọi người xung quanh, như một món quà nhỏ xinh mát lành.', NULL, 7),
(53, 'Thưởng trà nhớ bạn', 'thuongtranhoban.jpg', 18000000, 'Sơn dầu', '149× 119cm', 2020, 'Cô gái ngồi thẩm trà 1 mình và nhớ đến người bạn thân nơi đất khách quê người.', NULL, 5),
(54, 'Thời áo trắng', 'thoiaotrang.jpeg', 15000000, 'Acrylic', '80cm x 80cm', 2020, 'Tranh vẽ thiếu nữ thời áo trắng.', NULL, 5),
(55, 'Nắng Xuân Về', 'nangxuanve.jpeg', 6500000, 'Acylic', '60 x 80cm', 2021, 'Nắng Xuân về Hà Nội cởi bỏ lớp áo ồn ào vội vã hàng ngày.. để lộ ra cái dung dị..bình yên..tĩnh lặng mà mọi ngày ta khó tìm thấy..\r\n\r\nTrong cái nắng xuân dịu dàng..le lói ấy..Hà Nội rực rỡ mà mộc mạc vô cùng..\r\n\r\nMột Hà nội “khác hẳn”.', NULL, 2),
(56, 'Lối phố', 'loipho.jpeg', 2500000, 'Sơn dầu', '60x80cm', 2020, 'Phố Hà nội luôn là niềm cảm hứng với nhiều hoạ sĩ.Ở đây với lối vẽ chân thực và màu sắc sống động,tác giả đã thể hiện lên 1 hà nội gần gũi mà giàu cảm xúc đi vào tâm hồn của những con người Việt.', NULL, 2),
(57, 'Khoảng trời sau cơn mưa', 'khoangtroisauconmua.jpeg', 2800000, 'Acrylic', '65x85cm', 2020, 'Tranh phố cổ đẹp lãng mạn sau cơn mưa.', NULL, 2),
(58, 'Nắng Hội An', 'nanghoian.jpeg', 10000000, 'Sơn dầu', '90 x 70 cm', 2020, 'Bức tranh phong cảnh miêu tả về phố cổ Hội An đặc trưng với những chiếc đèn lồng đủ sắc màu rực rỡ, có lẽ đã trở nên quá quen thuộc với nhiều người nhưng mỗi ánh nắng Hội An điều đó thời sự khác với cái nắng nhẹ nhẹ nổi bật trong tông màu vàng của bức tranh lệnh áp toàn bộ những màu xanh của cây những màu sắc nhiều màu sắc của những chiếc đèn lồng từng ánh nắng lọt qua từng cái lá để lại những chiếc bóng xuống sân càng nổi bật hơn cái nắng giòn tan trong những ngày hè ở Hội An.thật có sức thu hút mạnh mẽ các du khách đến thành phố miền Trung này..!!! ', NULL, 2),
(59, 'Một thoáng cao nguyên', 'motthoangcaonguyen.jpg', 5000000, 'Sơn mài', '60×80 cm', 2022, 'Bức tranh chính là tình yêu của tôi đối với quê hương. Nơi ấy có những ngọn đồi thoai thoải mọc đầy xuyến chi mỏng manh và thanh tao. Vào mùa thu sắc trời rực rỡ và bên dưới lộ ra sắc hồng cam của cỏ hồng, một đặc trưng của Tây Nguyên.', NULL, 1),
(60, 'Cẩm tú cầu trên sườn núi\r\n', 'Camtucautrensuonnui.jpg', 9000000, 'Acrylic', '70x90cm', 2021, 'Khu vườn Cẩm Tú Cầu mọc trên sườn núi, phía xa xa kia là đường chân trời lúc hoàng hôn…', NULL, 1),
(61, 'Rừng thu', 'rungthu.jpg', 4500000, 'Acrylic', '50×70 cm', 2021, 'Ai trong chúng ta cũng đều có riêng cho mình một mùa để yêu và để nhớ. Và chắc hẳn trong số bốn mùa ấy, bạn đã cất giữ trong tim mình cả một miền hoài niệm gắn liền với những kỷ niệm ngọt ngào, khó quên.\r\n\r\nVới riêng tôi, bốn mùa trong năm, mỗi mùa đều có những sắc màu riêng, góp nhặt nên những năm tháng đầy ý nghĩa của cuộc đời. Cứ mỗi độ thu sang lòng tôi lại ngập tràn bao cảm xúc xốn xang, bao nỗi xuyến xao cứ ùa đến dâng tràn.', NULL, 1),
(62, 'Lối nhỏ trong rừng', 'loinhotrongrung.jpg', 9000000, 'Sơn dầu', '80x100cm', 2020, 'Tôi rất thích những con đường, những lối mòn nhỏ ở trong rừng thông. Con đường quanh co, ẩn hiện sau những tàn cây, nhưng bờ cỏ. Có những buổi sáng đi vào rừng dọc theo con đường khi mà những tia nắng len lỏi chiếu xuyên qua tán thông hắt bừng lên đám cây cỏ phía dưới, con đường như bừng sáng lên và cực kỳ thơ mộng.', NULL, 1),
(63, 'Nắng Rừng', 'nangung.jpeg', 9000000, 'Sơn dầu', '90 x 70 cm', 2021, 'Bức tranh Nắng rừngcho người xem  đắm mình trong khung cảnh khu rừng lãng mạn sống động.Trong bức tranh nắng rừng đã vẽ lên những tia nắng len lỏi vào trong những kẽ lá khiến cho chúng trở nên lấp lánh cùng với đó là những bóng cây đổ rạp xuống lối đi khiến cho nó trở nên chân thực như hiện ra ngay trước mắt người xem.', NULL, 1),
(64, 'Tranh loai một12', 'anh_SP1170374318.jpg', 700000, 'Sơn dầu1', '80*70cm', 2020, 'tranh rất đẹp1', '', 22),
(66, 'Tranh loai 2', 'anh_SP733982682.jpg', 75000000, 'Sn dau 2', '75*75', 2019, 'tranh dep', 'dep', 22),
(68, 'Tranh nghe thuat tim xu huong', 'anh_SP1275393929.jpg', 1000000, 'son dau', '45*45 cm', 2019, 'dep', '', 22);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products_catalog`
--

CREATE TABLE `products_catalog` (
  `id_catalog` int(11) NOT NULL COMMENT 'Mã danh mục',
  `name_catalog` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên danh mục'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `products_catalog`
--

INSERT INTO `products_catalog` (`id_catalog`, `name_catalog`) VALUES
(1, 'Tranh Núi Rừng'),
(2, 'Tranh Phố Cổ'),
(5, 'Tranh Thiếu Nữ'),
(6, 'Tranh Phong Cảnh Biển'),
(7, 'Tranh Phong Cảnh Vùng Cao'),
(8, 'Tranh Phong Cảnh Đồng Quê'),
(9, 'Tranh Ngõ Nhỏ,Phố Nhỏ'),
(12, 'Tranh Tĩnh Vật'),
(13, 'Tranh Phật Giáo'),
(14, 'Tranh Trừu Tượng'),
(15, 'Tranh Khắc Gỗ'),
(16, 'Tranh Hoa Sen'),
(17, 'Tranh Hoa Hồng'),
(18, 'Tranh Hoa Mẫu Đơn'),
(22, 'Tranh Tinh Yeu');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `slider`
--

CREATE TABLE `slider` (
  `id_slider` int(11) NOT NULL COMMENT 'Mã Slider',
  `titile_sider` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tiêu đề Slider',
  `image_slider` varchar(150) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Ảnh Slider'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `slider`
--

INSERT INTO `slider` (`id_slider`, `titile_sider`, `image_slider`) VALUES
(1, 'Phong cảnh đồng quê', 'tranh_phong_canh_dong_que_slider.jpg'),
(2, 'Sơn dầu phong cảnh', 'tranh_son_dau_phong_canh_slider.jpg'),
(3, 'Tranh thêu', 'tranh_theu_chu_thap_slider.jpg');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_customer`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `id_customer` (`id_customer`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id_order_detail`),
  ADD KEY `id_product` (`id_product`,`id_order`),
  ADD KEY `id_order` (`id_order`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id_product`),
  ADD KEY `id_catalog` (`id_catalog`),
  ADD KEY `id_catalog_2` (`id_catalog`);

--
-- Chỉ mục cho bảng `products_catalog`
--
ALTER TABLE `products_catalog`
  ADD PRIMARY KEY (`id_catalog`);

--
-- Chỉ mục cho bảng `slider`
--
ALTER TABLE `slider`
  ADD PRIMARY KEY (`id_slider`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã admin', AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `id_customer` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã khách hàng', AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã đơn hàng', AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id_order_detail` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã chi tiết đơn hàng', AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã sản phẩm', AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT cho bảng `products_catalog`
--
ALTER TABLE `products_catalog`
  MODIFY `id_catalog` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã danh mục', AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `slider`
--
ALTER TABLE `slider`
  MODIFY `id_slider` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã Slider', AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`id_catalog`) REFERENCES `products_catalog` (`id_catalog`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
