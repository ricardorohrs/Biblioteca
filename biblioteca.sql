SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `emprestimos` (
  `id_emprestimo` int(11) NOT NULL,
  `livro` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `nome` varchar(75) NOT NULL,
  `retirado` date DEFAULT NULL,
  `devolucao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `emprestimos` (`id_emprestimo`, `livro`, `usuario`, `nome`, `retirado`, `devolucao`) VALUES
(94, 26, 'ricardo', 'Teste', '2021-01-04', '2021-01-11'),
(95, 37, 'ricardo', 'Editar', '2021-01-04', '2021-01-11');

CREATE TABLE `livros` (
  `ID` int(10) NOT NULL,
  `ISBN` int(10) NOT NULL,
  `autor` varchar(75) NOT NULL,
  `edicao` int(10) NOT NULL,
  `editora` varchar(55) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `ano` year(4) NOT NULL,
  `reservado` int(11) NOT NULL,
  `emprestado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `livros` (`ID`, `ISBN`, `autor`, `edicao`, `editora`, `nome`, `ano`, `reservado`, `emprestado`) VALUES
(26, 123456, 'Teste', 1, 'Teste', 'Teste', 2020, 0, 1),
(27, 111111, 'J.K. Rowling', 1, 'Rocco', 'Harry Potter e a Pedra Filosofal', 1997, 0, 0),
(28, 222222, 'J.K. Rowling', 2, 'Rocco', 'Harry Potter e a Câmara Secreta', 1998, 0, 0),
(29, 333333, 'J.K. Rowling', 3, 'Rocco', 'Harry Potter e o Prisioneiro de Azkaban', 1999, 0, 0),
(30, 444444, 'J.K. Rowling', 4, 'Rocco', 'Harry Potter e o Cálice de Fogo', 2000, 0, 0),
(31, 555555, 'J.K. Rowling', 5, 'Rocco', 'Harry Potter e a Ordem da Fênix', 2001, 0, 0),
(32, 666666, 'J.K. Rowling', 6, 'Rocco', 'Harry Potter e o Enigma do Príncipe', 2002, 0, 0),
(33, 777777, 'J.K. Rowling', 7, 'Rocco', 'Harry Potter e as Relíquias da Morte', 2003, 0, 0),
(34, 888888, 'Érico Veríssimo', 1, 'Globo', 'O Tempo e o Vento', 1962, 1, 0),
(35, 999999, 'Waldemar Celes', 1, 'Elsevier', 'Introdução a Estrutura de Dados', 2004, 1, 0),
(36, 101010, 'Robert W. Sebesta', 11, 'Grupo A', 'Conceitos de Linguagens de Programação', 2018, 1, 0),
(37, 0, 'Excluir', 0, 'Teste', 'Editar', 2021, 0, 1);

CREATE TABLE `reservas` (
  `id_reserva` int(11) NOT NULL,
  `id_livro` int(11) NOT NULL DEFAULT 0,
  `login` varchar(50) NOT NULL DEFAULT '0',
  `nome` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `reservas` (`id_reserva`, `id_livro`, `login`, `nome`) VALUES
(167, 37, 'ricardo', 'Editar'),
(168, 36, 'aluno', 'Conceitos de Linguagens de Programação'),
(169, 35, 'aluno', 'Introdução a Estrutura de Dados'),
(170, 34, 'aluno', 'O Tempo e o Vento');

CREATE TABLE `users` (
  `login` varchar(55) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `cargo` tinyint(4) NOT NULL,
  `nome` varchar(55) NOT NULL,
  `reservas` tinyint(1) NOT NULL DEFAULT 0,
  `emprestimos` smallint(6) NOT NULL DEFAULT 0,
  `multa` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `users` (`login`, `senha`, `cargo`, `nome`, `reservas`, `emprestimos`, `multa`) VALUES
('aluno', '1234', 1, 'aluno', 3, 0, 0),
('piveta', '1234', 2, 'piveta', 0, 0, 0),
('professor', '1234', 2, 'professor', 0, 0, 0),
('ricardo', '123', 1, 'ricardo', 0, 2, 0);


ALTER TABLE `emprestimos`
  ADD PRIMARY KEY (`id_emprestimo`);

ALTER TABLE `livros`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`login`);


ALTER TABLE `emprestimos`
  MODIFY `id_emprestimo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

ALTER TABLE `livros`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

ALTER TABLE `reservas`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=171;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
