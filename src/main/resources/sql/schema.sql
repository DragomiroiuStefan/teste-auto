drop table if exists user_test_answers;
drop table if exists user_tests;
drop table if exists category_questions;
drop table if exists questions;
drop table if exists category;
drop table if exists users;

CREATE TABLE users (
	user_id serial PRIMARY KEY,
	first_name VARCHAR ( 50 ) NOT NULL,
	last_name VARCHAR ( 50 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	created_on TIMESTAMP NOT NULL,
    last_login TIMESTAMP 
);

CREATE TABLE category (
	category_id VARCHAR ( 2 ) PRIMARY KEY,
	test_time VARCHAR ( 5 ) not null,
	no_of_questions smallint not null
);

CREATE TABLE questions (
	question_id serial PRIMARY KEY,
	text VARCHAR ( 255 ) UNIQUE NOT NULL,
	figure BYTEA,
	answers VARCHAR ( 255 ) [] NOT NULL,
	correct_answers smallint [] NOT NULL
);

CREATE TABLE category_questions (
	category_id varchar ( 2 ) references category(category_id), 
	question_id int references questions(question_id),
	primary key (category_id, question_id)
);

CREATE TABLE user_tests (
	user_test_id serial PRIMARY KEY,
	user_id int references users(user_id), 
	category_id varchar ( 2 ) references category(category_id), 
	end_time timestamp NOT NULL,
	time_spent VARCHAR ( 5 ) NOT NULL
);

CREATE TABLE user_test_answers (
	user_test_id int references user_tests(user_test_id),
	question_id int references questions(question_id),
	user_answers smallint [],
	primary key (user_test_id, question_id)
);

insert into users values
(default, 'Stefan', 'Dragomiroiu', 'stefandragomiroiu@mail.com', 'stefan', NOW()),
(default, 'test', 'user', 'user@mail.com', 'user', NOW());

insert into category values
('A', '20:00', 20),
('AM', '20:00', 20),
('A1', '20:00', 20),
('A2', '20:00', 20),
('B', '30:00', 26),
('C', '30:00', 26);


insert into questions  values
(default, 'Viteza se va reduce obligatoriu:', null,  
array['la trecerile pentru pietoni;', 'la semnalul poliţistului de frontieră;', 'la întâlnirea indicatorului „Cedează trecerea”.'],
array[2,3]),
(default, 'Cum veţi semnaliza faptul că autovehiculul cu care circulaţi a rămas în pană pe partea carosabilă?', null,  
array['prin folosirea luminilor de poziţie;', 'prin instalarea triunghiurilor reflectorizante şi prin folosirea luminilor de avarie;', 'prin purtarea vestei reflectorizante.'],
array[1,2]),
(default, 'În care dintre situaţii consumul de carburant al unui motor creşte?', null,  
array['atunci când motorul nu atinge temperatura de funcţionare;', 'atunci când fumul de eşapament este de culoare neagră;', 'atunci când motorul funcţionează cu întreruperi.'],
array[1,2,3]),
(default, 'Pentru care dintre situaţiile de mai jos se dispune reţinerea permisului de conducere?', null,  
array['pentru neacordarea priorităţii de trecere;', 'permisul de conducere este deteriorat;', 'permisul de conducere prezintă modificări, ştersături sau adăugiri.'],
array[1,2,3]),
(default, 'Nu se poate circula cu un autoturism dacă:', null,  
array['se depăşeşte masa maximă admisă, înscrisă în certificatul de înmatriculare;', 'anvelopele sunt de mărimi şi caracteristici diferite faţă de cele înscrise în certificatul de înmatriculare;', 'autovehiculul depăşeşte înălţimea înscrisă în certificatul de înmatriculare.'],
array[1,2]),
(default, 'Neeliberarea completă a frânei de staţionare determină:', null,  
array['zgomote în zona manetei frânei de mână;', 'un consum suplimentar de carburant;', 'încălzirea excesivă a butucilor roţilor din spate.'],
array[2,3]),
(default, 'În care dintre situaţii este interzisă schimbarea direcţiei de mers spre stânga?', null,  
array['când conducătorul de vehicul este încadrat pe banda din dreapta;', 'când drumul pe care urmează să se intre este semnalizat cu indicatorul „Circulaţia interzisă în ambele sensuri”;', 'când strada pe care urmează să se intre este semnalizată cu indicatorul „Drum fără ieşire”.'],
array[1,2]),
(default, 'În care dintre situaţii este permisă depăşirea?', null,  
array['în intersecţia semnalizată cu indicatoare de prioritate;', 'în intersecţia dirijată prin semnale luminoase;', 'în intersecţia dirijată prin semnale ale poliţistului.'],
array[1,2,3]),
(default, 'Ce categorii de vehicule au obligaţia de a folosi luminile de întâlnire pe timp de zi?', null,  
array['motocicletele şi mopedele;', 'autovehiculele care însoţesc coloane militare sau transportă grupuri de persoane;', 'autovehiculele care tractează alte vehicule sau care transportă produse periculoase.'],
array[1,2,3]),
(default, 'În care dintre situaţii sunteţi obligat să reduceţi viteza?', null,  
array['la trecerea la nivel cu calea ferată curentă cu bariere;', 'la trecerea la nivel cu calea ferată curentă fără bariere;', 'la trecerea la nivel cu calea ferată curentă industrială.'],
array[1]),
(default, 'Ce obligaţii vă revin în timpul conducerii autovehiculului cu privire la viteză?', null,  
array['să respectaţi viteza maximă admisă pe sectorul de drum pe care circulaţi, corespunzătoare categoriei din care face parte autovehiculul condus;', 'să vă conformaţi limitelor de viteză impuse prin mijloacele de semnalizare specifice;', 'să rulaţi pe cât posibil în treptele inferioare de viteză, pentru a nu polua mediul.'],
array[1,2]),
(default, 'Când este permisă depăşirea prin stânga a tramvaielor aflate în mers?', null,  
array['atunci când drumul public este cu sens unic;', 'când între şina de tramvai din dreapta şi marginea trotuarului nu există suficient spaţiu pentru depăşire;', 'în nicio situaţie.'],
array[1,2]),
(default, 'Ce condiţii tehnice trebuie să îndeplinească un autovehicul pentru a circula pe drumurile publice?', null,  
array['să nu depăşească masa totală maximă autorizată înscrisă în certificatul de înmatriculare;', 'să nu depăşească gabaritul înscris în certificatul de înmatriculare;', 'să fie înmatriculat şi să corespundă condiţiilor tehnice legale.'],
array[1,3]),
(default, 'Se aplică puncte de penalizare pentru:', null,  
array['nerespectarea semnificaţiei indicatorului „Oprire”, instalat la trecerea la nivel cu o cale ferată;', 'depăşirea cu 31-40 km/h a vitezei maxime admise pe sectorul de drum respectiv pentru categoria din care face parte autovehiculul condus;', 'depăşirea cu 10-20 km/h a vitezei maxime admise pe sectorul de drum respectiv pentru categoria din care face parte autovehiculul condus.'],
array[1,2,3]),
(default, 'Cu ce viteză sunteţi obligat să circulaţi pe drumurile publice din afara localităţilor, când partea carosabilă este acoperită cu piatră cubică umedă?', null,  
array['cu o viteză care să nu depăşească 50 km/h;', 'cu o viteză care să permită efectuarea oricărei manevre în condiţii de siguranţă;', 'cu o viteză care să nu depăşească limita maximă de 90 km/h.'],
array[1,2]);

insert into category_questions values
('B', 1),
('B', 2),
('B', 3),
('B', 4),
('B', 5),
('B', 6),
('B', 7),
('B', 8),
('B', 9),
('B', 10),
('B', 11),
('B', 12),
('B', 13),
('B', 14),
('B', 15),
('C', 1),
('C', 2),
('C', 3),
('C', 4),
('C', 5),
('C', 6),
('C', 7),
('C', 8),
('C', 9),
('C', 10),
('C', 11),
('C', 12),
('C', 13),
('C', 14),
('C', 15);

-- Get a test
select * from questions q
inner join category_questions cq on q.question_id =cq.question_id 
where cq.category_id = 'B'
order by random() limit 6;

-- Get a user test list
select 
ut.user_test_id,
ut.category_id,
ut.end_time,
ut.time_spent, 
count (uta) as answers,
c.no_of_questions as total 
from user_tests ut
inner join user_test_answers uta on uta.user_test_id = ut.user_test_id
inner join "category" c on c.category_id  = ut.category_id 
where user_id = 2
group by ut.user_test_id, c.category_id;

-- Get a user saved test
select q.question_id, q."text", q.answers, q.correct_answers, uta.user_answers 
from user_test_answers uta
inner join questions q on q.question_id = uta.question_id 
where uta.user_test_id = 1;








