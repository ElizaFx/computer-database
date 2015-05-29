
  drop table if exists authorities;
  drop table if exists users;
  
  create table users (
	username                  varchar(50) not null primary key,
	password                  varchar(100) not null,
	enabled                   boolean not null)
  ;

  create table authorities (
	username                  varchar(50) not null,
	authority                 varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username))
  ;
	
  create unique index ix_auth_username on authorities (username,authority);
  
  insert into users values ('joxit', '$2a$11$kOxsREwa7IFniCu44aJoxuyU97LpdQuEXumbvrXmsrRI5qSmEvbgO', true);
  insert into users values ('admin', '$2a$11$kOxsREwa7IFniCu44aJoxuyU97LpdQuEXumbvrXmsrRI5qSmEvbgO', true);
  insert into authorities values('joxit', 'ROLE_USER');
  insert into authorities values('admin', 'ROLE_USER');
  insert into authorities values('admin', 'ROLE_ADMIN');
  