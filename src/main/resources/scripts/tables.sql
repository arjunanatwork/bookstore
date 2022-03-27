DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS CLASSIFICATION;
DROP TABLE IF EXISTS PROMOTION;
DROP TABLE IF EXISTS BOOK;

DROP SEQUENCE IF EXISTS author_seq;
DROP SEQUENCE IF EXISTS classification_seq;
DROP SEQUENCE IF EXISTS book_seq;
DROP SEQUENCE IF EXISTS promotion_seq;


CREATE SEQUENCE author_seq
  START WITH 1
  INCREMENT BY 1;
  
CREATE SEQUENCE classification_seq
  START WITH 1
  INCREMENT BY 1;

CREATE SEQUENCE book_seq
  START WITH 1
  INCREMENT BY 1;

CREATE SEQUENCE promotion_seq
  START WITH 1
  INCREMENT BY 1;
  
CREATE TABLE AUTHOR (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  created_date DATE NOT NULL,
  last_updated_date DATE NULL,
  created_by VARCHAR(24) NOT NULL,
  last_updated_by VARCHAR(24) NULL
);

CREATE TABLE CLASSIFICATION (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  created_date DATE NOT NULL,
  last_updated_date DATE NULL,
  created_by VARCHAR(24) NOT NULL,
  last_updated_by VARCHAR(24) NULL
);


CREATE TABLE PROMOTION (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  classification_id INT NOT NULL,
  promotion_code VARCHAR(32) NOT NULL,
  discount INT NOT NULL,
  created_date DATE NOT NULL,
  last_updated_date DATE NULL,
  created_by VARCHAR(24) NOT NULL,
  last_updated_by VARCHAR(24) NULL,
  foreign key (classification_id) references CLASSIFICATION(id)
);

CREATE TABLE BOOK (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  description VARCHAR(250) NULL,
  author_id INT NOT NULL,
  classification_id INT NOT NULL,
  price DECIMAL(5,2) NOT NULL,
  isbn VARCHAR(250) NULL,
  created_date DATE NOT NULL,
  last_updated_date DATE NULL,
  created_by VARCHAR(24) NOT NULL,
  last_updated_by VARCHAR(24) NULL,
  foreign key (author_id) references AUTHOR(id),
  foreign key (classification_id) references CLASSIFICATION(id)
);


INSERT INTO AUTHOR (id, name, created_date, last_updated_date,created_by,last_updated_by) VALUES
  (author_seq.NEXTVAL , 'Arjunan', SYSDATE, NULL, 'SYSTEM', NULL), (author_seq.NEXTVAL , 'Dan Brown', SYSDATE, NULL, 'SYSTEM', NULL);
  
INSERT INTO CLASSIFICATION (id, name, created_date, last_updated_date,created_by,last_updated_by) VALUES
  (classification_seq.NEXTVAL , 'FRICTION', SYSDATE, NULL, 'SYSTEM', NULL), (classification_seq.NEXTVAL , 'COMIC', SYSDATE, NULL, 'SYSTEM', NULL);  
  
INSERT INTO PROMOTION (id, classification_id, promotion_code, discount, created_date, last_updated_date,created_by,last_updated_by) VALUES
  (promotion_seq.NEXTVAL , (select id from classification where name='FRICTION'), 'TEST10',10,SYSDATE, NULL, 'SYSTEM', NULL);    

