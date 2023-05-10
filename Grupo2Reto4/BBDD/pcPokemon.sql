-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 172.17.0.1
-- Tiempo de generación: 10-05-2023 a las 11:16:52
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.1.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pcPokemon`
--
CREATE DATABASE IF NOT EXISTS `pcpokemon` DEFAULT CHARACTER SET utf8mb4;
USE `pcpokemon`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE `Usuario` (
  `user_login` varchar(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_pass` varchar(30) NOT NULL,
  `user_team` int DEFAULT '1',
  `baneado` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`user_login`, `user_name`, `user_pass`, `user_team`, `baneado`) VALUES
('clemen', 'Clemen', '123', 1, 1);

--
-- Disparadores `Usuario`
--
DELIMITER $$
CREATE TRIGGER `AltaUsuario` AFTER INSERT ON `Usuario` FOR EACH ROW insert into PC (user_login) values (new.user_login)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `borrar_equipo` BEFORE DELETE ON `Usuario` FOR EACH ROW delete from Equipo where user_login = old.user_login
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `borrar_pc` BEFORE DELETE ON `Usuario` FOR EACH ROW delete from PC where user_login = old.user_login
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`user_login`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
