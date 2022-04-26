-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 12:02 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payment`
--

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payID` int(5) NOT NULL,
  `AccNumber` varchar(20) NOT NULL,
  `totalAmount` double(10,2) NOT NULL,
  `payDate` date NOT NULL,
  `cardType` varchar(20) NOT NULL,
  `cardNumber` varchar(20) NOT NULL,
  `cvn` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payID`, `AccNumber`, `totalAmount`, `payDate`, `cardType`, `cardNumber`, `cvn`) VALUES
(1, 'EG1010', 5600.00, '2022-04-21', 'VISA', '2343236545782321', 450),
(2, 'EG1564', 7500.00, '2022-04-20', 'MASTER', '4565231234564323', 875),
(3, 'EG2590', 5000.00, '2022-04-19', 'AMEX', '2323487656453478', 450),
(4, 'EG2341', 4500.00, '2022-04-16', 'VISA', '6743234567123432', 765),
(5, 'Updated', 5000.00, '2022-04-15', 'Updated', '5645341189765478', 832);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
