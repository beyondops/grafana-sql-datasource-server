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
  (10, date_add(now(), INTERVAL -9 HOUR), 'target1', 17.3),
  (11, date_add(now(), INTERVAL 0 HOUR), 'target2', 15.3),
  (12, date_add(now(), INTERVAL -1 HOUR), 'target2', 22.2),
  (13, date_add(now(), INTERVAL -2 HOUR), 'target2', 24.3),
  (14, date_add(now(), INTERVAL -3 HOUR), 'target2', 20.1),
  (15, date_add(now(), INTERVAL -4 HOUR), 'target2', 54.3),
  (16, date_add(now(), INTERVAL -5 HOUR), 'target2', 18.6),
  (17, date_add(now(), INTERVAL -6 HOUR), 'target2', 22.3),
  (18, date_add(now(), INTERVAL -7 HOUR), 'target2', 40.4),
  (19, date_add(now(), INTERVAL -8 HOUR), 'target2', 2.8),
  (20, date_add(now(), INTERVAL -9 HOUR), 'target2', 47.3);

CREATE TABLE ds1_user_score (
  uid    INT,
  course VARCHAR(50),
  score  FLOAT
);

INSERT ds1_user_score VALUES
  (1, 'Math', 85.4),
  (1, 'Art', 50.4),
  (1, 'English', 90.4),
  (1, 'Sport', 84.4),
  (2, 'Math', 95.4),
  (2, 'Art', 80.4),
  (2, 'English', 96.4),
  (2, 'Sport', 74.4);
