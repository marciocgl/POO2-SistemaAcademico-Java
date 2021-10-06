
CREATE TABLE cursos (
	
	idCurso serial NOT NULL,
	nomeCurso varchar(50) NOT NULL,
	siglaCurso varchar (10) NOT NULL,
	duracao varchar(30) NOT NULL,
	turno varchar (30) NOT NULL,
	
	CONSTRAINT pk_cursos
      PRIMARY KEY(idCurso)

);

INSERT INTO cursos(nomeCurso, siglaCurso, duracao, turno) VALUES('Sistemas de Informação','SI','4 Anos', 'Integral');
INSERT INTO cursos(nomeCurso, siglaCurso, duracao, turno) VALUES('Matemática','MAT','4 Anos', 'Noturno');
INSERT INTO cursos(nomeCurso, siglaCurso, duracao, turno) VALUES('Informática','TI','2 Anos', 'Noturno');
INSERT INTO cursos(nomeCurso, siglaCurso, duracao, turno) VALUES('Mineração','MIN','2 Anos', 'Noturno');
INSERT INTO cursos(nomeCurso, siglaCurso, duracao, turno) VALUES('Engenharia Mecânica','ENGMC','4 Anos', 'Integral');



CREATE TABLE turmas (
	
	idTurma serial NOT NULL,
	nomeTurma varchar(50) NOT NULL,
	nomeOrientador varchar(50) NOT NULL,
	idCurso  serial NOT NULL,
	
	CONSTRAINT pk_turmas
      PRIMARY KEY(idTurma),
	CONSTRAINT fk_turmas_cursos
	  FOREIGN KEY (idCurso)
		REFERENCES cursos(idCurso)
	 

);

	 INSERT INTO turmas(nomeTurma, nomeOrientador ) VALUES('POO2','Vargas');
	 INSERT INTO turmas(nomeTurma, nomeOrientador) VALUES('Calculo','Elen');
	 INSERT INTO turmas(nomeTurma, nomeOrientador) VALUES('Metodologia de Pesquisa','Lauren');
	 INSERT INTO turmas(nomeTurma, nomeOrientador) VALUES('Sociologia','Rosana');
	 INSERT INTO turmas(nomeTurma, nomeOrientador) VALUES('Administração Financeira','Lucas');
	



CREATE TABLE alunos(

   idAlu serial  NOT NULL,
   nomeAlu varchar(50) NOT NULL,
   matricula varchar(50) NOT NULL,
   dataNascimento date NOT NULL,
   idCurso  serial NOT NULL,
   idTurma serial NOT NULL,
   
   
   CONSTRAINT pk_alunos
      PRIMARY KEY(idAlu),
	CONSTRAINT fk_alunos_cursos
		FOREIGN KEY (idCurso)
		REFERENCES cursos (idCurso),
	CONSTRAINT fk_alunos_turmas
		FOREIGN KEY (idTurma)
		REFERENCES turmas(idTurma)

);

	INSERT INTO alunos(nomeAlu, matricula, dataNascimento) VALUES ('Marcio Cesar','20181SI027','01/03/1995');
	INSERT INTO alunos(nomeAlu, matricula, dataNascimento) VALUES ('Juca Pet','20161MAT025','07/08/1999');
	INSERT INTO alunos(nomeAlu, matricula, dataNascimento) VALUES ('Joao Rosa','20191TI005','07/08/2004');
	INSERT INTO alunos(nomeAlu, matricula, dataNascimento) VALUES ('Ronaldinho Silva','20181MIN025','07/08/1994');
	INSERT INTO alunos(nomeAlu, matricula, dataNascimento) VALUES ('Ronilson Ror','20161ENGMC025','07/08/1992');
	

CREATE TABLE notas(
	
   idNotas serial NOT NULL,
   idCurso serial NOT NULL,
   idTurma serial NOT NULL,
   idAlu   serial NOT NULL,
   prova1 float NOT NULL,
   prova2 float NOT NULL,
   trabalho float NOT NULL,
   media float NOT NULL,
   
	CONSTRAINT pk_notas
		PRIMARY KEY (idNotas),
	CONSTRAINT fk_notas_cursos
		FOREIGN KEY (idCurso)
			REFERENCES cursos (idCurso),
	CONSTRAINT fk_notas_turmas
		FOREIGN KEY (idTurma)
			REFERENCES turmas (idTurma),
	CONSTRAINT fk_notas_alunos
		FOREIGN KEY (idAlu)
			REFERENCES alunos (idAlu)
  
	
);

	
	INSERT INTO notas(prova1, prova2, trabalho,  media) VALUES ( 10, 10, 10, 10);
	INSERT INTO notas(prova1, prova2, trabalho,  media) VALUES ( 5, 5, 5, 5);
	INSERT INTO notas(prova1, prova2, trabalho,  media) VALUES ( 10, 5, 9, 8);
	INSERT INTO notas(prova1, prova2, trabalho,  media) VALUES ( 6, 8, 10, 8);
	INSERT INTO notas(prova1, prova2, trabalho,  media) VALUES ( 6, 5, 7, 6);

/*
	select alunos.nomeAlu, notas.prova1, notas.prova2, notas.trabalho, notas.media
	from alunos alunos, notas notas
	where alunos.idAlu = notas.idAlu
	
	select alunos.nomeAlu, cursos.nomeCurso, turmas.nomeTurma, notas.prova1, notas.prova2, notas.trabalho, notas.media
	from alunos alunos, notas notas, turmas turmas, cursos cursos
	where alunos.idAlu = notas.idAlu
		and alunos.idAlu = cursos.idCurso
		and alunos.idAlu = turmas.idTurma
*/


