CREATE DATABASE AnimalHierarchy;

USE AnimalHierarchy;

-- Таблица для общей информации о животных
CREATE TABLE Animal (
    animal_id INT PRIMARY KEY,
    name VARCHAR(50),
    species VARCHAR(50),
    birthdate DATE
);

-- Таблица для информации о домашних животных
CREATE TABLE Domestic_Animals (
    domestic_animal_id INT PRIMARY KEY,
    animal_id INT,
    FOREIGN KEY (animal_id) REFERENCES Animal(animal_id)
);

-- Таблицы для информации о домашних животных (Собаки, Кошки, Хомяки)
CREATE TABLE Dog (
    dog_id INT PRIMARY KEY,
    domestic_animal_id INT,
    breed VARCHAR(50),
    FOREIGN KEY (domestic_animal_id) REFERENCES Domestic_Animals(domestic_animal_id)
);

CREATE TABLE Cat (
    cat_id INT PRIMARY KEY,
    domestic_animal_id INT,
    color VARCHAR(50),
    FOREIGN KEY (domestic_animal_id) REFERENCES Domestic_Animals(domestic_animal_id)
);

CREATE TABLE Hamster (
    hamster_id INT PRIMARY KEY,
    domestic_animal_id INT,
    wheel_color VARCHAR(50),
    FOREIGN KEY (domestic_animal_id) REFERENCES Domestic_Animals(domestic_animal_id)
);

-- Таблицы для информации о вьючных животных (Лошади, Верблюды, Ослы)
CREATE TABLE Horse (
    horse_id INT PRIMARY KEY,
    animal_id INT,
    purpose VARCHAR(50),
    FOREIGN KEY (animal_id) REFERENCES Animal(animal_id)
);

CREATE TABLE Camel (
    camel_id INT PRIMARY KEY,
    animal_id INT,
    hump_count INT,
    FOREIGN KEY (animal_id) REFERENCES Animal(animal_id)
);

CREATE TABLE Donkey (
    donkey_id INT PRIMARY KEY,
    animal_id INT,
    carrying_capacity INT,
    FOREIGN KEY (animal_id) REFERENCES Animal(animal_id)
);

-- Вставка данных в таблицу Animal
INSERT INTO Animal (animal_id, name, species, birthdate) VALUES
(1, 'Fido', 'Dog', '2019-01-15'),
(2, 'Whiskers', 'Cat', '2020-03-10'),
(3, 'Fluffy', 'Hamster', '2021-05-20'),
(4, 'Shadowfax', 'Horse', '2018-02-08'),
(5, 'Humpy', 'Camel', '2017-11-25'),
(6, 'Eeyore', 'Donkey', '2019-09-12');

-- Вставка данных в таблицу Domestic_Animals
INSERT INTO Domestic_Animals (domestic_animal_id, animal_id) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Вставка данных в таблицы Dog, Cat, Hamster
INSERT INTO Dog (dog_id, domestic_animal_id, breed) VALUES
(1, 1, 'Golden Retriever'),
(2, 2, 'Persian'),
(3, 3, 'Syrian');

INSERT INTO Cat (cat_id, domestic_animal_id, color) VALUES
(1, 2, 'Black'),
(2, 3, 'White'),
(3, 1, 'Gray');

INSERT INTO Hamster (hamster_id, domestic_animal_id, wheel_color) VALUES
(1, 3, 'Blue'),
(2, 1, 'Red'),
(3, 2, 'Green');

-- Вставка данных в таблицы Horse, Camel, Donkey
INSERT INTO Horse (horse_id, animal_id, purpose) VALUES
(1, 4, 'Riding'),
(2, 5, 'Carrying'),
(3, 6, 'Working');

INSERT INTO Camel (camel_id, animal_id, hump_count) VALUES
(1, 5, 2),
(2, 6, 1),
(3, 4, 0);

INSERT INTO Donkey (donkey_id, animal_id, carrying_capacity) VALUES
(1, 6, 150),
(2, 4, 120),
(3, 5, 100);

DELETE FROM Camel WHERE animal_id IN (SELECT animal_id FROM Animal WHERE species = 'Camel');
DELETE FROM Animal WHERE species = 'Camel';

-- Создание новой таблицы для объединенной информации о лошадях и ослах
CREATE TABLE Equines (
    equine_id INT PRIMARY KEY,
    animal_id INT,
    purpose VARCHAR(50),
    carrying_capacity INT,
    FOREIGN KEY (animal_id) REFERENCES Animal(animal_id)
);

-- Вставка данных о лошадях в новую таблицу
INSERT INTO Equines (equine_id, animal_id, purpose, carrying_capacity)
SELECT horse_id, animal_id, purpose, NULL FROM Horse;

-- Вставка данных о ослах в новую таблицу
INSERT INTO Equines (equine_id, animal_id, purpose, carrying_capacity)
SELECT donkey_id, animal_id, NULL, carrying_capacity FROM Donkey;

-- Удаление таблиц Horse и Donkey
DROP TABLE Horse;
DROP TABLE Donkey;

-- Создание новой таблицы "молодые животные"
CREATE TABLE Young_Animals AS
SELECT
    A.*,
    TIMESTAMPDIFF(MONTH, A.birthdate, CURDATE()) AS age_in_months
FROM
    Animal A
WHERE
    TIMESTAMPDIFF(YEAR, A.birthdate, CURDATE()) BETWEEN 1 AND 3;

-- Просмотр данных в новой таблице
SELECT * FROM Young_Animals;

-- Создание новой таблицы "All_Animals"
CREATE TABLE All_Animals AS
SELECT
    'Domestic_Animals' AS source_table,
    DA.*,
    NULL AS purpose,
    NULL AS carrying_capacity
FROM
    Domestic_Animals DA

UNION

SELECT
    'Dog' AS source_table,
    D.*,
    NULL AS breed
FROM
    Dog D

UNION

SELECT
    'Cat' AS source_table,
    C.*,
    NULL AS color
FROM
    Cat C

UNION

SELECT
    'Hamster' AS source_table,
    H.*,
    NULL AS wheel_color
FROM
    Hamster H

UNION

SELECT
    'Equines' AS source_table,
    E.*,
    E.purpose AS purpose,
    E.carrying_capacity AS carrying_capacity
FROM
    Equines E

UNION

SELECT
    'Young_Animals' AS source_table,
    YA.*,
    NULL AS purpose,
    NULL AS carrying_capacity
FROM
    Young_Animals YA;

-- Просмотр данных в новой таблице
SELECT * FROM All_Animals;
