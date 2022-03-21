-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-03-2022 a las 12:42:22
-- Versión del servidor: 10.4.6-MariaDB-log
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gimnas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clients`
--

CREATE TABLE `clients` (
  `idclient` int(11) NOT NULL,
  `username` varchar(15) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `llinatges` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefon` varchar(12) NOT NULL,
  `password` char(102) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clients`
--

INSERT INTO `clients` (`idclient`, `username`, `nom`, `llinatges`, `email`, `telefon`, `password`) VALUES
(1, 'amartinez', 'Angel', 'Martinez', 'amartinez@example.com', '666777888', 'pbkdf2:sha256:260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc25'),
(2, 'sikari', 'Shinji', 'Ikari', 'sikari@example.com', '666222888', 'pbkdf2:sha256:260000$10eZ4touJOL9A0Tl$001ca267fd695e4edaaf4412db0dfe70f4a05b323694045d2c30335614d992c7'),
(3, 'alangley', 'Asuka', 'Langley', 'alangley@example.com', '666333888', 'pbkdf2:sha256:260000$A9FpUwmSPxeZ552p$7037ca1fbbacb6bbb562984a542581c8ce8b641fc21de2fe120fa5b91ea4e2e1'),
(4, 'hkensjin', 'Himura', 'Kenshin', 'hkensjin@example.com', '666444888', 'pbkdf2 :sha256 :260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistes`
--

CREATE TABLE `pistes` (
  `idpista` int(11) NOT NULL,
  `tipo` enum('Coberta','Exterior') NOT NULL,
  `preu` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pistes`
--

INSERT INTO `pistes` (`idpista`, `tipo`, `preu`) VALUES
(1, 'Coberta', 12),
(2, 'Exterior', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserves`
--

CREATE TABLE `reserves` (
  `data` datetime NOT NULL,
  `idpista` int(11) NOT NULL,
  `idclient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `reserves`
--

INSERT INTO `reserves` (`data`, `idpista`, `idclient`) VALUES
('2022-02-02 15:00:00', 1, 1),
('2022-02-03 16:00:00', 2, 1),
('2022-01-10 19:00:00', 1, 2),
('2022-01-20 18:00:00', 2, 2),
('2022-02-02 18:00:00', 1, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`idclient`);

--
-- Indices de la tabla `pistes`
--
ALTER TABLE `pistes`
  ADD PRIMARY KEY (`idpista`);

--
-- Indices de la tabla `reserves`
--
ALTER TABLE `reserves`
  ADD PRIMARY KEY (`data`,`idpista`),
  ADD KEY `r_idclient` (`idclient`),
  ADD KEY `r_idpista` (`idpista`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clients`
--
ALTER TABLE `clients`
  MODIFY `idclient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pistes`
--
ALTER TABLE `pistes`
  MODIFY `idpista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reserves`
--
ALTER TABLE `reserves`
  ADD CONSTRAINT `reserves_ibfk_1` FOREIGN KEY (`idclient`) REFERENCES `clients` (`idclient`),
  ADD CONSTRAINT `reserves_ibfk_2` FOREIGN KEY (`idpista`) REFERENCES `pistes` (`idpista`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
