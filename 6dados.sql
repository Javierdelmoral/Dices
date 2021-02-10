-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 10-02-2021 a las 10:38:10
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 7.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dados`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `game`
--

CREATE TABLE `game` (
  `id_game` int(25) NOT NULL,
  `id_player` int(25) NOT NULL,
  `value_Dice1` int(11) NOT NULL,
  `value_Dice2` int(11) NOT NULL,
  `value_Dice3` int(11) NOT NULL,
  `value_Dice4` int(11) NOT NULL,
  `value_Dice5` int(11) NOT NULL,
  `value_Dice6` int(11) NOT NULL,
  `won` tinyint(1) NOT NULL,
  `player_id_player` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `game`
--

INSERT INTO `game` (`id_game`, `id_player`, `value_Dice1`, `value_Dice2`, `value_Dice3`, `value_Dice4`, `value_Dice5`, `value_Dice6`, `won`, `player_id_player`) VALUES
(209, 205, 2, 5, 3, 4, 5, 6, 1, 205),
(210, 205, 6, 4, 3, 4, 5, 6, 0, 205),
(211, 205, 1, 2, 3, 4, 5, 6, 0, 205),
(217, 206, 4, 3, 3, 4, 5, 6, 1, 206),
(218, 206, 3, 1, 3, 4, 5, 6, 0, 206),
(245, 204, 3, 5, 3, 4, 5, 6, 0, 204),
(246, 204, 3, 2, 3, 4, 5, 6, 0, 204);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(255);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequences`
--

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequences`
--

INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES
('default', 142);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `player`
--

CREATE TABLE `player` (
  `id_player` int(25) NOT NULL,
  `name` varchar(255) NOT NULL,
  `register_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_dice_rolls` int(11) NOT NULL,
  `games_won` int(11) NOT NULL,
  `success_rate` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `player`
--

INSERT INTO `player` (`id_player`, `name`, `register_date`, `total_dice_rolls`, `games_won`, `success_rate`) VALUES
(204, 'javi', '2020-04-28 18:41:17', 2, 0, 0),
(205, 'marta', '2020-04-28 18:21:28', 3, 1, 33.33),
(206, 'anonymous', '2020-04-28 18:23:30', 2, 1, 50),
(247, 'jacob', '2021-02-09 19:05:27', 0, 0, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id_game`),
  ADD KEY `id_player` (`id_player`),
  ADD KEY `FKss1l3mwkp44i09b7j2rolnyp5` (`player_id_player`);

--
-- Indices de la tabla `hibernate_sequences`
--
ALTER TABLE `hibernate_sequences`
  ADD PRIMARY KEY (`sequence_name`);

--
-- Indices de la tabla `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`id_player`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `game`
--
ALTER TABLE `game`
  MODIFY `id_game` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=255;

--
-- AUTO_INCREMENT de la tabla `player`
--
ALTER TABLE `player`
  MODIFY `id_player` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=248;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `FKss1l3mwkp44i09b7j2rolnyp5` FOREIGN KEY (`player_id_player`) REFERENCES `player` (`id_player`) ON DELETE CASCADE,
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`id_player`) REFERENCES `player` (`id_player`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
