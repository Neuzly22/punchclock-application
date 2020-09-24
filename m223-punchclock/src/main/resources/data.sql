INSERT INTO APPLICATION_USER (id, password, username) VALUES (1, '2a$10$i4INelEJBbELiXzBCKc6iOff9ibgZf2AP5IS.15sV5yvD2ewDEB0W', 'Noah');
INSERT INTO APPLICATION_USER (id, password, username) VALUES (2, '2a$10$i4INelEJBbELiXzBCKc6iOff9ibgZf2AP5IS.15sV5yvD2ewDEB0W', 'DonaldDuck');
INSERT INTO APPLICATION_USER (id, password, username) VALUES (3, '$2a$10$SimGQv.6EfF25i5ZNgov7OfZsrw1YMGRxvaH7C.0hChMCQ6.tzMCa', 'test');

INSERT INTO ENTRY (id, check_in, check_out, applicationuser_id) VALUES (1, '2020-09-24T07:40:20.345Z', '2020-10-24T11:35:21.345Z', 1);
INSERT INTO ENTRY (id, check_in, check_out, applicationuser_id) VALUES (2, '2020-09-24T10:06:08.345Z', '2020-09-24T11:40:20.345Z', 2);
INSERT INTO ENTRY (id, check_in, check_out, applicationuser_id) VALUES (3, '2020-09-24T08:50:21.345Z', '2020-09-24T11:50:21.345Z', 2)

