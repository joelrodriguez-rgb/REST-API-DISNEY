drop table if exists personaje;

create table personaje
(
 personaje_id bigint  not null auto_increment,
 name varchar(255) not null,
 img_personaje varchar(255) null,
 age int not null,
 weight int not null,
 movie_id bigint not null,
 primary key (personaje_id),
 key movie_id (movie_id),
 constraint fk_movie foreign key (movie_id) references movie (movie_id)
) engine = InnoDB;