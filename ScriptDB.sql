-- 
-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-07-2017 a las 00:39:58
-- Versión del servidor: 10.1.22-MariaDB
-- Versión de PHP: 7.1.4
--

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `almaccenture`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja`
--

CREATE TABLE `caja` (
  `id_caja` int(11) NOT NULL,
  `nombre_caja` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `caja`
--

INSERT INTO `caja` (`id_caja`, `nombre_caja`) VALUES
(1, 'Caja 1'),
(2, 'Caja 2'),
(3, 'Caja 3'),
(4, 'Caja 4'),
(5, 'Caja 5'),
(7, 'Caja 6');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id_categoria`, `nombre_categoria`) VALUES
(1, 'Aseo'),
(2, 'Tecnologia'),
(3, 'Hogar'),
(4, 'Moda');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `id_detalle` int(11) NOT NULL,
  `id_producto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `id_venta` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `valor_unitario` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`id_detalle`, `id_producto`, `id_venta`, `cantidad`, `valor_unitario`) VALUES
(1, 'def456', 1, 1, 2500000),
(2, 'abc123', 1, 2, 6000),
(3, 'wxy045', 2, 1, 850000),
(4, 'rst468', 2, 3, 13500),
(5, 'lmr180', 3, 1, 9900),
(6, 'iuo309', 3, 1, 149900),
(7, 'arm800', 4, 1, 48900),
(8, 'iuo309', 4, 2, 149900),
(9, ' bne667', 5, 1, 180000),
(10, 'jpq789', 5, 4, 50000),
(11, 'def456', 1, 3, 2500000),
(13, 'wxy045', 3, 20, 12333);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `nombre_producto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `estado_producto` tinyint(1) NOT NULL,
  `cantidad_producto` int(11) NOT NULL,
  `precio_producto` float NOT NULL,
  `descripcion_producto` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `fecha_modificacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre_producto`, `estado_producto`, `cantidad_producto`, `precio_producto`, `descripcion_producto`, `id_categoria`, `fecha_modificacion`) VALUES
(' bne667', 'escaparate', 1, 21, 180000, 'Escaparate con espejo y doble puerta, color roble', 3, '2017-07-18'),
('abc123', 'jabon de baño', 1, 80, 3000, 'jabon rexona hierbabuena ', 1, '2017-07-12'),
('arm800', 'sabana para cama', 1, 26, 48900, 'COnjunto de sabanas para cama doble', 3, '2017-07-19'),
('def456', 'smart TV ', 1, 20, 2500000, 'smart tv samsung 50 pulgadas full HD', 2, '2017-07-09'),
('hkm240', 'blusa dama', 1, 100, 35500, 'Blusa de boleros rosada', 4, '2017-07-02'),
('iuo309', 'zapatos', 1, 32, 149900, 'Zapatos para hombre marca Nike', 4, '2017-07-28'),
('jpq789', 'silla', 1, 45, 50000, 'silla marca estra sencilla', 3, '2017-07-01'),
('lmr180', 'detergente', 1, 75, 9900, 'Detergente liquido ariel', 1, '2017-07-27'),
('mmr732', 'Producto', 1, 20, 1800, 'Producto Prueba', 3, '2017-07-19'),
('mmr771', 'Jabon', 1, 20, 42000, 'JAbon de baño', 1, '2017-07-19'),
('mno596', 'chaqueta hombre', 1, 90, 150000, 'chaqueta cuero hombre negra', 4, '2017-07-05'),
('rpg001', 'nevecon', 1, 15, 3000000, 'Nevecon Haceb de 2 puertas no frost 50 kg', 2, '2017-07-04'),
('rst468', 'lava platos', 1, 48, 4500, 'Lava platos liquido axion', 1, '2017-07-29'),
('wxy045', 'celular', 1, 18, 850000, 'Smartphone lg G4', 2, '2017-08-30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `fecha_venta` date NOT NULL,
  `id_caja` int(11) NOT NULL,
  `estado_venta` tinyint(1) NOT NULL DEFAULT '1',
  `total_venta` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id_venta`, `fecha_venta`, `id_caja`, `estado_venta`, `total_venta`) VALUES
(1, '2017-07-10', 1, 1, 2500000),
(2, '2017-07-02', 2, 1, 850000),
(3, '2017-07-01', 3, 1, 9900),
(4, '2017-07-03', 4, 1, 149900),
(5, '2017-07-11', 5, 1, 180000),
(22, '2017-07-19', 2, 1, 3782350);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `caja`
--
ALTER TABLE `caja`
  ADD PRIMARY KEY (`id_caja`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_venta` (`id_venta`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `id_caja` (`id_caja`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `caja`
--
ALTER TABLE `caja`
  MODIFY `id_caja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON UPDATE CASCADE ON DELETE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`) ON DELETE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_caja`) REFERENCES `caja` (`id_caja`) ON UPDATE CASCADE ON DELETE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
