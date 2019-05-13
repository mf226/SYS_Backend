USE sys-production;

-- Delete all previous data first
--DELETE FROM user_roles WHERE NOT user_name='xxx';
--DELETE FROM roles WHERE NOT role_name ='xxx';
--DELETE FROM users WHERE NOT user_name ='xxx';

INSERT INTO users (user_name, user_pass, birth, driverLicenseNumber, email, firstName, lastName, gender, phone, status) 
VALUES ('TestUser', '$10$W6r96gcBrkhwVzzSTAFQju.d4seOMV/wvoDeuiLp2.4XfnQJ7Z46C', '2019-05-13', '1234567890', 'test@test.test', 'Test', 'Test', 'male', 'Active');
INSERT INTO users (user_name, user_pass, birth, driverLicenseNumber, email, firstName, lastName, gender, phone, status) 
VALUES ('TestAdmin', '$10$24ncNkwzcZRcKiW3vu/Av.tgedPlUOH7quyqrQAAxdB5IAfg.8lU2', '2019-05-13', '0987654321', 'test2@test2.test', 'Test2', 'Test2', 'male', 'Active');
INSERT INTO roles (role_name) VALUES ('user');
INSERT INTO roles (role_name) VALUES ('admin');
INSERT INTO user_roles (user_name, role_name) VALUES ('user', 'admin');
INSERT INTO user_roles (user_name, role_name) VALUES ('admin', 'admin');