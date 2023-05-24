-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2023 at 05:45 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aplikasiskripsifam`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_account`
--

CREATE TABLE `tb_detail_account` (
  `nidn` varchar(20) NOT NULL,
  `nama_depan` varchar(30) NOT NULL,
  `nama_belakang` varchar(30) NOT NULL,
  `nama_lengkap` varchar(60) NOT NULL,
  `email` varchar(30) NOT NULL,
  `no_telp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_detail_account`
--

INSERT INTO `tb_detail_account` (`nidn`, `nama_depan`, `nama_belakang`, `nama_lengkap`, `email`, `no_telp`) VALUES
('13123412', 'Kocak', 'Pop', 'Kocak Pop', 'Qifli2010@gmail.com', '08991541221'),
('181011400811', 'Popye', 'Arm', 'Popye Arm', 'qifli2018@gmail.com', '089602801793'),
('181011400812', 'Evolance', 'Vanhouten', 'Evolance Vanhouten', 'qifli2010@gmail.com', '089602801739');

-- --------------------------------------------------------

--
-- Table structure for table `tb_hasilpenilaian_fam`
--

CREATE TABLE `tb_hasilpenilaian_fam` (
  `id_hasilpenilaian` int(5) NOT NULL,
  `hasil_penilaianfam` double NOT NULL,
  `keputusan` varchar(25) NOT NULL,
  `penilai_nim` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_hasilpenilaian_fam`
--

INSERT INTO `tb_hasilpenilaian_fam` (`id_hasilpenilaian`, `hasil_penilaianfam`, `keputusan`, `penilai_nim`) VALUES
(1, 50.67, 'Cukup Memuaskan', '171011400225'),
(2, 56.25, 'Cukup Memuaskan', '171011400659'),
(3, 56.25, 'Cukup Memuaskan', '171011401988'),
(4, 100, 'Sangat Memuaskan', '171021700364'),
(5, 56.25, 'Cukup Memuaskan', '181010500930'),
(6, 85, 'Sangat Memuaskan', '181010501433'),
(7, 65, 'Cukup Memuaskan', '181010501469'),
(8, 70, 'Cukup Memuaskan', '181011400033'),
(9, 74.33, 'Cukup Memuaskan', '181011400161'),
(10, 58.06, 'Cukup Memuaskan', '181011400192'),
(11, 52.96, 'Cukup Memuaskan', '181011400230'),
(12, 51.28, 'Cukup Memuaskan', '181011400242'),
(13, 56.25, 'Cukup Memuaskan', '181011400268'),
(14, 80, 'Sangat Memuaskan', '181011400349'),
(15, 100, 'Sangat Memuaskan', '181011400360'),
(16, 75, 'Cukup Memuaskan', '181011400362'),
(17, 75, 'Cukup Memuaskan', '181011400490'),
(18, 73.33, 'Cukup Memuaskan', '181011400509'),
(19, 68, 'Cukup Memuaskan', '181011400685'),
(20, 47.22, 'Cukup Memuaskan', '181011400832'),
(21, 65, 'Cukup Memuaskan', '181011400846'),
(22, 95, 'Sangat Memuaskan', '181011400863'),
(23, 54.07, 'Cukup Memuaskan', '181011400869'),
(24, 100, 'Sangat Memuaskan', '181011400883'),
(25, 56.25, 'Cukup Memuaskan', '181011400894'),
(26, 75, 'Cukup Memuaskan', '181011400900'),
(27, 90, 'Sangat Memuaskan', '181011400908'),
(28, 100, 'Sangat Memuaskan', '181011400910'),
(29, 100, 'Sangat Memuaskan', '181011400917'),
(30, 95, 'Sangat Memuaskan', '181011400918'),
(31, 100, 'Sangat Memuaskan', '181011400924 '),
(32, 74.67, 'Cukup Memuaskan', '181011400927'),
(33, 76, 'Cukup Memuaskan', '181011400954'),
(34, 70, 'Cukup Memuaskan', '181011401001'),
(35, 55.42, 'Cukup Memuaskan', '181011401005'),
(36, 75, 'Cukup Memuaskan', '181011401145'),
(37, 75, 'Cukup Memuaskan', '181011401170'),
(38, 65, 'Cukup Memuaskan', '181011401251'),
(39, 75, 'Cukup Memuaskan', '181011401268'),
(40, 56.25, 'Cukup Memuaskan', '181011401386'),
(41, 75, 'Cukup Memuaskan', '181011401531'),
(42, 50.37, 'Cukup Memuaskan', '181011401551'),
(43, 65.83, 'Cukup Memuaskan', '181011401667'),
(44, 56.25, 'Cukup Memuaskan', '181011401692'),
(45, 48.61, 'Cukup Memuaskan', '181011401713'),
(46, 78, 'Cukup Memuaskan', '181011401760'),
(47, 71.94, 'Cukup Memuaskan', '181011401763'),
(48, 61.67, 'Cukup Memuaskan', '181011401793'),
(49, 70, 'Cukup Memuaskan', '181011401875'),
(50, 56.25, 'Cukup Memuaskan', '181011401895'),
(51, 75, 'Cukup Memuaskan', '181011402101'),
(52, 38.89, 'Kurang Memuaskan', '181011402138'),
(53, 47.22, 'Cukup Memuaskan', '181011402236'),
(54, 100, 'Sangat Memuaskan', '181011402251'),
(55, 80, 'Sangat Memuaskan', '181011402252'),
(56, 73, 'Cukup Memuaskan', '181011402298'),
(57, 80, 'Sangat Memuaskan', '181011402350'),
(58, 85, 'Sangat Memuaskan', '181011402365'),
(59, 100, 'Sangat Memuaskan', '181011402389'),
(60, 55.56, 'Cukup Memuaskan', '181011402508'),
(61, 56.25, 'Cukup Memuaskan', '181011402565'),
(62, 48.06, 'Cukup Memuaskan', '181011402644'),
(63, 100, 'Sangat Memuaskan', '181011402863'),
(64, 54.15, 'Cukup Memuaskan', '181011402865'),
(65, 73.33, 'Cukup Memuaskan', '181011402871'),
(66, 78, 'Cukup Memuaskan', '181011402909'),
(67, 56.25, 'Cukup Memuaskan', '181011600159'),
(68, 75, 'Cukup Memuaskan', '181021400021'),
(69, 100, 'Sangat Memuaskan', '181021400160'),
(70, 66, 'Cukup Memuaskan', '181021700049'),
(71, 85, 'Sangat Memuaskan', '181021700056'),
(72, 50, 'Cukup Memuaskan', '181021700057'),
(73, 76.67, 'Cukup Memuaskan', '181021700062'),
(74, 55.56, 'Cukup Memuaskan', '181021700074'),
(75, 75, 'Cukup Memuaskan', '191011400030'),
(76, 49.77, 'Cukup Memuaskan', '191011400056'),
(77, 75, 'Cukup Memuaskan', '191011401468'),
(78, 56.25, 'Cukup Memuaskan', '191011401864'),
(79, 56.67, 'Cukup Memuaskan', '191011402342'),
(80, 76.67, 'Cukup Memuaskan', '191011450382'),
(81, 58.97, 'Cukup Memuaskan', '191011502134'),
(82, 75, 'Cukup Memuaskan', '191021400018'),
(83, 56.25, 'Cukup Memuaskan', '201010200504'),
(84, 85, 'Sangat Memuaskan', '201010201033'),
(85, 100, 'Sangat Memuaskan', '201010201148'),
(86, 56.25, 'Cukup Memuaskan', '201010201424'),
(87, 55.56, 'Cukup Memuaskan', '201011400338'),
(88, 75, 'Cukup Memuaskan', '201011401600'),
(89, 75, 'Cukup Memuaskan', '201011450105'),
(90, 26.33, 'Kurang Memuaskan', '2016140615'),
(91, 90, 'Sangat Memuaskan', '211011400196'),
(92, 90, 'Sangat Memuaskan', '211011400755'),
(93, 56.25, 'Cukup Memuaskan', '211011400757'),
(94, 75, 'Cukup Memuaskan', '211011400762'),
(95, 100, 'Sangat Memuaskan', '211011400794'),
(96, 55, 'Cukup Memuaskan', '211011401493'),
(97, 56.25, 'Cukup Memuaskan', '211011401509'),
(98, 80, 'Sangat Memuaskan', '211011401865'),
(99, 85, 'Sangat Memuaskan', '211011402117'),
(100, 75, 'Cukup Memuaskan', '211011450560');

-- --------------------------------------------------------

--
-- Table structure for table `tb_penialaian_mahasiswa`
--

CREATE TABLE `tb_penialaian_mahasiswa` (
  `nim` varchar(25) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `semester` varchar(3) NOT NULL,
  `reguler` varchar(3) NOT NULL,
  `p_pembelajaran` int(5) NOT NULL,
  `p_administrasi` int(5) NOT NULL,
  `p_sarana` int(5) NOT NULL,
  `p_perpustakaan` int(5) NOT NULL,
  `p_kemahasiswaan` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_penialaian_mahasiswa`
--

INSERT INTO `tb_penialaian_mahasiswa` (`nim`, `nama`, `semester`, `reguler`, `p_pembelajaran`, `p_administrasi`, `p_sarana`, `p_perpustakaan`, `p_kemahasiswaan`) VALUES
('171011400225', 'Mas Shoultan Satria Nugroho', '11', 'B', 70, 85, 65, 75, 45),
('171011400659', 'Achmad Rivaldi', '10', 'CK', 75, 75, 75, 75, 75),
('171011401988', 'Muhammad ikhsan tripriyono', '10', 'B', 75, 75, 75, 75, 75),
('171021700364', 'Novan Rinaldo Kusuma', '8', 'B', 65, 75, 45, 100, 40),
('181010500930', 'Maria Natalia ', '9', 'B', 75, 75, 75, 75, 75),
('181010501433', 'Fitri rachmawati', '9', 'B', 85, 85, 65, 95, 85),
('181010501469', 'RISTI DIAH PUTRI UTAMI', '9', 'B', 80, 85, 80, 90, 65),
('181011400033', 'Fandy Hidayat', '9', 'B', 75, 100, 95, 95, 70),
('181011400161', 'Sufryah', '9', 'B', 75, 75, 65, 100, 45),
('181011400192', 'Reza anwar sidiq baehaqi', '9', 'B', 75, 55, 75, 75, 70),
('181011400230', 'Effendy Candra Sasmoro', '9', 'B', 65, 85, 80, 80, 50),
('181011400242', 'Dani Muliansyah', '9', 'B', 80, 70, 65, 75, 40),
('181011400268', 'Irwansyah', '9', 'B', 75, 75, 75, 75, 75),
('181011400349', 'Rita Septiani', '9', 'B', 75, 65, 70, 90, 40),
('181011400360', 'Dwi Chandra Maulana', '9', 'B', 100, 100, 100, 100, 100),
('181011400362', 'Arfira Trisna Devi', '9', 'B', 100, 75, 80, 80, 75),
('181011400490', 'Galih Haryanto', '9', 'B', 75, 75, 85, 100, 75),
('181011400509', 'Fira Novia Andriani', '9', 'B', 50, 85, 60, 80, 25),
('181011400685', 'Felina Amara Pasha', '9', 'B', 80, 85, 65, 90, 35),
('181011400832', 'BagasAdiPutra', '10', 'A', 75, 50, 75, 75, 75),
('181011400846', 'Achmad Casdik', '9', 'A', 75, 80, 95, 85, 65),
('181011400863', 'Muhamad Fiqih Julian Azni ', '9', 'A', 90, 75, 75, 85, 95),
('181011400869', 'Alifitar Afif Alyasri', '9', 'B', 80, 80, 55, 80, 50),
('181011400883', 'Muhammad Dimas N', '9', 'A', 100, 100, 90, 100, 100),
('181011400894', 'Aldie Hendrawanto ', '9', 'A', 75, 75, 75, 75, 75),
('181011400900', 'Muhamad Rapli ', '9', 'A', 80, 95, 100, 95, 75),
('181011400908', 'Agung Prasetiyo', '9', 'A', 85, 90, 85, 90, 90),
('181011400910', 'Akmal Razsiyansa ', '9', 'A', 95, 100, 100, 100, 100),
('181011400917', 'Yazid Azrie', '9', 'A', 95, 75, 100, 100, 100),
('181011400918', 'Imam Yusuf Arbianto ', '9', 'A', 100, 90, 75, 90, 95),
('181011400924 ', 'Sheilo Marseleno Sumbay ', '9', 'A', 100, 100, 100, 100, 100),
('181011400927', 'Teddy Djulianto', '9', 'A', 60, 60, 70, 75, 55),
('181011400954', 'Muhammad iqbal', '9', 'B', 65, 85, 65, 100, 40),
('181011401001', 'Ferdyan Pradana', '9', 'B', 70, 85, 60, 75, 50),
('181011401005', 'Bimo Aziz Nugroho', '9', 'B', 70, 80, 70, 85, 50),
('181011401145', 'Ricky Tobeli', '9', 'B', 90, 75, 90, 75, 75),
('181011401170', 'Rahmat Ghifari', '9', 'B', 90, 80, 75, 80, 75),
('181011401251', 'Arinda Chintya Sari', '9', 'B', 75, 75, 90, 100, 65),
('181011401268', 'JIHAN ABDUL ROHMAN', '9', 'CK', 75, 100, 100, 65, 75),
('181011401386', 'Nurhayati', '8', 'B', 75, 75, 75, 75, 75),
('181011401531', 'Muhamad Mirza', '10', 'B', 100, 100, 75, 75, 75),
('181011401551', 'Achmad Sudiro', '9', 'B', 85, 75, 70, 80, 40),
('181011401667', 'Adhitia Nugraha', '9', 'B', 75, 65, 80, 75, 75),
('181011401692', 'Fahrur Rozi', '9', 'B', 75, 75, 75, 75, 75),
('181011401713', 'Wisdom Gunawan', '9', 'B', 70, 80, 70, 70, 35),
('181011401760', 'Wahyu Aji Saputro', '9', 'B', 70, 75, 60, 85, 50),
('181011401763', 'Runisati Waruwu', '9', 'B', 80, 75, 85, 75, 75),
('181011401793', 'Ahmad Fauzi', '9', 'B', 80, 85, 70, 95, 45),
('181011401875', 'Fahreza Ardiansyah', '9', 'B', 65, 55, 60, 75, 45),
('181011401895', 'Rivant Dwi Seftavianto', '9', 'B', 75, 75, 75, 75, 75),
('181011402101', 'Adi Saputra', '9', 'A', 100, 85, 50, 90, 75),
('181011402138', 'Riki idayat', '9', 'B', 75, 75, 75, 75, 50),
('181011402236', 'Aenun Nisa', '9', 'B', 75, 50, 75, 75, 75),
('181011402251', 'Ripki Maulana', '9', 'B', 95, 50, 100, 90, 100),
('181011402252', 'Rizky Darmadi', '9', 'B', 70, 70, 80, 100, 25),
('181011402298', 'Dian', '9', 'A', 80, 60, 70, 75, 70),
('181011402350', 'Ivan Santoso', '9', 'B', 70, 85, 55, 90, 40),
('181011402365', 'Anggit Prastika Setiany ', '8', 'B', 60, 65, 90, 100, 85),
('181011402389', 'Yuni Sari Dwi Aestri Aisaroh', '9', 'B', 90, 100, 80, 100, 100),
('181011402508', 'Widya Indrianingsih', '9', 'B', 70, 75, 70, 75, 75),
('181011402565', 'Putri Purwaningsih ', '9', 'B', 75, 75, 75, 75, 75),
('181011402644', 'Endah Sumiati', '9', 'B', 85, 80, 70, 75, 40),
('181011402863', 'ASEP SAEPUDIN ', '9', 'A', 85, 100, 100, 100, 100),
('181011402865', 'Muhamad Yoga Febriansyah', '9', 'B', 75, 70, 65, 80, 45),
('181011402871', 'Gilang Aditya', '8', 'B', 75, 70, 60, 75, 50),
('181011402909', 'Irvansyah Satria Pamungkas', '9', 'B', 75, 85, 50, 90, 45),
('181011600159', 'Siti Faizah Nuraeni', '9', 'CK', 75, 75, 75, 75, 75),
('181021400021', 'Indah Rizki Sahara Harahap', '9', 'B', 75, 75, 100, 75, 75),
('181021400160', 'EKO SUGENG RIYADI', '9', 'A', 100, 100, 100, 100, 100),
('181021700049', 'Arif Kurniawan', '9', 'B', 75, 85, 70, 90, 50),
('181021700056', 'Naufaldo Dzaki Andhama', '9', 'B', 65, 75, 60, 95, 45),
('181021700057', 'Bagus Pangetsu Adji', '9', 'B', 90, 85, 75, 100, 50),
('181021700062', 'Dimas Bagas Prasetyo', '9', 'B', 75, 80, 60, 90, 40),
('181021700074', 'Nanda Rizky Pratama', '8', 'B', 75, 70, 55, 80, 45),
('191011400030', 'Amalia Oktavia Kusuma ', '6', 'B', 75, 75, 75, 100, 75),
('191011400056', 'Maulana Mohamad Nasirudin ', '7', 'B', 75, 50, 50, 75, 65),
('191011401468', 'Andriana', '8', 'B', 75, 100, 75, 75, 75),
('191011401864', 'Andika Saputra', '8', 'A', 75, 75, 75, 75, 75),
('191011402342', 'Dedi Radiani Rachman', '8', 'CK', 90, 95, 70, 75, 50),
('191011450382', 'Gilang Ermawan', '9', 'CK', 60, 80, 75, 95, 40),
('191011502134', 'Putra Pratama', '10', 'CK', 75, 75, 75, 75, 65),
('191021400018', 'Novika Eka Saputra', '6', 'B', 85, 100, 80, 75, 75),
('201010200504', 'Faiz Indiana Subagja', '5', 'A', 75, 75, 75, 75, 75),
('201010201033', 'CAFRICIA PUTRI PRATIKTO', '6', 'A', 85, 95, 90, 90, 85),
('201010201148', 'Hanin nurfadilah', '5', 'A', 100, 100, 100, 100, 100),
('201010201424', 'Muhammad Fahri Ardiansyah ', '6', 'A', 75, 75, 75, 75, 75),
('201011400338', 'Ryan Wisnu Saputra', '4', 'A', 70, 75, 70, 75, 75),
('201011401600', 'Ahmad Nur Kholis', '4', 'A', 80, 90, 65, 75, 75),
('201011450105', 'Faizal Maulana ', '4', 'B', 85, 95, 95, 100, 75),
('2016140615', 'Firli Nurdiansyah ', '12', 'CK', 35, 25, 30, 50, 25),
('211011400196', 'Muhammad Jabir Supriadi', '2', 'A', 70, 75, 90, 85, 90),
('211011400755', 'RIKO ABDI PRAYASA', '3', 'A', 80, 90, 100, 90, 90),
('211011400757', 'Marco Melandri ', '3', 'A', 75, 75, 75, 75, 75),
('211011400762', 'Frida Apriliani', '3', 'A', 85, 75, 85, 85, 75),
('211011400794', 'Maria Fransisco Hesthi Arya A.B.M', '3', 'A', 90, 100, 100, 100, 100),
('211011401493', 'Yusri Indrawan M. Nur', '2', 'A', 80, 100, 80, 100, 55),
('211011401509', 'Danny Toharuddin Ali', '4', 'A', 75, 75, 75, 75, 75),
('211011401865', 'Muhammad Imamuddin Sri Hartono', '3', 'A', 95, 95, 95, 90, 80),
('211011402117', 'Muhammad Malik Khalil', '3', 'A', 90, 95, 90, 100, 85),
('211011450560', 'VIALLY PUTRA PRIYANTO ', '2', 'A', 80, 75, 60, 75, 75);

-- --------------------------------------------------------

--
-- Table structure for table `tb_useraccount`
--

CREATE TABLE `tb_useraccount` (
  `id_user` int(3) NOT NULL,
  `Username` varchar(35) NOT NULL,
  `Password` varchar(35) NOT NULL,
  `user_nidn` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_useraccount`
--

INSERT INTO `tb_useraccount` (`id_user`, `Username`, `Password`, `user_nidn`) VALUES
(1, 'Pejuang', '121200', '181011400811'),
(2, 'Evo', '123456789', '181011400812'),
(3, 'Pop', '121200', '13123412');

-- --------------------------------------------------------

--
-- Stand-in structure for view `tb_viewproses`
-- (See below for the actual view)
--
CREATE TABLE `tb_viewproses` (
`nim` varchar(25)
,`nama` varchar(35)
,`hasil_penilaianfam` double
,`keputusan` varchar(25)
);

-- --------------------------------------------------------

--
-- Structure for view `tb_viewproses`
--
DROP TABLE IF EXISTS `tb_viewproses`;

CREATE ALGORITHM=UNDEFINED DEFINER=`Qifli`@`%` SQL SECURITY DEFINER VIEW `tb_viewproses`  AS SELECT `tb_penialaian_mahasiswa`.`nim` AS `nim`, `tb_penialaian_mahasiswa`.`nama` AS `nama`, `tb_hasilpenilaian_fam`.`hasil_penilaianfam` AS `hasil_penilaianfam`, `tb_hasilpenilaian_fam`.`keputusan` AS `keputusan` FROM (`tb_penialaian_mahasiswa` join `tb_hasilpenilaian_fam`) WHERE `tb_penialaian_mahasiswa`.`nim` = `tb_hasilpenilaian_fam`.`penilai_nim``penilai_nim`  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_detail_account`
--
ALTER TABLE `tb_detail_account`
  ADD PRIMARY KEY (`nidn`),
  ADD UNIQUE KEY `NIDN` (`nidn`);

--
-- Indexes for table `tb_hasilpenilaian_fam`
--
ALTER TABLE `tb_hasilpenilaian_fam`
  ADD PRIMARY KEY (`id_hasilpenilaian`),
  ADD UNIQUE KEY `UNIQUE` (`id_hasilpenilaian`),
  ADD KEY `FOREIGN KEY` (`penilai_nim`);

--
-- Indexes for table `tb_penialaian_mahasiswa`
--
ALTER TABLE `tb_penialaian_mahasiswa`
  ADD PRIMARY KEY (`nim`),
  ADD UNIQUE KEY `UNIQUE` (`nim`) USING BTREE;

--
-- Indexes for table `tb_useraccount`
--
ALTER TABLE `tb_useraccount`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `id_user` (`id_user`),
  ADD KEY `FOREIGN KEY` (`user_nidn`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_hasilpenilaian_fam`
--
ALTER TABLE `tb_hasilpenilaian_fam`
  MODIFY `id_hasilpenilaian` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `tb_useraccount`
--
ALTER TABLE `tb_useraccount`
  MODIFY `id_user` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_hasilpenilaian_fam`
--
ALTER TABLE `tb_hasilpenilaian_fam`
  ADD CONSTRAINT `tb_hasilpenilaian_fam_ibfk_1` FOREIGN KEY (`penilai_nim`) REFERENCES `tb_penialaian_mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_useraccount`
--
ALTER TABLE `tb_useraccount`
  ADD CONSTRAINT `FOREIGN KEY` FOREIGN KEY (`user_nidn`) REFERENCES `tb_detail_account` (`nidn`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
