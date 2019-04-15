-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-04-2019 a las 19:10:08
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `control_gastos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diesel`
--

CREATE TABLE `diesel` (
  `diesel_id` int(10) NOT NULL,
  `fecha` varchar(10) NOT NULL,
  `litros` double NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `diesel`
--

INSERT INTO `diesel` (`diesel_id`, `fecha`, `litros`, `precio`) VALUES
(141, '3/12/2019', 12, 123),
(142, '3/13/2019', 13, 122),
(143, '3/14/2019', 14, 1000);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `diesel_suma`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `diesel_suma` (
`total` double
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reparaciones`
--

CREATE TABLE `reparaciones` (
  `reparacion_id` int(10) NOT NULL,
  `fecha_rep` varchar(10) NOT NULL,
  `observaciones` varchar(200) NOT NULL,
  `costo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reparaciones`
--

INSERT INTO `reparaciones` (`reparacion_id`, `fecha_rep`, `observaciones`, `costo`) VALUES
(3, '3/12/2019', 'bujias', 150),
(5, '3/13/2019', 'amortiguadores', 500),
(6, '3/14/2019', 'flecha', 1000),
(7, '3/15/2019', 'vidrio', 100),
(8, '3/16/2019', 'llantas', 2000),
(9, '3/20/2019', 'espejos', 150),
(10, '3/25/2019', 'agua', 100);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `reparacion_suma`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `reparacion_suma` (
`total` double
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultados`
--

CREATE TABLE `resultados` (
  `resultado_id` int(11) NOT NULL,
  `suma_reparaciones` double NOT NULL,
  `suma_diesel` double NOT NULL,
  `suma_viajes` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suma`
--

CREATE TABLE `suma` (
  `suma_id` int(11) NOT NULL,
  `suma_reparaciones` double NOT NULL,
  `suma_diesel` double NOT NULL,
  `suma_viajes` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `suma`
--

INSERT INTO `suma` (`suma_id`, `suma_reparaciones`, `suma_diesel`, `suma_viajes`) VALUES
(1, 4000, 1245, 6000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajes`
--

CREATE TABLE `viajes` (
  `viaje_id` int(10) NOT NULL,
  `fecha_viaje` varchar(10) NOT NULL,
  `millas` double NOT NULL,
  `pago` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `viajes`
--

INSERT INTO `viajes` (`viaje_id`, `fecha_viaje`, `millas`, `pago`) VALUES
(6, '3/12/2019', 1000, 2000),
(7, '3/13/2019', 2000, 4000);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `viaje_suma`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `viaje_suma` (
`total` double
);

-- --------------------------------------------------------

--
-- Estructura para la vista `diesel_suma`
--
DROP TABLE IF EXISTS `diesel_suma`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `diesel_suma`  AS  select sum(`diesel`.`precio`) AS `total` from `diesel` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `reparacion_suma`
--
DROP TABLE IF EXISTS `reparacion_suma`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `reparacion_suma`  AS  select sum(`reparaciones`.`costo`) AS `total` from `reparaciones` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `viaje_suma`
--
DROP TABLE IF EXISTS `viaje_suma`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viaje_suma`  AS  select sum(`viajes`.`pago`) AS `total` from `viajes` ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `diesel`
--
ALTER TABLE `diesel`
  ADD PRIMARY KEY (`diesel_id`),
  ADD UNIQUE KEY `fecha` (`fecha`);

--
-- Indices de la tabla `reparaciones`
--
ALTER TABLE `reparaciones`
  ADD PRIMARY KEY (`reparacion_id`);

--
-- Indices de la tabla `resultados`
--
ALTER TABLE `resultados`
  ADD PRIMARY KEY (`resultado_id`);

--
-- Indices de la tabla `suma`
--
ALTER TABLE `suma`
  ADD PRIMARY KEY (`suma_id`);

--
-- Indices de la tabla `viajes`
--
ALTER TABLE `viajes`
  ADD PRIMARY KEY (`viaje_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `diesel`
--
ALTER TABLE `diesel`
  MODIFY `diesel_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- AUTO_INCREMENT de la tabla `reparaciones`
--
ALTER TABLE `reparaciones`
  MODIFY `reparacion_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `resultados`
--
ALTER TABLE `resultados`
  MODIFY `resultado_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `suma`
--
ALTER TABLE `suma`
  MODIFY `suma_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `viajes`
--
ALTER TABLE `viajes`
  MODIFY `viaje_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
