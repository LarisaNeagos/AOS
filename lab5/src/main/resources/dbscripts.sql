use tema2;

CREATE TABLE person (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  age int(3) NOT NULL, 
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into person (first,last,age) values('Maria','Popescu', 20);