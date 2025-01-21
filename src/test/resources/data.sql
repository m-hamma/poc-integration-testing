DROP TABLE IF EXISTS MESSAGE;
CREATE TABLE MESSAGE (
    message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    welcome VARCHAR(100),
    libelle VARCHAR(100),
    type VARCHAR(255)
);
DROP TABLE IF EXISTS CONTENU;
CREATE TABLE CONTENU (
    contenu_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100),
    commentaire VARCHAR(100),
    type VARCHAR(255)
);
insert into Message(message_id, welcome,libelle,type) values(1,'Welcome', 'of the world of programming!!! ','Geeks');
insert into Message(message_id, welcome,libelle,type) values(2,'WelcomeToEvent', 'You are selected to the contest!!! ','Geeks');

insert into Contenu(contenu_id, titre,commentaire,type) values(1,'Contenu', 'commentaire 1','Foot');
insert into Contenu(contenu_id, titre,commentaire,type) values(2,'Foot', 'Le foot, c est bien...','Sport');