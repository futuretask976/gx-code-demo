CREATE TABLE 'user' (
    'id' varchar(64) NOT NULL,
	'password' varchar(255) DEFAULT NULL,
	'username' varchar(255) DEFAULT NULL,
	PRIMARY KEY('id')
) ENGINE=InnoDB AUTO_INCREMENT-2 DEFAULT CHARSET=utf8;
INSERT INTO 'test'.'user'('id', 'password', 'username') VALUES('1', '123', 'miya')
;


