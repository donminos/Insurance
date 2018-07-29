INSERT INTO UsersStatus (idUsersStatus, description) VALUES ('1', 'ACTIVO');
INSERT INTO UsersStatus (idUsersStatus, description) VALUES ('2', 'BORRADO');
INSERT INTO UsersStatus (idUsersStatus, description) VALUES ('3', 'SUSPENDIDO');

INSERT INTO Roles (descriptions, role) VALUES ('Administrador', 'ROLE_ADMIN');
INSERT INTO Roles (descriptions, role) VALUES ('Cliente', 'ROLE_CLIENT');
INSERT INTO Roles (descriptions, role) VALUES ('Ajustador', 'ROLE_ADJUSTER');

INSERT INTO Users (Email,User, Name, Password, PrimaryLastName, SecundaryLastName, idUsersStatus) VALUES ('admin@test.com', 'admin', 'admin', '$2a$10$OkiD/zuUH9jN6PBnXatdXuU0BBaLvok8C35gCMqhogQT/lV8GoANS', 'admin', 'admin', 1);
INSERT INTO Users (Email,User, Name, Password, PrimaryLastName, SecundaryLastName, idUsersStatus) VALUES ('user@test.com', 'user', 'user', '$2a$10$nXHNLLp.WBaIYvZzVq9sveKvpjQww3naa2b8mkC5nqF7/OITw5BuG', 'user', 'user', 1);
INSERT INTO Users (Email, Name, Password, PrimaryLastName, SecundaryLastName, User, idUsersStatus) VALUES ('adjuster@test.com', 'adjuster', '$2a$10$nXHNLLp.WBaIYvZzVq9sveKvpjQww3naa2b8mkC5nqF7/OITw5BuG', 'adjuster', 'adjuster', 'adjuster', '1');
INSERT INTO Users (Email, Name, Password, PrimaryLastName, SecundaryLastName, User, idUsersStatus) VALUES ('adjuster2@test.com', 'adjuster2', '$2a$10$nXHNLLp.WBaIYvZzVq9sveKvpjQww3naa2b8mkC5nqF7/OITw5BuG', 'adjuster2', 'adjuster2', 'adjuster2', '1');
INSERT INTO Users (Email, Name, Password, PrimaryLastName, SecundaryLastName, User, idUsersStatus) VALUES ('adjuster3@test.com', 'adjuster3', '$2a$10$nXHNLLp.WBaIYvZzVq9sveKvpjQww3naa2b8mkC5nqF7/OITw5BuG', 'adjuster3', 'adjuster3', 'adjuster3', '1');

INSERT INTO UsersRol (idRoles, idUser) VALUES ('1', '1');
INSERT INTO UsersRol (idRoles, idUser) VALUES ('2', '1');
INSERT INTO UsersRol (idRoles, idUser) VALUES ('2', '2');
INSERT INTO UsersRol (idRoles, idUser) VALUES ('3', '3');
INSERT INTO UsersRol (idRoles, idUser) VALUES ('3', '4');
INSERT INTO UsersRol (idRoles, idUser) VALUES ('3', '5');

INSERT INTO Policies (idPolicies, MonthDuration, idUser,FinishPolice) VALUES ('a7dfccbc-fe92-478c-9caf-b938b2ce778a', '12', '2','2012-12-12');

INSERT INTO Clients (Telephone, City, Mobile, Settlement, State, Street, Town, ZipCode, idUser) VALUES ('555555555', 'CIudad de México', '555555555', 'Coyoacan', 'Ciudad de México', 'Calle falsa 123', 'Colonia', '12345', '2');

INSERT INTO InsuranceAdjuster (InFunctions, latitude, longitude, Name, PrimaryLastName, SecundaryLastName, idUser) VALUES ('1', '19.4360809', '-99.0718746', 'Juan', 'Hernandez', 'Bautizta',3);
INSERT INTO InsuranceAdjuster (InFunctions, latitude, longitude, Name, PrimaryLastName, SecundaryLastName, idUser) VALUES ('1', '19.4502447', '-99.1576195', 'Pedro', 'Mendoza', 'Bustamante',4);
INSERT INTO InsuranceAdjuster (InFunctions, latitude, longitude, Name, PrimaryLastName, SecundaryLastName, idUser) VALUES ('1', '19.3652137', '-99.1878514', 'Frida', 'Urrutia', 'Reyes',5);
