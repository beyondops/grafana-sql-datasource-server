CREATE TABLE ds2_user (
  id   INT,
  name VARCHAR(50),
  age  INT
);

INSERT INTO ds2_user (id, name, age) VALUES (1, 'ds2_user1', 22), (2, 'ds2_user2', 35);

CREATE TABLE ds2_timeserie (
  id         INT,
  created_at TIMESTAMP,
  target     VARCHAR(50),
  val        FLOAT
);

INSERT INTO ds2_timeserie VALUES
  (1, date_add(now(), INTERVAL 0 HOUR), 'target2', 10.3),
  (2, date_add(now(), INTERVAL -1 HOUR), 'target2', 12.2),
  (3, date_add(now(), INTERVAL -2 HOUR), 'target2', 4.3),
  (4, date_add(now(), INTERVAL -3 HOUR), 'target2', 20.1),
  (5, date_add(now(), INTERVAL -4 HOUR), 'target2', 14.3),
  (6, date_add(now(), INTERVAL -5 HOUR), 'target2', 16.6),
  (7, date_add(now(), INTERVAL -6 HOUR), 'target2', 12.3),
  (8, date_add(now(), INTERVAL -7 HOUR), 'target2', 10.4),
  (9, date_add(now(), INTERVAL -8 HOUR), 'target2', 7.8),
  (10, date_add(now(), INTERVAL -9 HOUR), 'target2', 17.3);
