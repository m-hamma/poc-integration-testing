DROP TABLE IF EXISTS MESSAGE;
CREATE TABLE MESSAGE (
    message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    welcome VARCHAR(100),
    libelle VARCHAR(100),
    type VARCHAR(255)
);
insert into Message(message_id, welcome,libelle,type) values(1,'Welcome', 'of the world of programming!!! ','Geeks');
insert into Message(message_id, welcome,libelle,type) values(2,'WelcomeToEvent', 'You are selected to the contest!!! ','Geeks');