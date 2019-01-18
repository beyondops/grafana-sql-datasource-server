CREATE TABLE ds1_user (
  id   INT,
  name VARCHAR(50),
  age  INT
);

INSERT INTO ds1_user (id, name, age) VALUES (1, 'ds1_user1', 20), (2, 'ds1_user2', 30);

CREATE TABLE ds1_timeserie (
  id         INT,
  created_at TIMESTAMP,
  target     VARCHAR(50),
  val        FLOAT
);

INSERT INTO ds1_timeserie VALUES
  (1, date_add(now(), INTERVAL 0 HOUR), 'target1', 10.3),
  (2, date_add(now(), INTERVAL -1 HOUR), 'target1', 12.2),
  (3, date_add(now(), INTERVAL -2 HOUR), 'target1', 4.3),
  (4, date_add(now(), INTERVAL -3 HOUR), 'target1', 20.1),
  (5, date_add(now(), INTERVAL -4 HOUR), 'target1', 14.3),
  (6, date_add(now(), INTERVAL -5 HOUR), 'target1', 16.6),
  (7, date_add(now(), INTERVAL -6 HOUR), 'target1', 12.3),
  (8, date_add(now(), INTERVAL -7 HOUR), 'target1', 10.4),
  (9, date_add(now(), INTERVAL -8 HOUR), 'target1', 7.8),
  (10, date_add(now(), INTERVAL -9 HOUR), 'target1', 17.3);
