drop table if exists movie;

create table movie
(
movie_id bigint not null auto_increment,
title varchar(255) not null unique,
img_movie varchar(255) null,
creation_date date not null,
qualification int not null,
gender_id bigint not null,
primary key (movie_id),
key gender_id (gender_id),
constraint fk_gender foreign key (gender_id) references gender (gender_id)
) engine = InnoDB;