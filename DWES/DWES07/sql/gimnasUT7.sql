-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-03-2022 a las 18:18:03
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
  `id` int(11) NOT NULL,
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

INSERT INTO `clients` (`id`, `username`, `nom`, `llinatges`, `email`, `telefon`, `password`) VALUES
(1, 'amartinez', 'Angel', 'Martinez', 'amartinez@example.com', '666777888', 'pbkdf2:sha256:260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc25'),
(2, 'sikari', 'Shinji', 'Ikari', 'sikari@example.com', '666222888', 'pbkdf2:sha256:260000$10eZ4touJOL9A0Tl$001ca267fd695e4edaaf4412db0dfe70f4a05b323694045d2c30335614d992c7'),
(3, 'alangley', 'Asuka', 'Langley', 'alangley@example.com', '666333888', 'pbkdf2:sha256:260000$A9FpUwmSPxeZ552p$7037ca1fbbacb6bbb562984a542581c8ce8b641fc21de2fe120fa5b91ea4e2e1'),
(4, 'hkensjin', 'Himura', 'Kenshin', 'hkensjin@example.com', '666444888', 'pbkdf2 :sha256 :260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc'),
(5, 'SaulGman', 'pepe', 'Goodman', '', '999777555', 'pbkdf2:sha256:260000$92p0bfnAB1JzNLzc$070f91a33e5c30b6f2c2870b6f3ceba2065603577fdb46152994a9a3061ba226');

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
('2022-02-01 16:00:00', 1, 1),
('2022-02-02 15:00:00', 1, 1),
('2022-02-03 16:00:00', 1, 1),
('2022-02-03 16:00:00', 2, 1),
('2022-01-10 19:00:00', 1, 2),
('2022-01-20 18:00:00', 2, 2),
('2022-02-02 18:00:00', 1, 3),
('2022-03-21 17:00:00', 2, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
  ADD CONSTRAINT `reserves_ibfk_1` FOREIGN KEY (`idclient`) REFERENCES `clients` (`id`),
  ADD CONSTRAINT `reserves_ibfk_2` FOREIGN KEY (`idpista`) REFERENCES `pistes` (`idpista`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
