insert into movies (TITLE, RUNTIME, GENRE, IMDB_SCORE, RATING)
VALUES ('Howard the Duck', 110, 'Sci-Fi', '4.6', 'PG');

insert into movies (TITLE, RUNTIME, GENRE, IMDB_SCORE, RATING)
VALUES ('Lavalantula', 83, 'Horror', '4.7', 'TV-14');

insert into movies (TITLE, RUNTIME, GENRE, IMDB_SCORE, RATING)
VALUES ('Starship Troopers', 129, 'Sci-Fi', '7.2', 'PG-13');

insert into movies (TITLE, RUNTIME, GENRE, IMDB_SCORE, RATING)
VALUES ('Waltz With Bashir', 90, 'Documentary', '8.0', 'R');

insert into movies (TITLE, RUNTIME, GENRE, IMDB_SCORE, RATING)
VALUES ('Spaceballs', 96, 'Comedy', '7.1', 'PG');

insert into movies (TITLE, RUNTIME, GENRE, IMDB_SCORE, RATING)
VALUES ('Monsters Inc.', 92, 'Animation', '8.1', 'G');

select * from movies
where GENRE = 'Sci-Fi';

select * from movies
where IMDB_SCORE >= 6.5;

SELECT *
FROM MOVIES
WHERE RATING = 'G'
      OR RATING = 'PG'
         AND RUNTIME < 100;

SELECT AVG(RUNTIME)
FROM MOVIES
WHERE IMDB_SCORE < 7.5
GROUP BY('GENRE');

UPDATE MOVIES
SET RATING = 'R'
WHERE TITLE = 'Starship Troopers';

SELECT ID, RATING
FROM MOVIES
WHERE GENRE = 'Horror'
      OR GENRE = 'Documentary';

SELECT AVG(IMDB_SCORE), MAX(IMDB_SCORE), MIN(IMDB_SCORE)
FROM MOVIES
GROUP BY RATING;

SELECT AVG(IMDB_SCORE), MAX(IMDB_SCORE), MIN(IMDB_SCORE)
FROM MOVIES
GROUP BY RATING
HAVING COUNT(*) > 1;

DELETE FROM MOVIES
WHERE RATING ='R';